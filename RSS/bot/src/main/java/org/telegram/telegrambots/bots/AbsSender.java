package org.telegram.telegrambots.bots;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.telegram.telegrambots.Constants.ERRORCODEFIELD;
import static org.telegram.telegrambots.Constants.ERRORDESCRIPTIONFIELD;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Implementation of all the methods needed to interact with Telegram Servers
 * @date 14 of January of 2016
 */
@SuppressWarnings("unused")
public abstract class AbsSender {
    private static final ContentType TEXT_PLAIN_CONTENT_TYPE = ContentType.create("text/plain", StandardCharsets.UTF_8);

    private final ExecutorService exe = Executors.newSingleThreadExecutor();
    private volatile CloseableHttpClient httpclient;
    private volatile RequestConfig requestConfig;
    private static final int SOCKET_TIMEOUT = 75 * 1000;

    AbsSender() {
        httpclient = HttpClientBuilder.create()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setConnectionTimeToLive(70, TimeUnit.SECONDS)
                .setMaxConnTotal(100)
                .build();
        requestConfig = RequestConfig.copy(RequestConfig.custom().build())
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(SOCKET_TIMEOUT)
                .setConnectionRequestTimeout(SOCKET_TIMEOUT).build();
    }

    /**
     * Returns the token of the bot to be able to perform Telegram Api Requests
     * @return Token of the bot
     */
    public abstract String getBotToken();

    // Send Requests

    public Message sendMessage(SendMessage sendMessage) throws TelegramApiException {
        if (sendMessage == null) {
            throw new TelegramApiException("Parameter sendMessage can not be null");
        }

        return sendApiMethod(sendMessage);
    }

    private <T extends Serializable> T sendApiMethod(BotApiMethod<T> method) throws TelegramApiException {
        String responseContent;
        try {
            String url = getBaseUrl() + method.getPath();
            HttpPost httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);
            httppost.addHeader("charset", StandardCharsets.UTF_8.name());
            httppost.setEntity(new StringEntity(method.toJson().toString(), ContentType.APPLICATION_JSON));
            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                HttpEntity ht = response.getEntity();
                BufferedHttpEntity buf = new BufferedHttpEntity(ht);
                responseContent = EntityUtils.toString(buf, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new TelegramApiException("Unable to execute " + method.getPath() + " method", e);
        }

        JSONObject jsonObject = new JSONObject(responseContent);
        if (!jsonObject.getBoolean(Constants.RESPONSEFIELDOK)) {
            throw new TelegramApiException("Error at " + method.getPath(), jsonObject.getString(ERRORDESCRIPTIONFIELD), jsonObject.getInt(ERRORCODEFIELD));
        }

        return method.deserializeResponse(jsonObject);
    }

    private String getBaseUrl() {
        return Constants.BASEURL + getBotToken() + "/";
    }
}

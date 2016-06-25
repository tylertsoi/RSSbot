package de.vogella.rss.read;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import de.vogella.rss.global.GlobalRssText;
import de.vogella.rss.model.Feed;
import de.vogella.rss.model.FeedMessage;

public class RSSFeedParser {
  static final String TITLE = "title";
  static final String DESCRIPTION = "description";
  static final String LINK = "link";
  static final String AUTHOR = "author";
  static final String ITEM = "item";
  static final String PUB_DATE = "pubDate";
  static final String GUID = "guid";
  static final String P = "SPAN";
  
  GlobalRssText rssStored;

  final URL url;

  public RSSFeedParser(String feedUrl) {
    try {
      this.url = new URL(feedUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public Feed readFeed() {
	  
    Feed feed = null;
    try {
      boolean isFeedHeader = true;
      // Set header values intial to the empty string
      String description = "";
      String title = "";
      String link = "";
      String author = "";
      String pubdate = "";
      String guid = "";
      String p = "";

      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = read();
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // read the XML document
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          String localPart = event.asStartElement().getName()
              .getLocalPart();
          switch (localPart) {
          case ITEM:
            if (isFeedHeader) {
              isFeedHeader = false;
              feed = new Feed(title, link, description, pubdate,
            		  author, guid);
            }
            event = eventReader.nextEvent();
            break;
          case TITLE:
            title = getCharacterData(event, eventReader);
            break;
          case DESCRIPTION:

                event = eventReader.nextEvent();
            break;
          case LINK:
            link = getCharacterData(event, eventReader);
            break;
          case GUID:
            guid = getCharacterData(event, eventReader);
            break;
          case AUTHOR:
            author = getCharacterData(event, eventReader);
            break;
          case PUB_DATE:
            pubdate = getCharacterData(event, eventReader);
            break;
            
          case P:
              p = getCharacterData(event, eventReader);
              break;
          }
        } else if (event.isEndElement()) {
          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
        	  
            FeedMessage message = new FeedMessage();
            message.setAuthor(author);
            message.setDescription(description);
            message.setGuid(guid);
            message.setLink(link);
            message.setTitle(title);
            message.setPubDate(pubdate);
        	rssStored = new GlobalRssText(pubdate, title, guid);            
            feed.getMessages().add(message);
            event = eventReader.nextEvent();
            continue;
          }
          if (event.asEndElement().getName().getLocalPart() == (DESCRIPTION)) {
        	  
              FeedMessage message1 = new FeedMessage();
              message1.setP(p);
              System.out.println(p);       
              //feed.getMessages().add(message1);
              event = eventReader.nextEvent();
              continue;
            }
        }
        
      }
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return feed;
  }

  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
      throws XMLStreamException {
    String result = "";
    event = eventReader.nextEvent();
    if (event instanceof Characters) {
      result = event.asCharacters().getData();
    }
    return result;
  }

  private InputStream read() {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
} 

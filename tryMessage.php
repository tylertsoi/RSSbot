<?php
ini_set('error_reporting', E_ALL);

$RSSbot = "214162234:AAHoD0x4AHlSUT-zSzF1hBxvlfY-b7LFMgk"
$website = "http://api.telegram.org/bot".$RSSbot;

$update = file_get_contents('php://input');
$update = json_decode($update,TRUE);

$chatID = $update["message"]["chat"]["id];
$message = $update["message"]["text"];

switch($message){
case "/test":
  sendMessage($ChatId,"test");
default:
  sendMessage("default");
  }
function sendMessage($chatId, $message){

	$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatId."&text=".urlencode($message);
	file_get_contents($url);
	
	
}

?>

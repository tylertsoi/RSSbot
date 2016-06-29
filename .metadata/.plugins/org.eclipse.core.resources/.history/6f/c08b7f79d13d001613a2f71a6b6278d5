package de.vogella.rss.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed {

  final String title;
  final String link;
  final String description;
  final String pubDate;  
  final String author;
  final String guid;

  final List<FeedMessage> entries = new ArrayList<FeedMessage>();

  public Feed(String title, String link, String description, String pubDate, String author, String guid) {
    this.title = title;
    this.link = link;
    this.description = description;
    this.pubDate = pubDate;
    this.author = author;   
    this.guid = guid;
  }

  public List<FeedMessage> getMessages() {
    return entries;
  }

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getDescription() {
    return description;
  }

  public String getPubDate() {
    return pubDate;
  }
  
  public String getGuid() {
    return guid;
  }
  
  public String getAuthor() {
    return author;
  }

  @Override
  public String toString() {
    return "Feed [title=" + title + ", description=" + description
        + ", link=" + link + ", pubDate=" + pubDate + "]";
  }

} 
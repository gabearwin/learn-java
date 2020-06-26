package xyz.gabear.learn.javase.exercise;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;

public class RSSDemo {
    public static void main(String[] args) {
        try {
            URL feedUrl = new URL("https://www.ifanr.com/feed");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            System.out.println(feed);
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
    }
}

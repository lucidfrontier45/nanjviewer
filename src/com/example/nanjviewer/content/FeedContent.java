
package com.example.nanjviewer.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class FeedContent {

    /**
     * An array of sample (Feed) items.
     */
    public static List<FeedItem> ITEMS = new ArrayList<FeedItem>();

    /**
     * A map of sample (Feed) items, by ID.
     */
    public static Map<String, FeedItem> ITEM_MAP = new HashMap<String, FeedItem>();

    public static void addItem(FeedItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    /**
     * A Feed item representing a piece of content.
     */
    public static class FeedItem {

        public String id;
        public String link;
        public String title;
        public String blog_name;
        public String date;

        public FeedItem() {

        }

        public FeedItem(String id, String link, String title, String
                blog_name, String date) {
            super();
            this.id = id;
            this.link = link;
            this.title = title;
            this.blog_name = blog_name;
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBlog_name() {
            return blog_name;
        }

        public void setBlog_name(String blog_name) {
            this.blog_name = blog_name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return title;
        }

    }
}

package com.example.android.goodnightnews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A {@link NewsAdapter} knows how to create a list item layout for each news article
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Remove the T from the date string and format it so that the date is more user friendly
     */
    private static final String DATE_SEPARATOR = "T";

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news is the list of news articles, which is the data source of the adapter
     */
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays the news article at the given position
     * in the list of news.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news article at the given position in the list of news
        News currentNews = getItem(position);

        // Find the TextView with view ID article_title
        TextView titleView = (TextView) listItemView.findViewById(R.id.article_title);
        // Display the Title of the current news article in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID article_author
        TextView authorView = (TextView) listItemView.findViewById(R.id.article_author);
        // Display the Author of the current news article in that TextView
        authorView.setText(currentNews.getAuthor());

        // Find the TextView with view ID article_section
        TextView sectionView = (TextView) listItemView.findViewById(R.id.article_section);
        // Display the Section of the current news article in that TextView
        sectionView.setText(currentNews.getSection());

        // Get the original date string from the News object
        String originalDate = currentNews.getDate();

        // Split the date into two strings, the date string and the time string
        // To display only the date string, the originalDate string will be split
        // before and after the "T" then only use one of the strings
        // to display the date to the user.
        String removeTime;
        String userDate;

        // Split the date string into different parts (as an array of Strings)
        // based on the "T" text. We expect an array of 2 Strings
        String[] parts = originalDate.split(DATE_SEPARATOR);
        // userDate should be "2018-09-26"
        userDate = parts[0];
        // removeTime should be 15:20:54z
        removeTime = parts[1];

        // Find the TextView with view ID article_date
        TextView dateView = (TextView) listItemView.findViewById(R.id.article_date);
        // Display the Date of the current news article in that TextView
        dateView.setText(userDate);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}

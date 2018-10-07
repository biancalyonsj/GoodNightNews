package com.example.android.goodnightnews;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    /** Tag for the log messages */
    private static final String TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */

    private QueryUtils() {
    }

    /**
     * Query the guardian and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestedUrl) {
        //Create URL Object
        URL newsUrl = createURL (requestedUrl);

        //Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(newsUrl);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link News}
        List<News> news = extractNewsFromJson(jsonResponse);

        // Return the list of {@link News}
        return news;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createURL(String requestedUrl) {
        URL url = null;
        try {
            url = new URL(requestedUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "createdUrl: Problem building URL", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL newsUrl) throws IOException{
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (newsUrl == null) {
            //returns no data
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //Create the connection
        try {
            urlConnection = (HttpURLConnection) newsUrl.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            //Establish an HTTP connection with the server
            urlConnection.connect();

            //Test to see what response we get
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200){
                //Valid connection
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "makeHttpRequest: Error Code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e(TAG, "makeHttpRequest: Problem retrieving the news JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null){
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<News> extractNewsFromJson(String newsJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            //Prevent parsing data that does not exist
            return null;
        }

        // Create an empty ArrayList that we can start adding new articles to
        List<News> news = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            // JSONObject parses through entire JSON String we recieve from the guardian API
            // This object is stored in the variable baseJSONResponse
            JSONObject baseJSONResponse = new JSONObject(newsJSON);

            JSONObject baseJSONResponseResult = baseJSONResponse.getJSONObject("response");

            // Array list of all the items within the JSON
            JSONArray resultsArray = baseJSONResponseResult.getJSONArray("results");

            // For each article in the resultsArray Array, create a {@link News} object
            for (int n = 0; n < resultsArray.length(); n++) {

                // Get a single news article at position i within the list of new articles
                JSONObject currentArticle = resultsArray.getJSONObject(n);

                String articleTitle = currentArticle.getString("webTitle");
                String articleUrl = currentArticle.getString("webUrl");
                String articleDate = currentArticle.getString("webPublicationDate");
                String articleSection = currentArticle.getString("sectionName");

                String articleAuthor = "Unknown";
                JSONArray tagsArray = currentArticle.getJSONArray("tags");
                for (int a = 0; a < tagsArray.length(); a++) {
                    JSONObject currentAuthor = tagsArray.getJSONObject(a);

                    articleAuthor = currentAuthor.getString("webTitle");
                }
                // Create a new {@link News} object with the articleTitle, articleAuthor,
                // articleDate and articleUrl from the JSON response.
                News gameNews = new News(articleTitle, articleAuthor, articleDate, articleUrl, articleSection);

                // Add the new {@link News} to the list of news.
                news.add(gameNews);
            }
        } catch (JSONException je) {
            Log.e(TAG, "extractNewsFromJson: Problem parsing results", je);
        }
        // Return the list of news articles
        return news;
    }

}

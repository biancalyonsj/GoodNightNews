package com.example.android.goodnightnews;

/**
 * A {@link News} object contains information related to a single News Article.
 */
public class News {

    /** Title of the News Article */
    private String mTitle;

    /** Author of the News Article */
    private String mAuthor;

    /** Date of the News Article */
    private String mDate;

    /** Website URL of the News Article */
    private String mUrl;

    /** Section of the News Article */
    private String mSection;

    /**
     * Constructs a new {@link News} object.
     *
     * @param articleTitle is the Title of the News Article
     * @param articleAuthor is the Author of the News Article
     * @param articleDate is the Date of the News Article
     * @param articleUrl is the website URL to find more details about the News Article
     * @param articleSection is the section of the News Article
     */
    public News (String articleTitle, String articleAuthor, String articleDate, String articleUrl,
    String articleSection){
        mTitle = articleTitle;
        mAuthor = articleAuthor;
        mDate = articleDate;
        mUrl = articleUrl;
        mSection = articleSection;
    }

    /**
     * Returns the Title of the News Article.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the Author of the News Article.
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Returns the Date of the News Article.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Returns the website URL of the News Article.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the Section of the News Article.
     */
    public String getSection() {
        return mSection;
    }
}

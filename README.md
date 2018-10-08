# GoodNightNews

<b> A multi-screen networking app that displays the latest news based on the user's preference. The user can select the section of
 entertainment news they want to be displayed like games, music, or technology. </b> 

<p> Project #6 for the <b>Android Basics Nanodegree</b> by Google </p>

<p> This app focuses on understanding the power of Web APIs and how to use them in my apps. I learned the basics of networking in Android, including HTTP networking, JSON parsing, and threads. Goodnight News queries the content.guardianapis.com API to fetch news stories. The stories are shown on the main screen update properly whenever new news data is fetched from the API. Goodnight News contains a main screen which displays multiple news stories. The title of the article, the name of the section that it belongs to as well as the author's name and date published are easily visible. The Settings Activity is accessed from the Main Activity via the toolbar menu. The Settings Activity allows users to see the value of all the preferences right below the preference name, and when the value is changed, the query is narrowed down and the summary updates immediately. Networking operations are done using a Loader. 
Clicking on a story uses an intent to open the story in the user’s browser. When there is no data to display, the app shows a 
default TextView that informs the user how to populate the list. The app checks whether the device is connected to the internet and 
responds appropriately. </p>
 
<b> The goal is to create a News Feed app which gives a user regularly-updated news from the internet related to a particular topic, person, or location. It was not required to include images in our project, however, to improve my news app I will add images in the form of thumbnails to the news articles. </b>

Core Concepts I learned Completing this Project:
----------------------------------------------

-	Connecting to an API
-	Parsing the JSON response
-	Handling error cases gracefully
-	Updating information regularly 
-	Using an AsyncTask
-	Doing network operations independent of the Activity lifecycle
-	Implement the Preference Fragment 
-	Launch a Settings Activity from a menu in the Main Activity
-	Use Uri.Builder class to add query parameters to the URL
-	Launch a Settings Activity from a menu in the Main Activity
-	Use Uri.Builder class to add query parameters to the URL
-	Update and display the Preference Summary

<img src="GoodNightNews\MainPage.png" width="350" height="620"> <img src="GoodNightNews\SettingsActivity.png" width="350" height="620">
<img src="GoodNightNews\SettingsOptions.png" width="350" height="620"> 

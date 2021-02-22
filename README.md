# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [x] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [x] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [x] Implement a shared element transition when user clicks into the details of a movie (1 point).
- [x] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [x] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - [x] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [x] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [x] Apply data binding for views to help remove boilerplate code. (1 point)
- [x] Add a rounded corners for the images using the Glide transformations. (1 point)

#### Additional Features
- [x] YouTube player is played in a different Activity and has no history on the Activity Stack.
- [x] Popular movies that automatically enter fullscreen in the YouTubeActivity will restore its playback when it exits from the fullscreen.
- [x] Popular movies detail page has a YouTube player built-in instead of an image (for less popular movies).
- [x] Landscape orientation compatible for both popular movies and less popular movies detail page and YouTubeActivity.
- [x] Explode transition for moving to detail page. 

### App Walkthough GIF

<img src="walkthrough2.gif" width=250><br>

### Notes

The difficulties that I face mainly arise from the transition from the YouTubeActivity to other activities. Being able to transition without keeping a history on the Activity Stack, while being able to transfer different features of a popular movie and regular movie. I also faced difficulties in getting the player to load the video when abruptly ending the YouTube player from the YouTubeActivity to the DetailPageActivity.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

---

## Flix Part 1

### User Stories

#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [x] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
- [x] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF
<img src="walkthrough.gif" width=250><br>

### Notes
One of the main challenges was figuring out how the heterogenous view holders work. However, with the help of the CodePath wiki and what was mentioned in the videos I was able to figure it out. Another challenge was working with Glide and what was expected when loading a placeholder and error image before it was loaded. Initially it was quite confusing how it works however, by looking at the Glide documentation I was able to figure out how I can achieve this feature.

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids

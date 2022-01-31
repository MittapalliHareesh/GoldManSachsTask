# GoldManSachsTask

<b>Idea of this Project :-</b>

The aim of this exercise is to create a mobile app that helps with displaying NASA’s Astronomy picture of the day.
API(https://api.nasa.gov/) using RetroFit REST client. Then, fetched Astronomy picture of the day details and displayed
the data in UI.

App cache the information , If there is no network it will display last updated data, And there is a option to
Favourite a Astronomy picture of the day details.


<b>Design</b>:- Implemented by following MVVM Clean architecture by using Coroutines, HILT
dependency injection & Retrofit.

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/151811351-fa5399fa-c3e5-4e0c-84e1-8046cae462f2.png" width="500" height="1000">
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/151811485-ce4ab133-ad78-4e24-b374-5cb67cbea09e.png" width="500" height="1000">
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/151811703-089e8655-25af-4279-8ea3-1020914a8bc7.png" width="500" height="1000">
</p>

Scroll down to see Astroronomy Picture of the day 

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/151811865-0a46b2ff-2dbf-495a-b06b-021577ba952d.png" width="500" height="1000">
</p>

Click on Favourite icon to add "Astroronomy Picture of the day" details to Favourites list.

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/151812297-7bd1c652-ecfe-41df-a752-eef104ee92cd.png" width="500" height="1000">
</p>

<h1>Release Notes:-</h1> 

1) Developed Application by using Android JetPack Components like HILT, Navigation & UI, ViewModel ,
   Livedata, DataBinding and Lifecycle-Aware Components.

2) To Update data to RecyclerView adapter used DiffUtil logic. It’s an is a utility class that can
   calculate the difference between two lists and output a list of update operations that converts
   the first list into the second one.

3) Used "com.intuit.ssp:ssp-android:1.0.6" & "com.intuit.sdp:sdp-android:1.0.6" libraries

SSP : An android SDK that provides a new size unit - ssp (scalable sp). This size unit scales with
the screen size based on the sp size unit (for texts). It can help Android developers with
supporting multiple screens.

SDP : An android SDK that provides a new size unit - sdp (scalable dp). This size unit scales with
the screen size. It can help Android developers with supporting multiple screens.

4) Used "Data Binding" for faster access and performance improvement. The Data Binding Library is a
   support library that allows you to bind UI components in your layouts to data sources in your app
   using a declarative format rather than programmatically.

5) Used "com.github.bumptech.glide:glide"
   By using of Glide library we are loading images.And it's an open source library that is used for
   efficiently loading and caching of images.

6) Used Android Coroutines. It mainly simplify code that executes asynchronously & help to manage
   long-running tasks in background without block the main thread.

7) Developed application by using HILT dependency injection. It's an opinionated dependency
   injection library for Android that reduces the boilerplate of using manual DI in your project.

8) Used Retrofit to make API calls.

9) Added Unit-tests for Business logic. Such as ViewModel and Repository.

10) Added Unit-tests for Retrofit Service API's by mocking OkHttp MockWebServer.

11) Added comments for fun's in Kdoc format.
 

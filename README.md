# NoteMemo Android Mobile Application
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)<br>
   ![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/kotlin-into.jpg)
<br><br>
## Overview
This is a Task Noting / ToDo  mobile application developed by using the official language for
android development i.e "Kotlin"<br>
The App can be used as an daily driver for noting down the important dates ,tasks and schedule other events.<br>
Features :<br>
1.Tasks can be easily added and being displayed in the apps home screen .<br>
2.Tasks can be deleted and updated .<br>
<b>3.The task title and task description can be shared by the press of single click.</b> <br>
<b>4.The RecyclerView can be rearranged and sorted</b> as per the convenience.<br>
<b>5.The date and time can be selected and entered by using the Material Calendar and Date Picker.</b> <br>

## TechStack  - (ROOM)
## The application is performing the required CRUD Operations.<br>

The app is developed in the android studio IDE completely using "Kotlin".<br>
<b>Room library</b> has been used to store data in the local system .<br>
<b>Kotlin Coroutines has been used to implement the database reading ,writing ,deleting related asynchronous task in the backgroud thread. </b><br>

<b>Reason to use ROOM persistence library over the native SQLite</b><br><br>
1.Room is a very powerful abstraction layer over the native SQLlite ,Database Management System.<br>
2.Using the Room instead of Raw MySQL provides a robust system and decrease the boilerplatecode drastically.<br><br>
<b>Reason for using Kotlin Coroutines in comparison with the native AsyncTask </b><br><br>
1.AsyncTask was deprecated in API level 30 in the official android documentation.<br>
2.<b>Kotlin Coroutines was promoted to be used in comparison to the AsynTask as</b> <br><br>
I .Kotin reduces the boilerplate code with proving different scopes of operation.<br>
II. More and more people are engaging in using Kotlin-Coroutines.<br>
III. Applying AsyncTask in complex code structure or function is difficult in comparison.<br>


## Screenshots<br><br>
## Sorting Menu and Overview<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/adding%20a%20single%20task%20with%20menu%20preview.jpeg)
<br><br>
## Adding a new task<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/adding%20task%20activity.jpeg)
<br><br>
## Choosing date using Material Calendar<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/calendar.jpeg)
<br><br>
## Time Picker<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/clock1.jpeg)
<br><br>
## Time Picker<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/clock2.jpeg)
<br><br>
## Tasks arranged as per the Latest Added<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/latest.jpeg)
<br><br>
## Tasks arranged as per the Earliest Added<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/sorting%20earlies.jpeg)
<br><br>
## Sharing the task<br>
![S1.png](https://github.com/shubham212001/NoteMemo/blob/master/share.jpeg)
<br><br>




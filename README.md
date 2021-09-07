# Weather-Forecast-Website
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)<br>
   [![forthebadge made-with-python](http://ForTheBadge.com/images/badges/made-with-python.svg)](https://www.python.org/)

## Overview
This is an project based on displaying data retrieved using API call.
The website is integrated with a search field and a button .
The website displays the geographical details and weather of the entered city.
The data is being pulled from the Open Weather server using an API Call.

## Setting Up

1.	Sign up for a free Open Weather Map account!
2.	Access the API here- https://openweathermap.org/
3.	Once you've signed up, you're given an API key. 
4.	http://api.openweathermap.org/data/2.5/weather?q=10025,us?units=imperial&appid= [PUT YOUR API KEY HERE]

## Webpage Content And Working

When the submit button is clicked:
* A GET request should fetch the weather data from the API.
* A POST submits the data in the backend .
* The following data should be rendered on the page:
  * City name
  * Country Code
  * Coordinates
  * Current temperature
  * Pressure 
  * Humidity
  * Short Description.




## Screenshots
![S1.png](https://github.com/suyash-9/Weather-Forecast-Website/blob/main/images/S1.png)
<br><br>
![S2.png](https://github.com/suyash-9/Weather-Forecast-Website/blob/main/images/S2.png)
<br><br>
![S3.png](https://github.com/suyash-9/Weather-Forecast-Website/blob/main/images/S3.png)
<br><br>

#### If an invalid city name is entered by a user or no input is given by user then following output is displayed 
<br><br>
![S4.png](https://github.com/suyash-9/Weather-Forecast-Website/blob/main/images/S4.png)
 

## Contributors
* Suyash Sinha
* Shubham Sharma
* Rahul Shukla
<br>

## Installation
### Prerequisites

#### 1. Install Python
 Install python-3.7.2 and python-pip. Follow the steps from the below reference document based on your Operating System. Reference: https://docs.python-guide.org/starting/installation/
 
#### 2. Install Django
 Install a stable version of Django. Follow the steps form the below reference document based on your Operating System. Reference: https://www.djangoproject.com/download/

#### 3. Setup Virtual Environment
  1) Install virtualenv using
  
            pip install virtualenv 

  2) Now in which ever directory you are, this line below will create a virtualenv there
  
             virtualenv myenv 
            
   And here also you can name it anything.

  3) Now if you are same directory then type,
  
               myenv\Scripts\activate
      You can explicitly specify your path too.

     Similarly like Linux you can deactivate it like
     
          deactivate
        
   #### OR Setup Virtual Environment Using Anaconda
   To setup virtual environment using anaconda install a stable version of anaconda. Follow the steps form the below reference document based on your Operating System. Reference: https://docs.anaconda.com/anaconda/install/
   
   Steps to set up virtual environment in anaconda . Reference: https://docs.conda.io/projects/conda/en/latest/user-guide/tasks/manage-environments.html#creating-an-environment-with-commands
      
  Step 1: Check if conda is installed in your path.

   *   Open up the anaconda command prompt.
   *   Type conda -V  and press enter.
    
               conda -V
  Step 2: Update the conda environment 
   
   *   Enter the following in the anaconda prompt.
    
               conda update conda
              
  Step 3: Set up the virtual environment

   *   Type conda search “^python$”  to see the list of available python versions.
   *   Now replace the envname with the name you want to give to your virtual environment and replace x.x with the python version you want to use.
    
              conda create -n envname python=x.x anaconda
             
  Step 4: Activating the virtual environment

   *   To see the list of all the available environments use command conda info -e
   *   To activate the virtual environment, enter the given command and replace your given environment name with envname
    
             conda activate envname
            
  Step 5: Installation of required packages to the virtual environment

   *   Type the following command to install the additional packages to the environment and replace envname with the name of your environment.
   
             conda install -n yourenvname package

#### 4. Clone Git Repository  
       git clone https://github.com/suyash-9/Weather-Forecast-Website
       
#### 5. Install Django in Virtual environment
  Activate the virtual environment and use the command to install django in the same environment
  
             pip install Django
            
  To install django in anaconda virtual environment enter the following command after activating the anaconda virtual environment
  
             conda install -c anaconda django
            
 #### 6. Create a Django Project
  In the activated virtual environment type the command to create Django project
  
             django-admin startproject mysite
            
#### 7. Create an APP in Django
 To create a basic app in your Django project you need to go to the directory containing manage.py and from there enter the command : 
      
            python manage.py startapp projectApp

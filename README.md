# CollegeFootballStandings

GooglePlay: https://play.google.com/store/apps/details?id=com.collegefootballstandings.app

![alt tag](https://lh3.googleusercontent.com/LylcpKQ1O6QBDJNLWWeO0xuFnQZHQlboOnfUSDiMsSRZhA7ymoQdXPGoFW7fG02LOu8=h310-rw)
![alt tag](https://lh3.googleusercontent.com/nZVCGPNaeYN3Gj1moKiem9nSIXJlrCYit9apn_KaaeTIGEV_K_Lx1dY_4HaZfrYx-Fw=h310-rw)

Android Gingerbread 10-Lollipop 23

Dispalys the current AP rankings for the 2015-2016 College Football season
Each weekly rankings are updated via a file server which is pulled as a json stream into the application
Stream is parsed and places in custom object models and displayed in a list format. 

To reduce server costs the applciation tracks how many requests are pulled from a single user if limit is reached then data is stored in memory
to be fetched quickly and easily. Rankings are updated every Sunday 12:00EST, list does not change. Data is stored in memory until the rankings are updated



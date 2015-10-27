# CollegeFootballStandings

GooglePlay: https://play.google.com/store/apps/details?id=com.collegefootballstandings.app

![alt tag](https://lh3.googleusercontent.com/qo8IqkcOHFTToiyQUAjarRUc3ueuBCpljd9dvVQvHhrLSChi2lCKpCqH4w4kWOFWzm8=w300-rw)

Android Gingerbread 10-Lollipop 23

Displays the current AP rankings for the 2015-2016 College Football season
Each weekly rankings are updated via a file server which is pulled as a json stream into the application
Stream is parsed and places in custom object models and displayed in a list format.

To reduce server costs the application tracks how many requests are pulled from a single user if limit is reached then data is stored in memory to be fetched quickly and easily. Rankings are updated every Sunday 12:00EST, list does not change. Data is stored in memory until the rankings are updated.

Data is pulled from an API server and stored on a cloud server
Data is updated weekly so a backgroudn application on the server runs PHP code and updates the file containing the new weekly AP rankings

![alt tag](https://lh3.googleusercontent.com/49M6MPCjwdNCb6E4pD22FZAgalPiECNJFt29PLVuWU0_BVDyJdM1KV0sy-LorAMMG48=h900-rw)



Hopbotnik
===============================================
Hoptoad logging library for Android
-----------------------------------------------

Hopbotnik is a drop-in library for logging errors to Hoptoad on Android 1.5+. It's intended for use in places where you want to log non-critical application errors as simply as possible. You could use it to log system-wide crashes, but Google's Android Market force-close error report logging works fairly well for that case.

It's based on socrata's [hoptoad-notifier-java](https://github.com/socrata/hoptoad-notifier-java), which in turn is based on an older Google Code project just called ["hoptoad"](http://code.google.com/p/hoptoad/). Most significantly, I've removed all external dependencies (log4j, hamcrest) and a lot of filtering/environment-reporting code that doesn't really apply to client applications. It also contains an Android unit-test project instead of standard Java tests (since I prefer running tests against the actual VM that will be running my code).

Other Options
-----------------------------------------------
You may also want to consider [ACRA](http://code.google.com/p/acra/), [android-remote-stacktrace](http://code.google.com/p/android-remote-stacktrace/), and this [StackOverflow post](http://stackoverflow.com/questions/601503/how-do-i-obtain-crash-data-from-my-android-application).
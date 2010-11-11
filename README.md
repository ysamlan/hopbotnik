Hopbotnik
===============================================
Hoptoad logging library for Android
-----------------------------------------------

Hopbotnik is a drop-in library for logging errors to [Hoptoad](http://hoptoadapp.com/) on Android 1.5+. It's intended for use in places where you want to log recoverable application errors as simply as possible. You could also use it to log uncaught exceptions app-wide that normally give Force Close dialog errors , but Google's Android Market crash-report logging and famed `Log.wtf` call works fairly well for that case.

Hopbotnik is based on socrata's [hoptoad-notifier-java](https://github.com/socrata/hoptoad-notifier-java), which in turn is based on an older Google Code project just called ["hoptoad"](http://code.google.com/p/hoptoad/). Most significantly, all external dependencies (log4j, hamcrest) have been removed, as have a lot of filtering/environment-reporting code that doesn't really apply to client applications, which lets the final JAR weigh in at just over 10KB. It also contains an Android unit-test project instead of standard Java tests (since I feel safer running tests against the actual VM that will be running my code).

Usage
-----------------------------------------------

To use Hopbotnik, create a `HoptoadNotice` with a `HoptoadNoticeBuilder`, passing it your Hoptoad API key, an exception or message to log, and an environment identifier string. Send the `HoptoadNotice` via 		a `HoptoadNotifier`'s `notify(notice)` or `notifyInBackground(notice)` methods (`notifyInBackground` spins off a `Thread` to send the error report and is highly recommended over the plain `notify` call to keep your app responsive to users and to avoid Android giving the user "Application Not Responding" errors). 

	try {
		doSomething();
	} catch (JsonInitializationException e) {
	    HoptoadNotice notice = new HoptoadNoticeBuilder(HOPTOAD_KEY, e, "production_env").newNotice();
		new HoptoadNotifier().notifyInBackground(notice);
	}

Licensing
-----------------------------------------------
Apache 2.0, like Luca Marrocco's original project.
The original header license included in all source code:

	Modified or written by Luca Marrocco for inclusion with hoptoad.
	Copyright (c) 2009 Luca Marrocco.
	Licensed under the Apache License, Version 2.0 (the "License")


Other Options
-----------------------------------------------
You may also want to consider [ACRA](http://code.google.com/p/acra/), [android-remote-stacktrace](http://code.google.com/p/android-remote-stacktrace/), and other ideas and notes in this [StackOverflow post](http://stackoverflow.com/questions/601503/how-do-i-obtain-crash-data-from-my-android-application).

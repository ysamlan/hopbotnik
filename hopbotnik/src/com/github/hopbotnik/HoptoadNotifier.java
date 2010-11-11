// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.util.Log;

public class HoptoadNotifier implements HoptoadNotify {

	private void addingProperties(final HttpURLConnection connection) throws ProtocolException {
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-type", "text/xml");
		connection.setRequestProperty("Accept", "text/xml, application/xml");
		connection.setRequestMethod("POST");
	}

	private HttpURLConnection createConnection() throws IOException, MalformedURLException {
		final HttpURLConnection connection = (HttpURLConnection) new URL("http://hoptoadapp.com/notifier_api/v2/notices").openConnection();
		return connection;
	}

	private void err(final HoptoadNotice notice, final Exception e) {
        Log.e("scvngr-hoptoad-notifier",
                "Problem writing error log to hoptoad", e);
	}

	@Override
    public int notify(final HoptoadNotice notice) {
		try {
			final HttpURLConnection toHoptoad = createConnection();
			addingProperties(toHoptoad);
			return send(new NoticeApi2(notice).toString(), toHoptoad);
		} catch (final Exception e) {
			err(notice, e);
		}
		return 0;
	}

	public void notifyInBackground(final HoptoadNotice notice) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HoptoadNotifier.this.notify(notice);
			}
		}).start();
	}

	private int send(final String yaml, final HttpURLConnection connection) throws IOException {
		int statusCode;
		final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(yaml);
		writer.close();

		statusCode = connection.getResponseCode();
		return statusCode;
	}

}

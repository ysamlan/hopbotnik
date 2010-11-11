// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik.test;

import static com.github.hopbotnik.test.Exceptions.ERROR_MESSAGE;
import static com.github.hopbotnik.test.Exceptions.newException;
import junit.framework.TestCase;
import android.util.Log;

import com.github.hopbotnik.Backtrace;

public class BacktraceTest extends TestCase {

	public void testJavaBacktrace() {
		final Throwable EXCEPTION = newException(ERROR_MESSAGE);

		final Iterable<String> backtrace = new Backtrace(EXCEPTION);
		boolean found = false;
		for (String s : backtrace) {
			Log.e("", s);
			if ("at com.github.hopbotnik.test.Exceptions.newException(Exceptions.java:15)".equals(s)) {
				found = true;
				break;
			}
		}

		assertTrue(found);

	}
}

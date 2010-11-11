// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik.test;

import static com.github.hopbotnik.test.Exceptions.ERROR_MESSAGE;
import static com.github.hopbotnik.test.Exceptions.newException;
import static java.util.Arrays.asList;
import junit.framework.TestCase;

import com.github.hopbotnik.Backtrace;
import com.github.hopbotnik.HoptoadNotice;
import com.github.hopbotnik.HoptoadNoticeBuilder;
import com.github.hopbotnik.HoptoadNotifier;
import com.github.hopbotnik.HoptoadNotify;

public class HoptoadNotifierTest extends TestCase {
	public static final String API_KEY = "a7bad952a319d10540fbbd64b597260d";

	protected static final Backtrace BACKTRACE = new Backtrace(asList("backtrace is empty"));

	private HoptoadNotify notifier;

	@Override
	public void setUp() {
		notifier = new HoptoadNotifier();
	}

	public void testNotifyToHoptoadUsingBuilderNoticeFromExceptionInEnv() {
		final Exception EXCEPTION = newException(ERROR_MESSAGE);
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, EXCEPTION, "test").newNotice();

		assertEquals(notifier.notify(notice), 200);
	}

	public void testNotifyToHoptoadUsingBuilderNoticeInEnv() {
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, ERROR_MESSAGE, "test").newNotice();

		assertEquals(notifier.notify(notice), 200);
	}

	public void testSendExceptionToHoptoad() {
		final Exception EXCEPTION = newException(ERROR_MESSAGE);
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, EXCEPTION).newNotice();

		assertEquals(notifier.notify(notice), 200);
	}

	public void testSendNoticeToHoptoad() {
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, ERROR_MESSAGE).newNotice();
		assertEquals(notifier.notify(notice), 200);

	}
}

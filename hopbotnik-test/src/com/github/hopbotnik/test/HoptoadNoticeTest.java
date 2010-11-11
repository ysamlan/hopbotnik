// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik.test;

import static com.github.hopbotnik.test.Exceptions.ERROR_MESSAGE;
import static com.github.hopbotnik.test.Exceptions.newException;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.github.hopbotnik.Backtrace;
import com.github.hopbotnik.HoptoadNotice;
import com.github.hopbotnik.HoptoadNoticeBuilder;

public class HoptoadNoticeTest extends TestCase{

	public static final String API_KEY = "ab2cedccb3c99732b8ea4793d7b53609";

	protected static final Backtrace BACKTRACE = new Backtrace(asList("backtrace is empty"));
	protected static final Map<String, Object> REQUEST = new HashMap<String, Object>();

	public void testNewHoptoadUsingBuilderNoticeFromException() {
		final Exception EXCEPTION = newException(ERROR_MESSAGE);
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, EXCEPTION).newNotice();
		assertNotNull(notice);
		assertEquals(notice.apiKey(), API_KEY);
		assertEquals(notice.errorMessage(), ERROR_MESSAGE);
		assertNotNull(notice.backtrace());
	}

	public void testNewHoptoadUsingBuilderNoticeWithBacktrace() {
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, ERROR_MESSAGE) {
			{
				backtrace(BACKTRACE);
			}
		}.newNotice();

		assertNotNull(notice);
		assertEquals(notice.apiKey(), API_KEY);
		assertEquals(notice.errorMessage(), ERROR_MESSAGE);
		assertNotNull(notice.backtrace());
		assertEquals(notice.backtrace(), BACKTRACE);
	}
	public void testNewHoptoadUsingBuilderNoticeWithErrorMessage() {
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, ERROR_MESSAGE) {
			{}
		}.newNotice();

		assertNotNull(notice);
		assertEquals(notice.apiKey(), API_KEY);
		assertEquals(notice.errorMessage(), ERROR_MESSAGE);
	}

	public void testNewHoptoadUsingBuilderNoticeWithRequest() {
		final HoptoadNotice notice = new HoptoadNoticeBuilder(API_KEY, ERROR_MESSAGE) {
			{
				request(REQUEST);
			}
		}.newNotice();

		assertNotNull(notice);
		assertEquals(notice.apiKey(), API_KEY);
		assertEquals(notice.errorMessage(), ERROR_MESSAGE);
		assertEquals(notice.request(), REQUEST);
	}
}
// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik.test;

import junit.framework.TestCase;

import com.github.hopbotnik.HoptoadNotice;
import com.github.hopbotnik.HoptoadNoticeBuilder;
import com.github.hopbotnik.NoticeApi2;

public class NoticeApi2XmlTest extends TestCase {

	private HoptoadNotice notice;

	private String clean(final String string) {
		return string.replaceAll("\\\"", "");
	}

	@Override
	public void setUp() {
		notice = new HoptoadNoticeBuilder("apiKey", new RuntimeException("errorMessage")).newNotice();
	}

	public void testApiKey() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<api-key>apiKey</api-key>"));
	}

	public void testError() {
		assertTrue(xml(new NoticeApi2(notice)).contains("error>"));
	}

	public void testErrorBacktrace() {
		assertTrue(xml(new NoticeApi2(notice)).contains("backtrace>"));
	}

	public void testErrorBacktraceLine() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<line method="));
	}

	public void testErrorClass() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<class>java.lang.RuntimeException</class>"));
	}

	public void testErrorMessage() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<message>errorMessage</message>"));
	}

	public void testNoticeVersion() {
		assertTrue(xml(new NoticeApi2(notice)).contains("notice version=2.0"));
	}

	public void testNotifier() {
		assertTrue(xml(new NoticeApi2(notice)).contains("notifier>"));
	}

	public void testNotifierName() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<name>hopbotnik</name>"));
	}

	public void testNotifierUrl() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<url>http"));
	}

	public void testNotifierVersion() {
		assertTrue(xml(new NoticeApi2(notice)).contains("<version>"));
	}

	private String xml(final NoticeApi2 noticeApi) {
		return clean(noticeApi.toString());
	}
}

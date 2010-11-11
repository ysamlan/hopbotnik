package com.github.hopbotnik.test;

import junit.framework.TestCase;

import com.github.hopbotnik.HoptoadNotice;
import com.github.hopbotnik.HoptoadNoticeBuilder;

public class HoptoadNoticeBuilderTest extends TestCase {

	public void testBuildNoticeErrorClass() {
		HoptoadNoticeBuilder builder = new HoptoadNoticeBuilder("apiKey",
				new RuntimeException("errorMessage"));
		HoptoadNotice notice = builder.newNotice();
		assertEquals(notice.errorClass(), "java.lang.RuntimeException");
	}
}

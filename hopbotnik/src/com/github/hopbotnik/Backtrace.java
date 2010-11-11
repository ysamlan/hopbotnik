/*
 * Modified version of Socrata's hoptoad-notifier-java
 * (https://github.com/socrata/hoptoad-notifier-java)
 * based on Luca Marrocco's hoptoad (http://code.google.com/p/hoptoad/).
 *
 * Original license:
 *
 * Copyright (c) 2009 Luca Marrocco.
 * Licensed under the Apache License, Version 2.0 (the "License").
 */

package com.github.hopbotnik;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Backtrace implements Iterable<String> {

	private final List<String> backtrace = new LinkedList<String>();

	protected Backtrace() {}

	public Backtrace(final List<String> backtrace) {
		this.backtrace.addAll(backtrace);
	}

	public Backtrace(final Throwable throwable) {
		toBacktrace(throwable);
	}

	private String causedBy(final Throwable throwable) {
		return MessageFormat.format("Caused by {0}", messageIn(throwable));
	}

	@Override
    public Iterator<String> iterator() {
		return backtrace.iterator();
	}

	private String messageIn(final Throwable throwable) {
		String message = throwable.getMessage();
		if (message == null) {
			message = throwable.getClass().getName();
		}
		return message;
	}

	public Backtrace newBacktrace(final Throwable throwable) {
		return new Backtrace(throwable);
	}

	private String toBacktrace(final StackTraceElement element) {
		return toBacktrace(element.getClassName(), element.getFileName(), element.getLineNumber(), element.getMethodName());
	}

	protected String toBacktrace(final String className, final String fileName, final int lineNumber, final String methodName) {
		return new BacktraceLine(className, fileName, lineNumber, methodName).toString();
	}

	private void toBacktrace(final Throwable throwable) {
		if (throwable == null) {
            return;
        }

		backtrace.add(causedBy(throwable));
		for (final StackTraceElement element : throwable.getStackTrace()) {
			backtrace.add(toBacktrace(element));
		}

		toBacktrace(throwable.getCause());
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		for (final String string : backtrace) {
			stringBuilder.append(string).append("\n");
		}
		return stringBuilder.toString();
	}
}

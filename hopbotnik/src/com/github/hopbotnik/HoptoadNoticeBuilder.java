// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik;

import static java.util.Arrays.asList;

import java.util.Map;
import java.util.TreeMap;

public class HoptoadNoticeBuilder {

	private String apiKey;

	private String errorMessage;

	private Backtrace backtrace = new Backtrace(asList("backtrace is empty"));

	private final String environment;

	private Map<String, Object> request = new TreeMap<String, Object>();

	private final Map<String, Object> session = new TreeMap<String, Object>();

	private Backtrace backtraceBuilder = new Backtrace();

	private String errorClass;

	public HoptoadNoticeBuilder(final String apiKey, final Backtrace backtraceBuilder, final Throwable throwable, final String env) {
		this(apiKey, throwable.getMessage(), env);
		this.backtraceBuilder = backtraceBuilder;
		errorClass(throwable);
		backtrace(throwable);
	}

	public HoptoadNoticeBuilder(final String apiKey, final String errorMessage) {
		this(apiKey, errorMessage, "test");
	}

	public HoptoadNoticeBuilder(final String apiKey, final String errorMessage, final String env) {
		apiKey(apiKey);
		errorMessage(errorMessage);
		environment = env;
	}

	public HoptoadNoticeBuilder(final String apiKey, final Throwable throwable) {
		this(apiKey, new Backtrace(), throwable, "test");
	}

	public HoptoadNoticeBuilder(final String apiKey, final Throwable throwable, final String env) {
		this(apiKey, new Backtrace(), throwable, env);
	}

	private void apiKey(final String apiKey) {
		if (notDefined(apiKey)) {
			error("The API key for the project this error is from (required). Get this from the project's page in Hoptoad.");
		}
		this.apiKey = apiKey;
	}

	/** An array where each element is a line of the backtrace (required, but can be empty). */
	protected void backtrace(final Backtrace backtrace) {
		this.backtrace = backtrace;
	}

	private void backtrace(final Throwable throwable) {
		backtrace(backtraceBuilder.newBacktrace(throwable));
	}

	private void error(final String message) {
		throw new RuntimeException(message);
	}

	private void errorClass(final Throwable throwable) {
		errorClass = throwable.getClass().getName();
	}

	protected boolean errorClassIs(final String possibleErrorClass) {
		return errorClass.equals(possibleErrorClass);
	}

	private void errorMessage(final String errorMessage) {
		if (notDefined(errorMessage))
        {
            this.errorMessage = "";
		}
        else
        {
            this.errorMessage = errorMessage;
        }
	}

	public HoptoadNotice newNotice() {
		return new HoptoadNotice(apiKey, errorMessage, errorClass, backtrace, request, session, environment);
	}

	private boolean notDefined(final Object object) {
		return object == null;
	}

	/** A hash of the request parameters that were given when the error occurred (required, but can be empty). */
	protected void request(final Map request) {
		this.request = request;
	}

	/** A hash of the session data that existed when the error occurred (required, but can be empty). */
	protected void session(final Map session) {
		this.session.putAll(session);
	}

}
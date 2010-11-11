// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik;

import static java.util.Arrays.asList;

import java.util.Map;
import java.util.TreeMap;

public class HoptoadNotice {
	private final String apiKey;

	private final String errorMessage;

	private Backtrace backtrace = new Backtrace(asList("backtrace is empty"));

	private Map<String, Object> request = new TreeMap<String, Object>();

	private Map<String, Object> session = new TreeMap<String, Object>();

	private final String errorClass;

	private final String environment;

	public String getEnvironment() {
		return environment;
	}

	public HoptoadNotice(final String apiKey, final String errorMessage, final String errorClass, final Backtrace backtrace, final Map<String, Object> request, final Map<String, Object> session, final String environment) {
		this.apiKey = apiKey;
		this.errorClass = errorClass;
		this.errorMessage = errorMessage;
		this.backtrace = backtrace;
		this.request = request;
		this.session = session;
		this.environment = environment;
	}

	public String apiKey() {
		return apiKey;
	}

	public Backtrace backtrace() {
		return backtrace;
	}

	public String errorClass() {
		return errorClass;
	}

	public String errorMessage() {
		return errorMessage;
	}

	public Map<String, Object> request() {
		return request;
	}

	public Map<String, Object> session() {
		return session;
	}
}

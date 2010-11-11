// Modified or written by Luca Marrocco for inclusion with hoptoad.
// Copyright (c) 2009 Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package com.github.hopbotnik;

public class NoticeApi2 {

	private final StringBuilder stringBuilder = new StringBuilder();

	public NoticeApi2(final HoptoadNotice notice) {
		notice("2.0.0");
		{
			apikey(notice);

			notifier();
			{
				name("hopbotnik");
				version("0.9-dev");
				url("https://github.com/ysamlan/hopbotnik");
			}
			end("notifier");

			error();
			{
				tag("class", notice.errorClass());
				tag("message", notice.errorMessage());

				backtrace();
				{
					for (final String backtrace : notice.backtrace()) {
						line(backtrace);
					}
				}
				end("backtrace");
			}
			end("error");

      server_environment();
      {
        tag("environment-name", notice.getEnvironment());
      }
      end("server_environment");
		}
		end("notice");
	}

	private void apikey(final HoptoadNotice notice) {
		tag("api-key");
		{
			append(notice.apiKey());
		}
		end("api-key");
	}

	private void append(final String str) {
		stringBuilder.append(str);
	}

	private void backtrace() {
		tag("backtrace");
	}

	private void end(final String string) {
		append("</" + string + ">");
	}

	private void error() {
		tag("error");
	}

  private void server_environment() {
    tag("server-environment");
  }

	private void line(final String backtrace) {
		append(new BacktraceLine(backtrace).toXml());
	}

	private void name(final String name) {
		tag("name", name);
	}

	private void notice(final String string) {
		append("<?xml version=\"1.0\"?>");
		append("<notice version=\"" + string + "\">");
	}

	private void notifier() {
		tag("notifier");
	}

	private void notifier(final String name, final String version, final String url) {
		notifier();
		{
			name(name);
			version(version);
			url(url);
		}
		end("notifier");
	}

	private NoticeApi2 tag(final String string) {
		append("<" + string + ">");
		return this;
	}

	private void tag(final String string, final String name2) {
		tag(string).text(name2).end(string);
	}

	private NoticeApi2 text(final String string) {
		append(string);
		return this;
	}

	@Override
	public String toString() {
		return stringBuilder.toString();
	}

	private void url(final String url) {
		tag("url", url);
	}

	private void version(final String version) {
		tag("version", version);
	}
}

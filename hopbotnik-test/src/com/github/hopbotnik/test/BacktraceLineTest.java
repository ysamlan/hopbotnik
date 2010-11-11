package com.github.hopbotnik.test;

import junit.framework.TestCase;

import com.github.hopbotnik.BacktraceLine;

public class BacktraceLineTest extends TestCase {
	public void testBacktraceLineFromString() {
		BacktraceLine backtraceLine = new BacktraceLine("at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:46)");
		assertEquals("org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference", backtraceLine.className());
		assertEquals("run", backtraceLine.methodName());
		assertEquals(46, backtraceLine.lineNumber());
		assertEquals("JUnit4TestReference.java", backtraceLine.fileName());
	}

	public void testBacktraceLineToString() {
		BacktraceLine backtraceLine = new BacktraceLine("org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference", "JUnit4TestReference.java", 46, "run");
		assertEquals("at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:46)", backtraceLine.toString());
	}

	public void testBacktraceLineToXml() {
		BacktraceLine backtraceLine = new BacktraceLine("org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference", "JUnit4TestReference.java", 46, "run");
		assertEquals("<line method=\"org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run\" file=\"JUnit4TestReference.java\" number=\"46\"/>", backtraceLine.toXml());
	}
}

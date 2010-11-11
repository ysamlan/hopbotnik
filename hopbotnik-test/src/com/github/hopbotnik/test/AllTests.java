package com.github.hopbotnik.test;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * A test suite containing all tests for the application.
 */
public final class AllTests extends TestSuite {
    /**
     * Bundles all the test cases up into a single Test.
     *
     * @return Bundled-up test cases.
     */
    public static Test suite() {
        return new TestSuiteBuilder(AllTests.class)
                .includeAllPackagesUnderHere().build();
    }
}

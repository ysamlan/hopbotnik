package com.github.hopbotnik;


/**
 * Utility class for XML operations.
 */
public final class XmlUtils {
    /**
     * Private default constructor to prevent instantiation of utility class.
     */
    private XmlUtils() {
        throw new AssertionError();
    }

    public static String escapeXmlProperty(final String value) {
		return value
				.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot")
				.replace("'", "&apos;");
    }
}

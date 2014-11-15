package com.maprosoft.maproweb.confluence;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class MaprowebAtlasUtil {
	
	private MaprowebAtlasUtil() {
	}

	public static void addParameter(
			final String key,
			final String value,
			final StringBuilder url,
			final AtomicReference<String> thisSeparator,
			final String nextSeparator) {
		if (value != null) {
			url.append(thisSeparator.get());
			url.append(key);
			url.append("=");
			url.append(value);
			thisSeparator.set(nextSeparator);
		}
	}

	public static String getParameter(String key, Map<String, String> parameters, final String defaultValue) {
		String value = parameters.get(key);
		if (value == null || value.length() == 0) {
			value = defaultValue;
		}
		return value;
	}

	public static boolean isNil(final String text) {
		return text == null || text.length() == 0;
	}

}

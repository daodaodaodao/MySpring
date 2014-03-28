package com.daodao.common.web;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
public class Servlets {

	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefix) {

		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				//String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(paramName, values);
				} else {
					String value = StringUtils.trim(values[0]);
					if ("true".equalsIgnoreCase(value)
							|| "false".equalsIgnoreCase(value)) {
						params.put(paramName, Boolean.valueOf(value));
					} else {
						params.put(paramName, value);
					}
				}
			}
		}
		return params;
	}

}

package common.servlet.wrapper;

import java.util.HashMap;
import java.util.Map;

import common.util.security.xss.XssPreventer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2025. 5. 14. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}

		return XssPreventer.escape(value);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null) {
			return new String[0];
		}

		int count = values.length;
		String[] encodedValues = new String[count];

		for (int i = 0; i < count; i++) {
			values[i] = XssPreventer.escape(values[i]);
		}

		return encodedValues;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null) {
			return null;
		}

		return XssPreventer.escape(value);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
	    Map<String, String[]> parameterMap = super.getParameterMap();
	    Map<String, String[]> escapedMap = new HashMap<>();

	    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
	        String key = entry.getKey();
	        String[] values = entry.getValue();

	        String[] escapedValues = new String[values.length];
	        for (int i = 0; i < values.length; i++) {
	            escapedValues[i] = XssPreventer.escape(values[i]);
	        }

	        escapedMap.put(key, escapedValues);
	    }

	    return escapedMap;
	}

}

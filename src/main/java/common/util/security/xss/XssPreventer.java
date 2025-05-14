package common.util.security.xss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2025. 5. 14. kdk	최초작성
 * </pre>
 *
 * 출처 : https://github.com/naver/lucy-xss-filter/blob/master/src/main/java/com/nhncorp/lucy/security/xss/XssPreventer.java
 *
 * @author kdk
 */
public class XssPreventer {

	private XssPreventer() {
		super();
	}

	private static Pattern escapePattern = Pattern.compile("'");
	private static Pattern unescapePttern = Pattern.compile("&#39;");

	public static String escape(String dirty) {

		String clean = StringEscapeUtils.escapeHtml4(dirty);

		if (clean == null) {
			return null;
		}

		Matcher matcher = escapePattern.matcher(clean);

		if (matcher.find()) {
			return matcher.replaceAll("&#39;");
		}

		return clean;
	}

	public static String unescape(String clean) {

		String str = StringEscapeUtils.unescapeHtml4(clean);

		if (str == null) {
			return null;
		}

		Matcher matcher = unescapePttern.matcher(str);

		if (matcher.find()) {
			return matcher.replaceAll("'");
		}

		return str;
	}

}

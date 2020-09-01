package prime.mgt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class BehindProxyServletUtil {
	private static final String FORWARDED_CLIENT_HTTP_HEADER_URI = "x-forwarded-uri";
	private static final String FORWARDED_CLIENT_HTTP_HEADER = "x-forwarded-for";
	private static final String FORWARDED_CLIENT_HTTP_HEADER_PORT = "x-forwarded-sourceport";
	private static final String TLS_VERSION_HEADER = "x-forwarded-tlsversion";
	private static final String TLS_CIPHER_HEADER = "x-forwarded-tlscipher";

	/**
	 * Returns remote clients IP address.
	 * 
	 * @param request
	 *            An HttpServerRequest
	 * @return Remote client's IP address. If client is behind a proxy AND proxy sends ${FORWARDED_CLIENT_HTTP_HEADER} header field, the value for this key is
	 *         returned. Otherwise, getRemoteAddr() is invoked on request and the result is returned.
	 * @throws NullPointerException
	 *             If request is null.
	 */
	public final String getRemoteAddr(HttpServletRequest request) {
		if (null == request) {
			throw new NullPointerException("Http Servlet Request is NULL.");
		}
		String ip = request.getHeader(FORWARDED_CLIENT_HTTP_HEADER);
		if (null != ip) {
			String[] ipList = ip.split(",");
			if (ipList != null && ipList.length > 0) {
				ip = ipList[0].trim();
				if (ip != null && isValidIP(ip)) {
					return ip;
				}
			}
		}
		return request.getRemoteAddr();
	}

	/**
	 * Returns if IP is valid.
	 * 
	 * @param ip
	 *            The IP address to check.
	 * @return True if IP format is valid.
	 */
	public boolean isValidIP(String ip) {
		if (null == ip) {
			throw new NullPointerException("IP address is NULL");
		}
		// return ip.matches("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");
		return ip
				.matches("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
	}

	/**
	 * Returns head / root uri info
	 * 
	 * @param req
	 *            An HttpServerRequest
	 * @return uri
	 */
	public String getUriHead(HttpServletRequest req) {
		String uri = req.getRequestURI();
		final String xForwardedUri = req.getHeader(FORWARDED_CLIENT_HTTP_HEADER_URI);
		if (xForwardedUri != null) {
			uri = xForwardedUri;
		}
		// Request Headers
		// > x-forwarded-proto : on
		// > x-forwarded-uri : /NestCollect/admin/Login
		// > x-forwarded-for : 192.168.0.1
		// > x-forwarded-host : testsanalpos.est.com.tr
		// > x-forwarded-server : testsanalpos.est.com.tr
		if (uri != null && uri.length() > 0) {
			if (uri.contains("/")) {
				uri = uri.substring(uri.indexOf("/") + 1);
				if (uri.contains("/")) {
					uri = uri.substring(0, uri.indexOf("/"));
				}
			}
		}
		return uri;
	}

	/**
	 * Returns remote clients port information.
	 * 
	 * @param request
	 *            An HttpServerRequest
	 * @return Remote client's port information. If client is behind a proxy AND proxy sends ${FORWARDED_CLIENT_HTTP_HEADER_FOR} header field, the value for
	 *         this key is returned. Otherwise, getRemotePort() is invoked on request and the result is returned.
	 * @throws NullPointerException
	 *             If request is null.
	 */
	public final int getRemotePort(HttpServletRequest request) {
		if (null == request) {
			throw new NullPointerException("Http Servlet Request is NULL.");
		}
		String port = request.getHeader(FORWARDED_CLIENT_HTTP_HEADER_PORT);
		if (null != port) {
			String[] portList = port.split(",");
			if (portList != null && portList.length > 0) {
				port = portList[0].trim();
				if (StringUtils.isNotBlank(port)) {
					return NumberUtils.toInt(port, 0);
				}
			}
		}
		return request.getRemotePort();
	}

	/**
	 * Returns TLS version information.
	 * 
	 * @param request
	 *            An HttpServerRequest
	 * @return TLS version.
	 * @throws NullPointerException
	 *             If request is null.
	 */
	public final String getTLSVersion(HttpServletRequest request) {
		if (null == request) {
			throw new NullPointerException("Http Servlet Request is NULL.");
		}
		return StringUtils.defaultIfBlank(request.getHeader(TLS_VERSION_HEADER), "");
	}

	/**
	 * Returns TLS cipher information.
	 * 
	 * @param request
	 *            An HttpServerRequest
	 * @return TLS cipher.
	 * @throws NullPointerException
	 *             If request is null.
	 */
	public final String getTLSCipher(HttpServletRequest request) {
		if (null == request) {
			throw new NullPointerException("Http Servlet Request is NULL.");
		}
		return StringUtils.defaultIfBlank(request.getHeader(TLS_CIPHER_HEADER), "");
	}
}

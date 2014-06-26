////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public class HTTPUtil {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(HTTPUtil.class);

	Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * Sends an HTTP GET request to a url
	 * 
	 * @param endpoint
	 *            - The URL of the server. (Example:
	 *            " http://www.yahoo.com/search")
	 * @param requestParameters
	 *            - all the request parameters (Example:
	 *            "param1=val1&param2=val2"). Note: This method will add the
	 *            question mark (?) to the request - DO NOT add it yourself
	 * @return - The response from the end point
	 */
	protected final String sendGetRequest(String endpoint,
			String requestParameters) {

		String result = null;
		if (endpoint.startsWith("http://")) {
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Reads data from the data reader and posts it to a server via POST
	 * request. data - The data you want to send endpoint - The server's address
	 * output - writes the server's response to output
	 * 
	 * @throws Exception
	 */
	protected final void postData(Reader data, String endpoint, Writer output)
			throws AlKhwarizmixException {

		HttpURLConnection urlc = null;
		try {
			URL url = new URL(endpoint);
			urlc = (HttpURLConnection) url.openConnection();
			try {
				urlc.setRequestMethod("POST");
			} catch (ProtocolException e) {
				throw new AlKhwarizmixException(
						"Shouldn't happen: HttpURLConnection doesn't support POST??",
						e);
			}
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setUseCaches(false);
			urlc.setAllowUserInteraction(false);
			urlc.setRequestProperty("Content-type", "text/xml; charset="
					+ "UTF-8");

			OutputStream out = urlc.getOutputStream();

			try {
				Writer writer = new OutputStreamWriter(out, "UTF-8");
				pipe(data, writer);
				writer.close();
			} catch (IOException e) {
				throw new AlKhwarizmixException(
						"IOException while posting data", e);
			} finally {
				if (out != null)
					out.close();
			}

			InputStream in = urlc.getInputStream();
			try {
				Reader reader = new InputStreamReader(in);
				pipe(reader, output);
				reader.close();
			} catch (IOException e) {
				throw new AlKhwarizmixException(
						"IOException while reading response", e);
			} finally {
				if (in != null)
					in.close();
			}

		} catch (IOException e) {
			throw new AlKhwarizmixException(
					"Connection error (is server running at " + endpoint
							+ " ?): " + e);
		} finally {
			if (urlc != null)
				urlc.disconnect();
		}
	}

	/**
	 * Pipes everything from the reader to the writer via a buffer
	 */
	private void pipe(Reader reader, Writer writer) throws IOException {
		char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0) {
			writer.write(buf, 0, read);
		}
		writer.flush();
	}

	/**
	 */
	protected final void sendGetRequest(String url, HttpClient httpclient)
			throws AlKhwarizmixException {

		boolean disposeHttpclient = false;
		if (httpclient == null) {
			httpclient = new DefaultHttpClient();
			disposeHttpclient = true;
		}

		try {
			HttpGet httpget = new HttpGet(url);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			getLogger().trace("Login form get: " + response.getStatusLine());
			EntityUtils.consume(entity);

			getLogger().trace("Initial set of cookies:");
			DefaultHttpClient defaultHttpClient = (DefaultHttpClient) httpclient;
			if (defaultHttpClient != null) {
				List<Cookie> cookies = defaultHttpClient.getCookieStore()
						.getCookies();
				if (cookies.isEmpty()) {
					getLogger().trace("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						getLogger().trace("- " + cookies.get(i).toString());
					}
				}
			}

			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			getLogger().trace("----------------------------------------");
			getLogger().trace(responseBody);
			getLogger().trace("----------------------------------------");
		} catch (Exception e) {
			throw new AlKhwarizmixException(e.getMessage());
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			if (disposeHttpclient)
				httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 */
	protected final void sendPostRequest(String url, List<NameValuePair> nvps,
			HttpClient httpclient) throws AlKhwarizmixException {

		boolean disposeHttpclient = false;
		if (httpclient == null) {
			httpclient = new DefaultHttpClient();
			disposeHttpclient = true;
		}

		try {
			HttpPost httpost = new HttpPost(url);

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();

			getLogger().trace("Header Local: " + response.getHeaders("Local"));

			getLogger().trace("Login form get: " + response.getStatusLine());
			EntityUtils.consume(entity);

			getLogger().trace("Post logon cookies:");
			DefaultHttpClient defaultHttpClient = (DefaultHttpClient) httpclient;
			if (defaultHttpClient != null) {
				List<Cookie> cookies = defaultHttpClient.getCookieStore()
						.getCookies();
				if (cookies.isEmpty()) {
					getLogger().trace("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						getLogger().trace("- " + cookies.get(i).toString());
					}
				}
			}

			// Create a response handler
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String responseBody = httpclient.execute(httpost,
						responseHandler);
				getLogger().trace("----------------------------------------");
				getLogger().trace(responseBody);
				getLogger().trace("----------------------------------------");
			}
		} catch (Exception e) {
			throw new AlKhwarizmixException(e.getMessage());
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			if (disposeHttpclient)
				httpclient.getConnectionManager().shutdown();
		}
	}

}
package request;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.xml.sax.SAXException;

import response.SimpleResponse;

public abstract class BasicRequest implements IBasicRequest {

	protected String reqType;
	protected String url;
	protected String contentType;

	public BasicRequest(String url, String accept, String reqType) {
		this.url = url;
		this.contentType = accept;
		this.reqType = reqType;
	}

	// this should be sendRequest and then decide based on reqType
	public SimpleResponse sendGetRequest()
			throws IOException, IllegalStateException, SAXException, ParserConfigurationException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		request.addHeader("content-type", contentType);
		SimpleResponse response = new SimpleResponse(client.execute(request));
		client.close();
		return response;
	}

}

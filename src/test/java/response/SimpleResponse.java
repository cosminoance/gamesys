package response;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SimpleResponse {

	private String responseString;
	private HttpResponse response;
	private Document document;

	public SimpleResponse(HttpResponse resp)
			throws IllegalStateException, IOException, SAXException, ParserConfigurationException {
		response = resp;
		parseString();
		document = parseDocument();
	}

	private void parseString() throws IllegalStateException, IOException {
		InputStream inputStream = response.getEntity().getContent();
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		responseString = scanner.useDelimiter("\\Z").next();
		scanner.close();
	}

	// via
	// https://stackoverflow.com/questions/16712391/parse-xml-simple-string-using-java-xpath
	private Document parseDocument() throws SAXException, IOException, ParserConfigurationException {
		String xml = "<resp><status>good</status><msg>hi</msg></resp>";
		InputSource source = new InputSource(new StringReader(xml));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(source);
	}

	public Document getAsDocument() {
		return document;
	}

	public String getAsString() {
		return responseString;
	}

	public int getResponseCode() {
		return response.getStatusLine().getStatusCode();
	}
}

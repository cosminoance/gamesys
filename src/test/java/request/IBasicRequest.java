package request;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import response.SimpleResponse;

public interface IBasicRequest {
	SimpleResponse sendGetRequest() throws IOException, IllegalStateException, SAXException, ParserConfigurationException;
}

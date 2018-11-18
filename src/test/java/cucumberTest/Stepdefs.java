package cucumberTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import request.BasicRequest;
import request.XmlRequest;
import response.SimpleResponse;
import utils.StringToOutputFile;
import xmlResponseModel.UserVideos;
import xmlResponseModel.videos.EntryModel;
import cucumber.api.java.en.Then;

public class Stepdefs {

	private String url;
	private String reqType;
	private String content;
	BasicRequest request;
	private SimpleResponse response;

	@Given("^the endpoint (.*)$")
	public void the_endpoint(String endpoint) throws Exception {
		this.url = endpoint;
	}

	@When("^I (.*) application/(\\D{3,4}) (.*)$")
	public void request(String requestType, String contentType, String resource) throws Exception {
		url += resource;
		reqType = requestType;
		content = contentType;
		if (content.equals("xml")) {
			request = new XmlRequest(url, reqType);
		}
		response = request.sendGetRequest();

	}

	@Then("^I should receive response code (\\d+)$")
	public void i_should_receive_response_code(int code) throws Exception {
		Assert.assertEquals(code, response.getResponseCode());
	}

	// via
	// https://stackoverflow.com/questions/16712391/parse-xml-simple-string-using-java-xpath
	@Then("^I should have at least (\\d+) author as (.*)$")
	public void countAuthor(int count, String authorName) throws Exception {
		UserVideos feed = new UserVideos(response.getAsString());
		feed.buildEntries();
		//List<EntryModel> videoEntries = feed.entries().stream().filter(s -> s.getAuthor().equals(authorName))
		//		.collect(Collectors.toList());
		List<EntryModel> videoEntries = new ArrayList<EntryModel>();
		for(EntryModel it:feed.entries()) {
			if(it.getAuthor().equals(authorName))
				videoEntries.add(it);
		}
		System.out.println("There are " + videoEntries.size() + " videos uploaded");
		Assert.assertTrue(videoEntries.size() >= count);
		
	}

	@Then("^I should print entries as string$")
	public void i_should_print_entries_as_string() throws Exception {
		// System.out.print(response.getAsString());
		UserVideos feed = new UserVideos(response.getAsString());
		feed.buildEntries();
		for (EntryModel it : feed.entries()) {
			System.out.println(it.getAuthor() + " posted \"" + it.getTitle() + "\" on " + it.getPublished());
		}
	}

	@Then("^save response as file for (.*)$")
	public void save_response_as_file(String name) throws Exception {
		if (response != null) {
			new StringToOutputFile(name, response.getAsString(), content).save();
		}
	}
}
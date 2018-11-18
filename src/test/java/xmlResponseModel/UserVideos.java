package xmlResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xmlResponseModel.videos.EntryModel;

public class UserVideos extends ResponseModel {

	ArrayList<EntryModel> entryList; 
	
	public UserVideos(String responseString) {
		super(responseString);
	}

	//could be done with generics in a parent
	//or at least extract some more objects from it
	public void buildEntries() {
		//String openTag = "<"+tagName + ">";
		//String closeTag = "</" + tagName + "<";
		entryList = new ArrayList<EntryModel>();
		String openTag = "<entry>";
		String closeTag = "</entry>";
		String aux = responseString;
		int crtIndex = aux.indexOf(openTag);
		while(crtIndex != -1) {
			String published = "";
			String author = "";
			String title = "";
			int closeIndex = aux.indexOf(closeTag);
			String entryString = aux.substring(crtIndex, closeIndex);
			Pattern pattern = Pattern.compile("<title>(.*)</title>");
			Matcher matcher = pattern.matcher(entryString);
			if (matcher.find())
			{
			    title = (matcher.group(1));
			}
			pattern = Pattern.compile("<published>(.*)</published>");
			matcher = pattern.matcher(entryString);
			if (matcher.find())
			{
			    published = (matcher.group(1));
			}
			pattern = Pattern.compile("<name>(.*)</name>");
			matcher = pattern.matcher(entryString);
			if (matcher.find())
			{
			    author = (matcher.group(1));
			}
			entryList.add(new EntryModel(title, published, author));
			//find next entry
			aux = aux.substring(closeIndex+8, aux.lastIndexOf("</feed>")+7);
			crtIndex = aux.indexOf(openTag);
		}
	}
	
	public List<EntryModel> entries(){
		return entryList;
	}
}

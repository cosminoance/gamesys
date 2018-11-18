package xmlResponseModel.videos;

/**
 * Used for Entries inside responses
 * @author Cosmin Oance: cosminoance@icloud.com / cosmin.oance@gmail.com / cosmin_oance@yahoo.com
 *
 */
public class EntryModel implements IModel{
	private String title;
	private String published;
	private String author;
	
	public EntryModel(String title, String published, String author) {
		this.title = title;
		this.published = published;
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}

	public String getPublished() {
		return published;
	}

	public String getAuthor() {
		return author;
	}
	
}

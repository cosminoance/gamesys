package xmlResponseModel;

//I would define the build tag here instead 
//of how it looks like in the UserVideos model
public abstract class ResponseModel {

	String responseString;
	
	public ResponseModel(String responseString) {
		this.responseString = responseString;
	}
}

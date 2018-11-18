package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
	
	static public String get() {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return timeStamp;
	}
}

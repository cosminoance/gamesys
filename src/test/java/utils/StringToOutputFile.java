package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringToOutputFile {
	String name;
	String output;
	String extension;

	public StringToOutputFile(String root, String output, String extension) {
		name = root+Timestamp.get() + extension;
		this.output = output;
	}

	// this should probably be done smarter
	// use OS specific file separator
	// also include calling method name in file name
	public void save() throws IOException {
		File f = new File("output/" + name);
		boolean saved = false;
		while (!saved) {
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				FileWriter fileWriter = new FileWriter("output/" + name);
				fileWriter.write(output);
				fileWriter.close();
				saved = true;
				System.out.println("Saved file as "+ name);
			} else {
				name += "x";
			}
		}
	}
}

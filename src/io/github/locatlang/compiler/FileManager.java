package io.github.locatlang.compiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by creeps on 02/05/15.
 */
public class FileManager {
	File path;
	public FileManager(String folderPath) {
		path = new File(folderPath);
	}

	private List<File> lcfiles = new ArrayList<File>();
	private boolean searchRunning = false;
	private void searchInDir(File dir) {
		File[] objects = dir.listFiles();
		for( File f : objects ) {
			if( f.isFile() ) {
				//checks if the file extension is .lcat
				int lastIndexOfDot = f.getName().lastIndexOf(".");
				//can't be 0, else the file would be named ".lcat"
				if( lastIndexOfDot > 0 ) {
					if ( f.getName().substring( lastIndexOfDot ).equalsIgnoreCase(".lcat") ) {
						lcfiles.add(f.getAbsoluteFile());
					}
				}
			} else if( f.isDirectory() ) {
				if ( searchRunning ) {
					searchInDir(f.getAbsoluteFile());
				}
			}
		}
	}

	public List<File> initSearch() {
		searchRunning = true;
		searchInDir(path);
		return lcfiles;
	}
	public void cancelSearch() {
		searchRunning = false;
	}

	public String[] readFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		List<String> lines = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		return lines.toArray(new String[lines.size()]);
	}
	public String[] readFile(String fpath) throws IOException {
		return readFile(new File(fpath));
	}

	public void writeFile(String path, String[] data, String encoding) throws IOException {
		PrintWriter writer = new PrintWriter(path, encoding);
		for( String line : data ) {
			writer.println(line);
		}
		writer.close();
	}
	public void writeFile(String path, String[] data) throws IOException {
		writeFile(path, data, "UTF-8");
	}
	public void writeFile(File file, String[] data, String encoding) throws IOException {
		writeFile(file.getAbsolutePath(), data, encoding);
	}
	public void writeFile(File file, String[] data) throws IOException {
		writeFile(file, data, "UTF-8");
	}
}

package io.github.locatlang.compiler;

import java.io.File;
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

	public void initSearch() {
		searchRunning = true;
		searchInDir(path);
		System.out.println("Found following files: ");
		for( File i : lcfiles ) {
			System.out.println(i.getName() + " (" + i.getAbsolutePath() + ")");
		}
	}
	public void cancelSearch() {
		searchRunning = false;
	}
}

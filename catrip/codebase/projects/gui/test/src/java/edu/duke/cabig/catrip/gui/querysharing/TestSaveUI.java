package edu.duke.cabig.catrip.gui.querysharing;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class TestSaveUI extends TestQuerySaveUI {
	private static String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryengine-2.0\\test\\resources\\simpleQuery1.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        QueryEditUI s = new QueryEditUI(getDcqlAsString());
        //s.setOpaque(true);
        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(s,BorderLayout.CENTER);
      //  frame.pack();
               frame.setSize(700, 400);
        frame.setVisible(true);
	}

	private static String getDcqlAsString() {
		File t = new File(dcqlQueryFile);
		String dcqlAsString = getContents(t);
		return dcqlAsString;
	}
	private static String getContents(File aFile) {
		//...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		//declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			//use buffering, reading one line at a time
			//FileReader always assumes default encoding is OK!
			input = new BufferedReader( new FileReader(aFile) );
			String line = null; //not declared within while loop
			/*
			 * readLine is a bit quirky :
			 * it returns the content of a line MINUS the newline.
			 * it returns null only for the END of the stream.
			 * it returns an empty String if two newlines appear in a row.
			 */
			while (( line = input.readLine()) != null){
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (input!= null) {
					//flush and close both "input" and its underlying FileReader
					input.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

}

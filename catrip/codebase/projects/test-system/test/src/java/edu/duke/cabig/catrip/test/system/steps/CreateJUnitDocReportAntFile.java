/*
 * Created on Jul 20, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.atomicobject.haste.framework.Step;

/**
 * This test case creates an ant file that will generate a JUnitDocReport.
 * @author MCCON012
 */
public class CreateJUnitDocReportAntFile
	extends Step
{
	private File antFile;
	private File xmlDir;
	private File destFile;
	private File coberturaDir;
	
	public CreateJUnitDocReportAntFile(File antFile, File xmlDir, File destFile, File coberturaDir)
	{
		super();
		
		this.antFile = antFile;
		this.xmlDir = xmlDir;
		this.destFile = destFile;
		this.coberturaDir = coberturaDir;
	}
	
	@Override
	public void runStep() throws Throwable
	{
		String projectDir = new File("..").getAbsolutePath().replace('\\', '/');
		String testReportDir = projectDir + "/" + "test-report";

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(antFile)));		
		out.println(
			"<project name=\"RunJUnitDocReportStep\" default=\"runreport\" basedir=\".\">\r\n" + 
			"	<target name=\"runreport\">\r\n" + 
			"		<taskdef name=\"junitDocReport\" classname=\"edu.duke.cabig.catrip.test.report.ant.JUnitDocReport\">\r\n" + 
			"			<classpath>\r\n" + 
			"				<fileset dir=\"" + testReportDir + "/build/jars\">\r\n" + 
			"					<include name=\"*.jar\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"				<fileset dir=\"" + coberturaDir.getAbsolutePath() + "\">\r\n" + 
			"					<include name=\"*.jar\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"				<fileset dir=\"" + new File(coberturaDir, "lib").getAbsolutePath() + "\">\r\n" + 
			"					<include name=\"*.jar\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"			</classpath>\r\n" + 
			"		</taskdef>\r\n" + 
			"\r\n" + 
			"		<junitDocReport format=\"html\" useTestType=\"true\" destfile=\"" + destFile.getAbsolutePath().replace('\\', '/') + "\">\r\n" + 
			"			<junitResults>\r\n" + 
			"				<fileset dir=\"" + xmlDir.getAbsolutePath().replace('\\', '/') + "\">\r\n" + 
			"					<include name=\"*.xml\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"			</junitResults>\r\n" + 
			"			<junitDocs>\r\n" + 
			"				<fileset dir=\"" + projectDir + "/security-password\">\r\n" + 
			"					<include name=\"src/java\"/>\r\n" + 
			"					<include name=\"test/src/java\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"				<fileset dir=\"" + projectDir + "/security-dukeidp\">\r\n" + 
			"					<include name=\"src/java\"/>\r\n" + 
			"					<include name=\"test/src/java\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"				<fileset dir=\"" + projectDir + "/test-report\">\r\n" + 
			"					<include name=\"src/java\"/>\r\n" + 
			"					<include name=\"test/src/java\"/>\r\n" + 
			"				</fileset>\r\n" + 
			"			</junitDocs>\r\n" + 
			"		</junitDocReport>\r\n" + 
			"	</target>\r\n" + 
			"</project>"
		);
		
		out.flush();
		out.close();		
	}

}

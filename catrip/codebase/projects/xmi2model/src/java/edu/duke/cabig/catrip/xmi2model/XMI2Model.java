/*
 * Created on Oct 18, 2006
 */
package edu.duke.cabig.catrip.xmi2model;

import java.io.File;

import javax.xml.namespace.QName;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class XMI2Model
{
	/**
	 * Just a main
	 */
	private XMI2Model() { super(); }
	
	/**
	 * Get the command-line options
	 */
	private static Options getOptions()
	{
		Option xmi = OptionBuilder.withArgName("xmi")
			.hasArg()
			.isRequired(true)
			.withDescription("the input xmi file")
			.create("xmi");
		
		Option model = OptionBuilder.withArgName("model")
			.hasArg()
			.isRequired(true)
			.withDescription("the output model file")
			.create("model");
		
		Options options = new Options();
		options.addOption(xmi);		
		options.addOption(model);		
		return options;
	}
	
	public static void main(String[] args) 
		throws Exception
    {
        Options options = getOptions();
        CommandLine cmd = null;
		try {			
			cmd = new BasicParser().parse(options, args);
		} catch(ParseException e) {						
			System.out.println("Error parsing arguments: " + e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("XMI2Model", options);
			System.exit(-1);
			return;
		}
		
		DomainModel model = new XMIParser().parse(new File(cmd.getOptionValue("xmi")));
		Utils.serializeDocument(cmd.getOptionValue("model"), model, new QName("extract"));
    }
}

/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import edu.duke.cabig.catrip.deid.util.RandomUtils;

public class DeIdServiceImpl
	implements DeIdService
{
	protected Random rand = new Random();
	protected String dbUrl;
	protected String user;
	protected String password;
	
	public DeIdServiceImpl() 
		throws IOException
	{
		super();

		Properties props = loadProperties();
		this.dbUrl = props.getProperty("dbUrl");
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
	}
	
	protected Properties loadProperties()
		throws IOException
	{
		Properties props = null;
		
		// try to load from axis home
		if (props == null) {
			String axis2Home = System.getenv("AXIS2_HOME");
			if (axis2Home != null) {
				props = new Properties();
				BufferedInputStream is = new BufferedInputStream(new FileInputStream(
					new File(axis2Home, "repository" + File.separator + "deid-config.properties")
				));
				props.load(is);
				is.close();
			}
		}
		
		// try to load from 
		if (props == null) {
	        Class clazz = Object.class;
			InputStream is = clazz.getResourceAsStream("/edu/duke/cabig/catrip/deid/deid-config.properties");
			if (is != null) {
				props = new Properties();
				props.load(is);
				is.close();
			}
		}
		
		if (props == null) throw new IOException("could not load deid service properties");		
		return props;
	}
	
	public DeIdServiceImpl(String dbUrl, String user, String password)
	{
		super();
		
		this.dbUrl = dbUrl;
		this.user = user;
		this.password = password;
	}
	
	public synchronized String deid(String phi) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbUrl, user, password);
		
		try {
			Statement stmt = con.createStatement();
			String val = null;
			
			ResultSet rs = stmt.executeQuery("select val from deid.deid where phi='" + phi + "'");
			if (rs.next()) {
				val = rs.getString("val");
				rs.close();
				stmt.close();
				return val;
			}
			rs.close();
			
			int checkCount = 100;
			do {
				if (--checkCount == 0) throw new Exception("unable to find a random value for your phi");
				val = RandomUtils.generateRandomValue(rand);
				rs = stmt.executeQuery("select count(id) from deid.deid where val='" + val + "'");
				rs.next();
				int count = rs.getInt(1);
				rs.close();
				if (count == 0) break;
			} while (true);
			stmt.executeUpdate("insert into deid.deid(phi,val) values ('" + phi + "','" + val + "')");
			stmt.close();
			
			return val;
		} finally {
			try { con.close(); } catch (Exception e) { }
		}
	}
}

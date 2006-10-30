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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import javax.xml.namespace.QName;

import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.wsdl.WSDLConstants;

import edu.duke.cabig.catrip.deid.util.RandomUtils;

public class DeIdServiceImpl
	implements DeIdService
{
	private MessageContext ctx;
	protected Random rand = new Random();
	protected String dbUrl;
	protected String user;
	protected String password;
	
	public DeIdServiceImpl() 
		throws IOException, ClassNotFoundException
	{
		super();

		Properties props = loadProperties();
		this.dbUrl = props.getProperty("dbUrl");
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
		
		init();
	}
	
	public DeIdServiceImpl(String dbUrl, String user, String password) 
		throws ClassNotFoundException
	{
		super();
		
		this.dbUrl = dbUrl;
		this.user = user;
		this.password = password;
		
		init();
	}
	
	protected void init() 
		throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	public void setOperationContext(OperationContext opCtx)
		throws AxisFault 
	{
		this.ctx = opCtx.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
	}
	
	protected Properties loadProperties()
		throws IOException
	{
		Properties props = null;
		
		String axisPath = "repository" + File.separator + "services" + File.separator + "DeIdService" + File.separator + "conf" + File.separator + "deid-config.properties";
		String tomcatPath = "webapps" + File.separator + "axis2" + File.separator + "WEB-INF" + File.separator + "services" + File.separator + "DeIdService" + File.separator + "conf" + File.separator + "deid-config.properties";
		
		File[] files = new File[] {
			new File(System.getProperty("user.home"), axisPath),
			new File(System.getProperty("user.home"), ".." + File.separator + axisPath),
			new File(System.getProperty("user.home"), tomcatPath),
			new File(System.getProperty("user.home"), ".." + File.separator + tomcatPath),
			new File(System.getenv("AXIS2_HOME"), axisPath),
			new File(System.getenv("AXIS2_HOME"), ".." + File.separator + axisPath),
			new File(System.getenv("CATALINA_HOME"), tomcatPath),
			new File(System.getenv("CATALINA_HOME"), ".." + File.separator + tomcatPath),
		};
		
		for (File file : files) {
			if (! file.exists()) continue;
			
			props = new Properties();
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
			props.load(is);
			is.close();
			break;
		}
		
		// try to load from classpath
		if (props == null) {
	        Class clazz = Object.class;
			InputStream is = clazz.getResourceAsStream("/edu/duke/cabig/catrip/deid/deid-config.properties");
			if (is != null) {
				props = new Properties();
				props.load(is);
				is.close();
			}
		}
		
		if (props == null) throw new IOException("could not load deid service properties (" + System.getProperty("user.dir") + ")");		
		return props;
	}
	
	public synchronized String deid(String phi) throws Exception
	{
		String user = this.user;
		String password = this.password;
		String table = null;
		try {
			if (ctx != null) {
				System.out.println(ctx.getEnvelope());
			}
			if (ctx != null && ctx.getEnvelope().getHeader() != null) {
				Iterator iter = ctx.getEnvelope().getHeader().examineAllHeaderBlocks();
				while (iter.hasNext()) {
					SOAPHeaderBlock block = (SOAPHeaderBlock) iter.next();
					if (! block.getLocalName().equals("security")) continue;
					
					user = block.getAttributeValue(new QName("user"));
					password = block.getAttributeValue(new QName("password"));
				}
			}
			table = getTable(user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, user, password);
			Statement stmt = con.createStatement();
			String val = null;
			
			ResultSet rs = stmt.executeQuery("select val from deid." + table + " where phi='" + phi + "'");
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
				rs = stmt.executeQuery("select count(id) from deid." + table + " where val='" + val + "'");
				rs.next();
				int count = rs.getInt(1);
				rs.close();
				if (count == 0) break;
			} while (true);
			stmt.executeUpdate("insert into deid." + table + "(phi,val) values ('" + phi + "','" + val + "')");
			stmt.close();
			
			return val;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
	}
	
	private String getTable(String user, String password) 
		throws SQLException
	{
		Connection con = DriverManager.getConnection(dbUrl, user, password);
		
		try {
			String tableName = null;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select tableName from deid.users where userName='" + user + "'");
			if (rs.next()) {
				tableName = rs.getString("tableName");
			}
			rs.close();
			stmt.close();
			
			if (tableName == null) {
				throw new SQLException("user " + user + " not mapped to a table");
			}
			return tableName;
		} finally {
			try { con.close(); } catch (Exception e) { }
		}
		
	}
}

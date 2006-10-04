/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class DeIdServiceImpl
	implements DeIdService
{
	public static final int RANDOM_SIZE = 40;
	public static final char[] RANDOM_CHARS = new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").toCharArray();
	
	private Random rand = new Random();
	private String dbUrl;
	private String user;
	private String password;
	
	public DeIdServiceImpl()
	{
		super();
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
				val = generateRandomValue();
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
	
	protected String generateRandomValue()
	{
		return generateRandomValue(RANDOM_SIZE);
	}
	
	protected String generateRandomValue(int len)
	{
		char[] ch = new char[len];
		for (int i = 0; i < ch.length; i++) {
			ch[i] = RANDOM_CHARS[rand.nextInt(RANDOM_CHARS.length)];
		}
		return new String(ch);
	}
}

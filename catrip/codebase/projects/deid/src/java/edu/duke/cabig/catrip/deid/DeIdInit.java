/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeIdInit
{
	public static void main(String[] args)
		throws Throwable
	{
		String dbUrl = System.getProperty("dbUrl", "jdbc:mysql://localhost/mysql");
		String user = System.getProperty("user", "root");
		String password = System.getProperty("password", "");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbUrl, user, password);

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("etc" + File.separator + "sql" + File.separator + "deid-create.sql")));
			Statement stmt = con.createStatement();
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.startsWith("#")) continue;
				stmt.executeUpdate(line);				
			}
			stmt.close();
		} finally {
			try { con.close(); } catch (Exception e) { }
		}
		System.out.println("successfully created deid database");
	}
}

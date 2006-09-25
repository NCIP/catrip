/**
 * Created on Aug 24, 2006 by PEEDI002
 */
package edu.pitt.cabig.cae.domain.general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class DBSchemaTest
{
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	/* ORACLE:
	 * gets the user tables
	 * select tname from tab where tabtype='TABLE' and tname not like 'BIN$%';
	 *
	 * gets the columns for a table
	 * SELECT TNAME,CNAME,COLTYPE,WIDTH FROM COL where tname='PARTICIPANT';
	 */

	/**
     * http://dev.mysql.com/downloads/connector/j/5.0.html
     */
	public static final String MySQL_DRIVER = "com.mysql.jdbc.Driver";
	/* MySQL:
	 * gets the user tables
	 * 		show tables;
	 * gets the columns for a table
	 *  	show columns from catissue_participant;
	 */
	
	//public static final String MSSQL_DRIVER = "lib.com.mircsoft.jdbc.sqlserver.SQLServerDriver";
	/* MS SQL:
	 * gets the user tables
	 *select @tablename=so.name from sysobjects  as so where so.type = 'U'
	 * gets the columns for a user table
	 * note: type values (38=int, 39=string, 48=boolean, 52=smallint, 111=date)
	 *  select sc.name,sc.type,sc.length from sysobjects as so 
	 *		inner join syscolumns as sc on so.id = sc.id 
	 *		where so.name = @tablename
	 * 		order by sc.type
	 */
	
	/**
	 * Whether to print out debug messages
	 */
	public static final boolean DEBUG = true;
	
	private static String server;
	private static String port;
	private static String db;
	private static String usrname;
	private static String usrpass;

	/**
	 * Constructor 
	 */
	public DBSchemaTest(String server, String port, String db, String usrname, String usrpass)
	{
	super();
	
	if (DEBUG) System.out.println("\tInside Constructor: dbSchema('"+db+"','"+usrname+"','"+usrpass+"')");
	
	if(server != null) this.server = server;
	if(port != null) this.port = port;
	this.db = db;
	this.usrname = usrname;
	this.usrpass = usrpass;
	}

	public String getMySQLConnectStr(
		String server,
		String dbName,
		String usrName,
		String usrPass
	) {
		if (DEBUG) System.out.println("\tInside getMySQLConnectStr()");
		return
			"jdbc:mysql://"+server+"/" + dbName + "?user=" + usrName + "&password=" + usrPass;
	}
	
	public String getMSSQLConnectStr(
			String server,
			String port,
			String dbName
		) {
		if (DEBUG) System.out.println("\t\tInside getMSSQLConnectStr()");
			return
				"jdbc:microsoft:sqlserver://" + server + ":" + port +
				";DatabaseName=" + dbName +
				";SelectMethod=cursor";
		}
	
	public String getORACLEConnectStr(
			String server,
			String port,
			String db,
			String usrName,
			String usrPass
		) {
		if (DEBUG) System.out.println("\t\tInside getORACLEConnectStr()");
			return
			"jdbc:oracle:thin:@" + server + ":" + port + ":" + db;
		}
	
	/**
     * Connect to database
     */
	protected Connection getConnection(
		String JDBCdriver , String ConnectStr
	)
		throws SecurityException
	{
		if (DEBUG) System.out.println("\t\tInside getConnection()");
		//Load the JDBC driver
		try {
			Class.forName(JDBCdriver);
		} catch (ClassNotFoundException e) {
			throw new SecurityException(e);
		}

		//Create a connection to the database
		try {
			return DriverManager.getConnection(ConnectStr);
		} catch (SQLException e) {
			System.out.println(ConnectStr);
			throw new SecurityException(e);
		}
	}
	
	
	/**
     * Connect to Oracle database
     */
	protected Connection getOracleConnection(
		String JDBCdriver , 
		String ConnectStr,
		String usrName,
		String usrPass
	)
		throws SecurityException
	{
		if (DEBUG) System.out.println("\t\tInside getOracleConnection()");
		//Load the JDBC driver
		try {
			Class.forName(JDBCdriver);
		} catch (ClassNotFoundException e) {
			throw new SecurityException(e);
		}

		//Create a connection to the database
		try {
			return DriverManager.getConnection(ConnectStr,usrName,usrPass);
		} catch (SQLException e) {
			System.out.println(ConnectStr);
			throw new SecurityException(e);
		}
	}

	/**
	 * List tables in db
	 */
	public String[] getTables(
		String server, String port, String db, String usrName, String usrPass
	)

	{
		int tabcnt=0;
		String curtab=null;
		Connection con;
		String[] tables=null;
		
		if (DEBUG) System.out.println("\tInside getTables()");
		
		if("152.16.7.99" .equals(server)){
			
			String ConnectStr=getORACLEConnectStr(server,port,db,usrName,usrPass);
			con = getOracleConnection(ORACLE_DRIVER ,ConnectStr,usrName,usrPass);
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
					"select tname from tab where tabtype='TABLE'" 
				);

				if (DEBUG) System.out.println("\tBack Inside getTables()");
				
				//Create and initialize Cached RowSet object.
				OracleCachedRowSet ocrs = new OracleCachedRowSet();     
				//Populate the Cached RowSet using the above Resultset.
				ocrs.populate(rs);	      
				//Move pointer to the last row in resultset.
				ocrs.last();      
				//Get the number of rows
				int rowcount = ocrs.getRow();
				if (DEBUG) System.out.println("\tTotal # of Tables are: "+rowcount);         		    			
				//Reset pointer to the beginning of the ResultSet
				ocrs.beforeFirst();
				
				tables = new String[rowcount];
				int i=0;
				
		         while (ocrs.next())
		         {
		        	++tabcnt;
		            tables[i++] = ocrs.getString(1);
		         }

				if (ocrs != null) ocrs.close();
					
				if(rs != null){
					rs.close();
					rs = null;
				}
				if(stmt != null){
					stmt.close();
					stmt = null;
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				try { con.close(); } catch (SQLException e2) { }
			}
			
		}else{
			String ConnectStr=getMySQLConnectStr(server,db,usrName,usrPass);
			con = getConnection(MySQL_DRIVER ,ConnectStr);
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
					"show tables" 
				);

				if (DEBUG) System.out.println("\tBack Inside getTables()");
				
				//Move pointer to the last row in resultset.
				rs.last(); 
				//Get the row position which is also the number of rows in the ResultSet.
				int rowcount = rs.getRow(); 
				if (DEBUG) System.out.println("\tTotal # of Tables are: "+rowcount);
				//Reset pointer to the beginning of the ResultSet
				rs.beforeFirst();
				tables = new String[rowcount];
		         while (rs.next())
		         {
		        	tables[tabcnt++] = rs.getString(1);
		         }
		         
				if(rs != null){
					rs.close();
					rs = null;
				}
				if(stmt != null){
					stmt.close();
					stmt = null;
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				try { con.close(); } catch (SQLException e2) { }
			}
		}
		return tables;
	}
	
	/**
	 * List columns in a table
	 * @return 
	 */
	public String[][] getColumns(
			String server, String port, String db, String[] tables, String usrName, String usrPass
	)

	{
		
		if (DEBUG) System.out.println("\tInside getColumns()");
		
		Connection con;
		String table;
		String[][] tablestructure;
		tablestructure = new String[1000][5];
		tablestructure[0][0]="TABNAME";
		tablestructure[0][1]="COLNAME";
		tablestructure[0][2]="COLTYPE";
		tablestructure[0][3]="COLWIDTH";
		tablestructure[0][4]="VALUE";
		int colcnt=0;
		int rowcnt=0;
		
		for (int i = 0; i < tables.length; i++) {

			System.out.println("\t\tFound Table("+i+"):  " + tables[i]);
				table = tables[i];

				if("152.16.7.99" .equals(server)){
					String ConnectStr=getORACLEConnectStr(server,port,db,usrName,usrPass);
					con = getOracleConnection(ORACLE_DRIVER ,ConnectStr,usrName,usrPass);
		
					//TODO put the query code here
					try {
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(
							"SELECT TNAME,CNAME,COLTYPE,WIDTH FROM COL where tname = '" +table+"'"
						);
						
						//Create and initialize Cached RowSet object.
						OracleCachedRowSet ocrs = new OracleCachedRowSet();     
						//Populate the Cached RowSet using the above Resultset.
						ocrs.populate(rs);	      
						//Move pointer to the last row in resultset.
						ocrs.last();      
						//Get the number of rows
						int rowcount = ocrs.getRow();
						if (DEBUG) System.out.println("\t\tTotal # of Columns are: "+rowcount);         		    			
						//Reset pointer to the beginning of the ResultSet
						ocrs.beforeFirst();
						
				         while (ocrs.next())
				         {
				        	colcnt++;
				        	rowcnt++;
							tablestructure[rowcnt][0]=ocrs.getString(1);
							tablestructure[rowcnt][1]=ocrs.getString(2);
							tablestructure[rowcnt][2]=ocrs.getString(3);
							tablestructure[rowcnt][3]=ocrs.getString(4);
							tablestructure[rowcnt][4]=null;
				         }
				         			
						if (ocrs != null) ocrs.close();
							
						if(rs != null){
							rs.close();
							rs = null;
						}
						if(stmt != null){
							stmt.close();
							stmt = null;
						}
						con.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
						try { con.close(); } catch (SQLException e2) { }
					}
		
				}else{
					String ConnectStr=getMySQLConnectStr(server,db,usrName,usrPass);
					con = getConnection(MySQL_DRIVER ,ConnectStr);
		
					try {
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(
							"show columns from "+table 
						);
						
						//Move pointer to the last row in resultset.
						rs.last(); 
						//Get the row position which is also the number of rows in the ResultSet.
						int rowcount = rs.getRow(); 
						if (DEBUG) System.out.println("\t\tTotal # of Columns are: "+rowcount);
						//Reset pointer to the beginning of the ResultSet
						rs.beforeFirst();

						String type=null;
						String width=null;
				         while (rs.next())
				         {
					       	colcnt++;
					       	rowcnt++;
					       	
					       	//parse the type and width into 2 seperate columns
				            int eptr = rs.getString(2).indexOf("(");
							if (eptr > 0) {
								type = rs.getString(2).substring(0, eptr);
								width = rs.getString(2).substring(eptr+1,rs.getString(2).length()-1);
							}else{
								type = rs.getString(2);
							}
							tablestructure[rowcnt][0]=table;
							tablestructure[rowcnt][1]=rs.getString(1);
							tablestructure[rowcnt][2]=type;
							tablestructure[rowcnt][3]=width;
							tablestructure[rowcnt][4]=null;
				         }
						
						if(rs != null){
							rs.close();
							rs = null;
						}
						if(stmt != null){
							stmt.close();
							stmt = null;
						}
						con.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
						try { con.close(); } catch (SQLException e2) { }
					}
				}
		}
		
		return tablestructure;
	}
	
	public static void main(String[] args)
	throws Exception
	{
		if (DEBUG) System.out.println("Inside main()");

		if (DEBUG) System.out.println("");		
		
		//testing mysql
		//dbSchema sc = new dbSchema("localhost",null,"catissuecore","catissue_core","catissue_core");

		//testing oracle
		DBSchemaTest sc = new DBSchemaTest("152.16.7.99","1521","TRIP","caedba","cae");

		String[] Tables = sc.getTables(server,port,db,usrname,usrpass);
		if (DEBUG) System.out.println("\tBack Inside main()");
		//System.out.println("\tCalling 'getColumns' for Table: "+curTable);
		String[][] tablearr=sc.getColumns(server,port,db,Tables,usrname,usrpass);
		
		int arrsize=tablearr.length;
		System.out.println("\t\t\tArrsize: " + arrsize);
		
        //print headings
        for (int col=0; col<4; col++) {
       	 System.out.print("\t\t\t" + tablearr[0][col]);
        }
        System.out.println("\t\t\t" + tablearr[0][4]);
        
        //print fields
        for (int row=1; row<arrsize; row++) {
        	if(tablearr[row][0].equals(null)) {
        		break;
        	}else{
	       	    for (int col=0; col<4; col++) {
	       	    	System.out.print("\t\t\t" + tablearr[row][col]);
	       	    }
	       	    System.out.println("\t\t\t" + tablearr[row][4]);
        	}
       	}
		
		if (DEBUG) System.out.println("\tBack Inside main()");

		if (DEBUG) {
			System.out.println("");
			System.out.println("...End of Testing...");
			
		}
	}
}
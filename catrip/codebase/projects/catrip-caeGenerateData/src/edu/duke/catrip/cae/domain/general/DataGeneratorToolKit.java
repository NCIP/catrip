/**
 * Created on Aug 24, 2006 by PEEDI002
 */
package edu.duke.catrip.cae.domain.general;

//my import statements
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.Random;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.catrip.datagenerator.HibernateUtil;

//import edu.duke.catrip.cae.util.HibernateUtil;

public class DataGeneratorToolKit extends TestCase
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
	
	/**
	 * Constructor 
	 */
	public DataGeneratorToolKit()
	{
	super();
	
	if (DEBUG) System.out.println("\tInside Constructor: DataGeneratorToolKit()");

	}

	//reads data file into a 2D array
	public String[][] Read2DFile(int maxrecs, String inFile) throws Exception {

		final boolean DEBUG = true;
		
		String[][] datatable;
		datatable = new String[maxrecs][2];
		int insrow=-1;
		int inscol=-1;
		int reccnt=0;

		if (DEBUG) System.out.println("Inside Read2DFile()...");

		if(FileExists(inFile)) {
			//read file
			RandomAccessFile FilePtr = null;
			FilePtr = new RandomAccessFile(inFile, "rw");
			String instr = "";
			while ((instr = FilePtr.readLine()) != "") {
				String field1 = "";
				String field2 = "";
				reccnt++;
				insrow++;
				inscol=0;
				if(reccnt>maxrecs) break;
				char getchar= 0;
				for (int i = 0; i < instr.length(); i++) {
					getchar=instr.charAt(i);
					if(getchar == (',')) {
						//get the gender
						for (int j = i+1; j < instr.length(); j++) {
							getchar=instr.charAt(j);
							field2 = field2+getchar;
						}
						break;
					}else{
						//get the first name
						if(getchar != (' ')) 
							field1 = field1+getchar;
					}
				}
				System.out.println("insrow = "+insrow+", field1 = "+field1+" and field2 = "+field2);
				datatable[insrow][inscol]=field1;
				inscol++;
				datatable[insrow][inscol]=field2;
			}
			FilePtr.close();
		}
		return datatable;
	}
	
	//reads data file into a scaler array
	public String[] ReadFile(int maxrecs, String inFile) throws Exception {

		final boolean DEBUG = true;
		
		String[] datatable;
		datatable = new String[maxrecs];
		int insrow=-1;
		int reccnt=0;
		RandomAccessFile FilePtr = null;

		if (DEBUG) System.out.println("Inside ReadFile()...");
		if (DEBUG) System.out.println("infile="+inFile);

		if(FileExists(inFile)) {
			//read file
			FilePtr = new RandomAccessFile(inFile, "rw");
			String instr = null;
			String field1 = "";
			while (((instr = FilePtr.readLine()) != null)) {
				if(instr.equals(null)) break;
				System.out.println("instr="+instr);
				reccnt++;
				if(reccnt>maxrecs) break;
				char getchar= 0;
				field1 = "";
				int strlen=instr.length();
				if(strlen>0){
					for (int i = 0; i < strlen; i++) {
						getchar=instr.charAt(i);
						field1 = field1+getchar;
					}
					System.out.println("field1 = "+field1);
					insrow++;
					datatable[insrow]=field1;
				}
			}
			FilePtr.close();
			System.out.println("Finished reading inFile="+inFile);
		}else{
			System.out.println("File "+inFile+" does not exists");
		}
		return datatable;
	}
	
	public void buildDataFile(String infile, int min, int max) throws ParseException, IOException

	{
		
		if (DEBUG) System.out.println("\tInside buildDataFile()");
				
		if(!FileExists(infile)) {
			String writestr;
			String LF = "\n";
			String CR = "\r";
			java.io.File Newfile = new File(infile);
			Newfile.createNewFile();
			FileChannel fc=new RandomAccessFile(infile,"rw").getChannel();
			for (int rowcnt = 0; rowcnt < 2000; rowcnt++) {
				writestr=randomInRange(min, max)+CR+LF;
				//write line to log file
				fc.position(fc.size());			//move to end
				fc.write(ByteBuffer.wrap(writestr.getBytes()));
			}
			fc.close();
		}

 
	}
	
	
	//build new file from another file
	public void buildDataFile(int maxrecs, String infile, String outfile) throws ParseException, IOException

	{
		int maxcnt=1000;
		if (DEBUG) System.out.println("\tInside buildDataFile()");
		if (DEBUG) System.out.println("\tinfile="+infile);
		if (DEBUG) System.out.println("\toutfile="+outfile);
		
		String[] inparr;// = new String[maxcnt];
				
		try {
			inparr=ReadFile(maxcnt,infile);
		
			int arrsiz=getsize(inparr);
			
			int reccnt=-1;
			if (DEBUG) System.out.println("\treccnt="+reccnt);
			
			if(FileExists(outfile)) FileDelete(outfile);
			
			if(!FileExists(outfile)) {
				String writestr;
				String LF = "\n";
				String CR = "\r";
				java.io.File Newfile = new File(outfile);
				Newfile.createNewFile();
				FileChannel fc=new RandomAccessFile(outfile,"rw").getChannel();
				for (int rowcnt = 0; rowcnt < maxcnt; rowcnt++) {
					reccnt++;
					if(inparr[reccnt]==null){
						reccnt=0;
					}
					writestr=inparr[randomInRange(0,arrsiz)]+CR+LF;
					System.out.println("writestr: "+writestr); 
					//write line to log file
					fc.position(fc.size());			//move to end
					fc.write(ByteBuffer.wrap(writestr.getBytes()));
				}
				fc.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//determines if file exists
	public boolean FileExists(String chkFile) {
		java.io.File file = new File(chkFile);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	//determines if file exists
	public void FileDelete(String delFile) {
		java.io.File file = new File(delFile);
		if (file.exists()) {
			file.delete();
		}
	}

	//removes chars that equal chr
	public String removeChar(String str, char chr) {

		String newstr="";
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != chr){
				newstr=newstr+str.charAt(i);
			}
		}
		
		return newstr;
	}
	
	//removes trailing chars that equal chrs
	public String removeChars(String str, String chrs) {

		String newstr="";
		
		int end = str.indexOf(chrs);
		if(end >0){
			newstr = str.substring(0, end);;
		}
		
		return newstr;
	}
	
	//insert data object into db
	public static void create(Object obj) {
		System.out.println("Inside create()...");
		Transaction tx = null;
		System.out.println("Inside create() - before: HibernateUtil.currentSession...");
		Session session = HibernateUtil.currentSession();
		System.out.println("Inside create() - before try...");
		try {
			System.out.println("Inside create() - session.beginTransaction...");
			tx = session.beginTransaction();
			System.out.println("Inside create() - session.save...");
			session.save(obj); 
			System.out.println("Inside create() - tx.commit...");
			tx.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null )
				tx.rollback();
			assertTrue(false);
		}
	}
	
	public int randomInRange(int min, int max) {
	    if (min >= max) {
	      StringBuilder message = new StringBuilder();
	      message.append("Lower limit (");
	      message.append(min);
	      message.append(") must be lower than Upper limit (");
	      message.append(max);
	      message.append(")");
	      throw new IllegalArgumentException(message.toString());
	    }

	    Random generator = new Random();
	    // get the range, casting to long to avoid overflow problems
	    long range = (long)max - (long)min + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * generator.nextDouble());
	    return (int)(fraction + min);
	  }
	
	public int getsize(String[] arr) {
		int arrsiz=arr.length;
		int reccnt=-1;
		
		for (int rowcnt = 0; rowcnt < arrsiz; rowcnt++) {
			
			if(arr[rowcnt]!=null){
				reccnt++;
			}else{
				break;
			}
		}
		return reccnt;
	}

}
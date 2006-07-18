/*
 * Created on Jul 18, 2006
 */
package edu.duke.cabig.catrip.security.password;

import java.io.File;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class SecurePasswordTest
	extends TestCase
{
	public SecurePasswordTest(String name)
	{
		super(name);
	}
	
	public void testGenerateTempKeys() 
		throws Exception
	{
		SecurePassword.generateTempKeys();
		File dir = new File("build" + File.separator + "keys");
		assertTrue(new File(dir, "public.key").exists());
		assertTrue(new File(dir, "private.key").exists());
	}
	
	public void testSecurePasswordGenerated() 
		throws Exception
	{
		File publicKey = File.createTempFile("public", ".key");
		File privateKey = File.createTempFile("private", ".key");
		File file = File.createTempFile("password", ".bin");
		publicKey.delete();
		privateKey.delete();
		file.delete();

		try {
			SecurePassword.generateKeys(publicKey, privateKey);
						
			String passwordText = "my password is cool";
			SecurePassword password = new SecurePassword();
			password.encrypt(passwordText, file);
			assertTrue(file.exists());
			assertEquals(passwordText, password.decrypt(file));
		} finally {
			publicKey.delete();
			privateKey.delete();
			file.delete();
		}
	}
	
	public void testSecurePassword() 
		throws Exception
	{
		File file = File.createTempFile("password", ".bin");
		file.delete();
		try {
			String passwordText = "my password is cool";
			SecurePassword password = new SecurePassword();
			password.encrypt(passwordText, file);
			assertTrue(file.exists());
			assertEquals(passwordText, password.decrypt(file));
		} finally {
			file.delete();
		}
	}
	
	/**
	 * Run the tests
	 * @param args ignored
	 */
	public static void main(String[] args)
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(SecurePasswordTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}

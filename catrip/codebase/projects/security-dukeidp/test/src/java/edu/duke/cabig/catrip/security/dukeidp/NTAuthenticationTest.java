/*
 * Created on May 22, 2006
 */
package edu.duke.cabig.catrip.security.dukeidp;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;

import javax.security.auth.login.LoginException;

import edu.duke.cabig.catrip.security.password.SecurePassword;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class NTAuthenticationTest
	extends TestCase
{
	public NTAuthentication ntAuth;
	
	public NTAuthenticationTest(String name)
	{
		super(name);
		
		ntAuth = new NTAuthentication(
			System.getProperty("domainController.address", "127.0.0.1")
		);
	}
	
	public void testInvalidCredentials()
		throws UnknownHostException
	{
		LoginException exception = null;
		boolean success = true;
		try {
			success = ntAuth.authenticate("DummyDomain", "DummyUser", "DummyPassword");
		} catch (LoginException e) {
			exception = e;
		} catch (UnknownHostException e) {
			throw e;
		}
		assertNull(exception);
		assertFalse(success);
	}
	
	public void testValidCredentials()
		throws IOException, GeneralSecurityException
	{
		File passwordDir = new File(System.getProperty("password.dir",
			"test" + File.separator + "resources" + File.separator + "passwords"
		));
		String domain = System.getProperty("password.domain", "canctr");

		File[] passwordFiles = passwordDir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile() && ! file.getName().contains(".");
			}
		});
		for (File passwordFile : passwordFiles) {
			SecurePassword sp = new SecurePassword();
			String name = passwordFile.getName();
			String password = sp.decrypt(passwordFile);
			
			LoginException exception = null;
			try {
				ntAuth.authenticate(domain, name, password);
			} catch (LoginException e) {
				exception = e;
			} catch (UnknownHostException e) {
				throw e;
			}
			assertNull(exception);
		}		
	}
	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(NTAuthenticationTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}

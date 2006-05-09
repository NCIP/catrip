import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This is a dummy class for testing the project management framework.  
 * It should be removed for a "production" development environment.
 * @author Patrick McConnell
 */
public class GUIDummyClassTest
	extends TestCase
{
	/**
	 * Construct a new test case
	 * @param name the name of the test case - usually the name of the class
	 */
	public GUIDummyClassTest(String name)
	{
		super(name);
	}
	
	/**
	 * Test dummyMethod of the DummyClass
	 */
	public void testDummyMethod()
	{
		assertEquals("this is dumb", new GUIDummyClass().dummyMethod());
	}
	
	/**
	 * Run the tests
	 * @param args ignored
	 */
	public static void main(String[] args)
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(GUIDummyClassTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}

The security-password project provides support for key generation and PKI encryption 
for projects that need to encrypt passwords.

Copy the grid-ca.jar into your lib directory.

Import security-password.jars and security-password.antfiles into your project.  For example:

	<target name="build-security-dukeidp" depends="prepare" description="Builds the security-dukeidp project">
		<resolveDependencies extdir="${projects.dir}/security-dukeidp/ext">
			<artifact refid="common.test.jars" />
			<artifact refid="security-password.jars" />
			<artifact refid="security-password.antfiles" />
		</resolveDependencies>
		<ant dir="${projects.dir}/security-dukeidp" inheritAll="false" antfile="build.xml" target="all" />
	</target>

Don't forget to run ant build from the top after adding the artifacts.

You should create the following directory structure in your project:

	test/resources/
		keys/
		passwords/

Generate keys:

	ant -f ext\antfiles\password-utils.xml keygen

Encrypt a password:

	ant -f ext\antfiles\password-utils.xml encrypt

These two steps will create the following:

	test/resources/
		keys/
			private.key   (do NOT add to CVS)
			public.key    (add to CVS)
		passwords/
			[username]    (add to CVS)

where [username] is the name of the user entered in the encryption step.

You can now use the SecurePassword API:

	SecurePassword sp = new SecurePassword();
	String password = sp.decrypt("[username]");

Some resources:

	http://www.phptr.com/articles/article.asp?p=170967&seqNum=4&rl=1
	http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/crypto.html
	http://java.sun.com/developer/JDCTechTips/2004/tt0116.html#1
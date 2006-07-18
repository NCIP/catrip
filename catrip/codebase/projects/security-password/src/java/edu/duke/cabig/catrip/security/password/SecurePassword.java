/*
 * Created on Jul 18, 2006
 */
package edu.duke.cabig.catrip.security.password;

import gov.nih.nci.cagrid.gridca.common.KeyUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class SecurePassword
{
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private Cipher cipher;
	
	public SecurePassword() 
		throws IOException, GeneralSecurityException
	{
		this(
			KeyUtil.loadPublicKey(new File("test" + File.separator + "resources" + File.separator + "keys", "public.key")),
			KeyUtil.loadPrivateKey(new File("test" + File.separator + "resources" + File.separator + "keys", "private.key"), null)
		);
	}
	
	public SecurePassword(PublicKey publicKey, PrivateKey privateKey) 
		throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException
	{
		super();
		
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.cipher = getCipher();
	}
	
	public void encrypt(String password, String userName) 
		throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		encrypt(password, new File("test" + File.separator + "resources" + File.separator +  "passwords" + File.separator + userName));		
	}
	
	public void encrypt(String password, File outFile) 
		throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		//String xform = "RSA/NONE/PKCS1PADDING";
		byte[] bytes = encrypt(password.getBytes(), publicKey);

		FileOutputStream os = new FileOutputStream(outFile);
		os.write(bytes);
		os.flush();
		os.close();
	}

	public static void generateKeys() 
		throws Exception
	{
		File dir = new File("test" + File.separator + "resources" + File.separator +  "keys");
		dir.mkdirs();
		generateKeys(
			new File(dir, "public.key"),
			new File(dir, "private.key")
		);
	}
	
	public String decrypt(String userName) 
		throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		return decrypt(new File("test" + File.separator + "resources" + File.separator +  "passwords" + File.separator + userName));
	}
	
	public String decrypt(File inFile) 
		throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		byte[] bytes = new byte[(int) inFile.length()];
		FileInputStream is = new FileInputStream(inFile);
		if (is.read(bytes) != bytes.length) throw new IOException("did not read entire file");
		is.close();

		//String xform = "RSA/NONE/PKCS1PADDING";
		bytes = decrypt(bytes, privateKey);
		return new String(bytes);
	}
	
	public static void generateTempKeys()
		throws Exception
	{
		File dir = new File("build" + File.separator + "keys");
		dir.mkdirs();
		generateKeys(
			new File(dir, "public.key"),
			new File(dir, "private.key")
		);
	}
	
	public static void generateKeys(File publicKey, File privateKey) 
		throws Exception 
	{
		KeyPair pair = KeyUtil.generateRSAKeyPair1024();
		KeyUtil.writePublicKey(pair.getPublic(), publicKey);
		KeyUtil.writePrivateKey(pair.getPrivate(), privateKey);
	}
	
	public static PublicKey readPublicKey(File file) 
		throws IOException, GeneralSecurityException
	{
		return KeyUtil.loadPublicKey(file);
	}
	
	public static PrivateKey readPrivateKey(File file) 
		throws IOException, GeneralSecurityException
	{
		return KeyUtil.loadPrivateKey(file, null);
	}

	protected Cipher getCipher() 
		throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException
	{
		return Cipher.getInstance("RSA/None/OAEPPadding", "BC");
	}
	
	protected byte[] encrypt(byte[] inpBytes, PublicKey key) 
		throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		//Cipher cipher = Cipher.getInstance(xform);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(inpBytes);
	}
	
	protected byte[] decrypt(byte[] inpBytes, PrivateKey key) 
		throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		//Cipher cipher = Cipher.getInstance(xform);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(inpBytes);
	}
	
	public static void main(String[] args) 
		throws Exception
	{
		if (args.length == 1 && args[0].equals("-keygen")) {
			generateKeys();
		} else if (args.length == 3 && args[0].equals("-encrypt")) {
			new SecurePassword().encrypt(args[1], new File(args[2]));
		} else {
			System.out.println("Usage:");
			System.out.println("  SecurePassword -keygen");
			System.out.println("  SecurePassword -encrypt password file");
		}		
	}
}

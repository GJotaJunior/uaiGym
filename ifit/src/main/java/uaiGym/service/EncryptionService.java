package uaiGym.service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionService {

	private static String symmetricKey = "012uai345Gym789+";
	private static SecretKey key = new SecretKeySpec(symmetricKey.getBytes(), "AES");

	public static String linkGenerator(Integer id) {
		String url = "/redefinir-senha?id=" + encrypt(id.toString());

		return url;
	}

	private static String encrypt(String argument) {
		byte[] message = null;

		try {
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, key);
			message = cipher.doFinal(argument.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message.toString();
	}
	
	public static String decrypt(String argument) {
		byte[] message = null;
		
		try {
			Cipher cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.DECRYPT_MODE, key);
			message = cipher.doFinal(argument.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message.toString();
	}

}

//http://clubedosgeeks.com.br/programacao/java/java-criptografia-e-descriptografia-com-aes-chave-assimetrica
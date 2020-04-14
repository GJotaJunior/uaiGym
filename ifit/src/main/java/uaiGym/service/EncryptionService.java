package uaiGym.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EncryptionService {

	public static String linkGenerator(Integer id) throws UnsupportedEncodingException {
		String url = "/redefinir-senha?id=" + encrypt(id.toString());

		return url;
	}

	private static String encrypt(String argument) throws UnsupportedEncodingException {
		return URLEncoder.encode(argument, StandardCharsets.UTF_8.toString());
	}
	
	public static String decrypt(String argument) throws UnsupportedEncodingException {
		return URLDecoder.decode(argument, StandardCharsets.UTF_8.toString());
	}

}
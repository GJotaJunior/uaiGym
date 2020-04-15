package uaiGym.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EncryptionService {

	// TODO implementar um algoritmo de criptografar

	private static final String AFTER_ID = "15878645431";
	private static final String BEFORE_ID = "983456873132";

	public static String linkGenerator(Integer id) throws UnsupportedEncodingException {
		String url = "/redefinir-senha?id=" + encrypt(id.toString());

		return url;
	}

	public static String encrypt(String argument) throws UnsupportedEncodingException {
		return AFTER_ID + URLEncoder.encode(argument.toString(), StandardCharsets.UTF_8.toString()) + BEFORE_ID;
	}

	public static String decrypt(String argument) throws UnsupportedEncodingException {
		return URLDecoder.decode(argument.toString(), StandardCharsets.UTF_8.toString()).replace(AFTER_ID, "")
				.replace(BEFORE_ID, "");
	}

}
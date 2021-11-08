package com.kh.mvc.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;


public class MvcUtils {

	/**
	 * 비밀번호 암호화 처리 메소드
	 * 
	 * 1. 암호화 처리 MessageDigest
	 * 
	 * 2. 암호화된 이진배열 문자열 인코딩 처리 Base64Encoder
	 * @param parameter
	 * @return
	 */
	public static String getEncyptedPassword(String password) {
		// TODO Auto-generated method stub
		
		// 1. 암호화처리
		byte[] encrypted = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] plain = password.getBytes("utf-8");
			md.update(plain); // md객체에 원본 문자열 설정
			encrypted = md.digest(); // 암호화
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new String(encrypted));

		// 2. 인코딩처리
		// 영문자, 숫자, +, / 로 이루어진 64개의 문자를 사용 (=는 패딩문자다)
		Encoder encoder = Base64.getEncoder();
		String encryptedPassword = encoder.encodeToString(encrypted);
		
		System.out.println(encryptedPassword);

		return encryptedPassword;
	}

}

package com.onkarwaman.sms.encryption;

import java.io.IOException;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/decrypt")
public class Decryption extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			String encryptedUsername = null,encryptedPassword=null;
			Cookie ck[] = req.getCookies();	
			for(Cookie c:ck) {
				if(c.getName().equals("username")){
					encryptedUsername = c.getValue();
				}
				else if(c.getName().equals("password")){
					encryptedPassword = c.getValue();
				}
			}
					
			try {
				String key=req.getParameter("passTextField");
				String decryptedUsername = decrypt(key, encryptedUsername);
				String decryptedPassword = decrypt(key, encryptedPassword);
				req.setAttribute("decryptedUsername", decryptedUsername);
				req.setAttribute("decryptedPassword", decryptedPassword);
			} catch (Exception e) {
				// let jvm do its work
			}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	public static String decrypt(String key, String encryptedText) throws Exception {
		while(key.length() < 16) {
			key = key.concat(key);
		}
		key = key.substring(0, 16);
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return new String(decryptedBytes);
	}

}

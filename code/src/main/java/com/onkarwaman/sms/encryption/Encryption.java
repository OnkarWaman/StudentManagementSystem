package com.onkarwaman.sms.encryption;

import java.io.IOException;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/encrypt")
public class Encryption extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			String username = req.getParameter("userTextField");
			String password = req.getParameter("passTextField");
			
			try {
				String encryptedUsername = encrypt(password, username);
				String encryptedPassword = encrypt(password, password);
				req.setAttribute("encryptedUsername", encryptedUsername);
				req.setAttribute("encryptedPassword", encryptedPassword);
			} catch (Exception e) {
				// let jvm do its work
			}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	private static String encrypt(String key, String text) throws Exception {
		while(key.length() < 16) {
	        key = key.concat(key);
	    }
	    key = key.substring(0, 16);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

}

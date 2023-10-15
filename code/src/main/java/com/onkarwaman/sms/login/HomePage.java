package com.onkarwaman.sms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@WebServlet("/homepage")
public class HomePage extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		if(req.getSession().getAttribute("isLogined")==null&&req.getParameter("userTextField")==null) {
			res.sendRedirect("loginpage");
		}
		else {
			res.setContentType("text/html");
		    PrintWriter pw = res.getWriter();
		   
		    Connection con = null;
			try {
				 ServletContext sc = getServletContext();
				 String DBurl = sc.getInitParameter("DBurl");
				 String DBusername = sc.getInitParameter("DBusername");
				 String DBpassword = sc.getInitParameter("DBpassword");
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
				 PreparedStatement pstmt = con.prepareStatement("select count(*) from sms_admins where username=? and binary password=?");
				 /* defaultly MySql checks columns by case-insensitivity
				  * but we want case-insensitivity for only username column
				  * and case-sensitivity for password column.
				  * So solution is that username will defaultly checked by case-insensitivity
				  * and to apply case sensitivity to password column, we have added the keyword "binary" before password
				  */
				 pstmt.setString(1, req.getParameter("userTextField"));
				 pstmt.setString(2, req.getParameter("passTextField"));
				 ResultSet rs = pstmt.executeQuery();
				 rs.next();
				 if(rs.getInt("count(*)") == 1) {
					 req.getSession().setAttribute("isLogined", true);
				 }
				 else {
					req.getSession().setAttribute("isLogined", false);
				 }
			} catch (Exception e) {
				pw.println("""
						<title>Status Page</title>
						<h1>Something went wrong, Try again later</h1>
						""");
			}
			
			
			// This if else block is needed on each page to verify authenticity of user
			if((boolean) req.getSession().getAttribute("isLogined")) {
				HttpSession hs = req.getSession();
				hs.setAttribute("conObject", con);
			
		        pw.println("""
		        		<title>Home Page</title>
				    		<form action="logout" method="post">
								<input type="submit" value="Log Out">
								<br>
							</form>
				    	<br>
		        		<h1><p id="time"></p></h1>
						<script>
						    updateTime();
						    function updateTime(){
						        document.getElementById("time").innerHTML = Date().replace("GMT+0530 (India Standard Time)","IST");
						    }
						    setInterval(updateTime,1000);
						</script>
		        		<br>
		        		<br>
		        		<a href="insertpage">Add New Student</a>
		        		<br>
		        		<br>
		        		<a href="updatepage1">Update existing Student</a>
		        		<br>
		        		<br>
		        		<a href="deletepage">Delete a Student</a>
		        		<br>
		        		<br>
		        		<a href="deleteallpage">Delete ALL Students</a>
		        		<br>
		        		<br>
		        		<a href="viewall">View All Student</a>
		        		<br>
		        		<br>
		        		<a href="viewonepage">View specific Student</a>
		        		  """);
		        
		        RequestDispatcher rd = req.getRequestDispatcher("/encrypt");
		        rd.include(req, res);
		        Cookie c1 = new Cookie("username", (String) req.getAttribute("encryptedUsername"));
		        Cookie c2 = new Cookie("password", (String) req.getAttribute("encryptedPassword"));
		        res.addCookie(c1);
		        res.addCookie(c2);
		        
			}
			else if(!(boolean) req.getSession().getAttribute("isLogined")&&req.getParameter("userTextField")!=null) {
				pw.println("""
						<title>Status Page</title>
						<h1>Inavalid Username or password</h1>
						""");
			}
		}
		
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}	
}
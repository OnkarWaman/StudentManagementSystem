package com.onkarwaman.sms.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updatepage2")
public class UpdatePage2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			HttpSession hs = req.getSession();
			hs.setAttribute("ugidOfUpdateStudent", req.getParameter("idTextField"));
			try {
				Connection con =(Connection) hs.getAttribute("conObject");
				PreparedStatement pstmt = con.prepareStatement("select * from sms_students where ugid=?");	
				pstmt.setInt(1, Integer.parseInt(req.getParameter("idTextField")));
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					pw.println("""
							<!DOCTYPE html>
							<html>
							<head>
							<meta charset="ISO-8859-1">
							<title>Update Page-2</title>
							</head>
							<body>
							<h1>Update Info of a student</h1>
							<br>
							<br>
							<form action="updatelogic" method="post">
							Name: <input type="text" name="nameTextField" value="%s" required autocomplete="off">
							<br>
							<br>
							Branch: <input type="text" name="branchTextField" value="%s" required autocomplete="off">
							<br>
							<br>
							DOB: <input type="date" name="dobTextField" value="%s" required autocomplete="off">
							<br>
							<br>
							Mobile No: <input type="tel" maxlength="10" name="mobileTextField" value="%s" required autocomplete="off">
							<br>
							<br>
							Email: <input type="email" name="emailTextField" value="%s" required autocomplete="off">
							<br>
							<br>
							Address: <input type="text" name="addressTextField" value="%s" required autocomplete="off">
							<br>
							<br>
							<input type="reset">&nbsp
							<input type="submit" value="Update">
							</form>
							</body>
							</html>
							""".formatted(rs.getString("name"),rs.getString("branch"),rs.getDate("dob").toString(),rs.getString("mobileno"),rs.getString("email"),rs.getString("address")));
				}
				else {
					pw.println("""
							<title>Status Page</title>
							<h1>Student not found...!</h1>
							 """);
				}
			}
			catch (Exception e) {
				pw.println("""
						<title>Status Page</title>
						<h1>Something went wrong, Try again later</h1>
						""");
			}
		}
	}
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

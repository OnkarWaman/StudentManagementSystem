package com.onkarwaman.sms.insert;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/insertpage")
public class InsertPage extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			pw.println("""
					<!DOCTYPE html>
					<html>
					<head>
					<meta charset="ISO-8859-1">
					<title>Insert Page</title>
					</head>
					<body>
					<h1>Add Student to System</h1>
					<br>
					<br>
					<form action="insertlogic" method="post">
					Name: <input type="text" name="nameTextField" required autocomplete="off">
					<br>
					<br>
					Branch: <input type="text" name="branchTextField" required autocomplete="off">
					<br>
					<br>
					DOB: <input type="date" name="dobTextField" required autocomplete="off">
					<br>
					<br>
					Mobile No: <input type="tel" maxlength="10" name="mobileTextField" required autocomplete="off">
					<br>
					<br>
					Email: <input type="email" name="emailTextField" required autocomplete="off">
					<br>
					<br>
					Address: <input type="text" name="addressTextField" required autocomplete="off">
					<br>
					<br>
					<input type="reset">&nbsp
					<input type="submit" value="Add Student">
					</form>
					</body>
					</html>
					""");
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}

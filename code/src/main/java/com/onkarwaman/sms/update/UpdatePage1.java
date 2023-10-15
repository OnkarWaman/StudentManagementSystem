package com.onkarwaman.sms.update;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatepage1")
public class UpdatePage1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			pw.println("""
					<!DOCTYPE html>
					<html>
					<head>
					<meta charset="ISO-8859-1">
					<title>Update Page-1</title>
					</head>
					<body>
					<h1>Update Info of a student</h1>
					<br>
					<br>
					<form action="updatepage2" method="post">
					Enter student id: <input type="number" name="idTextField" required autocomplete="off">
					<br>
					<br>
					<input type="reset">&nbsp
					<input type="submit" value="next">
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

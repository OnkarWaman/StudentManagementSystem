package com.onkarwaman.sms.deleteone;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeletePage
 */
@WebServlet("/deletepage")
public class DeletePage extends HttpServlet {
	
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
					<title>Delete Page</title>
					</head>
					<body>
					<h1>Delete a student from system</h1>
					<br>
					<br>
					<form action="deletelogic" method="post">
					Enter student id: <input type="number" name="idTextField" required autocomplete="off">
					<br>
					<br>
					<input type="reset">&nbsp
					<input type="submit" value="Delete Student">
					</form>
					</body>
					</html>
					""");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}

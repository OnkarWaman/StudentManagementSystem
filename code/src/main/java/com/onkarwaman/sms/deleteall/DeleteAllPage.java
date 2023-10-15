package com.onkarwaman.sms.deleteall;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteallpage")
public class DeleteAllPage extends HttpServlet {
	
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
								<title>Delete All Page</title>
							</head>
							<body>
								
								<div><h1 style="color:red;">This operation is non-reversible 
								and cannot be undone once commited</h1></div>
								<div><h1 style="color:red;">Do you really want to Delete All students data</h1></div>
								<h1 style="color:red;">If YES, then enter username and password to confirm</h1>
								<br>
								<br>
									<form action="deletealllogic" method="post">
						               Username: <input type="text" name="userTextField" required autocomplete="off">
						               <br>
						               <br>
						               Password: <input type="password" name="passTextField" required autocomplete="off">
						               <br>
						               <br>
						               <input type="reset" value="clear">&nbsp &nbsp
						               <input type="submit" value="Confirm Delete">
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

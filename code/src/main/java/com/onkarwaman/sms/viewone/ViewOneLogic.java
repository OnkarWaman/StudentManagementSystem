package com.onkarwaman.sms.viewone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewonelogic")
public class ViewOneLogic extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			HttpSession hs = req.getSession();
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
								<title>Student details Page</title>
								<style>
									table {
										border-collapse: collapse;
									}
									th, td {
										border: 1px solid black;
										padding: 8px;
									}
								</style>
							</head>
							<body>
								<h1>Student details</h1>
								<br>
								<table>
									<tr>
										<th>UGID</th>
										<th>Name</th>
										<th>Branch</th>
										<th>DOB</th>
										<th>Mobile No.</th>
										<th>Email id</th>
										<th>Address</th>
									</tr>
									<tr>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
									</tr>
									</table>
							</body>
							</html>
							""".formatted(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				}
				else {
					pw.println("""
							<title>Status Page</title>
							<h1>Student not found...!</h1>
							""");
				}
					
			} catch (Exception e) {
				pw.println("""
						<title>Status Page</title>
						<h1>Something went wrong, Try again later</h1>
						""");
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}

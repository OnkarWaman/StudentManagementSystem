package com.onkarwaman.sms.viewall;

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

@WebServlet("/viewall")
public class ViewAll extends HttpServlet {

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
				PreparedStatement pstmt = con.prepareStatement("select * from sms_students");	
				ResultSet rs = pstmt.executeQuery();
				pw.println("""
						<!DOCTYPE html>
							<html>
							<head>
								<title>All Students Details Page</title>
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
								<h1>All Students Details</h1>
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
						""");
				while(rs.next()) {
					pw.println("""
									<tr>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
									</tr>
							""".formatted(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				}
				pw.println("""
							</table>
							</body>
							</html>
						""");
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
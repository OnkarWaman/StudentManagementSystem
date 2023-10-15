package com.onkarwaman.sms.deleteone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteLogic
 */
@WebServlet("/deletelogic")
public class DeleteLogic extends HttpServlet {
	
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
				PreparedStatement pstmt = con.prepareStatement("delete from sms_students where ugid=?");
				pstmt.setInt(1, Integer.parseInt(req.getParameter("idTextField")));
				int del = pstmt.executeUpdate();
				if(del==1)
				pw.println("""
						<title>Status Page</title>
						<h1>Student deleted successfully...!</h1>
						""");
				else if(del==0) {
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

package com.onkarwaman.sms.deleteall;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deletealllogic")
public class DeleteAllLogic extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			
			RequestDispatcher rd = req.getRequestDispatcher("/decrypt");
			rd.include(req, res);
			String username = req.getParameter("userTextField");
			String password = req.getParameter("passTextField");
			
			if(username.equalsIgnoreCase((String)req.getAttribute("decryptedUsername"))
					&&password.equals(req.getAttribute("decryptedPassword"))) {
				HttpSession hs = req.getSession();
				try {
					Connection con =(Connection) hs.getAttribute("conObject");
					PreparedStatement pstmt = con.prepareStatement("truncate sms_students");
					pstmt.executeUpdate();
					pw.println("""
							<title>Status Page</title>
							<h1>All students deleted successfully...!</h1>
							""");
				} catch (Exception e) {
					pw.println("""
							<title>Status Page</title>
							<h1>Something went wrong, Try again later</h1>
							""");
				}
			}
			else {
				res.sendRedirect("logout");
			}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

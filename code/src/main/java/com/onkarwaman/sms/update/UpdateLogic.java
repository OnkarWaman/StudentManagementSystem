package com.onkarwaman.sms.update;

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

@WebServlet("/updatelogic")
public class UpdateLogic extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			HttpSession hs = req.getSession();
			int ugid = Integer.parseInt(hs.getAttribute("ugidOfUpdateStudent").toString());
			try {
				Connection con =(Connection) hs.getAttribute("conObject");
				PreparedStatement pstmt = con.prepareStatement("update sms_students set name=?,branch=?,dob=?,mobileno=?,email=?,address=? where ugid=?");
				pstmt.setString(1, req.getParameter("nameTextField"));
				pstmt.setString(2, req.getParameter("branchTextField"));
				// form gives date in yyyy-MM-dd format
				pstmt.setDate(3, stringDateToSqlDateConverter(req.getParameter("dobTextField")));
				pstmt.setString(4, req.getParameter("mobileTextField"));
				pstmt.setString(5, req.getParameter("emailTextField"));
				pstmt.setString(6, req.getParameter("addressTextField"));
				pstmt.setInt(7, ugid);
				int update = pstmt.executeUpdate();
				if(update==1) {
					pw.println("""
							<title>Status Page</title>
							<h1>Details updated successfully...!</h1>
							""");
				}
				else {
					pw.println("""
							<title>Status Page</title>
							<h1>Something went wrong, Try again later</h1>
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
		doGet(req, res);
	}

	static java.sql.Date stringDateToSqlDateConverter(String dob) throws Exception {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date udf = sdf.parse(dob);
        long ms = udf.getTime();
        java.sql.Date sd = new java.sql.Date(ms);
        return sd;
   }
}

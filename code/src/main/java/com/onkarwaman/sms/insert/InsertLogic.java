package com.onkarwaman.sms.insert;

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

@WebServlet("/insertlogic")
public class InsertLogic extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		if(req.getSession().getAttribute("isLogined")==null) {
			res.sendRedirect("loginpage");
		}
		else if((boolean) req.getSession().getAttribute("isLogined")) {
			HttpSession hs = req.getSession();
			try {
				Connection con =(Connection) hs.getAttribute("conObject");
				PreparedStatement pstmt = con.prepareStatement("insert into sms_students(name,branch,dob,mobileno,email,address) values(?,?,?,?,?,?)");
				pstmt.setString(1, req.getParameter("nameTextField"));
				pstmt.setString(2, req.getParameter("branchTextField"));
				// form gives date in yyyy-MM-dd format
				pstmt.setDate(3, stringDateToSqlDateConverter(req.getParameter("dobTextField")));
				pstmt.setString(4, req.getParameter("mobileTextField"));
				pstmt.setString(5, req.getParameter("emailTextField"));
				pstmt.setString(6, req.getParameter("addressTextField"));
				int ins = pstmt.executeUpdate();
				pstmt = con.prepareStatement("select ugid from sms_students where name=? and dob=? order by ugid desc");
				pstmt.setString(1, req.getParameter("nameTextField"));
				pstmt.setDate(2, stringDateToSqlDateConverter(req.getParameter("dobTextField")));
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				pw.println("<title>Status Page</title>");
				pw.println("<h1>Student added successfully with id: "+rs.getInt(1)+"</h1>");
				
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

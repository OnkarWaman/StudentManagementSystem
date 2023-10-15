package com.onkarwaman.sms.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/loginpage")
public class LoginPage extends HttpServlet {
	private long visitorCount=0;
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession hs = req.getSession();
    	hs.setAttribute("isLoginPageLoaded", true);
    	res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        visitorCount++;
        pw.println("""
            <!DOCTYPE html>
            <html>
            <head>
            <meta charset="ISO-8859-1">
            <title>Login Page</title>
            </head>
            <body>
                <h1>Welcome to Student Management System</h1>
                <br>
                <br>
                <form action="homepage" method="post">
                    Username: <input type="text" name="userTextField" required autocomplete="off">
                    <br>
                    <br>
                    Password: <input type="password" name="passTextField" required autocomplete="off">
                    <br>
                    <br>
                    <input type="reset">&nbsp
                    <input type="submit" value="Log in">
                </form>
                <br>
                <br>
                <br>
                <h1>Visitor Count: %s</h1>
                <br>
                <br>
                <h4>Designed & Developed by <a href="https://www.linkedin.com/in/onkarwaman/" style="color:red">OnkarWaman</a></h4>
            </body>
            </html>
            """.formatted(visitorCount));
    }
}

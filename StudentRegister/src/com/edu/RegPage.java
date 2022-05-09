package com.edu;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegPage
 */
@WebServlet("/RegPage")
public class RegPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("sid"));
		String sn=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("sage"));
		double fee=Double.parseDouble(request.getParameter("sfees"));
		String pass=request.getParameter("pwd");


		Connection scon=DbConnector.getConnection();
		try {
			Statement st=scon.createStatement();
			String sql="select * from student where sid="+id;
			ResultSet rs=st.executeQuery(sql);
			if(!rs.next())
			{
				String sql1="insert into student values("+id+",'"+sn+"',"+age+","+fee+",'"+pass+"')";
				int i=st.executeUpdate(sql1);
				if(i>0)
				{
					out.println("Register sucessfully!");
				}
				else
				{
					out.println("Not Register!");
				}
			}
			else
			{
				out.println("id is exist");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}

package credit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int amount=Integer.parseInt(request.getParameter("amount"));
		String user=request.getParameter("users");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		out.println("<link rel='stylesheet' href='CSS/style.css'>");
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into transfers values(?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			
			pst.setString(1, user);
			pst.setInt(2, amount);
			
			int i=pst.executeUpdate();
			if(i!=0)
			{
				out.println("<h1 class='tran'>Transaction done Successfully</h1>");
			}
			else
			{
				out.println("<h1>Transaction fails</h1>");
			}
			out.println("<table>");
			out.println("<tr><td>"+"<a href=\"ViewUsers.jsp\">View All Users</a>"+"</td></tr>");
			out.println("</table>");
			out.println("</body></html>");
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			out.println(e);
		}		
	}
}

package credit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewUsers")
public class ViewUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		out.println("<link rel='stylesheet' href='CSS/style.css'>");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from users");
			
			out.println("<div class='res-tab'><table>");
			out.println("<h1>Users Details</h1>");
			out.println("<tr class='view'><th>Account Number</th><th>Name</th><th>Email</th><th>Current Credit</th><th>Select The User</th></tr>");
			while(rs.next())
			{
				int n=rs.getInt("accno");
				String name=rs.getString("tname");
				String email=rs.getString("email");
				int cc=rs.getInt("current_credit");
				out.println("<tr><td>"+n+"</td><td>"+name+"</td><td>"+email+"</td><td>"+cc+"</td><td>"+"<a href=\"SelectUser.jsp\">Select</a>"+"</td></tr>");
			}
			out.println("</table></div>");
			out.println("</body></html>");
			con.close();
		}
		catch(Exception e)
		{
			out.println("error");
		}
	}
}

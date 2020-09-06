package credit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OneUser")
public class OneUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OneUser() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String un=request.getParameter("number");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql=" select * from users where accno=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,un);
			
			ResultSet rs=pst.executeQuery();
			out.println("<table>");
			out.println("<link rel='stylesheet' href='CSS/style.css'>");
			out.println("<tr><th>Account Number</th><th>Name</th><th>Email</th><th>Current_Credit</th><th>Select the User</th></tr>");
			if(rs.next())
			{
				int n=rs.getInt("accno");
				String name=rs.getString("tname");
				String email=rs.getString("email");
				int cc=rs.getInt("current_credit");
				out.println("<tr><td>"+n+"</td><td>"+name+"</td><td>"+email+"</td><td>"+cc+"</td><td>"+"<a href=\"TransferCredit.jsp\">Transfer_Credit</a>"+"</td></tr>");
			
			}
			else
			{
				response.sendRedirect("SelectUser.jsp");
				return;
			}
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

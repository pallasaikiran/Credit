package credit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAllUsers")
public class ViewAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewAllUsers() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select accno,tname,email,gained_amount from users left join transfers on users.tname=transfers.name");
			out.println("<table border=1>");
			out.println("<link rel='stylesheet' href='CSS/style.css'>");
			out.println("<tr><th>Account Number</th><th>Name</th><th>Email</th><th>Gained Amount</th></tr>");
			while(rs.next())
			{
				int n=rs.getInt("accno");
				String name=rs.getString("tname");
				String email=rs.getString("email");
				int gm=rs.getInt("gained_amount");
				out.println("<tr><td>"+n+"</td><td>"+name+"</td><td>"+email+"</td><td>"+gm+"</td></tr>");
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

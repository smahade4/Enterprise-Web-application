	
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.*;
import java.text.*;

public class AddCustomers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
PrintWriter out=response.getWriter();
response.setContentType("text/html");		

    			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='/SalesHome'>Home</a></li>");
out.println("<li class=''><a href='ViewOrders'>View Order</a></li>");
out.println("<li class=''><a href='AddCustomers' Name='AddCustomers'>AddCustomers</a></li>");
out.println("<li class=''><a href='UpdateCustomerOrder' Name='UpdateOrders'>UpdateCustomerOrder</a></li>");
out.println("<li class=''><a href='CancelCustomerOrder' Name='CancelOrders'>CancelCustomerOrder</a></li>");
out.println("<li class=''><a href='UpdateCart' Name='UpdateCart'>UpdateCart</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	
		
			
  
	 out.println(" <form   method = 'get' action = 'RegisterCustomer'>");

 out.println("<h2>Register Customer</h2><br><br>");
 out.println("Username: <input type = 'text' name ='Username'><br><br>");
 out.println("Password: <input  type = 'password' name ='Password' ><br><br>");
	 out.println("FirstName: <input type = 'text' name ='FirstName'><br><br>");
 out.println("LastName: <input type = 'text' name ='LastName' ><br><br>");
 out.println("<div id='text-login'>EmailId: <input type = 'text' name ='EmailId'><br><br>");
 out.println("PhoneNo: <input type = 'text' name ='PhoneNo'><br><br>");
					
 out.println("<input class = 'submit-button' type = 'submit' name = 'Register' value = 'Register'>");					
 out.println("</div></form> </section>");
out.println("</body></html>");
	}
catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}



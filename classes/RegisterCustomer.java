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
public class RegisterCustomer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		MongoClient mongo = new MongoClient("localhost", 27017);
	
	public void init(){
			
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
				DB db = mongo.getDB("CSP595Tutorial");

		DBCollection LoginDetails = db.getCollection("LoginDetails");
		
	

		int UserId=0;
		String Username=request.getParameter("Username");	
		String Password=request.getParameter("Password");
		String FirstName=request.getParameter("FirstName");
		String EmailId=request.getParameter("EmailId");
		String PhoneNo=request.getParameter("PhoneNo");
		

		DBCursor cursor = LoginDetails.find();
			PrintWriter out=response.getWriter();	
	
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
	
if(Username.equals("")||Password.equals(""))		
		{
		out.println("UserName Password Cannot be blank");
		return;
		}
	

		while(cursor.hasNext()){
			BasicDBObject searchQuery = (BasicDBObject)cursor.next();
		if(searchQuery.get("Username").equals(Username))		
		{
		out.println("Userid already exists Please choose different name");
		return;
		}
		UserId++;		
		}
			BasicDBObject doc = new BasicDBObject("title", "LoginDetails").
				append("UserId", UserId+1).
				append("Username",Username).
				append("Password",Password).
				append("FirstName", FirstName).
				append("EmailId", EmailId).
				append("PhoneNo",PhoneNo);
	
		response.setContentType("text/html");
		LoginDetails.insert(doc);
		

				    
	
	out.println("<h2>Added User"+Username);
				
	
	out.println("</h2></div></body></html>");
								
									
	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	}
}
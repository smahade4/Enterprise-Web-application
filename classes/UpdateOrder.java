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
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

public class UpdateOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
				
						
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myCart = db.getCollection("OrderDetails");
			String Button = request.getParameter("Process");
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
	


if(Button.equals("Update")) 
	{

	String searchField="PaymentId";
	int i;
	BasicDBObject searchQuery = new BasicDBObject();
	int PaymentId = Integer.parseInt(request.getParameter("PaymentId"));
	
		searchQuery.put(searchField,PaymentId);
	DBCursor cursor=myCart.find(searchQuery);
	while(cursor.hasNext())
	{	BasicDBObject obj = (BasicDBObject)cursor.next();	
		
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			if(!(obj.get("ShippingStatus").equals("Cancelled")))
 			{
			out.println(" <form method=\"get\" action=\"UpdateCustomerPayment\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Payment Id: </td>");
			out.println("<td> <input type=\"text\" name=\"PaymentId\" value= "+obj.get("PaymentId")+" readonly> </td>");
			out.println("</tr>");		
			out.println("<tr>");
			out.println("<td>CustId: </td>");
			
			out.println("<td> <input type=\"text\" name=\"CustId\" value= "+obj.get("CustId")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>FirstName: </td>");
			out.println("<td> <input type=\"text\" name=\"FirstName\" value= "+obj.get("FirstName")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ProductName: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\" value= "+obj.get("ProductName")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ShippingStatus: </td>");
			out.println("<td> <input type=\"text\" name=\"ShippingStatus\" value= "+obj.get("ShippingStatus")+"> </td>");
			out.println("</tr>");		

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");			
			out.println("<tr>");
			out.println("<td>ShippingDate: </td>");
			out.println("<td> <input type=\"text\" name=\"ShippingDate\" value= "+format.format(obj.get("ShippingDate"))+"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ShippingDate: </td>");
			out.println("<td> <input type=\"submit\" name=\"Update\" value= 'Submit'> </td>");
			out.println("</tr>");		
			
			out.println("</table>");
			}
			out.println("</body>");
			out.println("</html>");
			 			
		
	

}
	}	


			
		} catch (MongoException e) {
			e.printStackTrace();
		}
	
}	
	public void destroy()	{
      // do nothing.
	}
	
}



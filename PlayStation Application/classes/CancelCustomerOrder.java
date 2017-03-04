
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
public class CancelCustomerOrder extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
   protected HashMap products;
    
	public void init() throws ServletException{
	
		mongo = new MongoClient("localhost", 27017);


	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
		PrintWriter out = response.getWriter();
	DB db = mongo.getDB("CSP595Tutorial");	
			DBCollection myOrder = db.getCollection("OrderDetails");
						
			
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
	

			DBCursor cursor = myOrder.find();
			if(cursor.count()==0)
			{ out.println("There are no Orders to Update");
				}
			else{
			while(cursor.hasNext())
			{BasicDBObject obj = (BasicDBObject)cursor.next();
			out.println(" <form method=\"get\" action=\"CancelOrder\">");
			if(!(obj.getString("ShippingStatus").equals("Cancelled")))
			{
				
 			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"checkbox\" name=\"PaymentId\" value= "+obj.get("PaymentId")+">"+ obj.get("PaymentId")+"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product: </td>");
			out.println("<td> <input type=\"text\" name=\"CustId\" value= "+obj.get("CustId")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td>"+obj.getString("ProductName")+" </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td><input type='Submit' name='Process' value='Cancel'></td>");
						
					out.println("</tr>");
				
	
			out.println("</table></form>");
				}		
				}			
					} 
			}
	
			 catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}
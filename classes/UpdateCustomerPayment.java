
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
public class UpdateCustomerPayment extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
    
	public void init() throws ServletException{
      		
	mongo = new MongoClient("localhost", 27017);
	

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{

int PaymentId=Integer.parseInt(request.getParameter("PaymentId"));
response.setContentType("text/html");		


			PrintWriter out = response.getWriter();			
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
	
	DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("OrderDetails");
		



SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

Date ShippingDate=format.parse(request.getParameter("ShippingDate"));
String ShippingStatus=request.getParameter("ShippingStatus");	

						

			DBCollection myOrder = db.getCollection("OrderDetails");
			String searchField = "PaymentId";
			// Find and display 
			
			BasicDBObject searchQuery = new BasicDBObject();
 
			searchQuery.put(searchField,Integer.parseInt(request.getParameter("PaymentId")));


			DBCursor cursor = myOrder.find(searchQuery);
			while(cursor.hasNext())
			{
			BasicDBObject obj = (BasicDBObject)cursor.next();
            		obj.put("ShippingDate",ShippingDate);
            		obj.put("ShippingStatus",ShippingStatus);
	
	 			myOrder.save(obj);


		out.println("OrderUpdated for"+obj.getString("ProductName"));
					

			}


			
		out.println("</body></html>");
			
				} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}
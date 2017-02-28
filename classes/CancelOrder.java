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
public class CancelOrder extends HttpServlet {
	
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
			DBCollection myOrders = db.getCollection("OrderDetails");
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

	if(Button.equals("Cancel")) 

	{
		BasicDBObject searchQuery = new BasicDBObject();
	String Payment[] = request.getParameterValues("PaymentId");
	String searchField="PaymentId";
	for( int i=0; i <Payment.length; i++){
	searchQuery.put(searchField,Integer.parseInt(Payment[i]));
	DBCursor cursor=myOrders.find(searchQuery);
	int ProductId=0;
	int Quantity=0;
	 HashMap products = (HashMap)request.getSession().getAttribute("products");
		String ProductNames="";	
	while(cursor.hasNext())
	{
	BasicDBObject obj = (BasicDBObject)cursor.next();								
	if(obj.getString("ShippingStatus").equals("Cancelled"))
	 	{
		out.println("Order is Already Cancelled");
		return;
		}
	
	 ProductId=(int)obj.get("ProductId");
	 ProductNames=ProductNames+obj.get("ProductName");
	 Quantity=(int)obj.get("Quantity");
		
			for(i=1;i<=products.size();i++)
        		{     
	 		ProductDetails p=(ProductDetails)products.get(i);
			if(p.getProductId()==ProductId)
			{
				p.setQuantityAvailable(p.getQuantityAvailable()+Quantity);
			}
			 }
	obj.put("ShippingStatus","Cancelled");
		myOrders.save(obj);	
	}
				response.setContentType("text/html");
			//Send the response back to the JSP
				
	


			
		out.println("OrderCancelled for"+ProductNames);
					
			out.println("</table>");
			
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



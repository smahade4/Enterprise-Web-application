	
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


public class SalesHome extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//protected Map Products = new HashMap();
    
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB

		mongo = new MongoClient("localhost", 27017);
		//Products.put(New ProductDetails());
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myCart = db.getCollection("ProductDetails");
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
 
			//String CustId=request.getSession().getAttribute("UserId").toString();
			///searchQuery.put(searchField,CustId);

			//DBCursor cursor = myCart.find(searchQuery);
			
			PrintWriter out = response.getWriter();
	
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewOrders'>View Order</a></li>");
out.println("<li class=''><a href='ViewOrders'>View Cart</a></li>");
out.println("<li class=''><a href='AddCustomers' Name='AddCustomers'>AddCustomers</a></li>");
out.println("<li class=''><a href='UpdateOrders' Name='UpdateOrders'>UpdateOrders</a></li>");
out.println("<li class=''><a href='UpdateCart' Name='UpdateCart'>UpdateCart</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	

				    
	
	out.println("</div></body></html>");
}

catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}



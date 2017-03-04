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
public class CancelPayment extends HttpServlet {
	
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
		out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='#'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>");
out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

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
		Date date = new Date();
			Date ShippingDate=(Date)obj.get("ShippingDate");	
			int diffInDays = (int) ((ShippingDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
	if((obj.getString("ShippingStatus").equals("Cancelled"))||diffInDays<=5)
	 	{out.println(request.getParameter("diffInDays")+obj.getString("ShippingStatus")); 
		out.println("Cannot cancel order");
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

		out.println("OrderCancelled for"+ProductNames);
					
			out.println("</article></section>");			

			out.println("<aside class='sidebar'>");
			
				out.println("<ul>");	
					   out.println("<li>");
							out.println("<h4>Options</h4>");
							out.println("<ul>");
							out.println("<li><a href='WriteReview.html'>Write Reviews</a></li>");
							out.println("<li><a href='/HW1_MahadevanSushma/DataAnalytics1'>Data Analytics</a></li>");
							
							out.println("</ul>");
						out.println("</li>");	
						
					out.println("</ul>");
				
			out.println("</aside>");




		out.println("</div></body>");

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



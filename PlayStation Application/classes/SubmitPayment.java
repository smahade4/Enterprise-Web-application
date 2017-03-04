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
import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.Date;

import java.text.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

public class SubmitPayment extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
  public static Date addDays(Date d, int days)
    {
        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
        return d;
    }

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
				
						
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			DBCollection myOrder = db.getCollection("OrderDetails");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myCart = db.getCollection("CartDetails");
			//String Button = request.getParameter("ProcessOrder");
	
			String searchField="CustId";
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(searchField,request.getSession().getAttribute("UserId"));
			DBCursor cursor = myCart.find(searchQuery);
			int PaymentId=0;
			DBCursor cursorPayment=myOrder.find();
			while(cursorPayment.hasNext())
			{
			BasicDBObject obj = (BasicDBObject) cursorPayment.next();	
			if(PaymentId<(int)obj.get("PaymentId"));
				PaymentId=(int)obj.get("PaymentId");
			}
				String ProductNames=""; 
				
				while(cursor.hasNext())
				{
			BasicDBObject obj1 = (BasicDBObject) cursor.next();
			if(!(obj1.getString("OrderStatus").equals("Unavailable")))
			{
			String ShippingStatus="Pending";	
				
			ProductNames=ProductNames+obj1.getString("ProductName");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date dt=new Date();					  
			Date date=new Date();					  
				
			BasicDBObject doc = new BasicDBObject("title", "myOrder").append("PaymentId", PaymentId+1).
				append("FirstName",request.getParameter("FirstName")).
				append("LastName", request.getParameter("LastName")).
				append("Price", obj1.get("Price")).
				append("OrderId", obj1.get("OrderId")).
				append("CustId", obj1.get("CustId")).
				append("ProductId",(int)obj1.get("ProductId")).
				append("ProductName", obj1.getString("ProductName")).

				append("ProductDesc", obj1.getString("ProductDesc")).
				append("Discounts", obj1.get("Discounts")).
				append("Rebates", obj1.get("Rebates")).
				append("WarrantyPurchase", obj1.getString("WarrantyPurchase")).
				append("WarrantyPeriod", obj1.get("WarrantyPeriod")).
				append("WarrantyCost", obj1.get("WarrantyCost")).
				append("Total", obj1.get("Total")).
				append("Quantity", obj1.get("Quantity")).
				append("CardNumber",request.getParameter("CardNumber")).
				append("ExpirationDate",request.getParameter("ExpirationDate")).
				append("CVNO",request.getParameter("CVNO")).
				append("ShippingAddress",request.getParameter("ShippingAddress")).	
				append("City",request.getParameter("City")).	
				append("State",request.getParameter("State")).	
				append("ZipCode",request.getParameter("ZipCode")).	
				append("PhoneNo",request.getParameter("PhoneNo")).		
				append("EmailId",request.getParameter("EmailId")).
				append("ShippingStatus","Pending").
				append("OrderDate",date).
				append("ShippingDate",addDays(dt,20));
				myOrder.insert(doc);
				PaymentId++;
				myCart.remove(obj1);
			
				}
		}

			int CartOrder=0;
        		DBCursor cursorOrder = myCart.find(searchQuery);
			if(cursorOrder.count() == 0){
				request.getSession().setAttribute("CartOrder",0);
				}

			else
			{
			while (cursorOrder.hasNext()) 
				{			 
			CartOrder++;
			cursorOrder.next();
				}	
			request.getSession().setAttribute("CartOrder",CartOrder);
			}

			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();
			
					out.println("<html>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
		out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/PageLayoutCSS/Index'>Home</a></li>");
out.println("<li class=''><a href='/PageLayoutCSS/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/PageLayoutCSS/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='#'>Accessories</a></li>");
out.println("<li><a href='/PageLayoutCSS/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='ViewPayment'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>");
		out.println("</a></li></ul></nav>");
  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");


			out.println("<h1> Payment Done for:");
		
			out.println(ProductNames);
			
			out.println("</h1>");
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
			 			
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}
	
}	
	public void destroy()	{
      // do nothing.
	}
	
}



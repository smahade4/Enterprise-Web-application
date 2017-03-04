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
public class SubmitOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
			//Get the values from the form

			int ProductId =Integer.parseInt(request.getParameter("ProductId"));

				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			String ProductName = request.getParameter("ProductName");
			int Price = Integer.parseInt(request.getParameter("Price"));
			int Quantity=Integer.parseInt(request.getParameter("Quantity"));
			//Date ShippingDate=format.parse(request.getParameter("ShippingDate"));
			int Rebates=Integer.parseInt(request.getParameter("Rebates"));
			int Discounts=Integer.parseInt(request.getParameter("Discounts"));
			int WarrantyCost=0;;
			int  WarrantyPeriod=0;
			String OrderStatus="Available";
			String ProductDesc=request.getParameter("ProductDesc");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String WarrantyPurchase="";

			response.setContentType("text/html");
			//Send the response back to the JSP
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
 out.println("<li class=''><a href='ViewPayment'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>");
out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

		 HashMap products = (HashMap)request.getSession().getAttribute("products");
			for(int i=1;i<=products.size();i++)
        		{
			ProductDetails p=(ProductDetails)products.get(i);
			if(p.getProductId()==ProductId)
			{
				if((p.getQuantityAvailable()-Quantity)<0)
				{out.println("There is no Stock for this much products order less Quantity");
				return;
				}}
			}
	
			if(request.getParameter("Warranty").equals("No"))
				{
			 WarrantyPurchase="Not Available";
			WarrantyCost=0;
			WarrantyPeriod=0;
		 	}
		if((request.getParameter("Warranty").equals("Yes")) && (request.getParameter("WarrantyPurchase").equals("")))										
		{out.println("There is no Warranty selected for this much products order less Quantity");
				return;
		}
			if((request.getParameter("Warranty").equals("Yes")) && (request.getParameter("WarrantyPurchase").equals("Yes")))										
			{
			 WarrantyPurchase=request.getParameter("WarrantyPurchase");
			 WarrantyCost=Integer.parseInt(request.getParameter("WarrantyCost"));
			 WarrantyPeriod=Integer.parseInt(request.getParameter("WarrantyPeriod"));		
			}
		if((request.getParameter("Warranty").equals("Yes")) && (request.getParameter("WarrantyPurchase").equals("No")))										
		{
			 WarrantyPurchase="No";
			WarrantyCost=0;
			 WarrantyPeriod=0;							
			}
				
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myCart = db.getCollection("CartDetails");
			System.out.println("Collection myOrders selected successfully");
				
				int OrderId=0;

        		DBCursor cursor = myCart.find();
			while (cursor.hasNext()) 
				{

			BasicDBObject obj = (BasicDBObject) cursor.next();	
			if(OrderId<(int)obj.get("OrderId"))
				OrderId=(int)obj.get("OrderId");
			}

				int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			Date date = new Date();
			
			BasicDBObject doc = new BasicDBObject("title", "myCart").
				append("OrderId", OrderId+1).
				append("CustId",CustId).
				append("ProductId", ProductId).
				append("ProductName", ProductName).
				append("ProductDesc", ProductDesc).
				append("Price", Price).
				append("Discounts",Discounts).
				append("Rebates",Rebates).
				append("WarrantyPurchase",WarrantyPurchase).
				append("WarrantyPeriod",WarrantyPeriod).
				append("WarrantyCost",WarrantyCost).
				append("Quantity",Quantity).
				//append("ShippingDate",addDays(date,20)).
				append("OrderStatus", OrderStatus).
				append("Total",Quantity*(Price-Discounts-Rebates+WarrantyCost));
				myCart.insert(doc);

					String searchField = "CustId";
					BasicDBObject searchQuery = new BasicDBObject();
					searchQuery.put(searchField,request.getSession().getAttribute("UserId"));
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

			
			for(int i=1;i<=products.size();i++)
        		{     
	 		ProductDetails p=(ProductDetails)products.get(i);
			if(p.getProductId()==ProductId)
			{
				p.setQuantityAvailable(p.getQuantityAvailable()-Quantity);
			}	
			}
			request.getSession().setAttribute("products",products);
			
			System.out.println("Document inserted successfully");
			
			out.println("<h1> Order placed for:"+ ProductName + "</h1>");

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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}
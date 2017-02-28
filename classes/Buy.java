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
public class Buy extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int ProductId=0;	
	String ProductName = " ";
	String imageLocation = " ";
	int Price = 0;
	int Rebates;
	int Discounts;
	String ProductDesc;
	String Warranty;
	int WarrantyCost;
	int WarrantyPeriod;
	//Date ShippingDate;	
	
	public void init(){
		//Connect to Mongo DB
		MongoClient mongo = new MongoClient("localhost", 27017);
						
		// if database doesn't exists, MongoDB will create it for you
		//DB db = mongo.getDB("CustomerReviews");
		
		//DBCollection myReviews = db.getCollection("myReviews");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
						
		try{
			//Get the values from the form

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		 HashMap products = (HashMap)request.getSession().getAttribute("products");
			for(int i=1;i<=products.size();i++)
        			{     
			 ProductDetails p=(ProductDetails)products.get(i);
		
			if (request.getParameter(String.valueOf(p.getProductId()))!=null){
			ProductId=p.getProductId();
			ProductName=p.getProductName();
			Price=p.getPrice();		
			 Rebates=p.getRebates();
			Discounts=p.getDiscounts();	
			ProductDesc=p.getProductDescription();
			//ShippingDate=p.getShippingDate();
			Warranty=p.getWarranty();
			out.println(p.getWarranty());
			WarrantyPeriod=p.getWarrantyPeriod();
			WarrantyCost=p.getWarrantyCost();	
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Buy</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Place Order</h1>");							
			out.println(" <h3>" + ProductName + "</h3> ");
			
			out.println(" <form method=\"get\" action=\"SubmitOrder\">");
			out.println("<fieldset>");
			out.println("<legend>Product information:</legend>");
			out.println("<table>");

			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\" value= '"+ ProductName +"' readonly>"); 
			out.println("<input type=\"hidden\" name=\"ProductId\" value= "+ ProductId +" readonly></td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Product Price: </td>");
			out.println("<td> <input type=\"text\" name=\"Price\" value= "+ Price +" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			/*out.println("<td>Shipping Date: </td>");
			out.println("<td> <input type=\"text\" name=\"ShippingDate\" value= '"+ format.format(ShippingDate) +"' readonly> </td>");
			out.println("</tr>");
			*/
			out.println("<tr>");
			out.println("<td>Discounts: </td>");
			out.println("<td> <input type=\"text\" name=\"Discounts\" value= "+Discounts+" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Rebates: </td>");
			out.println("<td> <input type=\"text\" name=\"Rebates\" value= "+Rebates+" readonly> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Quantity: </td>");
			
			out.println("<td> <input type=\"text\" name=\"Quantity\"> </td>");
			out.println("</tr>");
						out.println("<tr>");
			out.println("<td>Product Desc: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductDesc\" value= '"+ ProductDesc +"' readonly> </td>");
			out.println("</tr>");

			
			out.println("<tr>");
			out.println("<td>Warranty: </td>");
			out.println("<td> <input type=\"text\" name=\"Warranty\" value= '"+ Warranty +"' readonly> </td>");
			out.println("</tr>");

			
			if(Warranty.equals("Yes"))
			{			


			out.println("<tr>");
			out.println("<td>WarrantyCost: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyCost\" value= "+WarrantyCost+" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>WarrantyPeriod: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyPeriod\" value= "+WarrantyPeriod+" readonly> </td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Warranty Purchase: </td>");
			
			out.println("<td> <input type=\"radio\" name='WarrantyPurchase' value='Yes'\"> yes </td>");
			out.println("<td> <input type=\"radio\" name='WarrantyPurchase' value='No'\"> no </td>");
			
			out.println("</tr>");
			

			}

			out.println("<tr>");
				out.println("<td>Submit: </td>");
			out.println("<td> <input type=\"Submit\" name=\"Submit\" value= 'Add To Cart'> </td>");
			out.println("</tr>");

			
		out.println("</table>");
			out.println("</fieldset>");		
			out.println("</form>");	
			out.println("</body>");
			out.println("</html>");
}
			}
			
						
	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	}
}
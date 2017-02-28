
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
public class AddProducts extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
   protected HashMap products;
    
	public void init() throws ServletException{
   	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
		int productNum=1;
	  products = (HashMap)request.getSession().getAttribute("products");
		if(products!=null)
		{
    		Iterator it = products.entrySet().iterator();
 	 	while (it.hasNext()) 
		{	
		productNum++;
		    it.next();
		}
		}
			PrintWriter out = response.getWriter();
				out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	
		out.println(request.getSession().getAttribute("Username"));
		 out.println("</a></li></ul></nav>");
			out.println(" <form method=\"get\" action=\"SubmitProducts\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductId\" value= "+(productNum+1)+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product: </td>");
			out.println("<td> <input type=\"text\" name=\"Product\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ProductDescription: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductDescription\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Retailer Name: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerName\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Manufacturer Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ManufacturerName\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Retailer State: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerState\"> </td>");
			out.println("</tr>");		


			out.println("<tr>");
			out.println("<td>Retailer City: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerCity\"> </td>");
			out.println("</tr>");


			out.println("<tr>");
			out.println("<td>Retailer Zip: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerZip\"> </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td>QuantityAvailable: </td>");
			out.println("<td> <input type=\"text\" name=\"QuantityAvailable\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Rebates: </td>");
			out.println("<td> <input type=\"text\" name=\"Rebates\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Price: </td>");
			out.println("<td> <input type=\"text\" name=\"Price\"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Discount: </td>");
			out.println("<td> <input type=\"text\" name=\"Discounts\"> </td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td>Warranty: </td>");
			out.println("<td> <input type=\"radio\" name=\"Warranty\" value=\"Yes\">Yes</input>"); 
			out.println("<input type=\"radio\" name=\"Warranty\" value=\"No\">No</input></td>");
			out.println("</tr>");
		


			out.println("<tr>");
			out.println("<td>WarrantyCost: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyCost\"> </td>");
			out.println("</tr>");
		

			out.println("<tr>");
			out.println("<td>WarrantyPeriod: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyPeriod\"> </td>");
			out.println("</tr>");

			/*out.println("<tr>");
			out.println("<td>ShippingDate: </td>");
			out.println("<td> <input type=\"Date\" name=\"ShippingDate\"> </td>");
			out.println("</tr>");
			*/
			out.println("<tr>");
			out.println("<td><input type='Submit' name='AddProducts' value='Add'></td>");
						
					out.println("</tr>");

			out.println("</table>");				
			out.println("</body>");
				out.println("</html>");


			} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}
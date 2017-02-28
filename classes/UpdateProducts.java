
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
public class UpdateProducts extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
   protected HashMap products;
    
	public void init() throws ServletException{
      		
	mongo = new MongoClient("localhost", 27017);
	

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
int ProductId=Integer.parseInt(request.getParameter("ProductId"));
response.setContentType("text/html");		
PrintWriter out = response.getWriter();
String ProductDescription=request.getParameter("ProductDescription");
int QuantityAvailable=Integer.parseInt(request.getParameter("QuantityAvailable"));
int Rebates=Integer.parseInt(request.getParameter("Rebates"));
int Price=Integer.parseInt(request.getParameter("Price"));
int Discounts=Integer.parseInt(request.getParameter("Discounts"));
String Warranty=request.getParameter("Warranty");
int WarrantyCost=0;
int WarrantyPeriod=0;

		out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	

if(request.getParameter("Warranty")==null)
{
out.println("Warranty  cannot be null Please select one");
return;
}

else
{Warranty=request.getParameter("Warranty");
}
if((request.getParameter("WarrantyCost").equals(""))||(request.getParameter("WarrantyPeriod").equals("")))
{
out.println("Cost and Period cannot be null Please Enter");
return;
}

 WarrantyCost=Integer.parseInt(request.getParameter("WarrantyCost"));
 WarrantyPeriod=Integer.parseInt(request.getParameter("WarrantyPeriod"));

SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

//Date ShippingDate=format.parse(request.getParameter("ShippingDate"));
	
out.println("<html><body>");
						
 HashMap products = (HashMap)request.getSession().getAttribute("products");

			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myCart = db.getCollection("CartDetails");
			String searchField = "ProductId";
			// Find and display 
			for(int i=1;i<=products.size();i++)
        		{
			ProductDetails p=(ProductDetails)products.get(i);
			if(p.getProductId()==ProductId)
			{ 
			p.setProductDescription(ProductDescription);
			p.setQuantityAvailable(QuantityAvailable);
			p.setRebates(Rebates);
			p.setPrice(Price);
			p.setDiscounts(Discounts);
			//p.setShippingDate(ShippingDate);
			if(Warranty.equals("Yes"))
			{
			p.setWarranty(Warranty);
			p.setWarrantyCost(WarrantyCost);
			p.setWarrantyPeriod(WarrantyPeriod);
			}
			else
			if(Warranty.equals("No"))
			{
			p.setWarranty(Warranty);
			 WarrantyCost=0;
 			WarrantyPeriod=0;
			p.setWarrantyCost(WarrantyCost);
			p.setWarrantyPeriod(WarrantyPeriod);
			}
			
			out.println("Updated Info For :"+p.getProductName());
			out.println(p.getProduct());
				}	
			}

			BasicDBObject searchQuery = new BasicDBObject();
 
			searchQuery.put(searchField,Integer.parseInt(request.getParameter("ProductId")));


			DBCursor cursor = myCart.find(searchQuery);
			while(cursor.hasNext())
			{
			BasicDBObject obj = (BasicDBObject)cursor.next();
            		obj.put("Price",Price);

			obj.put("Discounts",Discounts);
			obj.put("Rebates",Rebates);
			if(Warranty.equals("No"))
			{
			obj.put("WarrantyPurchase","Not Available");
			obj.put("WarrantyCost",0);
			obj.put("WarrantyPeriod",0);
			obj.put("Total",((int)(obj.get("Quantity"))*(Price-Rebates-Discounts)));
			}
			if(Warranty.equals("Yes") && obj.getString("WarrantyPurchase").equals("Yes"))
			{
			obj.put("WarrantyPurchase","Yes");
			obj.put("WarrantyCost",WarrantyCost);
			obj.put("WarrantyPeriod",WarrantyPeriod);
			obj.put("Total",((int)(obj.get("Quantity"))*(Price-Rebates-Discounts+WarrantyCost)));
			}
			if(Warranty.equals("Yes") && obj.getString("WarrantyPurchase").equals("No"))
			{obj.put("WarrantyPurchase","Not Available");
			obj.put("WarrantyCost",0);
			obj.put("WarrantyPeriod",0);
			obj.put("Total",((int)(obj.get("Quantity"))*(Price-Rebates-Discounts)));
			}
			if(QuantityAvailable==0)
			{obj.put("OrderStatus","Unavailable");
			}	
	 			myCart.save(obj);



			}


		request.getSession().setAttribute("products",products);
		
			
		out.println("</body></html>");
			
				} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}
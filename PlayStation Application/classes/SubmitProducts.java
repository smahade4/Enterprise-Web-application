
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
public class SubmitProducts extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
   protected HashMap products;
    
	public void init() throws ServletException{
      		


	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
int ProductId=Integer.parseInt(request.getParameter("ProductId"));
String Product=request.getParameter("Product");
String ProductName=request.getParameter("ProductName");
String ProductDescription=request.getParameter("ProductDescription");
String RetailerName=request.getParameter("RetailerName");
String ManufacturerName=request.getParameter("ManufacturerName");
String RetailerState=request.getParameter("RetailerState");
String RetailerCity=request.getParameter("RetailerCity");
String RetailerZip=request.getParameter("RetailerZip");

int QuantityAvailable=Integer.parseInt(request.getParameter("QuantityAvailable"));
int Rebates=Integer.parseInt(request.getParameter("Rebates"));
int Price=Integer.parseInt(request.getParameter("Price"));
int Discounts=Integer.parseInt(request.getParameter("Discounts"));

String Warranty=request.getParameter("Warranty");

PrintWriter out = response.getWriter();
		
if(Warranty=="")
{out.println("Atleast One option should be selected for Warranty");
}
int WarrantyCost=Integer.parseInt(request.getParameter("WarrantyCost"));
int WarrantyPeriod=Integer.parseInt(request.getParameter("WarrantyPeriod"));

		//out.println(ProductName);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		//Date ShippingDate=format.parse(request.getParameter("ShippingDate"));
		ProductDetails p=new ProductDetails();
		p.setProductId(ProductId);
		p.setProduct(Product);
		p.setProductName(ProductName);
		p.setProductDescription(ProductDescription);
		p.setRetailerName(RetailerName);
		p.setManufacturerName(ManufacturerName);
		p.setRetailerState(RetailerState);
		p.setRetailerCity(RetailerCity);
		p.setRetailerZip(RetailerZip);
		p.setQuantityAvailable(QuantityAvailable);
		p.setRebates(Rebates);
		p.setPrice(Price);
		p.setDiscounts(Discounts);
		p.setWarranty(Warranty);
		//p.setShippingDate(ShippingDate);
		
		if(Warranty.equals("Yes"))
		{
		p.setWarrantyCost(WarrantyCost);
		p.setWarrantyPeriod(WarrantyPeriod);
		}
		else	
		{p.setWarrantyCost(0);
		p.setWarrantyPeriod(0);
		}
	 products = (HashMap)request.getSession().getAttribute("products");		
	if(products==null)
	{products=new HashMap();
	}	
	products.put(ProductId,p);

	request.getSession().setAttribute("products",products);	
		response.setContentType("text/html");
	out.println("<html><body>");

			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	
		out.println("Added Product"+p.getProductName() +"<br> in Category");
		out.println(p.getProduct());
	out.println("</body>");

			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}
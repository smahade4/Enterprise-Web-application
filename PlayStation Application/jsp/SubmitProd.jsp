<%@ page import="java.io.*"%>
<%@ page import="com.mongodb.MongoClient"%>
<%@ page import="com.mongodb.MongoException"%>
<%@ page import="com.mongodb.WriteConcern"%>
<%@ page import="com.mongodb.DB"%>
<%@ page import="com.mongodb.DBCollection"%>
<%@ page import="com.mongodb.BasicDBObject"%>
<%@ page import="com.mongodb.DBObject"%>
<%@ page import="com.mongodb.DBCursor"%>
<%@ page import="com.mongodb.ServerAddress"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set,java.util.Date,javax.servlet.http.*,javax.servlet.RequestDispatcher"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="assign3.*"%>
<%
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

		
if(Warranty=="")
{out.println("Atleast One option should be selected for Warranty");
}
int WarrantyCost=Integer.parseInt(request.getParameter("WarrantyCost"));
int WarrantyPeriod=Integer.parseInt(request.getParameter("WarrantyPeriod"));
		
FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
 HashMap products = (HashMap)objectInputStream.readObject();

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
	if(products==null)
	{products=new HashMap();
	}	
	products.put(ProductId,p);

int i;
/*for(i=1;i<=products.size();i++)
{ 
out.println("insidehash");
out.println(products.get(i));
}*/

       FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\test12.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(products);
 	   objectOutputStream.flush();
	   objectOutputStream.close();       
	fileOutputStream2.close();
             

		response.setContentType("text/html");
	out.println("<html><body>");

			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts.jsp'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	
		out.println("Added Product"+p.getProductName() +"<br> in Category");
		out.println(p.getProduct());
	out.println("</body>");

			} catch (Exception e) {
			e.printStackTrace();
		}
		
%>
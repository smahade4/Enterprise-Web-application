<%@ page import="java.io.*,javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
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

      		
	MongoClient mongo= new MongoClient("localhost", 27017);
	
int ProductId=Integer.parseInt(request.getParameter("ProductId"));
response.setContentType("text/html");		
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
out.println("<nav><ul><li class='start selected'><a href='StoreManager.jsp'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts.jsp'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'><a href='login.html' Name='login'>Logout</a></li>");
 out.println("</a></li></ul></nav>");
	
out.println(request.getSession().getAttribute("Username"));

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
FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
 HashMap products = (HashMap)objectInputStream.readObject();


			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myCart = db.getCollection("CartDetails");
			String searchField = "ProductId";
			// Find and display 
			Iterator it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
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
			}	}	
			}

      FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\test12.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(products);
 	   objectOutputStream.flush();
	   objectOutputStream.close();       
	fileOutputStream2.close();


			BasicDBObject searchQuery = new BasicDBObject();
 
			//searchQuery.put(searchField,Integer.parseInt(request.getParameter("ProductId")));


				HashMap orders=(HashMap)session.getAttribute("orders");
			if(orders != null)
			{
			Iterator entries = orders.entrySet().iterator();
			while (entries.hasNext()) 
			{
			 //out.println("entry"+entries.next());
			Map.Entry er = (Map.Entry)entries.next();
			out.println(er);
			if(er!=null)
			{
			out.println("entry"+er.getKey()+er.getValue());
			OrderDetails o=(OrderDetails)er.getValue();
			if(o.getProductId()==ProductId)
			{
			o.setPrice(Price);
			o.setDiscounts(Discounts);
			o.setRebates(Rebates);
			if(Warranty.equals("No"))
			{
			o.setWarrantyPurchase("Not Available");
			o.setWarrantyCost(0);
			o.setWarrantyPeriod(0);
			o.setTotal((o.getQuantity())*(Price-Rebates-Discounts));
			}
			if(Warranty.equals("Yes") && o.getWarrantyPurchase().equals("Yes"))
			{
			o.setWarrantyPurchase("Yes");
			o.setWarrantyCost(WarrantyCost);
			o.setWarrantyPeriod(WarrantyPeriod);
			o.setTotal((o.getQuantity())*(Price-Rebates-Discounts+WarrantyCost));
			}
			if(Warranty.equals("Yes") && o.getWarrantyPurchase().equals("No"))
			{
			o.setWarrantyPurchase("Not Available");
			o.setWarrantyCost(0);
			o.setWarrantyPeriod(0);
			o.setTotal((o.getQuantity())*(Price-Rebates-Discounts));
			}
			if(QuantityAvailable==0)
			{o.setOrderStatus("Unavailable");
			}	
			}
			}	
			}
			}

		session.setAttribute("products",products);
		session.setAttribute("orders",orders);
		
			
		out.println("</body></html>");
			
				} catch (Exception e) {
			e.printStackTrace();
		}
		
%>
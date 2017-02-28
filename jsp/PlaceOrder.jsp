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

%>

<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
<nav><ul><li class='start selected'><a href='/SalesHome.jsp'>Home</a></li>
<li class=''><a href='AddOrders.jsp'>Add Order</a></li>
<li class=''><a href='ViewOrders.jsp'>View Order</a></li>
<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>
<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateOrder</a></li>
<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelOrder</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
</a></li></ul></nav>
	

<%
		FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		 HashMap products = (HashMap)objectInputStream.readObject();

		HashMap orders=(HashMap)session.getAttribute("orders");
				
	String  ProductId= request.getParameter("ProductId");
	int  Quantity= Integer.parseInt(request.getParameter("Quantity"));
			String WarrantyPurchase=request.getParameter("WarrantyPurchase");
			int WarrantyCost=0;
			int WarrantyPeriod=0;
			Iterator it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			if(p.getProductId()==Integer.parseInt(ProductId))
			{
				if((p.getQuantityAvailable()-Quantity)<0)
				{out.println("There is no Stock for this much products order less Quantity");
				return;
				}
			}
			}
			}
			it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			if(p.getProductId()==Integer.parseInt(ProductId))
			{
				
			if(p.getWarranty().equals("No"))
			{
			WarrantyPurchase="Not Available";
			WarrantyCost=0;
			WarrantyPeriod=0;
		 	}
			if((p.getWarranty().equals("Yes")) && (WarrantyPurchase.equals(null)))										
			{
			out.println("Please Select Warranty for product");
			return;
			}
			if((p.getWarranty().equals("Yes")) && (WarrantyPurchase.equals("Yes")))										
			{
			 WarrantyPurchase=request.getParameter("WarrantyPurchase");
			 WarrantyCost=p.getWarrantyCost();
			 WarrantyPeriod=p.getWarrantyPeriod();		
			}
			if(p.getWarranty().equals("Yes") && (request.getParameter("WarrantyPurchase").equals("No")))										
			{
			 WarrantyPurchase="No";
			WarrantyCost=0;
			 WarrantyPeriod=0;							
			}
				
			// If database doesn't exists, MongoDB will create it for you
			/*DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myCart = db.getCollection("CartDetails");
			System.out.println("Collection myOrders selected successfully");
			*/				
				int OrderId=0;
				String OrderStatus="pending";			
				if(orders==null)
				{orders=new HashMap();
				}
				else 
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
			if(OrderId<o.getOrderId())
				OrderId=o.getOrderId();
				}
				}
				}	
     				
			int CustId=Integer.parseInt(request.getParameter("CustId").toString());
				OrderDetails o=new OrderDetails();
           	             o.setOrderId(OrderId+1);
			o.setProductId(Integer.parseInt(ProductId));
				o.setProductName(p.getProductName());
			o.setCustId(CustId);
			o.setPrice(p.getPrice());
			o.setQuantity(Quantity);
			o.setRebates(p.getRebates());
			o.setDiscounts(p.getDiscounts());
			o.setWarrantyCost(p.getWarrantyCost());
			o.setWarrantyPurchase(WarrantyPurchase);
			o.setOrderStatus(OrderStatus);
			o.setWarrantyPeriod(p.getWarrantyPeriod());
			o.setTotal(Quantity*(p.getPrice()-p.getDiscounts()-p.getRebates()+p.getWarrantyCost()));
			orders.put(OrderId+1,o);
	
			p.setQuantityAvailable(p.getQuantityAvailable()-Quantity);
					             

		out.println("Document inserted successfully");
			out.println("<h1> Order placed for:"+ p.getProductName() + "</h1>");


		}
	}		
}

		session.setAttribute("orders",orders);
	
       FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\test12.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(products);
 	   objectOutputStream.flush();
	   objectOutputStream.close();       
	fileOutputStream2.close();
	

		out.println("</div></body>");
			out.println("</html>");
					
		} catch (Exception e) {
			e.printStackTrace();
		}
%>	
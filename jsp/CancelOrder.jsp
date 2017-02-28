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

		MongoClient mongo = new MongoClient("localhost", 27017);
	
				
						
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("OrderDetails");
			String Button = request.getParameter("Process");
				%>
			<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
		<nav><ul><li class='start selected'><a href='/SalesHome.jsp'>Home</a></li>
		<li class=''><a href='AddOrders.jsp'>Add Order</a></li>
		<li class=''><a href='ViewOrders.jsp'>View Order</a></li>
<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>
<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateCustomerOrder</a></li>
<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelCustomerOrder</a></li>
<li class='end'><a href='UpdateCart.jsp' Name='UpdateCart'>UpdateCart</a></li>
</a></li></ul></nav>

	<%		out.println(request.getSession().getAttribute("Username")+"<br> <br>");
	if(Button.equals("Cancel")) 

	{
		BasicDBObject searchQuery = new BasicDBObject();
	String Payment[] = request.getParameterValues("PaymentId");
	String searchField="PaymentId";
		String ProductNames="";	
	for( int i=0; i <Payment.length; i++)
	{
	searchQuery.put(searchField,Integer.parseInt(Payment[i]));
	DBCursor cursor=myOrders.find(searchQuery);
	int ProductId=0;
	int Quantity=0;

	                FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		 HashMap products = (HashMap)objectInputStream.readObject();
		

		while(cursor.hasNext())
		{
	BasicDBObject obj = (BasicDBObject)cursor.next();								
	if(obj.getString("ShippingStatus").equals("Cancelled"))
	 	{
		out.println("Order is Already Cancelled");
		return;
		}
	
	 ProductId=Integer.parseInt(obj.get("ProductId").toString());
	 ProductNames=ProductNames+obj.get("ProductName");
	 Quantity=Integer.parseInt(obj.get("Quantity").toString());
		Iterator it = products.entrySet().iterator();			
    		while (it.hasNext()) 
		{
		Map.Entry pi = (Map.Entry)it.next();
		if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
	if(p.getProductId()==ProductId)
			{
				p.setQuantityAvailable(p.getQuantityAvailable()+Quantity);
			}
			 }
		}
	
	obj.put("ShippingStatus","Cancelled");
		myOrders.save(obj);	
		}
		}
				response.setContentType("text/html");
			//Send the response back to the JSP
				
	


			
		out.println("OrderCancelled for"+ProductNames);%>
			</table>

		<%}%>	
			
			
			</body>
			</html>
			 			
		
	<%

		
	

			
		} catch (MongoException e) {
			e.printStackTrace();
		}
%>


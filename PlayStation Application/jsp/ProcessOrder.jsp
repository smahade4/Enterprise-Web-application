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
						
		String Button = request.getParameter("ProcessOrder");

	if(Button.equals("CheckOut")) 
	{
			 	RequestDispatcher rd=getServletContext().getRequestDispatcher("/Payment.jsp");
			 		rd.forward(request, response);	
	}
	if(Button.equals("Cancel")) 
	{
	String searchField="OrderId";
	int i=0,j=0;
	BasicDBObject searchQuery = new BasicDBObject();
	String Order[] = request.getParameterValues("Order");

	 FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
	int ProductId=0;
	int Quantity=0;
	String ProductNames="";	
		
	 HashMap products = (HashMap)objectInputStream.readObject();
	HashMap orders=(HashMap)session.getAttribute("orders");	
		
	
	String id=Order[i];
	if(orders != null)
	{Iterator entries = orders.entrySet().iterator();
	Map.Entry er=null;
      	
		
	      
	for(i=0; i<Order.length; i++)
		{
		
	 while (entries.hasNext())
	{
	er=(Map.Entry)entries.next();
	if(er!=null)
	{ 
	OrderDetails o=(OrderDetails)er.getValue();

	
		if(Integer.toString(o.getOrderId()).equals(id))
		{
                id=Order[i];
	      	
		ProductId=o.getProductId();
		ProductNames=ProductNames+o.getProductName();
		Quantity=o.getQuantity();
		int y;	
			Iterator it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();if(p.getProductId()==ProductId && !o.getOrderStatus().equals("Unavailable"))
			{
				p.setQuantityAvailable(p.getQuantityAvailable()+Quantity);
			}
			}
			 }

			entries.remove();

			}
	   }
	 }			
		}
	}					 
       FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\test12.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(products);
 	   objectOutputStream.flush();
	   objectOutputStream.close();       
	fileOutputStream2.close();
 
		session.setAttribute("orders",orders);

 	int CustId=Integer.parseInt(session.getAttribute("UserId").toString());
	

			int CartOrder=0;
			if(orders != null)
			{
			Iterator entries = orders.entrySet().iterator();
			while (entries.hasNext()) 
			{
			Map.Entry er = (Map.Entry)entries.next();
			if(er!=null)
			{
			OrderDetails o=(OrderDetails)er.getValue();
			if(o.getCustId()==CustId)
			{
			CartOrder++;
			}
			}
			}
			}

	session.setAttribute("CartOrder",CartOrder);
			

		response.setContentType("text/html");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>");
out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='#'>Accessories</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/login.html'>logout</a></li>");
out.println("</a></li></ul></nav>");


			out.println("<h1> Order Cancelled for:");
		
			out.println(ProductNames);
			
			out.println("</h1>");
				
			                out.println("</article></section>");			



 			}
	%>

		<% HashMap orders =(HashMap)session.getAttribute("orders");
			int OrderNo=0;
 	int CustId=Integer.parseInt(session.getAttribute("UserId").toString());
	

			if(orders == null){%>
				<h2 align='center'>There are no Order Placed In your cart.</h2>
			
				<%	}
				else{
			
				if(orders != null)
				{
				Iterator entries = orders.entrySet().iterator();
				while (entries.hasNext()) {
				Map.Entry er = (Map.Entry)entries.next();
				if(er!=null)
				{
				OrderDetails o=(OrderDetails)er.getValue();
				if(o.getCustId()==CustId)
				{OrderNo++;
				}
				
				if(o.getCustId()==CustId)
				{
				 int OrderId=o.getOrderId();
				int CustId1=o.getCustId();
				String ProductName = o.getProductName();
				int Price = o.getPrice();
				int Quantity=o.getQuantity();
				int Discount=o.getDiscounts();
				int Rebates=o.getRebates();
				//String Total=o.getTotal();
				String Warranty=o.getWarranty();
				int WarrantyPeriod=o.getWarrantyPeriod();
	
				//String ProductDescription=o.getProductDesc();

				%>	
					<form name='ProcessOrder' method='Get' action='ProcessOrder.jsp'>
					<table>
				<tr>
					
				<td><input type='checkbox' name='Order' value=<%=OrderId%> </td>
				<%=OrderId%></input>	</td>
				<%if(o.getOrderStatus().equals("Unavailable"))
					{%>
				<td> <%out.println("Product Unavailable for check out");%></td>
				<%}%>
				</tr>
					
					<tr>
					<td> Product Name: </td>
					<td> <%=ProductName%></td>
					</tr>
					
					<tr>
					<td> Price: </td>
					<td><%=Price%></td>
					</tr>
					
					<tr>
					<td> CustID1: </td>
					<td><%=CustId1%></td>
					</tr>
					
					<tr>
					<td> Order Quantity: </td>
					<td> <%=Quantity%> </td>
					</tr>
					
				
				
					<tr>
				<td><input type='Submit' name='ProcessOrder' value='Cancel'>Remove Order </td>
						
					</tr>
						
				
				
					<tr>
					<td> Total: </td>
					<%int Total = o.getTotal(); %>
					<td> <%=Total%> </td>
					</tr>
		<%} } }
			if(OrderNo!=0){%>
			<br><br><tr><td><input type='Submit' name='ProcessOrder' value='CheckOut'>Proceed to Check Out</td></tr>
			</table></form>
			<%}		 


				if(OrderNo==0)
				{out.println("<h2 align='center'>There are no Order Placed In your cart.</h2>");
			}
					}		
		} 

		
		%>	



<%
		

		out.println("</div></body>");
			out.println("</html>");	
			
		} catch (MongoException e) {
			e.printStackTrace();
		}	
%>



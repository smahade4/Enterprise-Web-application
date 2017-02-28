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
			//Get the values from the form

MongoClient mongo = new MongoClient("localhost", 27017);
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
			response.setContentType("text/html");
			String WarrantyPurchase="";

			response.setContentType("text/html");
			//Send the response back to the JSP

		FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		 HashMap products = (HashMap)objectInputStream.readObject();

			Iterator it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			if(p.getProductId()==ProductId)
			{
				if((p.getQuantityAvailable()-Quantity)<0)
				{out.println("There is no Stock for this much products order less Quantity");
				return;
				}
			}
			}
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
				
				int OrderId=0;
				HashMap orders=(HashMap)session.getAttribute("orders");
				int i;				
			
				if(orders==null)
				{orders=new HashMap();
				}
				else {
				Iterator entries = orders.entrySet().iterator();
			while (entries.hasNext()) 
			{
			 //out.println("entry"+entries.next());
			Map.Entry er = (Map.Entry)entries.next();
			
			if(er!=null)
			{
			OrderDetails o=(OrderDetails)er.getValue();
				if(OrderId<o.getOrderId())
				OrderId=o.getOrderId();

				}
				}
				}	
     				int CustId=Integer.parseInt(session.getAttribute("UserId").toString());
				OrderDetails o=new OrderDetails();
                        o.setOrderId(OrderId+1);
			o.setProductId(ProductId);
			o.setProductName(ProductName);
			o.setCustId(CustId);
			o.setPrice(Price);
			o.setQuantity(Quantity);
			o.setRebates(Rebates);
			o.setDiscounts(Discounts);
			o.setWarrantyCost(WarrantyCost);
			o.setWarrantyPurchase(WarrantyPurchase);
			o.setOrderStatus(OrderStatus);
			o.setWarrantyPeriod(WarrantyPeriod);
			o.setTotal(Quantity*(Price-Discounts-Rebates+WarrantyCost));
			//out.println("total"+o.getTotal());
			orders.put(OrderId+1,o);
			int CartOrder=0;
			if(orders!=null)
			{
			for(i=1;i<=orders.size();i++)
			{
			if(o.getCustId()==CustId)
			CartOrder++;
			}
			}
			session.setAttribute("CartOrder",CartOrder);
			session.setAttribute("orders",orders);

	                /*FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
 HashMap products = (HashMap)objectInputStream.readObject();
*/ 			 it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			if(p.getProductId()==ProductId)
			{
				p.setQuantityAvailable(p.getQuantityAvailable()-Quantity);
			}	
				}
			}
       FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\test12.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(products);
 	   objectOutputStream.flush();
	   objectOutputStream.close();       
	fileOutputStream2.close();
             

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
 out.println("<li class=''><a href='ViewPayment.jsp'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>");
out.println("</a></li></ul></nav>");



		out.println("Document inserted successfully");
			
			out.println("<h1> Order placed for:"+ ProductName + "</h1>");
			
			%>


			<% orders =(HashMap)session.getAttribute("orders");
			int OrderNo=0;
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
				 o=(OrderDetails)er.getValue();
				if(o.getCustId()==CustId)
				{OrderNo++;
				}
				
				if(o.getCustId()==CustId)
				{
				 OrderId=o.getOrderId();
				int CustId1=o.getCustId();
				ProductName = o.getProductName();
				Price = o.getPrice();
				Quantity=o.getQuantity();
				int Discount=o.getDiscounts();
				Rebates=o.getRebates();
				//String Total=o.getTotal();
				String Warranty=o.getWarranty();
				WarrantyPeriod=o.getWarrantyPeriod();
	
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





<%		} catch (Exception e) {
			e.printStackTrace();
		}
%>	
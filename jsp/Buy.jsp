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
		
		response.setContentType("text/html");
		
						
		try{
			//Get the values from the form
		MongoClient mongo = new MongoClient("localhost", 27017);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	                FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		 HashMap products = (HashMap)objectInputStream.readObject();
		Iterator it = products.entrySet().iterator();			
    			
			%>

		<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'>
<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>
<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>
<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>
<li class='end'><a href='#'>Accessories</a></li>
<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>
<%=request.getSession().getAttribute("Username")%>
<%=request.getSession().getAttribute("CartOrder")%>
<li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrder</a></li>
<li class=''><a href='/HW3_MahadevanSushma/login.html'>logout</a></li>
</a></li></ul></nav>

<%

			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();

			if (request.getParameter(String.valueOf(p.getProductId()))!=null)
			{
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
			
			out.println(" <form method=\"get\" action=\"SubmitOrder.jsp\">");
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
}			
						
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
%>
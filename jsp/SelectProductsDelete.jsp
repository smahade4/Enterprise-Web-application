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
	MongoClient mongo;
	
    %>
		<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
<nav><ul><li class='start selected'><a href='StoreManager.jsp'>Home</a></li>
<li class=''><a href='ViewProducts.jsp'>View Products</a></li>
<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>
<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>
<li class=''><a href='SelectProductsDelete.jsp' Name='DeleteProducts'>DeleteProducts</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
 </a></li></ul></nav>
<%	
 out.println(request.getSession().getAttribute("Username"));

			out.println(" <form method=\"get\" action=\"DeleteProducts.jsp\">");
			  
FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
 HashMap products = (HashMap)objectInputStream.readObject();

		
			if(products==null)
			{ out.println("There are no Products to Update");
				}
			

			else
			{Iterator it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
		     
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();

			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"checkbox\" name=\"ProductId\" value= "+p.getProductId()+">"+p.getProductId() +"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product: </td>");
			out.println("<td> <input type=\"text\" name=\"Product\" value= '"+p.getProduct()+"' readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\" value= '"+p.getProductName()+"' readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td><input type='Submit' name='Delete' value='Delete'></td>");
						
					out.println("</tr>");
				}
			}
			out.println("</table>");				
			out.println("</body>");
				out.println("</html>");
			}	
			} catch (MongoException e) {
			e.printStackTrace();
}
%>
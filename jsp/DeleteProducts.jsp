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

      		
String ProductName="";	
	String ProductId[] = request.getParameterValues("ProductId");
     response.setContentType("text/html");		
out.println("<html><body>");
FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
 HashMap products = (HashMap)objectInputStream.readObject();

			%>	
			<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>
<li class=''><a href='viewproducts.jsp'>View Products</a></li>
<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>
<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>
<li class=''><a href='SelectProductsDelete.jsp' Name='DeleteProducts'>DeleteProducts</a></li>
<li class='end'>
<li><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
</a></li></ul></nav>
		

<%
out.println(request.getSession().getAttribute("Username"));
	Map.Entry er=null;
      	      HashMap orders=(HashMap)session.getAttribute("orders");
	for(int i=0; i<ProductId.length; i++)
		{
		out.println(Integer.parseInt(ProductId[i].toString()));
		Iterator it = products.entrySet().iterator();			
    		
				while (it.hasNext()) 
			{
		     
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
				if(p.getProductId()==Integer.parseInt(ProductId[i].toString()))
				{
			ProductName=ProductName+p.getProductName();
				if(orders!=null)
				{   
				 Iterator entries = orders.entrySet().iterator();

				while (entries.hasNext())
				{
				er=(Map.Entry)entries.next();
				if(er!=null)
				{ 
					OrderDetails o=(OrderDetails)er.getValue();
					out.println(o.getProductId());
					if(o.getProductId()==Integer.parseInt(ProductId[i].toString()))
					{o.setOrderStatus("Unavailable");
					out.println(o.getProductId() + p.getProductId());}
				}
				}
				}
				it.remove();

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
%>



				<h2>deleted<%=ProductName%></h2>
</div></body></html>

<%				} catch (Exception e) {
			e.printStackTrace();
		}
		
%>
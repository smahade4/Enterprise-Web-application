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
response.setContentType("text/html");		


%>    			<html><head>
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
	
		
			
<%=request.getSession().getAttribute("Username")%>
  
<form   method = 'get' action = 'RegisterCustomer.jsp'>

<h2>Register Customer</h2><br><br>
 Username: <input type = 'text' name ='Username'><br><br>
Password: <input  type = 'password' name ='Password' ><br><br>
FirstName: <input type = 'text' name ='FirstName'><br><br>
LastName: <input type = 'text' name ='LastName' ><br><br>
 <div id='text-login'>EmailId: <input type = 'text' name ='EmailId'><br><br>
 PhoneNo: <input type = 'text' name ='PhoneNo'><br><br>
					
<input class = 'submit-button' type = 'submit' name = 'Register' value = 'Register'>					
 </div></form> </section>
</body></html>
	<%}
catch (MongoException e) {
			e.printStackTrace();
		}

%>
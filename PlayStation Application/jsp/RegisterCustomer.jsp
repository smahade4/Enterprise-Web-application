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
	

		
	
	FileInputStream fileInputStream = new FileInputStream(new File("E:\\Login.txt"));
 	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	HashMap Login =(HashMap)objectInputStream.readObject();
	
	if(Login==null)
	{
	Login=new HashMap();
	}	


		int UserId=0;
		String Username=request.getParameter("Username");	
		String Password=request.getParameter("Password");
		String FirstName=request.getParameter("FirstName");
		String EmailId=request.getParameter("EmailId");
		String PhoneNo=request.getParameter("PhoneNo");
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
 out.println( request.getSession().getAttribute("Username"));
if(Username.equals("")||Password.equals(""))		
		{
		out.println("UserName Password Cannot be blank");
		return;
		}
	
for(int i=1;i<=Login.size();i++)
		{
		LoginDetails l=(LoginDetails)Login.get(i);		
		if(l.getUsername().equals(Username))		
		{
		out.println("Userid already exists Please choose different name");
		return;
		}

		UserId++;		
		}
			LoginDetails l=new LoginDetails();
		
				l.setUserId(UserId+1);
					l.setUsername(Username);
				l.setPassword(Password);
				l.setFirstName(FirstName);
				l.setEmailId(EmailId);
				l.setPhoneNo(PhoneNo);
			Login.put(UserId+1,l);


       FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\Login.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(Login);
 	   objectOutputStream.flush();
	   objectOutputStream.close();       
	fileOutputStream2.close();

%>	
<h2>Added User <%=Username%>
				
	
	</h2></div></body></html>
					<%			
									
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
%>
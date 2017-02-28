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
	//DB db = mongo.getDB("CSP595Tutorial");
		//DBCollection LoginDetails = db.getCollection("LoginDetails");
	

	FileInputStream fileInputStream = new FileInputStream(new File("E:\\Login.txt"));
 	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	HashMap Login =(HashMap)objectInputStream.readObject();
	
%>
<html>
<head>
	<link rel="stylesheet" href="styles2.css" type="text/css" />
</head>


<body>
<div id="container">
    <header>
	<nav>
	<ul>
        	<li class="start selected"><a href="login.html">Home</a></li>
            <li class=""><a href="ContactUs.html">ContactUs</a></li>
            <li class=""><a href="AboutUS.html">About US</a></li>
            <li class="end" margin-left=80px><a href="login.html"> login/Register</a></li>
        </ul>
    </nav>

	<img class="header-image" src="images/img_index1.jpg" width = "100%" height = "100%" alt="Index Page Image" />

    
<%	if(Login==null)
	{
	Login=new HashMap();
	}	
		int UserId=0;
		String Username=request.getParameter("Username");	
		String Password=request.getParameter("Password");
		String FirstName=request.getParameter("FirstName");
		String EmailId=request.getParameter("EmailId");
		String PhoneNo=request.getParameter("PhoneNo");
		
		//DBCursor cursor = LoginDetails.find();
		response.setContentType("text/html");
		if(Username.equals("")||Password.equals(""))		
		{
		out.println("UserName Password Cannot be blank");
		out.println("<li class=''><a href='login.html'>login</a></li>");
		out.println("<li class=''><a href='Register.html'>SignUp</a></li>");
		return;
		}
	

		for(int i=1;i<=Login.size();i++)
		{
		LoginDetails l=(LoginDetails)Login.get(i);		
		if(l.getUsername().equals(Username))		
		{
		out.println("Userid already exists Please choose different name");
		out.println("<li class=''><a href='login.html'>login</a></li>");
		out.println("<li class=''><a href='Register.html'>SignUp</a></li>");
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

		response.setContentType("text/html");
	//	LoginDetails.insert(doc);
		
									
	out.println("Added User");
				
	
									
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
%>
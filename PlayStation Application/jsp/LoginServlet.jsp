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
	try
	{
	
	MongoClient  mongo = new MongoClient("localhost", 27017);	


			String Username = request.getParameter("Username");
			String Password = request.getParameter("Password");
			String ButtonVal = request.getParameter("ContinueAsGuest");
			if(ButtonVal!=null)
			{
			//HttpSession session = request.getSession(true);	
			
			session.setAttribute("Username","Guest");
			session.setAttribute("UserId",0);
			session.setAttribute("CartOrder",0);
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			 	rd.forward(request, response);
			return;
			}


			if(Username.equals("SalesMen") && Password.equals("SalesMen"))
			{				

					session.setAttribute("Username","SalesMen");
			 		session.setAttribute("UserId",0);					
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/SalesHome.jsp");
			 		rd.forward(request, response);
			}	
			if(Username.equals("StoreManager") && Password.equals("StoreManager"))
			{
			
				
				session.setAttribute("Username","StoreManager");
			 		session.setAttribute("UserId",0);
					
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/StoreManager.jsp");
			 		rd.forward(request, response);

			}				
					
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			//DBCollection myLogin = db.getCollection("LoginDetails");

        
        		//DBCursor cursor = myLogin.find();
			FileInputStream fileInputStream = new FileInputStream(new File("E:\\Login.txt"));
 	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	HashMap Login =(HashMap)objectInputStream.readObject();
	
			if(Login == null){
			
  			response.sendRedirect("/HW3_MahadevanSushma/login.html"); 
			return;	
			}	
			for(int i=1;i<=Login.size();i++)
				{			 
				LoginDetails l =(LoginDetails)Login.get(i);			

				if(l.getUsername().equals(Username) && l.getPassword().equals(Password))
				{
					session.setAttribute("Username",l.getUsername());
						int UserId=l.getUserId();
			 		session.setAttribute("UserId",UserId);

					//DBCollection myCart = db.getCollection("CartDetails");
					//String searchField = "CustId";
						//BasicDBObject searchQuery = new BasicDBObject();
						//searchQuery.put(searchField,UserId);
			int CartOrder=0;
        		HashMap orders =(HashMap)session.getAttribute("orders");
			if(orders != null)
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
			if(o.getCustId()==UserId)
			CartOrder++;
			}
		       }
			}
			session.setAttribute("CartOrder",CartOrder);
			

				

			 	RequestDispatcher rd=getServletContext().getRequestDispatcher("/index.jsp");
			 		rd.forward(request, response);

							}
				}

			 	RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
			 		rd.forward(request, response);





	} catch (Exception e) {
			e.printStackTrace();
		}	

%>
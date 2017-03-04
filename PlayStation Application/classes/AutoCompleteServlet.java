import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;


import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import java.util.HashMap;
import java.util.*;
import java.util.Iterator;
import java.io.*;
/**
 *
 * @author nbuser
 */
public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
    //private ComposerData compData = new ComposerData();
   
  HashMap products;
    String targetId=null;	

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();

	    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
	try{	
	
        String action = request.getParameter("action");
         targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

		products=(HashMap)request.getSession().getAttribute("products");

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

		
                Iterator it = products.entrySet().iterator();

		
                while (it.hasNext()) {
                    Map.Entry pi = (Map.Entry)it.next();
		if(pi!=null)
			{
			ProductDetails composer=(ProductDetails)pi.getValue();
		
                   
                    if ( // targetId matches first name
                         String.valueOf(composer.getProductId()).toLowerCase().startsWith(targetId) ||
                         // targetId matches last name
                         composer.getProductName().toLowerCase().startsWith(targetId) ||
                         // targetId matches full name
                         composer.getProduct().toLowerCase().concat(" ")
                            .concat(composer.getProduct().toLowerCase()).startsWith(targetId)) {

                        sb.append("<composer>");
                        sb.append("<id>" + composer.getProductId() + "</id>");
                        sb.append("<firstName>" + composer.getProductName() + "</firstName>");
                        sb.append("<lastName>" + composer.getProduct() + "</lastName>");
                        sb.append("</composer>");
                        namesAdded = true;
                    }
			}
                }
            }

           if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<composers>" + sb.toString() + "</composers>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

	products=(HashMap)request.getSession().getAttribute("products");
	  if ((targetId != null) && products.containsKey(Integer.parseInt(targetId.trim()))) {
    		request.setAttribute("ProductData",products.get(Integer.parseInt(targetId.trim())));
               
		RequestDispatcher rd=context.getRequestDispatcher("/ProductData");
			rd.forward(request,response);
		}
          }
	}
	catch(Exception e)
	{
	}
    }
}

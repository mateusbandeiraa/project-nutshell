package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
	    String username = request.getParameter("username");
	    System.out.println("SERVLET REQUEST: " + username);
	    
	    String[] assembly;
		try {
			assembly = LogicController.tweetInANutshell(username);
		} catch (Exception ex) {
			assembly = new String[] {"/error/", ex.getMessage()};
			ex.printStackTrace();
		}
	    
	    Gson gilson = new GsonBuilder().create();
	    
	    String responseJson = gilson.toJson(assembly);
	    
		
	    response.getWriter().write(responseJson);
	}

}

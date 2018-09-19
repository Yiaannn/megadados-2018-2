package std;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class ServletHome extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//fazer um *if já estiver logado*
		DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		if (ls.isValid()){
			String message = ls.getUser().getLoginName();
			message="Logado como "+message;
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/HomeAdmin.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//Realizar o signout aqui
		
		DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		ls.signOut();
		
		request.getRequestDispatcher("/Home.jsp").forward(request, response);
	}
	
}
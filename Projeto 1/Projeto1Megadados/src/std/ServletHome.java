package std;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class ServletHome extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ServletHome() {
        super();
        // TODO Auto-generated constructor stub
    }
	
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
		
		String message= "200 - OK (Log-out realizado com sucesso)";
		response.setStatus(200);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/Home.jsp").forward(request, response);
	}
	
}
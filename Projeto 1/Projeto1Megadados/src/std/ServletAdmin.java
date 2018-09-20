package std;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Admin")
public class ServletAdmin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String message = "";
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("/SignIn.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		DAO dao= new DAO();
		
		User user= new User();
		user.setLoginName(request.getParameter("login"));
		
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(  request.getParameter("pass").getBytes(StandardCharsets.UTF_8)  );
			String passhash = bytesToHex(hash);
			
			System.out.println("Para debugar, printar o hash:");
			System.out.println(passhash);
			user.setPassHash(passhash);
			
			
			System.out.println("Abri meu LoginSession!");
			LoginSession ls= new LoginSession(dao, request, response);
			if(  ls.signIn(user)  ){
				//Login deu sucesso!
				
				String message= "200 - OK (A autenticação fornecida é válida)";
				response.setStatus(200);
				request.setAttribute("message", message);
				request.getRequestDispatcher("/SignIn.jsp").forward(request, response);
			}else{
				//Login deu ruim!
				
				String message= "403 - Forbidden (A autenticação fornecida não é válida)";
				response.setStatus(403);
				request.setAttribute("message", message);
				request.getRequestDispatcher("/SignIn.jsp").forward(request, response);
			}
		}catch (Exception e){
			System.out.println("Something went really wrong while hashing");
			e.printStackTrace();
		}
		

		dao.close();
	}
	
	private static String bytesToHex(byte[] bytes) { //adaptado de https://stackoverflow.com/a/9855338
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
		
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
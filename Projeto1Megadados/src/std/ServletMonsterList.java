package std;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Monsters")
public class ServletMonsterList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletMonsterList() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		String[] monsternames= dao.getMonsterNames();
		
		request.setAttribute("monsternames", monsternames);
		
		if( ls.isValid() ){
			String message = ls.getUser().getLoginName();
			message="Logado como "+message;
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/MonsterNamesAdmin.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/MonsterNames.jsp").forward(request, response);
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//demux pelo delete (limitação do html, etc etc)
    	String methodcheck= request.getParameter("_method");
    	if( methodcheck!=null && methodcheck.equals("DELETE") ){
    		doDelete(request, response);
    	}else{
    		//true post
    		
    		//Checar por autenticação
    		
    		//Continuar em sucesso, 403 se inválido
    		
    		DAO dao= new DAO();
    		LoginSession ls= new LoginSession(dao, request, response);
    		
    		if( ls.isValid() ){
    			//Inserir um novo monstro aqui
    			
        		//TODO: devolver 201 em sucesso, 400(?) se repetido
    			
    			String monstername= request.getParameter("newname");
    			
    			if( !dao.checkIfMonsterExists(monstername) ){
    				
    				dao.createMonsterByName(monstername);
    				
    				String message= "201 - Created (O recurso foi criado com sucesso)";
    				response.setStatus(201);
    				request.setAttribute("message", message);
    				String[] monsternames= dao.getMonsterNames();
    				request.setAttribute("monsternames", monsternames);
    				request.getRequestDispatcher("/MonsterNamesAdmin.jsp").forward(request, response);
    			}else{
    				
    				String message= "400 - Bad Request (O recurso a ser adicionado já existe)";
    				response.setStatus(400);
    				request.setAttribute("message", message);
    				String[] monsternames= dao.getMonsterNames();
    				request.setAttribute("monsternames", monsternames);
    				request.getRequestDispatcher("/MonsterNamesAdmin.jsp").forward(request, response);
    			}
    		}else{
    			String message= "403 - Forbidden (É necessária uma autenticação de Administrador)";
    			
    			response.setStatus(403);
    			request.setAttribute("message", message);
    			String[] monsternames= dao.getMonsterNames();
    			request.setAttribute("monsternames", monsternames);
    			request.getRequestDispatcher("/MonsterNames.jsp").forward(request, response);
    		}
    	}
	}

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Remover um monstro inteiro aqui
    	
    	DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		if( !ls.isValid() ){
			//Autenticar para remoção aqui
			
			String message= "403 - Forbidden (É necessária uma autenticação de Administrador)";
			response.setStatus(403);
			String[] monsternames= dao.getMonsterNames();
			request.setAttribute("monsternames", monsternames);
			request.getRequestDispatcher("/MonsterNames.jsp").forward(request, response);
		}else{
			String monstername= request.getParameter("deletetarget");
			System.out.println("Para confirmar, o monstro informado é "+monstername);
			
			//TODO: devolver 200 em sucesso, 404 se não encontrar
			if( dao.checkIfMonsterExists(monstername) ){
				//Sucesso!
				System.out.println("DELETE > Encontrei o monstro");
				dao.deleteMonsterByName(monstername);
				
				String message= "200 - OK (O recurso foi removido com sucesso)";
				response.setStatus(200);
				request.setAttribute("message", message);
				String[] monsternames= dao.getMonsterNames();
				request.setAttribute("monsternames", monsternames);
				request.getRequestDispatcher("/MonsterNamesAdmin.jsp").forward(request, response);
			}else{
				//Não achei :(
				System.out.println("DELETE > Não encontrei o monstro");
				
				String message= "404 - Not Found (O recurso a ser removido não existe)";
				response.setStatus(404);
				request.setAttribute("message", message);
				String[] monsternames= dao.getMonsterNames();
				request.setAttribute("monsternames", monsternames);
				request.getRequestDispatcher("/MonsterNamesAdmin.jsp").forward(request, response);
			}
			
			//Pronto, agora voltar
			//doGet(request, response);
		}
	}
}

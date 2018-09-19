package std;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import monster.BodyPart;
import monster.Monster;

@WebServlet("/Monsters/*")
public class ServletMonster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletMonster() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String[] tmp= request.getRequestURI().split("/");
    	String monstername= tmp[tmp.length-1];
    	
    	monstername = URLDecoder.decode(monstername, "UTF-8");
    	//System.out.println("Deixa eu ver se estou com o monstro certo "+monstername);
    	//TODO: verificar se o nome é válido, se não for, jogar 404
    	
    	DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		Monster monster= dao.getMonsterFromName(monstername);
		
		BodyPart[] bodypartlist= dao.getMonsterBodyParts(monstername);
		//Conferir aqui se tou pegando o bodypartlist corretamente;
		/*
		System.out.println("DEBUG PARTLIST");
		System.out.println(bodypartlist.length);
		for (BodyPart part : bodypartlist) {
			System.out.println(part.name);
		}
		*/
		
		
		request.setAttribute("bodypartlist", bodypartlist);
		request.setAttribute("monster", monster);
		if( ls.isValid() ){
			String message = ls.getUser().getLoginName();
			message="Logado como "+message;
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/MonsterViewAdmin.jsp").forward(request, response);
		}else{
			//String message="DEBUG";
			//request.setAttribute("message", message);
			request.getRequestDispatcher("/MonsterView.jsp").forward(request, response);
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//demux pelo delete (limitação do html, etc etc)
    	String methodcheck= request.getParameter("_method");
    	if( methodcheck!=null && methodcheck.equals("DELETE") ){
    		doDelete(request, response);
    	}else{
    		//Eventualmente fazer partes novas aqui
    	}
	}
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Remover um bodyPart inteiro aqui
    	
    	DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		//Checar por autenticação
		
		//Continuar em sucesso, 403 se inválido
		
		if( !ls.isValid() ){
			String message= "403 - Forbidden (É necessária uma autenticação de Administrador)";
			response.setStatus(403);
			request.setAttribute("message", message);
			//rebuildar o view aqui
			String[] tmp= request.getRequestURI().split("/");
	    	String monstername= tmp[tmp.length-1];
	    	monstername = URLDecoder.decode(monstername, "UTF-8");
			Monster monster= dao.getMonsterFromName(monstername);
			BodyPart[] bodypartlist= dao.getMonsterBodyParts(monstername);
			request.setAttribute("bodypartlist", bodypartlist);
			request.setAttribute("monster", monster);
			//fim do rebuild
			request.getRequestDispatcher("/MonsterView.jsp").forward(request, response);
			
		}else{
			
			//TODO: devolver 200 em sucesso, 404 se não encontrar
			
			
			int bodypartid=  Integer.parseInt(  request.getParameter("bodypartid")  );
			
			//Simular sucesso que não tenho o check yet
			
			System.out.println("DEBUG DO ID");
			System.out.println(bodypartid);
			dao.deleteBodyPartByID(bodypartid);
			
			String message= "200 - OK (O recurso foi removido com sucesso)";
			response.setStatus(200);
			request.setAttribute("message", message);
			//rebuildar o view aqui
			String[] tmp= request.getRequestURI().split("/");
	    	String monstername= tmp[tmp.length-1];
	    	monstername = URLDecoder.decode(monstername, "UTF-8");
			Monster monster= dao.getMonsterFromName(monstername);
			BodyPart[] bodypartlist= dao.getMonsterBodyParts(monstername);
			request.setAttribute("bodypartlist", bodypartlist);
			request.setAttribute("monster", monster);
			//fim do rebuild
			request.getRequestDispatcher("/MonsterViewAdmin.jsp").forward(request, response);
			
			/*
			//TODO: devolver 204 em sucesso, 404 se não encontrar
			if( dao.checkIfMonsterExists(monstername) ){
				//Sucesso!
				System.out.println("DELETE > Encontrei o monstro");
				
				dao.deleteMonsterByName(monstername);
			}else{
				//Não achei :(
				System.out.println("DELETE > Não encontrei o monstro");
				
			}
			*/
			
			//Pronto, agora voltar
			//doGet(request, response);
		}
	}

}

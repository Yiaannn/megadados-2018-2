package std;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import monster.BodyPart;
import monster.Monster;
import monster.PhysicalVulnerability;

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
    	
    	
    	DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		//verificar se o nome é válido, se não for, jogar 404
		
		if( !dao.checkIfMonsterExists(monstername) ){
			String message= "404 - Not Found (O recurso a ser acessado não existe)";
			response.setStatus(404);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/MonsterView404.jsp").forward(request, response);
			return;
		}
		
		if( ls.isValid() ){
			HttpSession session = request.getSession(false);
			String message= (String)session.getAttribute("message");
		    session.removeAttribute("message");
			if (message == null){
				message= "Logado como "+ls.getUser().getLoginName();
			}
			
			request.setAttribute("message", message);
			deployJSP(request, response, true);
		}else{
			deployJSP(request, response, false);
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//demux pelo delete (limitação do html, etc etc)
    	String methodcheck= request.getParameter("_method");
    	if( methodcheck!=null && methodcheck.equals("DELETE") ){
    		doDelete(request, response);
    	}else if( methodcheck!=null && methodcheck.equals("PATCH") ){
    		doPatch(request, response);
    	}else{
    		//True post
    		//Checar por autenticação
    		//Continuar em sucesso, 403 se inválido
    		DAO dao= new DAO();
    		LoginSession ls= new LoginSession(dao, request, response);
    		
    		if( !ls.isValid() ){
    			String message= "403 - Forbidden (É necessária uma autenticação de Administrador)";
    			response.setStatus(403);
    			request.setAttribute("message", message);
    			deployJSP(request, response, false);
    			return;
    		}
    		
    		//devolver 200 em sucesso, 404 se não encontrar
    		
    		String monstername= request.getParameter("target");
			if ( !dao.checkIfMonsterExists(monstername) ){
				
				String message= "404 - Not Found (O recurso a ser alterado não existe)";
				response.setStatus(404);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
    		
    		BodyPart bp= new BodyPart(
    				request.getParameter("newname"),
    				Boolean.parseBoolean( request.getParameter("isBreakable") ),
    				Boolean.parseBoolean( request.getParameter("isSeverable") ),
    				false //hard coded por enquanto
    				);
    		
    		PhysicalVulnerability pv = new PhysicalVulnerability(
    				Integer.parseInt( request.getParameter("blunt") ),
    				Integer.parseInt( request.getParameter("severing") ),
    				Integer.parseInt( request.getParameter("shot") )
    				);
    		
    		bp.physicalVulnerability= pv;
    		dao.createBodyPart(monstername, bp);
    		
    		String message= "200 - OK (O recurso foi adicionado com sucesso)";
			response.setStatus(200);
			request.setAttribute("message", message);
			deployJSP(request, response, true);
			return;
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
			deployJSP(request, response, false);
			
		}else{
			//devolver 200 em sucesso, 404 se não encontrar
			
			int bodypartid=  Integer.parseInt(  request.getParameter("bodypartid")  );
			
			//Simular sucesso que não tenho o check yet
			//TODO implementar o check
			
			System.out.println("DEBUG DO ID");
			System.out.println(bodypartid);
			dao.deleteBodyPartByID(bodypartid);
			
			String message= "200 - OK (O recurso foi removido com sucesso)";
			response.setStatus(200);
			request.setAttribute("message", message);
			deployJSP(request, response, true);
			
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
    
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Antes de mais nada, conferir por autenticação
    	
    	DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		//Continuar em sucesso, 403 se inválido
		
		if( !ls.isValid() ){
			String message= "403 - Forbidden (É necessária uma autenticação de Administrador)";
			response.setStatus(403);
			request.setAttribute("message", message);
			deployJSP(request, response, false);
			
			return;
		}
		
		//filtrar pelo o quê exatamente eu estou atualizando
		String query= request.getParameter("query");
		
		if( query.equals("modifyname") ){
			//devolver 200 em sucesso, 404 se não encontrar
			String monstername= request.getParameter("target");
			
			if ( !dao.checkIfMonsterExists(monstername) ){
				
				String message= "404 - Not Found (O recurso a ser alterado não existe)";
				response.setStatus(404);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
			
			String newname= request.getParameter("newname");
			dao.updateMonsterName(monstername, newname);
			
			String message= "200 - OK (O recurso foi alterado com sucesso)";
			response.setStatus(200);
			HttpSession session = request.getSession(false);
			session.setAttribute("message", message);
			//deployJSP(request, response, true);
			//Fazer um redirect manual aqui, pois o recurso mudou de nome
			response.sendRedirect("/Projeto1Megadados/Monsters/"+newname);
			return;
			
		}else if( query.equals("modifyailments") ){
			//devolver 200 em sucesso, 404 se não encontrar
			String monstername= request.getParameter("target");
			
			if ( !dao.checkIfMonsterExists(monstername) ){
				
				String message= "404 - Not Found (O recurso a ser alterado não existe)";
				response.setStatus(404);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
			
			Monster monster= dao.getMonsterFromName(monstername);
			//Completar com a info a ser alterada
			monster.ailmentVulnerability.poisonVulnerability= Integer.parseInt( request.getParameter("poison") );
			monster.ailmentVulnerability.sleepVulnerability= Integer.parseInt( request.getParameter("sleep") );
			monster.ailmentVulnerability.paralysisVulnerability= Integer.parseInt( request.getParameter("paralysis") );
			monster.ailmentVulnerability.blastVulnerability= Integer.parseInt( request.getParameter("blast") );
			monster.ailmentVulnerability.stunVulnerability= Integer.parseInt( request.getParameter("stun") );
			
			dao.updateMonsterCore(monster);
			
			String message= "200 - OK (O recurso foi alterado com sucesso)";
			response.setStatus(200);
			request.setAttribute("message", message);
			deployJSP(request, response, true);
			return;
			
		}else if( query.equals("modifyelemental") ){
			//devolver 200 em sucesso, 404 se não encontrar
			String monstername= request.getParameter("target");
			
			if ( !dao.checkIfMonsterExists(monstername) ){
				
				String message= "404 - Not Found (O recurso a ser alterado não existe)";
				response.setStatus(404);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
			
			Monster monster= dao.getMonsterFromName(monstername);
			//Completar com a info a ser alterada
			monster.elementalVulnerability.fireVulnerability= Integer.parseInt( request.getParameter("fire") );
			monster.elementalVulnerability.waterVulnerability= Integer.parseInt( request.getParameter("water") );
			monster.elementalVulnerability.thunderVulnerability= Integer.parseInt( request.getParameter("thunder") );
			monster.elementalVulnerability.iceVulnerability= Integer.parseInt( request.getParameter("ice") );
			monster.elementalVulnerability.dragonVulnerability= Integer.parseInt( request.getParameter("dragon") );
			
			dao.updateMonsterCore(monster);
			
			String message= "200 - OK (O recurso foi alterado com sucesso)";
			response.setStatus(200);
			request.setAttribute("message", message);
			deployJSP(request, response, true);
			return;
			
		}else if( query.equals("modifysubspecies") ){
			//devolver 200 em sucesso, 404 se não encontrar, 400 se o nome dado não for um monstro válido
			String monstername= request.getParameter("target");
			
			if ( !dao.checkIfMonsterExists(monstername) ){
				
				String message= "404 - Not Found (O recurso a ser alterado não existe)";
				response.setStatus(404);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
			
			String rootmonstername= request.getParameter("subspecies");
			
			if( rootmonstername.equals("") ){
				
				dao.clearFieldSubspecies(monstername);
				
				String message= "200 - OK (O recurso foi alterado com sucesso)";
				response.setStatus(200);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
			
			if ( !dao.checkIfMonsterExists(rootmonstername) ){
				
				String message= "400 - Bad Request ('"+rootmonstername+"' não existe)";
				response.setStatus(400);
				request.setAttribute("message", message);
				deployJSP(request, response, true);
				return;
			}
			
			dao.setFieldSubspecies(monstername, rootmonstername);
			
			String message= "200 - OK (O recurso foi alterado com sucesso)";
			response.setStatus(200);
			request.setAttribute("message", message);
			deployJSP(request, response, true);
			return;
		}
    }
    
    private void deployJSP(HttpServletRequest request, HttpServletResponse response, boolean admin) throws ServletException, IOException {
    	DAO dao= new DAO();
    	
		String[] tmp= request.getRequestURI().split("/");
    	String monstername= tmp[tmp.length-1];
    	monstername = URLDecoder.decode(monstername, "UTF-8");
		Monster monster= dao.getMonsterFromName(monstername);
		BodyPart[] bodypartlist= dao.getMonsterBodyParts(monstername);
		request.setAttribute("bodypartlist", bodypartlist);
		request.setAttribute("monster", monster);
		
		//Conferir aqui se tou pegando o bodypartlist corretamente;
		/*
		System.out.println("DEBUG PARTLIST");
		System.out.println(bodypartlist.length);
		for (BodyPart part : bodypartlist) {
			System.out.println(part.name);
		}
		*/
    	
    	if(admin){
    		request.getRequestDispatcher("/MonsterViewAdmin.jsp").forward(request, response);	
    	}else{
    		request.getRequestDispatcher("/MonsterView.jsp").forward(request, response);
    	}
    }
}

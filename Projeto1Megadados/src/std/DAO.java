package std;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import monster.AilmentVulnerability;
import monster.BodyPart;
import monster.ElementalVulnerability;
import monster.Monster;
import monster.PhysicalVulnerability;

public class DAO {

	private Connection connection = null;
	
	public DAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection= DriverManager.getConnection("jdbc:mysql://localhost/projetoummegadados?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "12063cb5d72571662926aa355c97abc8a28c87f3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getMonsterNames(){
		String[] ans= new String[128];
		
		String sql= "SELECT label FROM Monster";
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sql);
			
			rs= stmt.executeQuery();
			
			int i=0;
			while( rs.next() ){
				ans[i]= rs.getString("label");
				
				i+= 1;
			}
			ans= Arrays.copyOfRange(ans, 0, i);
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans;
	}
	
	public Monster getMonsterFromName(String monstername){
		PreparedStatement stmt= null;
		String sql= "";
		Monster ans= null;
		ElementalVulnerability eleVul= null;
		AilmentVulnerability ailVul= null;
		
		try{
			sql= "SELECT id_Monster INTO @id_Monster FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			sql= "SELECT * FROM ElementalVulnerability WHERE id_Monster=@id_Monster";
			stmt = connection.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery();
			while( rs.next() ){
				
				eleVul= new ElementalVulnerability(
						rs.getInt("fireVulnerability"),
						rs.getInt("waterVulnerability"),
						rs.getInt("thunderVulnerability"),
						rs.getInt("iceVulnerability"),
						rs.getInt("dragonVulnerability")
						);
			}
			rs.close();
			
			sql= "SELECT * FROM AilmentVulnerability WHERE id_Monster=@id_Monster";
			stmt = connection.prepareStatement(sql);
			rs= stmt.executeQuery();
			while( rs.next() ){
				
				ailVul= new AilmentVulnerability(
						rs.getInt("poisonVulnerability"),
						rs.getInt("sleepVulnerability"),
						rs.getInt("paralysisVulnerability"),
						rs.getInt("blastVulnerability"),
						rs.getInt("stunVulnerability")
						);
			}
			rs.close();
			
			ans= new Monster( monstername, ailVul, eleVul );
			
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ans;
	}
	
	public BodyPart[] getMonsterBodyParts(String monstername){
		PreparedStatement stmt= null;
		String sql= "";
		BodyPart[] ans= new BodyPart[128];
		PhysicalVulnerability tmpVul= null;
		
		try{
			sql= "SELECT id_Monster INTO @id_Monster FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			sql= "SELECT * FROM BodyPart WHERE idMonster= @id_Monster";
			stmt = connection.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery();
			
			int i= 0;
			while( rs.next() ){
				
				ans[i]= new BodyPart(
						rs.getString("label"),
						rs.getBoolean("isBreakable"),
						rs.getBoolean("isSeverable"),
						rs.getBoolean("isHidden") );
				
				ans[i].setId(rs.getInt("id_BodyPart"));
				
				sql= "SELECT * FROM PhysicalVulnerability WHERE id_bodypart= ?";
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, rs.getInt("id_bodypart") );
				
				ResultSet rs2= stmt.executeQuery();
				
				if( rs2.next() ){
					tmpVul= new PhysicalVulnerability(
							rs2.getInt("bluntVulnerability"),
							rs2.getInt("severingVulnerability"),
							rs2.getInt("shotVulnerability") );
				}
				
				ans[i].setPhysicalVulnerability(tmpVul);
				
				//TODO: tratar o opcional Pair
				
				rs2.close();
				i+= 1;
			}
			
			
			ans= Arrays.copyOfRange(ans, 0, i);
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ans;
	}
	
	public boolean checkIfMonsterExists(String monstername){
		PreparedStatement stmt= null;
		String sql= "";
		
		boolean ans= false;
		try{
			sql = "SELECT * FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			
			ResultSet rs= stmt.executeQuery();	
			ans= rs.next();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ans;
	}
	
	public void deleteBodyPartByID(int id){
		
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			/*
			sql= "START TRANSACTION;\n";
			sql+= "DELETE FROM PhysicalVulnerability WHERE id_BodyPart = ?;\n";
			sql+= "DELETE FROM BodyPart WHERE id_BodyPart = ?;\n";
			sql+= "COMMIT;\n";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			stmt.execute();
			*/
			
			
			sql= "START TRANSACTION";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "DELETE FROM PhysicalVulnerability WHERE id_BodyPart = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
			sql= "DELETE FROM BodyPart WHERE id_BodyPart = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
			sql= "COMMIT";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			//tentar fazer o Rollback
			
			try{
				sql="ROLLBACK";
				stmt = connection.prepareStatement(sql);
				
				stmt.execute();
				stmt.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public void createMonsterByName(String monstername){
		
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			sql= "START TRANSACTION";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "INSERT INTO Monster (label) VALUES(?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			sql= "INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) "
				+"VALUES(0, 0, 0, 0, 0)";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) "
				+"VALUES(0, 0, 0, 0, 0)";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "COMMIT";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			//tentar fazer o Rollback
			
			try{
				sql="ROLLBACK";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, monstername);
				
				stmt.execute();
				stmt.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		return;
	}
	
	public void deleteMonsterByName(String monstername){
		
		PreparedStatement stmt= null;
		String sql= "";
		try{
			//Primeiro apagar as partes
			sql= "SELECT id_Monster INTO @temp_label FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			sql= "SELECT id_BodyPart FROM BodyPart WHERE idMonster= @temp_label";
			stmt = connection.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery();
			
			while( rs.next() ){
				deleteBodyPartByID( rs.getInt("id_BodyPart") );
			}
			
			sql= "START TRANSACTION";
			stmt = connection.prepareStatement(sql);
			stmt.execute();	
			
			sql= "DELETE FROM ElementalVulnerability WHERE id_Monster = @temp_label";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "DELETE FROM AilmentVulnerability WHERE id_Monster = @temp_label";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "DELETE FROM Monster WHERE id_Monster = @temp_label";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "COMMIT";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			//tentar fazer o Rollback
			
			try{
				sql="ROLLBACK";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, monstername);
				
				stmt.execute();
				stmt.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public boolean validateUser(User user) {
		//Retorna false se o signUp falhar (par login e hash incorreto)
		//pesquisar pelo par login+senha
		
		PreparedStatement stmt= null;
		String sql= "";
		boolean ans= false;
		try {
			sql = "SELECT * FROM Admin WHERE login_name=? AND pass_hash=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getLoginName());
			stmt.setString(2, user.getPassHash());
			ResultSet rs= stmt.executeQuery();
			
			ans= rs.next();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Checando a validação do usuário: "+Boolean.toString(ans));
		return ans;
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
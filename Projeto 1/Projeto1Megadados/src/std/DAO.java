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
	
	//CHECKs
	
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
	
	//SELECTs
	
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
	
	public Monster getMonster(int monsterId ){
		PreparedStatement stmt= null;
		String sql= "";
		Monster ans= null;
		ElementalVulnerability eleVul= null;
		AilmentVulnerability ailVul= null;
		
		try{		
			sql= "SELECT * FROM Monster "
					+"INNER JOIN ElementalVulnerability ON Monster.id_Monster=ElementalVulnerability.id_Monster "
					+"INNER JOIN AilmentVulnerability ON Monster.id_Monster=AilmentVulnerability.id_Monster "
					+"WHERE Monster.id_Monster=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, monsterId);
			ResultSet rs= stmt.executeQuery();
			
			while( rs.next() ){
				eleVul= new ElementalVulnerability(
						rs.getInt("fireVulnerability"),
						rs.getInt("waterVulnerability"),
						rs.getInt("thunderVulnerability"),
						rs.getInt("iceVulnerability"),
						rs.getInt("dragonVulnerability")
						);
				
				ailVul= new AilmentVulnerability(
						rs.getInt("poisonVulnerability"),
						rs.getInt("sleepVulnerability"),
						rs.getInt("paralysisVulnerability"),
						rs.getInt("blastVulnerability"),
						rs.getInt("stunVulnerability")
						);
				
				ans= new Monster( rs.getString("label"), ailVul, eleVul );
			}
			rs.close();
			
			
			
			//checar e carregar subspécie se tiver
			
			sql= "SELECT idSubspeciesOf FROM Monster WHERE Monster.id_Monster=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, monsterId);
			rs= stmt.executeQuery();
			
			while( rs.next() ){
			
				if( rs.getObject("idSubspeciesOf") != null ){
					Monster rootmonster= getMonster( rs.getInt("idSubspeciesOf") );
					ans.setSubspeciesOf(rootmonster);
				}
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
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
			
			sql= "SELECT * FROM Monster "
					+"INNER JOIN ElementalVulnerability ON Monster.id_Monster=ElementalVulnerability.id_Monster "
					+"INNER JOIN AilmentVulnerability ON Monster.id_Monster=AilmentVulnerability.id_Monster "
					+"WHERE Monster.id_Monster=@id_Monster";
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
				
				ailVul= new AilmentVulnerability(
						rs.getInt("poisonVulnerability"),
						rs.getInt("sleepVulnerability"),
						rs.getInt("paralysisVulnerability"),
						rs.getInt("blastVulnerability"),
						rs.getInt("stunVulnerability")
						);
				
				ans= new Monster( monstername, ailVul, eleVul );
			}
			rs.close();
			
			//checar e carregar subspécie se tiver
			
			sql= "SELECT idSubspeciesOf FROM Monster WHERE Monster.id_Monster=@id_Monster";
			stmt = connection.prepareStatement(sql);
			rs= stmt.executeQuery();
			
			while( rs.next() ){
			
				if( rs.getObject("idSubspeciesOf") != null ){
					Monster rootmonster= getMonster( rs.getInt("idSubspeciesOf") );
					ans.setSubspeciesOf(rootmonster);
				}
			}
			rs.close();
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
	
	//DELETEs
	
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
				
				stmt.execute();
				stmt.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
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
	
	//CREATEs
	
	public void createBodyPart(String monstername, BodyPart bodyPart){
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			sql= "START TRANSACTION";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			sql= "SELECT id_Monster INTO @id_Monster FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			sql="INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, bodyPart.physicalVulnerability.bluntVulnerability);
			stmt.setInt(2, bodyPart.physicalVulnerability.severingVulnerability);
			stmt.setInt(3, bodyPart.physicalVulnerability.shotVulnerability);
			stmt.execute();
			
			sql="INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(?, @id_Monster, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, bodyPart.name);
			stmt.setBoolean(2, bodyPart.isBreakable);
			stmt.setBoolean(3, bodyPart.isHidden);
			stmt.setBoolean(4, bodyPart.isSeverable);
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
				
				stmt.execute();
				stmt.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		return;
	}
	
	//UPDATEs
	
	public void clearFieldSubspecies(String monstername){
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			sql= "UPDATE Monster SET idSubspeciesOf=NULL WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setFieldSubspecies(String monstername, String rootmonstername){
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			sql= "SELECT id_Monster INTO @id_Monster FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, rootmonstername);
			stmt.execute();
			
			sql= "UPDATE Monster SET idSubspeciesOf=@id_Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monstername);
			stmt.execute();
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateMonsterName(String oldname, String newname){
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			sql= "UPDATE Monster SET label=? WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, newname);
			stmt.setString(2, oldname);
			stmt.execute();
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateMonsterCore(Monster monster){
		
		PreparedStatement stmt= null;
		String sql= "";
		
		try{
			sql= "SELECT id_Monster INTO @id_Monster FROM Monster WHERE label=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, monster.getName());
			stmt.execute();
			
			sql= "UPDATE Monster "
					+"INNER JOIN AilmentVulnerability ON Monster.id_Monster=AilmentVulnerability.id_Monster "
					+"INNER JOIN ElementalVulnerability ON Monster.id_Monster=ElementalVulnerability.id_Monster "
					+"SET label=?, "
						+" poisonVulnerability=?, sleepVulnerability=?, paralysisVulnerability=?, blastVulnerability=?, stunVulnerability=?, "
						+" fireVulnerability=?, waterVulnerability=?, thunderVulnerability=?, iceVulnerability=?, dragonVulnerability=? "
					+"WHERE Monster.id_Monster=@id_Monster";
			stmt = connection.prepareStatement(sql);
			//Lá vamos nós
			stmt.setString(1, monster.getName());
			
			stmt.setInt(2, monster.ailmentVulnerability.poisonVulnerability);
			stmt.setInt(3, monster.ailmentVulnerability.sleepVulnerability);
			stmt.setInt(4, monster.ailmentVulnerability.paralysisVulnerability);
			stmt.setInt(5, monster.ailmentVulnerability.blastVulnerability);
			stmt.setInt(6, monster.ailmentVulnerability.stunVulnerability);
			
			stmt.setInt(7, monster.elementalVulnerability.fireVulnerability);
			stmt.setInt(8, monster.elementalVulnerability.waterVulnerability);
			stmt.setInt(9, monster.elementalVulnerability.thunderVulnerability);
			stmt.setInt(10, monster.elementalVulnerability.iceVulnerability);
			stmt.setInt(11, monster.elementalVulnerability.dragonVulnerability);
			//fim
			stmt.execute();
			
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//close
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
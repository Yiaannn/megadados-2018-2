package monster;

public class Monster{

	String name;
	Monster subspeciesOf;
	
	AilmentVulnerability ailmentVulnerability;
	ElementalVulnerability elementalVulnerability;
	BodyPart[] bodyParts;
	
	
	public Monster(String name, AilmentVulnerability ailment, ElementalVulnerability elemental){
		//setar aqui os parametros obrigatórios
		
		this.name= name;
		this.ailmentVulnerability= ailment;
		this.elementalVulnerability= elemental;
	}
	
	//Gets porque o jsp não me deixa fazer de outra forma
	//Todos os gets como String para facilitar na hora de montar o jsp
	public String getName(){
		return this.name;
	}
	
	public String getPoisonVulnerability(){
		return this.ailmentVulnerability.getPoisonVulnerability();
	}
	
	public String getSleepVulnerability(){
		return this.ailmentVulnerability.getSleepVulnerability();
	}
	
	public String getParalysisVulnerability(){
		return this.ailmentVulnerability.getParalysisVulnerability();
	}
	
	public String getBlastVulnerability(){
		return this.ailmentVulnerability.getBlastVulnerability();
	}
	
	public String getStunVulnerability(){
		return this.ailmentVulnerability.getStunVulnerability();
	}
	
	public String getFireVulnerability(){
		return this.elementalVulnerability.getFireVulnerability();
	}
	
	public String getWaterVulnerability(){
		return this.elementalVulnerability.getWaterVulnerability();
	}
	
	public String getThunderVulnerability(){
		return this.elementalVulnerability.getThunderVulnerability();
	}
	
	public String getIceVulnerability(){
		return this.elementalVulnerability.getIceVulnerability();
	}
	
	public String getDragonVulnerability(){
		return this.elementalVulnerability.getDragonVulnerability();
	}
}
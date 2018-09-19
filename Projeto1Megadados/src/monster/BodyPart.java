package monster;

public class BodyPart {

	private int id;
	
	String name;
	int hardness; //precisa ser traduzido, é como um nome por id
	boolean isBreakable;
	boolean isSeverable;
	boolean isHidden;
	BodyPart pair;
	PhysicalVulnerability physicalVulnerability;
	
	/*
	public BodyPart(int name, int hardness, boolean isBreakable, boolean isSeverable, boolean isHidden){
		this.name= name;
		this.hardness= hardness;
		this.isBreakable= isBreakable;
		this.isSeverable= isSeverable;
		this.isHidden= isHidden;
	}
	*/
	
	public BodyPart(String name, boolean isBreakable, boolean isSeverable, boolean isHidden){
		this.name= name;
		this.isBreakable= isBreakable;
		this.isSeverable= isSeverable;
		this.isHidden= isHidden;
	}
	
	//Sets opcionais:
	public void setPair(BodyPart bodyPart){
		this.pair= bodyPart;
	}
	
	public void setPhysicalVulnerability(PhysicalVulnerability physVul){
		this.physicalVulnerability= physVul;
	}
	
	public void setId(int id){
		this.id= id;
	}
	
	
	//Gets porque o jsp não me deixa fazer de outra forma
	//Todos os gets como String para facilitar na hora de montar o jsp
	public String getName(){
		return this.name;
	}
	
	public String getIsBreakable(){
		String jspmessage= "Quebrável: ";
		
		if(this.isBreakable){
			jspmessage+="Sim";
		}else{
			jspmessage+="Não";
		}
		
		return jspmessage;
	}
	
	public String getIsSeverable(){
		String jspmessage= "Cortável: ";
		
		if(this.isSeverable){
			jspmessage+="Sim";
		}else{
			jspmessage+="Não";
		}
		
		return jspmessage;
	}
	
	public String getBluntVulnerability(){
		int alias= this.physicalVulnerability.bluntVulnerability;
		String message="Vulnerabilidade à Impacto: ";
		
		if(alias == 0){
			message+="🚫";
		}else if(alias == 1){
			message+="⭐";
		}else if(alias == 2){
			message+="⭐⭐";
		}else if(alias == 3){
			message+="⭐⭐⭐";
		}
		
		return message;
	}
	
	public String getSeveringVulnerability(){
		int alias= this.physicalVulnerability.severingVulnerability;
		String message="Vulnerabilidade à Corte: ";
		
		if(alias == 0){
			message+="🚫";
		}else if(alias == 1){
			message+="⭐";
		}else if(alias == 2){
			message+="⭐⭐";
		}else if(alias == 3){
			message+="⭐⭐⭐";
		}
		
		return message;
	}
	
	public String getShotVulnerability(){
		int alias= this.physicalVulnerability.shotVulnerability;
		String message="Vulnerabilidade à Projétil: ";
		
		if(alias == 0){
			message+="🚫";
		}else if(alias == 1){
			message+="⭐";
		}else if(alias == 2){
			message+="⭐⭐";
		}else if(alias == 3){
			message+="⭐⭐⭐";
		}
		
		return message;
	}
	
	public int getId(){
		return this.id;
	}
}

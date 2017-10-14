package mensajeria;

import java.io.Serializable;
import java.util.Map;

public class PaqueteNPCs extends Paquete implements Serializable, Cloneable {


	private Map<Integer, PaqueteNPC> NPCs;

	public PaqueteNPCs(){

	}

	public PaqueteNPCs(Map<Integer, PaqueteNPC> NPCs){
		this.NPCs = NPCs;
	}

	public Map<Integer, PaqueteNPC> getNPCs(){
		return NPCs;
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
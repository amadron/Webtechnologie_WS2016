package pokeklon.model.impl.types;

import pokeklon.model.IType;
import util.TypeEnum;

public class TypeFire implements IType{
	private final String type = "Fire";
	
	@Override
	public String getName(){
		return type;
	}
	
	@Override
	public TypeEnum getType() {
		return TypeEnum.FIRE;
	}

	@Override
	public TypeEnum getWeak() {
		return TypeEnum.WATER;
	}

	@Override
	public TypeEnum getStrength() {
		return TypeEnum.PLANT;
	}

	

}

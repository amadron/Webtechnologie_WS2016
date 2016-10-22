package pokeklon.model.impl.types;

import pokeklon.model.IType;
import util.TypeEnum;

public class TypeWater implements IType {

	private final String type = "Water";
	
	@Override
	public TypeEnum getType() {
		return TypeEnum.WATER;
	}

	@Override
	public TypeEnum getWeak() {
		return TypeEnum.PLANT;
	}

	@Override
	public TypeEnum getStrength() {
		return TypeEnum.FIRE;
	}

	@Override
	public String getName() {
		return type;
	}

}

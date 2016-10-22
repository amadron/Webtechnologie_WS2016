package pokeklon.model.impl.types;

import pokeklon.model.IType;
import util.TypeEnum;

public class TypePlant implements IType {

	private final String type = "Plant";

	@Override
	public TypeEnum getType() {
		return TypeEnum.PLANT;
	}

	@Override
	public TypeEnum getWeak() {
		return TypeEnum.FIRE;
	}

	@Override
	public TypeEnum getStrength() {
		return TypeEnum.WATER;
	}

	@Override
	public String getName() {
		return type;
	}

}

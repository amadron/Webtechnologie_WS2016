package pokeklon.model.impl.types;

import pokeklon.model.IType;
import util.TypeEnum;

public class TypeNormal implements IType {

	private final String type = "Normal";
	@Override
	public TypeEnum getType() {
		return TypeEnum.NORMAL;
	}

	@Override
	public TypeEnum getWeak() {
		return null;
	}

	@Override
	public TypeEnum getStrength() {
		return null;
	}

	@Override
	public String getName() {
		return type;
	}

}

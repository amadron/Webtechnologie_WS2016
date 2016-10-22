package pokeklon;

import pokeklon.controller.IMonsterFactory;
import pokeklon.controller.IPokeklonController;

import com.google.inject.AbstractModule;

public class PokeklonModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IMonsterFactory.class).to(pokeklon.controller.impl.MonsterFactory.class);
		bind(IPokeklonController.class).to(pokeklon.controller.impl.PokeklonController.class);
	}

}

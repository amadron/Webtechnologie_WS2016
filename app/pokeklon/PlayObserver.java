package pokeklon;

import play.mvc.WebSocket;
import pokeklon.controller.impl.MonsterFactory;
import util.observer.*;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class PlayObserver implements IObserver{
	public PlayObserver()
	{
		Pokeklon.controller.addObserver(this);
	}
	@Override
	public void update(Event e) {
		//String updateString = "update:" + Pokeklon.controller.getGameStat();
		Gson gson = new GsonBuilder().create();
		WUIStatus state = new WUIStatus();
		state.update = Pokeklon.controller.getGameStat();
		state.tui = Pokeklon.tui.output;
		state.monP1 = Pokeklon.controller.getCurrentP1Mon();
		if(state.monP1 != null)
			state.monP1No = Pokeklon.controller.getMonsterNumber(state.monP1.getName());
		state.monP2 = Pokeklon.controller.getCurrentP2Mon();
		if(state.monP2 != null)
			state.monP2No = Pokeklon.controller.getMonsterNumber(state.monP2.getName());
		String updateString =  gson.toJson(state);
		//System.out.println("Updating wui: " + updateString);
		PlayFunctions.webSocketOut.write(updateString);
	}

}

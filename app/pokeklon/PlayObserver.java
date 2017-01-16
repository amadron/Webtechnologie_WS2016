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
		String updateString =  PlayFunctions.getWuiState();
		if(PlayFunctions.webSocketOutP1 != null)
		{
			PlayFunctions.webSocketOutP1.write(updateString);
		}
		if(PlayFunctions.webSocketOutP2 != null)
		{
			PlayFunctions.webSocketOutP2.write(updateString);
		}
	}

}

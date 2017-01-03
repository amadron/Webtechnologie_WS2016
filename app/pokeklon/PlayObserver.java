package pokeklon;

import play.mvc.WebSocket;
import util.observer.*;

public class PlayObserver implements IObserver{

	public PlayObserver()
	{
		Pokeklon.controller.addObserver(this);
	}
	@Override
	public void update(Event e) {
		String updateString = "update:" + Pokeklon.controller.getGameStat();
		System.out.println("Updating wui: " + updateString);
		PlayFunctions.webSocketOut.write(updateString);
	}

}

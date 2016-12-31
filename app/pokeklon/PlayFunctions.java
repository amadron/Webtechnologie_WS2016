package pokeklon;

import play.mvc.*;

import play.mvc.Result;
import play.mvc.WebSocket;

import pokeklon.Pokeklon;
import pokeklon.controller.impl.MonsterFactory;
import pokeklon.model.IMonster;
import views.html.*;
import javax.json.*;

import java.io.IOException;
import java.util.List;

public class PlayFunctions extends Controller{
	
	public static WebSocket.In<String> guiSocketIn = null;
	public static WebSocket.Out<String> guiSocketOut = null;
	
	MonsterFactory factory = new MonsterFactory();
	
	public Result getTuiState()
	{
		String tuiText = Pokeklon.tui.output;
		tuiText = tuiText.replace("\n", "<br>");
		return ok(views.html.tui.render(tuiText));
	}
	
	public Result getMonsterList()
	{
		List<IMonster> monList = factory.getMonsterListFull();
		return ok(views.html.monsterlist.render(monList));
		
	}
	
	public Result getGuiState()
	{
		return ok(views.html.canvas.render());
	}
	
	public LegacyWebSocket<String> tuiSocket()
	{
		return WebSocket.whenReady((in, out) -> {
		    Pokeklon.inSocket = in;
		    Pokeklon.outSocket = out;
		    in.onMessage(con ->
		    {
		    	Pokeklon.tui.processInputLine(con);
		    }
		    );
		    out.write(Pokeklon.tui.output.replace("\n", "<br>"));
		});
	}
	
	public LegacyWebSocket<String> guiSocket()
	{
		System.out.println("Open Socket!");
		return WebSocket.whenReady((in, out) -> {
			guiSocketOut = out;
			guiSocketIn = in;
			out.write(getCurrentMonster());
		});
	}
	
	public String getCurrentAttacks()
	{
		String json = "";
		return json;
	}
	
	public static String getCurrentMonster()
	{
		String json = "";
		IMonster p1Mon = Pokeklon.controller.getCurrentP1Mon();
		IMonster p2Mon = Pokeklon.controller.getCurrentP2Mon();
		JsonObject mon1 = getCurrentMonInfo(p1Mon);
		JsonObject mon2 = getCurrentMonInfo(p2Mon);
		JsonArray jObject = Json.createArrayBuilder().add(mon1).add(mon2).build();
		return jObject.toString();
	}
	
	public static JsonObject getCurrentMonInfo(IMonster mon)
	{
		JsonObject ret = Json.createObjectBuilder()
				.add("name", mon.getName())
				.add("maxHealth", mon.getMaxLife())
				.add("health", mon.getLife())
				.add("image", Pokeklon.controller.getMonsterNumber(mon.getName()))
				.build();
		return ret;
	}
	
	public String getItems()
	{
		String json = "";
		return json;
	}
	
	public String getMonster()
	{
		String ret = "";
		return ret;
	}
}

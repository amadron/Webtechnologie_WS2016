package pokeklon;

import play.mvc.*;

import play.mvc.Result;
import play.mvc.WebSocket;

import pokeklon.Pokeklon;
import pokeklon.controller.impl.MonsterFactory;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import views.html.*;
import javax.json.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayFunctions extends Controller{
	
	public static WebSocket.In<String> guiSocketIn = null;
	public static WebSocket.Out<String> guiSocketOut = null;
	public static WebSocket.In<String> webSocketIn = null;
	public static WebSocket.Out<String> webSocketOut = null;
	
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
	
	public Result getCurrMonsterList()
	{
		List<IMonster> monList = new ArrayList<IMonster>(Pokeklon.controller.getCurrentPlayerMonsterList());
		int[] numb = new int[monList.size()];
		for(int i = 0; i < numb.length; i++)
		{
			numb[i] = factory.getMonsterNumber(monList.get(i).getName());
		}
		return ok(views.html.currmonsterlist.render(monList,numb));
	}
	
	public Result getItemList()
	{
		return ok(views.html.itemlist.render(Pokeklon.controller.getItemList()));
	}
	
	public Result getAttackList()
	{
		return ok(views.html.attacklist.render(Pokeklon.controller.getCurrentAttackList()));
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
	
	public LegacyWebSocket<String> getSocket()
	{
		System.out.println("Open Socket!");
		return WebSocket.whenReady((in, out) -> {
			webSocketOut = out;
			webSocketIn = in;
			in.onMessage(con ->
		    {
		    	String mes = (String) con;
		    	System.out.println("Controller got Message: " + mes);
		    	if(mes.equals("startGame"))
		    	{
		    		if(Pokeklon.controller == null)
		    		{
		    			PokeklonThread thread = new PokeklonThread();
		    			thread.start();
		    		}
		    	}
		    	if(mes.equals("quickGame"))
		    	{
		    		Pokeklon.controller.quickGame();
		    	}
		    	if(mes.equals("2v2"))
		    	{
		    		Pokeklon.controller.newGame(2);
		    	}
		    	if(mes.equals("4v4"))
		    	{
		    		Pokeklon.controller.newGame(4);
		    	}
		    	if(mes.equals("showStart"))
		    	{
		    		Pokeklon.controller.getMain();
		    	}
		    	if(mes.equals("showBattle"))
				{
					Pokeklon.controller.battleMenu();
				}
		    	if(mes.equals("showItem"))
		    	{
		    		Pokeklon.controller.itemMenu();
		    	}
		    	if(mes.equalsIgnoreCase("showAttack"))
		    	{
		    		Pokeklon.controller.attackMenu();
		    	}
		    	if(mes.equalsIgnoreCase("showChange"))
		    	{
		    		Pokeklon.controller.changeMonsterMenu();
		    	}
		    	if(mes.contains("attack"))
		    	{
		    		String attack = mes.split(":")[1];
		    		for(IAttack att : Pokeklon.controller.getCurrentAttackList())
		    		{
		    			if(att.getName().equals(attack))
		    			{
		    				Pokeklon.controller.attack(att);
		    			}
		    		}
		    	}
		    	if(mes.contains("item"))
		    	{
		    		String item = mes.split(":")[1];
		    		for(IItem it : Pokeklon.controller.getCurrentPlayer().getItemList())
		    		{
		    			if(it.getName().equals(item))
		    			{
		    				Pokeklon.controller.useItem(it);
		    			}
		    		}
		    	}
		    	if(mes.contains("changemon"))
		    	{
		    		String monster = mes.split(":")[1];
		    		for(IMonster mon : Pokeklon.controller.getCurrentPlayerMonsterList())
		    		{
		    			System.out.println("Monster in list: " +  mon.getName());
		    			if(mon.getName().equals(monster))
		    			{
		    				System.out.println("Chane monster: " + monster);
		    				Pokeklon.controller.changeMonster(mon);
		    			}
		    		}
		    	}
		    }
		    );
			String updateString = "update:" + Pokeklon.controller.getGameStat();
			out.write(updateString);
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

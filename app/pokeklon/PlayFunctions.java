package pokeklon;

import play.mvc.*;

import play.mvc.Result;
import play.mvc.WebSocket;

import pokeklon.Pokeklon;
import pokeklon.controller.IPokeklonController;
import pokeklon.controller.impl.MonsterFactory;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.view.gui.PokeklonFrame;
import pokeklon.view.tui.TextUI;
import views.html.*;
import javax.json.*;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PlayFunctions extends Controller{
	
	public static WebSocket.In<String> guiSocketIn = null;
	public static WebSocket.Out<String> guiSocketOut = null;
	public static WebSocket.In<String> webSocketIn = null;
	public static WebSocket.Out<String> webSocketOut = null;
	
	public static MonsterFactory factory = new MonsterFactory();
	
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
			    			//startGame();
			    		}
			    	}
			    	
			    	if(mes.contains("confirmMon"))
			    	{
			    		String arr = mes.split(":")[1];
			    		String nos[] = arr.split(",");
			    		int noArr[] = new int[nos.length];
			    		for(int i = 0;i < nos.length; i++)
			    		{
			    			noArr[i] = Integer.parseInt(nos[i].replaceAll("[\\D]","")) - 1;
			    		}
			    		Pokeklon.controller.addMonster(noArr);
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
			    	if(mes.contains("tui"))
			    	{
			    		String inp = mes.split(":")[1];
			    		Pokeklon.tui.processInputLine(inp);
			    	}
			    	
			    	if(mes.contains("changemon"))
			    	{
			    		String monster = mes.split(":")[1];
			    		for(IMonster mon : Pokeklon.controller.getCurrentPlayerMonsterList())
			    		{
			    			if(mon.getName().equals(monster))
			    			{
			    				Pokeklon.controller.changeMonster(mon);
			    			}
			    		}
			    	}
			    }
		    );
			in.onClose( () -> 
				{
					System.out.println("Connection closed!");
				}
			);
			
			String updateString = "update:" + Pokeklon.controller.getGameStat();
			out.write(updateString);
		});
	}
	
	void startGame()
	{
		//Dependency Injection
				Injector injector = Guice.createInjector(new PokeklonModule());
				//Build Application
				
				Pokeklon.controller = injector.getInstance(IPokeklonController.class);
					//GUI
				@SuppressWarnings("unused")
				PokeklonFrame gui = injector.getInstance(PokeklonFrame.class);
					//TUI
				Pokeklon.tui = injector.getInstance(TextUI.class);
				
				Pokeklon.observer = new PlayObserver();
				//Create game
				Pokeklon.controller.getMain();
				
				//read input from tui
				boolean running = true;
				InputStreamReader ips = new InputStreamReader(System.in);
				Pokeklon.reader = new BufferedReader(ips);
				/*
				while(running){
					try {
						Pokeklon.tui.processInputLine(Pokeklon.reader.readLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} */
	}
}

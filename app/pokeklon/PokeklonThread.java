package pokeklon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

import pokeklon.controller.IPokeklonController;
import pokeklon.view.gui.PokeklonFrame;
import pokeklon.view.tui.TextUI;

public class PokeklonThread  extends Thread{
	
	public void run()
	{
		//Dependency Injection
		Injector injector = Guice.createInjector(new PokeklonModule());
		//Build Application
		
		Pokeklon.controller = injector.getInstance(IPokeklonController.class);
			//GUI
		//@SuppressWarnings("unused")
		//Pokeklon.gui = injector.getInstance(PokeklonFrame.class);
			//TUI
		Pokeklon.tui = injector.getInstance(TextUI.class);
		
		Pokeklon.observer = new PlayObserver();
		//Create game
		Pokeklon.controller.getMain();
		
		//read input from tui
		boolean running = true;
		InputStreamReader ips = new InputStreamReader(System.in);
		Pokeklon.reader = new BufferedReader(ips);
		while(running){
			try {
				Pokeklon.tui.processInputLine(Pokeklon.reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

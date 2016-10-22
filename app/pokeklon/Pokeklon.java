package pokeklon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pokeklon.controller.impl.PokeklonController;
import pokeklon.view.gui.PokeklonFrame;
import pokeklon.view.tui.TextUI;

import com.google.inject.Guice;
import com.google.inject.Injector;

import pokeklon.controller.IPokeklonController;

 public class Pokeklon {
	
	private Pokeklon() {}
	
	private static BufferedReader reader;
	
	public static void main(String[] args) throws IOException{
		//Dependency Injection
		Injector injector = Guice.createInjector(new PokeklonModule());
		//Build Application
		IPokeklonController controller = new PokeklonController();//injector.getInstance(IPokeklonController.class);
			//GUI
		@SuppressWarnings("unused")
		PokeklonFrame gui = new PokeklonFrame(controller);//injector.getInstance(PokeklonFrame.class);
			//TUI
		TextUI tui = injector.getInstance(TextUI.class);
		
		//Create game
		controller.getMain();
		
		//read input from tui
		boolean running = true;
		InputStreamReader ips = new InputStreamReader(System.in);
		reader = new BufferedReader(ips);
		/*while(running){
			tui.processInputLine(reader.readLine());
		}*/
	}
}

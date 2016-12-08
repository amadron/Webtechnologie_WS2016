package pokeklon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pokeklon.controller.impl.PokeklonController;
import pokeklon.view.gui.PokeklonFrame;
import pokeklon.view.tui.TextUI;

import com.google.inject.Guice;
import com.google.inject.Injector;

import play.mvc.WebSocket;
import pokeklon.controller.IPokeklonController;

 public class Pokeklon {
	
	private Pokeklon() {}
	public static BufferedReader reader;
	public static IPokeklonController controller;
	public static TextUI tui;
	public static WebSocket.In<String> inSocket = null;
	public static WebSocket.Out<String> outSocket = null;
	
	public static void main(String[] args) throws IOException
	{
		Runnable runnable = new Runnable()
		{
			public void run()
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
				
				//Create game
				Pokeklon.controller.getMain();
				
				//read input from tui
				boolean running = true;
				InputStreamReader ips = new InputStreamReader(System.in);
				reader = new BufferedReader(ips);
				while(running){
					try {
						tui.processInputLine(Pokeklon.reader.readLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}

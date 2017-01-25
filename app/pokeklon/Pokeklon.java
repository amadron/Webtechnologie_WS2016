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
	public static PokeklonFrame gui;
	public static WebSocket.In<String> inSocket = null;
	public static WebSocket.Out<String> outSocket = null;
	public static PlayObserver observer = null;
	
}

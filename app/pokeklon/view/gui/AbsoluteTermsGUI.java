package pokeklon.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

public final class AbsoluteTermsGUI {
	private static final int TEN = 10;


	private AbsoluteTermsGUI() {}
	public static final int FIVE = 5;

	public static final int ONE_HUNDRED = 100;
	
	public static final Color BACKGROUND_COLOR = new Color(255,255,255);
	
	public static final int WINDOW_WIDTH = 600;
	
	public static final int WINDOW_HEIGHT = 600;
	
	public static final int BUTTON_WIDTH = 150;
	
	public static final int BUTTON_HEIGHT = 50;

	public static final Dimension BIG_PANE = new Dimension(600, 400);
	
	public static final Dimension SMALL_PANE = new Dimension(600, 190);
	
	public static final Dimension STATAUS_LINE = new Dimension(600, 10);

	public static final Dimension WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	public static final Dimension BUTTON_SIZE = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

	public static final Dimension J_PROGRESS_BAR_SIZE = new Dimension((AbsoluteTermsGUI.WINDOW_WIDTH/2) - TEN, BUTTON_HEIGHT);
	
	public static final File LOGO_PATH = new File("app/pokeklon/data/logo.png");
	
	public static final int TWO = 2;
	
	public static final int FOUR = 4;
}

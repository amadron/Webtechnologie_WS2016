package pokeklon;

import play.mvc.*;
import play.mvc.Result;
import play.mvc.WebSocket;

import pokeklon.Pokeklon;
import views.html.*;

import java.io.IOException;

public class PlayFunctions extends Controller{
	
	public Result getTuiState()
	{
		String tuiText = Pokeklon.tui.output;
		tuiText = tuiText.replace("\n", "<br>");
		return ok(views.html.tui.render(tuiText));
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
}

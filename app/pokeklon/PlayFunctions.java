package pokeklon;

import play.mvc.*;
import play.mvc.Result;

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
}

package in.abhinavmishra.jchess;

import com.badlogic.gdx.Game;
import in.abhinavmishra.jchess.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ChessGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}

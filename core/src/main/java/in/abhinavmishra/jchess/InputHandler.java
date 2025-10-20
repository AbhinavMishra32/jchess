package in.abhinavmishra.jchess;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
    Board board;

    public InputHandler(Board board) {
        this.board = board;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(Integer.toString(screenX) + " " + Integer.toString(screenY) + " " + Integer.toString(pointer));
        System.out.println(board.getSquareAt(0,0).isInSquare(screenX, screenY));
        return super.touchDragged(screenX, screenY, pointer);
    }
}

package in.abhinavmishra.jchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
    Board board;
    Square selectedSquare;

    public InputHandler(Board board) {
        this.board = board;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int correctedY = Gdx.graphics.getHeight() - screenY;

        System.out.println(Integer.toString(screenX) + " " + Integer.toString(correctedY) + " " + Integer.toString(pointer));
        System.out.println(board.getSquareAt(0,3).isInSquare(screenX, correctedY));
        board.getSquares().forEach(squares -> {squares.forEach(square -> {
            if (square.isInSquare(screenX, correctedY)) {
                selectedSquare = square;
                if (selectedSquare.getPiece() != null) {
                    System.out.println(selectedSquare.getPiece().getName());
                    selectedSquare.getPiece().setSelected(true);
                    if (selectedSquare.getPiece().isSelected) {
                        selectedSquare.getPiece().setX(screenX);
                        selectedSquare.getPiece().setY(correctedY);
                    }
                }
            }
        });});

        return super.touchDragged(screenX, screenY, pointer);
    }
}

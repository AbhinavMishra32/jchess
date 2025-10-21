package in.abhinavmishra.jchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;

public class InputHandler extends InputAdapter {
    Board board;
    Square selectedSquare;
    Piece selectedPiece;
    Logger log;
    Integer deltaX;
    Integer deltaY;

    public InputHandler(Board board) {
        this.board = board;
        this.log = new Logger("InputHandler");
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int correctedY = Gdx.graphics.getHeight() - screenY;

        board.getSquares().forEach(squares -> {squares.forEach(square -> {
            if (square.isInSquare(screenX, correctedY) && square.getPiece() != null) {
                selectedSquare = square;
                selectedSquare.getPiece().setSelected(true);
                selectedPiece = selectedSquare.getPiece();
                selectedPiece.setSize((int) selectedSquare.getSize());
                deltaX = selectedSquare.getPiece().getX() - screenX;
                deltaY = selectedSquare.getPiece().getY() - correctedY;
            }
        });});
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int correctedY = Gdx.graphics.getHeight() - screenY;
        if (selectedPiece != null && selectedPiece.isSelected && selectedPiece.isInPiece(screenX, correctedY)) {
            selectedPiece.setX(screenX + deltaX);
            selectedPiece.setY(correctedY + deltaY);
        }
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (selectedSquare != null && selectedPiece != null) {
            // put the piece in the closest square when we stop dragging
//            selectedPiece.setSelected(false);
//            selectedPiece = null;
            selectedSquare = null;
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
}

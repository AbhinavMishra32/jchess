package in.abhinavmishra.jchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
import java.util.Arrays;

public class InputHandler extends InputAdapter {
    Board board;
    Square selectedSquare;
    Piece selectedPiece;
    Integer deltaX;
    Integer deltaY;

    public InputHandler(Board board) {
        this.board = board;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int correctedY = Gdx.graphics.getHeight() - screenY;

        board.getSquares().forEach(squares -> {squares.forEach(square -> {
            if (square.isInSquare(screenX, correctedY) && square.getPiece() != null) {
                selectedSquare = square;
                selectedPiece = selectedSquare.getPiece();
                selectedPiece.setSelected(true);
                selectedSquare.setSelected(true);
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
            selectedSquare.setSelected(false);
            selectedPiece.setSelected(false);

            Square newSquare = board.getSquareAtCoords(
                selectedPiece.getCenterCoords()[0],
                selectedPiece.getCenterCoords()[1],
                selectedSquare
            );

            ArrayList<Square> allowedSquares = board.getAllowedSquares(selectedSquare);

            boolean isAllowed = false;

            if (allowedSquares != null) {
                for (Square square : allowedSquares) {
                    if (square.getRow() == newSquare.getRow() && square.getCol() == newSquare.getCol()) {
                        isAllowed = true;
                        break;
                    }
                }
            }

            if (isAllowed) {
                selectedSquare.setPiece(null);
                newSquare.setPiece(selectedPiece);
            } else {
                selectedSquare.setPiece(selectedPiece);
            }

            selectedPiece = null;
            selectedSquare = null;
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
}

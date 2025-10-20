package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Piece;

import java.util.ArrayList;

public class Pawn extends Piece {
    private int[][] allowedMoves;

    public Pawn(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y) {
        super(pieceColor, name, texture, row, col, x, y);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{row, col});

        if (isVisibleOnBoard(row + 1, col)) {
            moves.add(new int[]{row + 1, col - 1});
        }

        if (isVisibleOnBoard(row - 1, col)) {
            moves.add(new int[]{row + 1, col + 1});
        }

        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    public int[][] getAllowedMoves() {
        return allowedMoves;
    }

}

package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.Piece;
import in.abhinavmishra.jchess.Square;

import java.util.ArrayList;

public class King extends Piece {

    public King(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (!isVisibleOnBoard(newRow, newCol)) continue;

            Piece target = board.getPieceAt(newRow, newCol);
            if (target == null || target.getPieceColor() != getPieceColor()) {
                moves.add(new int[]{newRow, newCol});
            }
        }

        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    protected int[][] getAllowedMoves() {
        return allowedMoves;
    }
}

package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.Piece;
import in.abhinavmishra.jchess.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        int[][] offsets = {
            { 2,  1}, { 1,  2}, {-1,  2}, {-2,  1},
            {-2, -1}, {-1, -2}, { 1, -2}, { 2, -1}
        };

        for (int[] off : offsets) {
            int newRow = row + off[0];
            int newCol = col + off[1];

            if (!isVisibleOnBoard(newRow, newCol)) continue;

            Piece target = board.getPieceAt(newRow, newCol);
            if (target == null || target.getPieceColor() != getPieceColor()) {
                moves.add(new int[]{newRow, newCol});
            }
        }

        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    public int[][] getAllowedMoves() {
        return allowedMoves;
    }

}

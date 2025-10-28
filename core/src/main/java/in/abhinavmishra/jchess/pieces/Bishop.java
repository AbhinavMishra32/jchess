package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.Piece;
import in.abhinavmishra.jchess.Square;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int[][] directions = {
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int[] dir : directions) {
            int r = row;
            int c = col;

            while(true) {
                r += dir[0];
                c += dir[1];

                if (!isVisibleOnBoard(r, c)) {
                    break;
                }

                Piece pieceAt = board.getPieceAt(r, c);

                if (pieceAt == null) {
                    moves.add(new int[]{r, c});
                } else {
                    if (pieceAt.getPieceColor() != this.getPieceColor()) {
                        moves.add(new int[]{r, c});
                    }
                    break;
                }
            }
        }

        allowedMoves = moves.toArray(new int[moves.size()][]);

    }

    @Override
    public int[][] getAllowedMoves() {
        return allowedMoves;
    }
}

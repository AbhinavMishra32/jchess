package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.Piece;
import in.abhinavmishra.jchess.Square;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        int r = this.row;
        int c = this.col;
        while (isVisibleOnBoard(r, c)) {
            r++;
            moves.add(new int[]{r, c});
        }
        r = this.row;
        while(isVisibleOnBoard(r, c)) {
            r--;
            moves.add(new int[]{r, c});
        }
        r = this.row;
        while(isVisibleOnBoard(r,c)) {
            c++;
            moves.add(new int[]{r, c});
        }
        c = this.col;
        while(isVisibleOnBoard(r, c)) {
            c--;
            moves.add(new int[]{r, c});
        }
        c = this.col;
        allowedMoves = moves.toArray(new int[moves.size()][]);

    }

    @Override
    protected int[][] getAllowedMoves() {
        return allowedMoves;
    }
}

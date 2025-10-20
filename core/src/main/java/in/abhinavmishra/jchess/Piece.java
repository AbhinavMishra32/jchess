package in.abhinavmishra.jchess;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.pieces.PieceColor;

public abstract class Piece {
    private String name;
    private PieceColor pieceColor;
    Texture texture;
    protected int row;
    protected int col;
    protected boolean isSelected;

    private int[][] allowedMoves = {};

    public Piece(PieceColor pieceColor, String name, Texture texture, int row, int col) {
        this.name = name;
        this.texture = texture;
        this.row = row;
        this.col = col;
        this.pieceColor = pieceColor;
        setAllowedMoves();
    }

    protected abstract void setAllowedMoves();
    protected abstract int[][] getAllowedMoves();

    protected boolean isVisibleOnBoard(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return false;
        else return true;
    }

}

package in.abhinavmishra.jchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.pieces.PieceColor;

public abstract class Piece {
    private String name;
    private PieceColor pieceColor;
    Texture texture;
    protected int row;
    protected int col;
    protected boolean isSelected;
    protected int x;
    protected int y;
    protected int size;

    private int[][] allowedMoves = {};

    public Piece(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y) {
        this.name = (name != null) ? name : "";
        this.texture = texture;
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;
        this.pieceColor = pieceColor;
        setAllowedMoves();
    }

    protected abstract void setAllowedMoves();
    protected abstract int[][] getAllowedMoves();

    protected boolean isVisibleOnBoard(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return false;
        else return true;
    }

    public boolean isInPiece(int px, int py) {
        return px >= x && px <= x + size && py >= y && py <= y + size;
    }

    public String getName() {
        return name;
    }

    public int[] getCenterCoords() {
        return new int[]{x + size / 2, y + size / 2};
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

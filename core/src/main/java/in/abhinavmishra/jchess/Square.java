package in.abhinavmishra.jchess;

import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import sun.rmi.runtime.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Square {
    Logger log;
    private Piece piece;
    private float size;
    private ShapeRenderer shapeRenderer;
    private Color color;
    private float x;
    private float y;
    private int row;
    private int col;
    private boolean isSelected = false;

    public Square(float size, ShapeRenderer shapeRenderer, float x, float y, Color color, int row, int col) {
        log = new Logger("Square");
        this.size = size;
        this.shapeRenderer = shapeRenderer;
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            piece.setX((int) this.x);
            piece.setY((int) this.y);
            piece.row = this.row;
            piece.col = this.col;
            piece.setAllowedMoves();
        }
    }

    public void drawSquare() {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, size, size);
    }


    public void drawPiece(SpriteBatch batch) {
        if (piece != null && piece.texture != null) {
            batch.draw(piece.texture, piece.getX(), piece.getY(), size, size);
        }
    }

    public void drawAllowedMoves(ArrayList<ArrayList<Square>> squares) {
        if (piece == null) return;
        int[][] allowedMoves = piece.getAllowedMoves();
        // this is supposed to make the green shape lesser in opacity idk why its not working (fix later)
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        for(ArrayList<Square> row : squares) {
            for(Square square : row) {
                // optimize this later as stream + lambda creates temporary objects every frame
                // (main cause of increasing objects in heap)
                boolean isAllowed = Arrays.stream(allowedMoves)
                    .anyMatch(move -> move[0] == square.getRow() && move[1] == square.getCol());

                if (isAllowed) {
                    shapeRenderer.setColor(0, 1, 0, 0.3f);
                    shapeRenderer.rect(square.getX(), square.getY(), square.getSize(), square.getSize());
                }
            }
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public boolean isInSquare(int x, int y) {
        return x < this.x + size && x >= this.x && y < this.y + size && y > this.y;
    }

    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}

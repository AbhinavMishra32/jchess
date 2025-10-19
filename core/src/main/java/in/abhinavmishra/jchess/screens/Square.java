package in.abhinavmishra.jchess.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Square {
    private Piece piece;
    private float size;
    private boolean hovered = false;
    private ShapeRenderer shapeRenderer;
    private Color color;
    private float x;
    private float y;

    public Square(float size, ShapeRenderer shapeRenderer, float x, float y, Color color) {
        this.size = size;
        this.shapeRenderer = shapeRenderer;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw() {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, size, size);
        // use this end after rendering every shape... in the board renderer
//        shapeRenderer.end();
    }

}

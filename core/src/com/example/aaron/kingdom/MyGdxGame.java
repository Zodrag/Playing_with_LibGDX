package com.example.aaron.kingdom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor, GestureDetector.GestureListener {

	private SpriteBatch batch;
	private BitmapFont fontTitle, font;
	private Texture img;
	private Sprite sprite, spriteMap;
	private int screenWidth, screenHeight;
	private String message = "Touch Me";
	private GlyphLayout layout;
	private float width, height;
	private OrthographicCamera camera;

	@Override
	public void create () {

		batch = new SpriteBatch();

		fontTitle = new BitmapFont();
		fontTitle.setColor(Color.RED);
		fontTitle.getData().scale(5);  //fast but blurry

		img = new Texture("map.png");
		sprite = new Sprite(img);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2); //center on screen

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		font = new BitmapFont();
		font.setColor(Color.BLUE);
		font.getData().scale(5);

		Gdx.input.setInputProcessor(this);
		Gdx.input.setInputProcessor(new GestureDetector(this));

		layout = new GlyphLayout();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void dispose() {
		batch.dispose();
		fontTitle.dispose();
		font.dispose();
		img.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1); //Color White
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //White background

		layout.setText(font, message);
		width = screenWidth/2 - layout.width/2;
		height = screenHeight/2 + layout.height/2;

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		sprite.draw(batch);
		font.draw(batch, message, width, height);
		fontTitle.draw(batch, "Kingdom Alliance", 100, 400); //(0,0) bottom left corner
		batch.end();


	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		message = "Touch down at " + screenX + ", " + screenY;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		message = "Touch up at " + screenX + ", " + screenY;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		message = "Touch dragging at " + screenX + ", " + screenY;
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if (camera.position.x - deltaX < -6350 || camera.position.x - deltaX > 6350 || camera.position.y + deltaY < -2800 || camera.position.y + deltaY > 2800){
			return true;
		}
		//else if (camera.position.y - deltaY < -280 || camera.position.y - deltaY > 280){
		//	return true;
		//}
		else {
			camera.translate(-deltaX, deltaY, 0);
			message = camera.position.x - deltaX + ", " + camera.position.y + deltaY;
			camera.update();
			return true;
		}

	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
}

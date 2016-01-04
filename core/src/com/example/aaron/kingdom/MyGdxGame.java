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
import com.badlogic.gdx.math.Vector3;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor, GestureDetector.GestureListener {

	private SpriteBatch batch;
	private BitmapFont fontTitle, fontMessage;
	private Texture imgMap;
	private Sprite spriteMap;
	private int screenWidth, screenHeight;
	private String message = "Touch Me";
	private String title = "Kingdom Alliance";
	private GlyphLayout layoutMessage, layoutTitle;
	private float widthTitle, heightTitle, widthMessage, heightMessage;
	private OrthographicCamera camera;

	@Override
	public void create () {

		batch = new SpriteBatch();

		imgMap = new Texture("map.png");
		spriteMap = new Sprite(imgMap);
		spriteMap.setPosition(-spriteMap.getWidth()/2, -spriteMap.getHeight()/2); //image center is center on screen when started

		screenWidth = Gdx.graphics.getWidth();  //phones screen size in px
		screenHeight = Gdx.graphics.getHeight();

		fontMessage = new BitmapFont(); // need to learn proper way to increase text size
		fontMessage.setColor(Color.BLUE);
		fontMessage.getData().scale(5);
		fontTitle = new BitmapFont();
		fontTitle.setColor(Color.GOLD);
		fontTitle.getData().scale(8);

		Gdx.input.setInputProcessor(this);
		Gdx.input.setInputProcessor(new GestureDetector(this));

		layoutMessage = new GlyphLayout();
		layoutTitle = new GlyphLayout();

		camera = new OrthographicCamera(screenWidth, screenHeight ); //area of pixel that camera will show
	}

	@Override
	public void dispose() {
		batch.dispose();
		fontTitle.dispose();
		fontMessage.dispose();
		imgMap.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1); //Color White
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //White background

		layoutMessage.setText(fontMessage, message);
		layoutTitle.setText(fontTitle, title);

		widthTitle = -layoutTitle.width/2;
		heightTitle = layoutTitle.height/2 + layoutTitle.height;
		widthMessage = -layoutMessage.width/2;
		heightMessage = layoutMessage.height/2 - layoutMessage.height;
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		spriteMap.draw(batch);
		fontMessage.draw(batch, message, widthMessage, heightMessage);
		fontTitle.draw(batch, title, widthTitle, heightTitle); //(0,0) center of image
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
		camera.translate(-deltaX, deltaY);
		keepCameraInBounds();
		camera.update();
		return true;
	}

	private void keepCameraInBounds () {
		Vector3 camPos = camera.position;
		float HW = camera.viewportWidth / 2, HH = camera.viewportHeight / 2;
		camPos.x = MathUtils.clamp(camPos.x, spriteMap.getWidth()/2 - spriteMap.getWidth() + HW, spriteMap.getWidth() - spriteMap.getWidth()/2 - HW);
		camPos.y = MathUtils.clamp(camPos.y, spriteMap.getHeight()/2 - spriteMap.getWidth() + HH, spriteMap.getHeight() - spriteMap.getHeight()/2 - HH);
		message = Float.toString(camPos.x) + " " + Float.toString(camPos.y);
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

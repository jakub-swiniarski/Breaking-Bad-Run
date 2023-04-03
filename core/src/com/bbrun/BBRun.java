package com.bbrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bbrun.entities.Player;

public class BBRun extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	Texture sand;
	int sandX;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	BitmapFont font28;

	@Override
	public void create () {
		batch = new SpriteBatch();

		player=new Player(0f,192f);
		sand=new Texture(Gdx.files.internal("world/sand.png"));

		//font
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 28;
		parameter.borderWidth=2;
		parameter.borderColor=Color.BLACK;
		font28 = generator.generateFont(parameter);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 1, 1);
		batch.begin();
		batch.draw(player.img,player.getX(),player.getY());
		batch.draw(sand,sandX,0);
		sandX-=450f*Gdx.graphics.getDeltaTime();
		if(sandX<=-192f){
			sandX=0;
		}
		font28.draw(batch, "SCORE:" + player.score, 5, 715);
		batch.end();

		player.inputChecker();
		player.stateChecker();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.img.dispose();
		sand.dispose();
		font28.dispose();
		generator.dispose();
	}
}

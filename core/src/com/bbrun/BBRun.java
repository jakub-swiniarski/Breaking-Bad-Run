package com.bbrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bbrun.entities.Enemy;
import com.bbrun.entities.Player;
import com.bbrun.items.Meth;

public class BBRun extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	Texture sand;
	int sandX;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter1, parameter2;
	BitmapFont font28,font48;
	Meth meth;
	int movingSpeed;
	Music music;
	Enemy hank;
	Sound scream;
	boolean played;

	@Override
	public void create () {
		batch = new SpriteBatch();

		player=new Player(0f,192f);
		hank=new Enemy();
		sand=new Texture(Gdx.files.internal("world/sand.png"));

		//font
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
		parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter1.size = 28;
		parameter1.borderWidth=2;
		parameter1.borderColor=Color.BLACK;
		font28 = generator.generateFont(parameter1);

		parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter2.size = 48;
		parameter2.borderWidth=4;
		parameter2.borderColor=Color.BLACK;
		font48 = generator.generateFont(parameter2);

		meth=new Meth();
		movingSpeed=1000;

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
		music.setLooping(true);
		music.setVolume(1f);
		music.play();

		scream = Gdx.audio.newSound(Gdx.files.internal("sounds/scream.mp3"));
		played=false;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 1, 1);
		batch.begin();
		batch.draw(player.img,player.getX(),player.getY());
		batch.draw(sand,sandX,0);
		batch.draw(meth.img,meth.rect.x,meth.rect.y);
		batch.draw(hank.img,hank.rect.x,hank.rect.y);
		font28.draw(batch, "SCORE:" + player.score, 5, 715);
		if(player.canMove) {
			sandX-=movingSpeed*Gdx.graphics.getDeltaTime();
			meth.rect.x-=movingSpeed* Gdx.graphics.getDeltaTime();
			hank.rect.x-=movingSpeed*Gdx.graphics.getDeltaTime();
		}
		else{
			if(!played) {
				scream.play(1.0f);
				played=true;
			}
			music.stop();
			font48.draw(batch, "GAME OVER", 480, 450);
			font28.draw(batch, "PRESS R TO TRY AGAIN", 465, 350);
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
				music.play();
				played=false;
				player= new Player(0f,192f);
				hank=new Enemy();
				sand=new Texture(Gdx.files.internal("world/sand.png"));
				meth=new Meth();
			}
		}
		batch.end();

		if(sandX<=-192f){
			sandX=0;
		}

		//checks
		player.inputChecker();
		player.stateChecker();
		if(meth.rect.overlaps(player.rect)){
			meth.rect.x=1280+(int)(Math.random() * 500);
			player.score+=1;
		}
		if(meth.rect.x+meth.rect.width<=0){
			meth.rect.x=1280+(int)(Math.random() * 500);
		}

		if(hank.rect.overlaps(player.rect)){
			player.canMove=false;
		}
		if(hank.rect.x+hank.rect.width<=0){
			hank.rect.x=1280+(int)(Math.random() * 500 + 500);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.img.dispose();
		sand.dispose();
		font28.dispose();
		font48.dispose();
		generator.dispose();
		meth.img.dispose();
		hank.img.dispose();
	}
}

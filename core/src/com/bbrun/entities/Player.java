package com.bbrun.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity{
    int state; //0-normal, 1-ascending, 2-descending
    public int score;
    public boolean canMove;
    private int delay, frame;
    public Texture[] walkingImg=new Texture[4];
    public Player(float x, float y){
        img=new Texture(Gdx.files.internal("entities/player0.png"));
        for(int i=0; i<4; i++){
            walkingImg[i]=new Texture(Gdx.files.internal("entities/player"+i+".png"));
        }
        delay=0;
        frame=0;
        rect.x=x;
        rect.y=y;
        state=0;
        score=0;
        canMove=true;
    }
    public void inputChecker(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && rect.y==192f && canMove){
            state=1;
        }
    }
    public void stateChecker(){
        if(state==1){
            rect.y+=850f*Gdx.graphics.getDeltaTime();
            if(rect.y>=700f){
                state=2;
            }
        }
        if(state==2){
            rect.y-=850*Gdx.graphics.getDeltaTime();
            if(rect.y<=192f){
                rect.y=192f;
                state=0;
            }
        }
    }
    public void animation(){
        if(state==0 && canMove){
            delay++;
            if(delay%3==0){
                img=walkingImg[frame%4];
                frame++;
            }
        }
    }
}

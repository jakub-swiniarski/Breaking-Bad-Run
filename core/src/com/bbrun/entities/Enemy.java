package com.bbrun.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Entity{
    public Enemy(){
        img=new Texture(Gdx.files.internal("entities/enemy.png"));
        rect.x=1880;
        rect.y=192;
        rect.height-=32;
    }
}

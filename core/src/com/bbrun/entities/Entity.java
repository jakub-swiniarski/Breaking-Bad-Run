package com.bbrun.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    protected Rectangle rect;
    public Texture img;

    public Entity(){
        rect=new Rectangle();
        rect.width=192;
        rect.height=256;
    }
    public float getX(){
        return rect.x;
    }
    public float getY(){
        return rect.y;
    }
}

package com.bbrun.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Item {
    public Rectangle rect;
    public Texture img;

    public Item(){
        rect=new Rectangle();
        rect.width=96;
        rect.height=96;
        rect.x=1280;
        rect.y=192;
    }
}

package main;

import java.util.Random;

import objects.Obj_mouse;


public class AssetSetter {
	GamePanel gp;
	Random rn = new Random();
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setMouse(){
		gp.obj[0] = new Obj_mouse();
		gp.obj[0].screenX = rn.nextInt(gp.maxScreenCol - 3) * gp.tileSize + gp.tileSize;
		gp.obj[0].screenY = rn.nextInt(gp.maxScreenRow - 3) * gp.tileSize + gp.tileSize;
		
		
	}
}

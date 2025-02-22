package objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_mouse extends SuperObject{
	
	public Obj_mouse() {
		this.name = "Mouse";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Mouse.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		solidArea = new Rectangle(0, 0, 24, 24);
		solidAreaDefaultX = 0;
		solidAreaDefaultY = 0;
	}
}

package main;



import entity.Body;
import entity.Entity;
import entity.Snake;

public class CollisionManager {
	GamePanel gp;
	
	public CollisionManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftScreenX = entity.screenX + entity.solidArea.x;
		int entityRightScreenX = entity.screenX + entity.solidArea.width;
		int entityTopScreenY = entity.screenY;
		int entityBottomScreenY = entity.screenY + entity.solidArea.height;
		
		int entityLeftCol = entityLeftScreenX/gp.tileSize;
		int entityRightCol = entityRightScreenX/gp.tileSize;
		int entityTopRow = entityTopScreenY/gp.tileSize;
		int entityBottomRow = entityBottomScreenY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopScreenY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tMan.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gp.tMan.mapTileNumber[entityRightCol][entityTopRow];
			
			if(gp.tMan.tile[tileNum1].collision == true || gp.tMan.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomScreenY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tMan.mapTileNumber[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tMan.mapTileNumber[entityRightCol][entityBottomRow];
			
			if(gp.tMan.tile[tileNum1].collision == true || gp.tMan.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftScreenX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tMan.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gp.tMan.mapTileNumber[entityLeftCol][entityBottomRow];
			
			if(gp.tMan.tile[tileNum1].collision == true || gp.tMan.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightScreenX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tMan.mapTileNumber[entityRightCol][entityTopRow];
			tileNum2 = gp.tMan.mapTileNumber[entityRightCol][entityBottomRow];
			
			if(gp.tMan.tile[tileNum1].collision == true || gp.tMan.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	public int checkObject(Entity entity, boolean snake) {
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				gp.obj[i].solidArea.x = gp.obj[i].screenX;
				gp.obj[i].solidArea.y = gp.obj[i].screenY;
				
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.x = entity.screenX;
					entity.solidArea.y = entity.screenY - entity.speed;
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							//entity.collisionOn = true;
						}
						if(snake) {
							index = i;
						}
					}
					break;
					
				case "down":
					entity.solidArea.x = entity.screenX;
					entity.solidArea.y = entity.screenY + entity.speed;
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							//entity.collisionOn = true;
						}
						if(snake) {
							index = i;
						}
					}
					break;
					
				case "left":
					entity.solidArea.x = entity.screenX - entity.speed;
					entity.solidArea.y = entity.screenY;
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							//entity.collisionOn = true;
						}
						if(snake) {
							index = i;
						}
					}
					break;
					
				case "right":
					entity.solidArea.x = entity.screenX + entity.speed;
					entity.solidArea.y = entity.screenY;
					
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							//entity.collisionOn = true;
						}
						if(snake) {
							index = i;
						}
					}
					break;
				}
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
			}
		}
		
		return index;
	}
	public void checkSnake(Snake s) {
		
		s.snake[0].solidArea.x = s.screenX;
		s.snake[0].solidArea.y = s.screenY;
		
		
		for(int i = 0; i < s.length+2; i++) {
			if(i == 0) {
			}else {
				Body bdy1 = s.snake[i-1];
				Body bdy2 = s.snake[i];
				switch(bdy1.direction) {
				case "up": 
					bdy2.solidArea.x = bdy1.solidArea.x;
					bdy2.solidArea.y = bdy1.solidArea.y + gp.tileSize;
					
					break;
				case "down": 
					bdy2.solidArea.x = bdy1.solidArea.x;
					bdy2.solidArea.y = bdy1.solidArea.y - gp.tileSize;         
					break;
				case "left":
					bdy2.solidArea.x = bdy1.solidArea.x + gp.tileSize;
					bdy2.solidArea.y = bdy1.solidArea.y;
					break;
				case "right":
					bdy2.solidArea.x = bdy1.solidArea.x - gp.tileSize;
					bdy2.solidArea.y = bdy1.solidArea.y;
					break;
				
				}
				
			}
			if(i != 0) {
				if(s.snake[0].solidArea.intersects(s.snake[i].solidArea)) {
					s.collisionOn = true;
					break;
				}
			}
		}
		s.snake[0].solidArea.x =  s.solidAreaDefaultX;
		s.snake[0].solidArea.y =  s.solidAreaDefaultY;
		
	}
}

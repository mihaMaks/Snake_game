package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[5];
		mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			 int col = 0;
			 int row = 0;
			 
			 while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				 String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					 String numbers[] = line.split(" ");
					 int number = Integer.parseInt(numbers[col]);
					 mapTileNumber[col][row] = number;
					 col++;
			 	}
				 if(col == gp.maxScreenCol) {
					 col = 0;
					 row++;
				 }
			 
			 }
			 br.close();
		}catch(Exception e) {
			
		}
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/RockWall.png"));
			tile[1].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int screenCol = 0;
		int screenRow = 0;
		
		while(screenCol < gp.maxScreenCol && screenRow < gp.maxScreenRow) {
			int screenX = screenCol * gp.tileSize;
			int screenY = screenRow * gp.tileSize;
			int tileNum = mapTileNumber[screenCol][screenRow];
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			screenCol++;
			if(screenCol == gp.maxScreenCol) {
				screenCol = 0;
				screenRow++;
			}
		}
	}
}

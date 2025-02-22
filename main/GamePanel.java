package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Snake;
import objects.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 8;
	final int scale = 3;
	public final int tileSize = scale * originalTileSize;
	
	public final int maxScreenCol = 45;
	public final int maxScreenRow = 35;
	public final int screenWidth = maxScreenCol * tileSize;
	public final int screenHight = maxScreenRow * tileSize;

	//FPS
	int FPS = 6;
	Thread gameThread;
	
	TileManager tMan = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	public CollisionManager cMan = new CollisionManager(this);
	Snake snake = new Snake(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public AssetSetter aSet = new AssetSetter(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHight));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread = new  Thread(this);
		gameThread.start();
	}
	
	public void startUpGame() {
		aSet.setMouse();
	}
	
	public void update() {
		snake.update();
		if(obj[0] == null) {
			aSet.setMouse();
		}
		//nared vec objetou z random efekti
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tMan.draw(g2);
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(this, g2);
			}
		}
		
		snake.draw(g2);
		
		
		
		g2.dispose(); // good practice frees memory
	}

	@Override
	public void run() {
		// game loop - core
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			if(delta >= 1) {
				//Update (player position)
				update();
				//draw screen with updated information
				repaint();
				drawCount++;
				delta--;
				
			}
			if(timer >= 1000000000) {
				System.out.println("FPS:"+drawCount);
				timer = 0;
				drawCount = 0;
			}
			
		}
		
	}
	
}

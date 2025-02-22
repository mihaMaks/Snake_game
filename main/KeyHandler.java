package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public KeyHandler() {
		upPressed = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(upPressed == true || downPressed == true) {
			if(key == KeyEvent.VK_A) {
				leftPressed = true;
				upPressed = false;
				downPressed = false;
				
			}else if(key == KeyEvent.VK_D) {
				rightPressed = true;
				upPressed = false;
				downPressed = false;
			}
		}else if(leftPressed == true || rightPressed == true){
			if(key == KeyEvent.VK_W) {
				upPressed = true;
				rightPressed = false;
				leftPressed = false;
				
			}else if(key == KeyEvent.VK_S) {
				downPressed = true;
				rightPressed = false;
				leftPressed = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method
		

	}
}

package audio;

import java.awt.event.*;
import javax.swing.*;


public class Keyboard extends KeyAdapter implements KeyToSound, Runnable{
	
	public Keyboard() {
		
	}
	
	public Boolean insert(char c) {return buffer.add(c);}
	public Character read() {return buffer.remove();}
	public Character peek() {return buffer.peek();}

	
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode()) {
		
			case KeyEvent.VK_A:
				this.insert('A');
				System.out.println(this.buffer);
				System.out.println("A pressed");
				break;
			case KeyEvent.VK_B:
				System.out.println("B pressed");
				break;
			case KeyEvent.VK_C:
				System.out.println("C pressed");
				break;
			case KeyEvent.VK_D:
				System.out.println("D pressed");
				break;
			case KeyEvent.VK_E:
				System.out.println("E pressed");
				break;
			case KeyEvent.VK_F:
				System.out.println("F pressed");
				break;
			case KeyEvent.VK_G:
				System.out.println("G pressed");
				break;
			case KeyEvent.VK_H:
				System.out.println("H pressed");
				break;
			case KeyEvent.VK_I:
				System.out.println("I pressed");
				break;
			case KeyEvent.VK_J:
				System.out.println("J pressed");
				break;
			case KeyEvent.VK_K:
				System.out.println("K pressed");
				break;
			case KeyEvent.VK_L:
				System.out.println("L pressed");
				break;
			case KeyEvent.VK_M:
				System.out.println("M pressed");
				break;
			case KeyEvent.VK_N:
				System.out.println("N pressed");
				break;
			case KeyEvent.VK_O:
				System.out.println("O pressed");
				break;
			case KeyEvent.VK_P:
				System.out.println("P pressed");
				break;
			case KeyEvent.VK_Q:
				System.out.println("Q pressed");
				break;
			case KeyEvent.VK_R:
				System.out.println("R pressed");
				break;
			case KeyEvent.VK_S:
				System.out.println("S pressed");
				break;
			case KeyEvent.VK_T:
				System.out.println("T pressed");
				break;
			case KeyEvent.VK_U:
				System.out.println("U pressed");
				break;
			case KeyEvent.VK_V:
				System.out.println("V pressed");
				break;
			case KeyEvent.VK_W:
				System.out.println("W pressed");
				break;
			case KeyEvent.VK_X:
				System.out.println("X pressed");
				break;
			case KeyEvent.VK_Y:
				System.out.println("Y pressed");
				break;
			case KeyEvent.VK_Z:
				System.out.println("Z pressed");
				break;
			case KeyEvent.VK_1:
				System.out.println("1 pressed");
				break;
			case KeyEvent.VK_2:
				System.out.println("2 pressed");
				break;
			case KeyEvent.VK_3:
				System.out.println("3 pressed");
				break;
			case KeyEvent.VK_4:
				System.out.println("4 pressed");
				break;
			case KeyEvent.VK_5:
				System.out.println("5 pressed");
				break;
			case KeyEvent.VK_6:
				System.out.println("6 pressed");
				break;
			case KeyEvent.VK_7:
				System.out.println("7 pressed");
				break;
			case KeyEvent.VK_8:
				System.out.println("8 pressed");
				break;
			case KeyEvent.VK_9:
				System.out.println("9 pressed");
				break;
			case KeyEvent.VK_0:
				System.out.println("0 pressed");
				break;
				
			default: break;
		}
		
	}
	
	public void run() {
		
		JFrame frame = new JFrame("Test");
//		frame.pack();
//		frame.setSize(new Dimension(300,200));
		frame.setVisible(true);
		Keyboard test = new Keyboard();
		frame.addKeyListener(test);
		
		
		while(true);

	}

}

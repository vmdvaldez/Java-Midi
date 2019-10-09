package audio;



public class Main {

	public static void main(String[] args) {

		// Keyboard k = new Keyboard();
		// System.out.println(k);
		// Thread keyboard = new Thread(k);


		Gui gui = new Gui();
		Thread t_gui = new Thread(gui);
		System.out.println(gui.get_keyboard());
		Thread sound = new Thread(new Sound(gui.get_keyboard()));
		t_gui.start();
		sound.start();
		try {
			t_gui.join();
			sound.join();
		}catch(Exception e) {e.printStackTrace();}
	
			
		
	}
}

/*
 *TODO: 
 * - ADD Keyboard to Note name mapping. (Allow user to modify it in the future if implemented this way?)
 * - ADD GUI change instrument functionality
 * - 
 *x
 */
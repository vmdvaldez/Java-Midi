package audio;



public class Main {

	public static void main(String[] args) {

		Gui gui = new Gui();
		Thread t_gui = new Thread(gui);
		Thread sound = new Thread(gui.get_midi());
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

//VOIDING MAIN FUNCTION Constructor for sound is called twice when implemented this way -- everything is now under the GUI class
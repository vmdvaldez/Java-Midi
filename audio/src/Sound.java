import java.io.*;
import javax.sound.midi.*;
import java.util.*;
import java.util.Map.Entry;




public class Sound
{
	Map<String, Integer> note_mapper = null;
	Map<Integer, String> reverse_note_mapper = null;
	
	
	public Sound() {
		note_mapper = new HashMap<String, Integer>();
		reverse_note_mapper = new HashMap<Integer, String>();
		String f_name = "Notes/mapper.txt";
		
		
		try {
			File file = new File(f_name);
			FileReader f_reader = new FileReader(file);
			BufferedReader b_reader = new BufferedReader(f_reader);
			
			String row;
			
			//col[0] = note #, col[1] = note_name
			while((row = b_reader.readLine()) != null) {
				String[] col = row.split(" ");
				note_mapper.put(col[1], Integer.parseInt(col[0]));
				reverse_note_mapper.put(Integer.parseInt(col[0]),col[1]);
			}
			
			b_reader.close();
		} catch (Exception e) {e.printStackTrace();}

	}
	
	
	public static void print_dict(Map<?,?> m) {
		for (Map.Entry<?,?> x : m.entrySet()) {
			String key = (String) x.getKey();
			
			System.out.println(key + ":" + (Integer)x.getValue());
		}
	}
	
	
	public static void main(String args[]){
		
		Sound test = new Sound();
		
		print_dict(test.note_mapper);

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			
			MidiChannel[] m_channel = synth.getChannels();
			System.out.println("Number of Channels: " + m_channel.length);
			
			Integer x = 60;
			for (Integer i = 0; i < 10; i++) {
				m_channel[0].noteOn(test.note_mapper.get("C6"), 100);
				x += 5;
				Thread.sleep(1000);
			}

			
		}catch (Exception e) { e.printStackTrace(); }
		
		
	}
	
	

		
	
	
}

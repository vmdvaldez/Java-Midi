package audio;

import java.io.*;
import javax.sound.midi.*;
import java.util.*;




public class Sound implements Runnable
{
	Map<String, Integer> note_mapper = null;
	Map<Integer, String> reverse_note_mapper = null;
	KeyToSound k2s = null;
	
	public Sound(KeyToSound k2s_) {
		note_mapper = new HashMap<String, Integer>();
		reverse_note_mapper = new HashMap<Integer, String>();
		k2s = k2s_;
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
			f_reader.close();
			b_reader.close();
			
		} catch (Exception e) {e.printStackTrace();}

	}
		
	public static void print_dict(Map<?,?> m) {
		for (Map.Entry<?,?> x : m.entrySet()) {
			String key = (String) x.getKey();
			
			System.out.println(key + ":" + (Integer)x.getValue());
		}
	}
	
	
	/**
	 * @param channel;		each channel has an instrument, note, velocity, pitch ... etc.
	 * @param bank;			bank is a collection of patches or presets. (i.e. bank  #1 has multiple instruments (a.k.a patches)
	 * @param patch;		patch is the instrument number.
	 * @param synth;		Synthesizer contains multiple channels.
	 * */
	
	public static void change_instrument(Integer channel, Integer bank, Integer patch, Synthesizer synth) {
		// NEED TO LOAD IF NEEDED
		try {
			Receiver rec = synth.getReceiver();
			
			//bank is a collection of presets
			//Receiver.send(MidiMessage message, long timeStamp)
			//ShortMessage(command, channel, data1,data2); 
			//program_change(
			rec.send(new ShortMessage(ShortMessage.CONTROL_CHANGE, channel, 0, bank >> 7), -1);
			rec.send(new ShortMessage(ShortMessage.CONTROL_CHANGE, channel, 32, bank & 0x7F), -1);
			rec.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, patch, 0), -1);
		} catch (Exception e) {e.printStackTrace();}

	}
	
	public void keyboard_read() {
		String x = k2s.read();
		System.out.println(x);
	}

	public void run() {
//		while(true) {			
//			try {
//			System.out.println("sound"); 
//			Thread.sleep(1000);}
//			catch(Exception e){}
//			}
		
			try {
				Synthesizer synth = MidiSystem.getSynthesizer();
				synth.open();
				
				MidiChannel[] m_channel = synth.getChannels();
			
					System.out.println(k2s);
					System.out.println(KeyToSound.buffer);
					
					
					while(true) {
						KeyToSound.lock.lock();
						while(k2s.peek() == null) 
							KeyToSound.c_buffer.await();
						
						
						String s = k2s.read();
						KeyToSound.lock.unlock();
						
						m_channel[0].noteOn(this.note_mapper.get(s), 1000);
					}
						
				}catch (Exception e) { e.printStackTrace(); }			
		

		

			
		
		
		
	}
	

		
	
	
}

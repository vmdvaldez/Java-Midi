package audio;

import java.io.*;
import javax.sound.midi.*;
import java.util.*;



public class Sound implements Runnable
{
	// NOTE MAPPING DATA-STRUCTURES
	private Map<String, Integer> note_mapper = null;
	private Map<Integer, String> reverse_note_mapper = null;
	private Map<String, Integer[]> inst_bank_preset = null;
	private List<String> instrument_names = null;

	private SoundDriver k2s = null;
	
	// MIDI SPECIFIC VARIABLES
	private Synthesizer synth = null;
	private MidiChannel[] m_channel = null;
	private Instrument[] instruments = null;

	public List<String> get_instruments(){return this.instrument_names;}

	public Sound(SoundDriver k2s_) {

		List<Thread> threads = new ArrayList<Thread>();

		threads.add(new Thread(() -> {
			note_mapper = new HashMap<String, Integer>();
			reverse_note_mapper = new HashMap<Integer, String>();
			k2s = k2s_;
			String f_name = "Notes/mapper.txt";
			
			// Read file to get note mappings (populate HashMaps)
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
				
				// Initialize Midi Synthesizer and keyboards
			} catch (Exception e) {e.printStackTrace();}
		}));


		threads.add(new Thread(()->{
			inst_bank_preset = new HashMap<String, Integer[]>();
			instrument_names = new ArrayList<String>();
			try{
				this.synth = MidiSystem.getSynthesizer();
				this.m_channel = synth.getChannels();
				this.instruments = synth.getAvailableInstruments();

				print_instruments();
				
				for (Instrument i : instruments){
					Integer[] bank_preset = new Integer[2];
					String [] parsed = i.toString().split(" ");
					int index = 0;
					for(String word : parsed){
						// System.out.println(word);
						if (word.charAt(0) == '#'){
							bank_preset[index] = Integer.parseInt(word.replace("#",""));
							// System.out.println(bank_preset[index]);
							index ++;
						}
					}
					System.out.println(i.getName());
					System.out.println("bank #: " + bank_preset[0] + "\t" + "preset #: " + bank_preset[1]);

					instrument_names.add(i.getName());
					inst_bank_preset.put(i.getName(), bank_preset);
				}

				// print_inst_mapping();

			}catch(Exception e){ e.printStackTrace();}
		}));
		
		try{

			for (Thread t : threads){
				t.start();
				t.join();
			}

		}catch (Exception e){e.printStackTrace();}



	}
		

	
	/**
	 * @param channel;		each channel has an instrument, note, velocity, pitch ... etc.
	 * @param bank;			bank is a collection of patches or presets. (i.e. bank  #1 has multiple instruments (a.k.a patches)
	 * @param patch;		patch is the instrument number.
	 * @param synth;		Synthesizer contains multiple channels.
	 * */
	
	public void change_instrument(String inst){
		Integer[] bank_preset = inst_bank_preset.get(inst);
		System.out.println(bank_preset[0] + bank_preset[1]);
		change_instrument(0, bank_preset[0], bank_preset[1]);

	}

	public void change_instrument(Integer channel, Integer bank, Integer patch) {
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
	
	public void run() {

		try {
			// Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			// MidiChannel[] m_channel = synth.getChannels();
		
			
			while(true) {
				SoundDriver.lock.lock();
				while(k2s.peek() == null) 
					SoundDriver.c_buffer.await();
				
				
				String s = k2s.read();
				SoundDriver.lock.unlock();
				int note_num = this.note_mapper.get(s);
				int velocity = 1000;


				m_channel[0].noteOn(note_num, velocity);

				new Thread(() ->{
					try{
						Thread.sleep(1000);
						m_channel[0].noteOff(note_num, velocity);
					}catch (Exception e){e.printStackTrace();}
				}).start();

			}
					
		}catch (Exception e) { e.printStackTrace(); }			
		
	}
	

	// FOR DEBUGGING

	public void print_dict(Map<?,?> m) {
		for (Map.Entry<?,?> x : m.entrySet()){
			String key = (String) x.getKey();
			System.out.println(key + ":" + (Integer)x.getValue());
		}
	}

	public void print_instruments(){
		System.out.println("Total Instruments: " + instruments.length);
		for (Instrument i : instruments)
			System.out.println(i.toString());


	}

	public void print_inst_mapping(){
			for (Map.Entry<String,Integer[]> x : inst_bank_preset.entrySet()){
				String key = (String) x.getKey();
				System.out.println(key);
				System.out.println(x.getValue()[0]);
				System.out.println(x.getValue()[1]);
		}
	}
}

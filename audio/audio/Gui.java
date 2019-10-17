package audio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections; 
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;

public class Gui extends Application implements Runnable{
	
	private Keyboard keyboard = new Keyboard();
	private Sound midi = new Sound(keyboard);

	// public Keyboard get_keyboard(){return this.keyboard;}
	// public Sound get_midi(){return this.midi;}

	private void key_handler(KeyEvent event){keyboard.map2sound(event.getText());}

	private void drop_down_key_handler(KeyEvent event){System.out.println(event.getText());}

	private void drop_down_handler(ActionEvent event){
		System.out.println("test");
	}

	private Text create_text(int fsize, double xloc, double yloc, String text){
		Text t = new Text(xloc, yloc, text);
		t.setFont(new Font(fsize));
		return t;
	}

	@Override
	public void start(Stage main){
		
		Thread sound = new Thread(midi);
		sound.start();

		Text text = this.create_text(15, 0, 0, "Instrument: ");
		ComboBox drop_down = new ComboBox();
		ObservableList <String> items = FXCollections.observableArrayList(midi.get_instruments());
		drop_down.getItems().addAll(items);
		drop_down.getSelectionModel().selectFirst();

		// drop_down.setOnMousePressed((event) -> {
		// 	System.out.println("TEST");
		// });

		// drop_down.setOnMouseReleased((event) ->{
			
		// });

		drop_down.setOnAction((event) -> {
			// System.out.println(drop_down.getSelectionModel().getSelectedItem().getClass());
			String s = drop_down.getSelectionModel().getSelectedItem().toString();
			System.out.println(s);
			midi.change_instrument(s);
		});

		
		
		// add Text to Group and create scene
		
		// Group root = new Group();
		// ObservableList list = root.getChildren();
		// list.add(text);
		// list.add(text2);
		// list.add(drop_down);


		// Using GRID
		GridPane gpane = new GridPane();
		gpane.setPadding(new Insets(20,20,20,20));
		gpane.setAlignment(Pos.TOP_LEFT);
		gpane.add(text, 0, 0);
		gpane.add(drop_down, 1 ,0);

		

		BorderPane root_bpane = new BorderPane();

		root_bpane.setLeft(gpane);

		Scene scene = new Scene(root_bpane, 900, 600);
		main.setTitle("Music Application");
		main.setScene(scene);
		main.show();

		scene.addEventHandler(KeyEvent.KEY_PRESSED, this::key_handler);
		


	}

	public void run(){
		launch();
	}

	public static void main(String[] args){
		launch();
	}
}
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
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;

public class Gui extends Application implements Runnable{
	
	private Keyboard keyboard = new Keyboard();

	public Keyboard get_keyboard(){return this.keyboard;}

	private void key_handler(KeyEvent event){
		// System.out.println(event.getText());
		keyboard.map2sound(event.getText());
	}

	private Text create_text(int fsize, double xloc, double yloc, String text){
		Text t = new Text(xloc, yloc, text);
		t.setFont(new Font(fsize));
		return t;
	}

	@Override
	public void start(Stage main){
		
		// Create Text
		// Text text = new Text();
		// text.setFont(new Font(45));
		// text.setX(50);
		// text.setY(150);
		// text.setText("testing");

		Text text = this.create_text(15, 0, 0, "Instrument: ");
		ComboBox drop_down = new ComboBox();
		drop_down.getItems().addAll("test1","test2","test3");
		
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

		

		Scene scene = new Scene(gpane, 900, 600);
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
// Jasmine Chin 111717723

import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Minesweeper extends Application {
	static int size = 18;
	static int buttonsize = 40;
	static int[][] matrix = new int[size][size];
	static int[][] seen = new int[size][size];
	private static Text message = new Text("");
	private static Button[][] buttons = new Button[size][size];
	private static boolean done = false;
	private static boolean hasLost = false;
	private static int numOfBombs = 0;
	private static Text numbombs = new Text();
	private static Button reset = new Button("Start Over");
	private static int numOfSpaces;
	private static Text numSpaces = new Text();
	
	@Override
	public void start(Stage primaryStage) {
//		StackPane rootPane = new StackPane();
		GridPane gridPane = new GridPane();
//		gridPane.setPadding(new Insets(40, 0, 0, 0));
		
		message.setFont(Font.font("Helvetica", FontPosture.ITALIC, 25));
		message.setFill(Color.DODGERBLUE);
		message.setText("Welcome to Minesweeper! Click on the buttons!");

		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				buttons[row][column] = new Button();
				buttons[row][column].setMinHeight(buttonsize);
				buttons[row][column].setMinWidth(buttonsize);
				gridPane.setRowIndex(buttons[row][column], row);
				gridPane.setColumnIndex(buttons[row][column], column);
				gridPane.getChildren().add(buttons[row][column]);
			}
		}
		
		HBox boxForMessage = new HBox(30);
		boxForMessage.getChildren().addAll(message);
		boxForMessage.setAlignment(Pos.CENTER);
		HBox paneForTop = new HBox(30);
		paneForTop.setStyle("-fx-border-color: black");
		paneForTop.getChildren().addAll(numbombs, reset, numSpaces);
		paneForTop.setAlignment(Pos.TOP_CENTER);
		numbombs.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 20));
		numbombs.setFill(Color.RED);
		numSpaces.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 20));
		numSpaces.setFill(Color.DARKSLATEBLUE);
		BorderPane pane = new BorderPane();
		pane.setTop(paneForTop);
		pane.setCenter(gridPane);
		pane.setBottom(boxForMessage);
//		rootPane.getChildren().addAll(pane, gridPane);
		Scene scene = new Scene(pane, buttonsize*size-10, buttonsize*size + 60);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Minesweeper");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		generate();
		buttonActions();
		reset.setOnAction(e -> {reset(primaryStage);});
	}
	
	public static void generate() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i]. length; j++) {
				matrix[i][j] = (Math.random()<0.5 ? 0 : 1);
				seen[i][j] = matrix[i][j];
				if (matrix[i][j] == 1) {
					numOfBombs++;
				}
			}
		}
		numOfSpaces = size*size - numOfBombs;
		numSpaces.setText("Number of Spaces Left: " + numOfSpaces);
		numbombs.setText("Number of Bombs: " + numOfBombs);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			} System.out.println();
		}
	}
	
	public static void buttonActions() {
		for (int i = 0; i < size; i++) {
			int row = i;
			for (int j = 0; j < size; j++) {
				int column = j;
				buttons[row][column].setOnAction(e -> {check(row, column);});
			}
		}
		
//		reset.setOnAction(e -> {reset(primaryStage);});
	}

	public static void check(int x, int y) {
		if (!hasLost && !done) {
			if(matrix[x][y]==1) {
				hasLost = true;
				ImageView bomb = new ImageView("minesweeper bomb.jpg");
				bomb.setFitHeight(buttonsize-20);
				bomb.setFitWidth(buttonsize-20);
				bomb.setPreserveRatio(true);
				buttons[x][y].setGraphic(bomb);
				message.setFill(Color.RED);
				message.setText("There is a bomb. Boom. End!");
			} else {
				numOfSpaces--;
			}
			int count = 0;
			if (x != 0 && y != 0) {
				count += matrix[x-1][y-1];
			}
			if (x != 0) {
				count += matrix[x-1][y];
			}
			if (y != 0) {
				count += matrix[x][y-1];
			}
			if (x != matrix.length-1 && y != matrix.length-1) {
				count += matrix[x+1][y+1];
			}
			if (x != matrix.length-1) {
				count += matrix[x+1][y];
			}
			if (y != matrix.length-1) {
				count += matrix[x][y+1];
			}
			if (x!=0 && y != matrix.length-1) {
				count += matrix[x-1][y+1];
			}
			if (x != matrix.length-1 && y!=0) {
				count += matrix[x+1][y-1];
			}
			seen[x][y] = 1;
			done = true;
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i]. length; j++) {
					if(seen[i][j]==0) done = false;
				}
			}
			if(!hasLost && !done) {
				message.setText("There is/are " + count + " bomb(s) around");
				numSpaces.setText("Number of Spaces Left: " + numOfSpaces);
				buttons[x][y].setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 18));
				buttons[x][y].setText(String.valueOf(count));
			}
			
			if(done) {
				message.setText("You won!");
				buttons[x][y].setText(String.valueOf(count));
			}
		}
	}
	
	public void reset(Stage primaryStage) {
		scene = (new BorderPane(), buttonsize*size-10, buttonsize*size + 60));
		Stage newStage = new Stage();
		start(newStage);
	}

	public static void main(String[] args) {
		launch(args);
		
	}

}

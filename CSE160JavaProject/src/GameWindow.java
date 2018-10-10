import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

class DXDY {
	double dx, dy;
	DXDY(double x, double y){
		this.dx = x;
		this.dy = y;
	}
}

class BallPane extends Pane {
	public final double radius = 10;
	private double x = radius, y = 450;
	private double dx = 1, dy = 1;
	private Timeline animation;
	
	public BallPane() {
		Circle circle = new Circle(x, y, radius, Color.AQUAMARINE);
		circle.setStroke(Color.CORNFLOWERBLUE);
		getChildren().add(circle);
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		final KeyValue kv = new KeyValue(circle.centerXProperty(), 890);
		final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		
		DXDY dxdy = new DXDY(dx,dy);
		animation = new Timeline(new KeyFrame(Duration.millis(1), e -> moveBall(circle, dxdy)));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	
	public void play() {
		animation.play();
	}
	
	public void stop() {
		animation.stop();
	}
	
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}
	
	protected void moveBall(Circle circle, DXDY dxdy) {
		dx = getWidth() - radius;
		dy = getHeight() - radius;
		double distancex = (dx - x);
		double distancey = (dy - y);
		circle.setFocusTraversable(true);
		circle.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case DOWN: 
				for (int i = 1; i <= distancey/radius; i++) {
					circle.setCenterY(circle.getCenterY() + radius);
			} 
			break;
			case UP: 
				for (int i = 1; i <= distancey/radius; i++) {
					circle.setCenterY(circle.getCenterY() - radius);
				}
			break;
			case LEFT: circle.setCenterX(circle.getCenterX() - 10); break;
			case RIGHT: circle.setCenterX(circle.getCenterX() + 10); break;
			}
		});
		if (circle.getCenterX() < radius) {
			circle.setCenterX(radius);
		} 
		if (circle.getCenterX() > getWidth() - radius) {
			circle.setCenterX(getWidth() - radius);
		}
		if (circle.getCenterY() < radius) {
			circle.setCenterY(radius);
		}
		if (circle.getCenterY() > getHeight() - radius) {
			circle.setCenterY(getHeight() - radius);
		}
	}
}

public class GameWindow extends Application{
	@Override
	public void start(Stage primaryStage) {
		BallPane pane = new BallPane();
		Scene scene = new Scene(pane, 900, 900);
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		pane.setStyle("-fx-background-color: black;");

	}
	
	public static void main(String[] args) {
		launch();
	}
}

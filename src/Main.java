import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Board board;
    private BorderPane mainLayout;
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        board =  new Board('X');
        mainLayout = new BorderPane();
        mainLayout.setCenter(board);
        mainScene = new Scene(mainLayout);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

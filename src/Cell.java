import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell extends Button {
    private int size = 100;

    public Cell() {
        super();
        setPrefSize(size,size);
        setFocusTraversable(false);
    }
    public void paint(boolean x){
        String imagePath = x ? "assets/x.png":"assets/o.png";
        Image cellImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(cellImage);
        imageView.setFitWidth(size/1.5);
        imageView.setFitHeight(size/1.5);
        setGraphic(imageView);
        setDisable(true);
    }
    public void reset(){
        setDisable(false);
        setGraphic(null);

    }

}

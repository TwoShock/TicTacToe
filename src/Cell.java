import javafx.scene.control.Button;

public class Cell extends Button {
    private int size = 100;

    public Cell() {
        super();
        setPrefSize(size,size);
        setFocusTraversable(false);
    }
}

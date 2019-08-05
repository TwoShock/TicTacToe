import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board extends GridPane {
    private ArrayList<ArrayList<Cell>> board;//actual gui board
    private ArrayList<ArrayList<Character>> aiBoard;//its a copy of the board used for AI purposes
    public final int BOARDSIZE = 3;
    public Board(){
        super();
        initializeAiBoard();
        intializeBoard();
    }
    private void initializeAiBoard(){//handles intialization of aiBoard
        for (int y = 0;y < BOARDSIZE;y++){
            ArrayList<Character> currentList = new ArrayList<Character>();
            for (int x = 0;x < BOARDSIZE;x++){
                currentList.add(new Character('.'));
            }
        }
    }
    private void intializeBoard(){//handles adding cells to gridpane
        for (int y = 0;y < BOARDSIZE;y++){
            ArrayList<Cell> currentList = new ArrayList<Cell>();
            for (int x = 0;x < BOARDSIZE;x++){
                Cell currentCell = new Cell();
                currentList.add(currentCell);
                add(currentCell,x,y);
            }
        }
    }
}

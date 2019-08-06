import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board extends GridPane {
    private ArrayList<ArrayList<Cell>> board;//actual gui board
    private ArrayList<ArrayList<Character>> aiBoard;//its a copy of the board used for AI purposes
    private boolean turn;//0=>x 1=>O
    public final int BOARDSIZE = 3;
    public Board(){
        super();
        board = new ArrayList<ArrayList<Cell>>();
        aiBoard = new ArrayList<ArrayList<Character>>();
        initializeAiBoard();
        intializeBoard();
    }
    private void initializeAiBoard(){//handles intialization of aiBoard
        for (int y = 0;y < BOARDSIZE;y++){
            ArrayList<Character> currentList = new ArrayList<Character>();
            aiBoard.add(currentList);
            for (int x = 0;x < BOARDSIZE;x++){
                currentList.add(new Character('.'));
            }
        }
    }
    private void printAIBoard(){
        for (int y = 0;y < BOARDSIZE;y++) {
            for (int x = 0; x < BOARDSIZE; x++) {
                System.out.print(getAiCellAtPos(x,y));
            }
            System.out.println();
        }
    }
    private void intializeBoard(){//handles adding cells to gridpane
        for (int y = 0;y < BOARDSIZE;y++){
            ArrayList<Cell> currentList = new ArrayList<Cell>();
            board.add(currentList);
            for (int x = 0;x < BOARDSIZE;x++){
                Cell currentCell = new Cell();
                currentList.add(currentCell);
                add(currentCell,x,y);
                final int i = x;
                final int j = y;
                currentCell.setOnMouseClicked(e->{handleInput(i,j);});
            }
        }
    }
    private Cell getCellAtPosition(int x,int y){
        return board.get(y).get(x);
    }
    private void setAICellAtPos(int x,int y,Character c){
        aiBoard.get(y).set(x,c);
    }
    private Character getAiCellAtPos(int x,int y){
        return aiBoard.get(y).get(x);
    }
    private void handleInput(int x,int y){

      if(turn == false){
          getCellAtPosition(x,y).paint(true);
          setAICellAtPos(x,y,'X');
          turn = true;
      }
      else{
          getCellAtPosition(x,y).paint(false);
          setAICellAtPos(x,y,'O');
          turn = false;
      }

        printAIBoard();
    }
}

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board extends GridPane {
    private ArrayList<ArrayList<Cell>> board;//actual gui board
    private ArrayList<ArrayList<Character>> aiBoard;//its a copy of the board used for AI purposes
    private boolean turn;//0=>x 1=>O
    public final int BOARDSIZE = 3;
    private Character player;
    private AI aiPlayer;
    public Board(Character player){
        super();
        board = new ArrayList<ArrayList<Cell>>();
        aiBoard = new ArrayList<ArrayList<Character>>();
        initializeAiBoard();
        intializeBoard();
        this.player = player;
        Character aiSymbol = player == 'X'?'O':'X';
        aiPlayer = new AI(aiSymbol);
        System.out.println(aiSymbol+" "+player);
    }
    public int evaluateGameState(){
        for (int x = 0;x < BOARDSIZE;x++){//checks for row win
            if(aiBoard.get(x).get(0) == aiBoard.get(x).get(1) &&
               aiBoard.get(x).get(1) == aiBoard.get(x).get(2) &&
               aiBoard.get(x).get(0) != '.'){
                if (aiBoard.get(x).get(0) == player)
                    return -10;
                else if(aiBoard.get(x).get(0) == aiPlayer.getAiSymbol())
                    return 10;
            }
        }
        for(int y = 0;y < BOARDSIZE;y++){//checks for column win
            if(aiBoard.get(0).get(y) == aiBoard.get(1).get(y) &&
               aiBoard.get(1).get(y) == aiBoard.get(2).get(y) &&
               aiBoard.get(0).get(y) != '.'){
                if (aiBoard.get(0).get(y) == player)
                    return -10;
                else if(aiBoard.get(0).get(y) == aiPlayer.getAiSymbol())
                    return 10;
            }
        }
        if(aiBoard.get(0).get(0) == aiBoard.get(1).get(1) &&//check for diagonal
           aiBoard.get(1).get(1) == aiBoard.get(2).get(2) &&
           aiBoard.get(0).get(0) != '.'){
            if (aiBoard.get(0).get(0) == player)
                return -10;
            else if(aiBoard.get(0).get(0) == aiPlayer.getAiSymbol())
                return 10;
        }
        if (aiBoard.get(0).get(2) == aiBoard.get(1).get(1) && aiBoard.get(1).get(1) == aiBoard.get(2).get(0)){
            if (aiBoard.get(0).get(2) == player)
                return -10;
            else if(aiBoard.get(0).get(2) == aiPlayer.getAiSymbol())
                return 10;
        }
    return 0;
    }
    public boolean isMovesLeft(){
        for (int y = 0;y < BOARDSIZE;y++){
            for (int x = 0;x<BOARDSIZE;x++){
                if (aiBoard.get(y).get(x) == '.')
                    return true;
            }
        }
        return false;
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
    public void setAICellAtPos(int x,int y,Character c){
        aiBoard.get(y).set(x,c);
    }
    public Character getAiCellAtPos(int x,int y){
        return aiBoard.get(y).get(x);
    }
    private void handleInput(int x,int y){
        if(isMovesLeft() && evaluateGameState() == 0) {
            getCellAtPosition(x, y).paint(true);
            setAICellAtPos(x, y, 'X');
            Move aiMove = aiPlayer.getBestMove(this);
            getCellAtPosition(aiMove.getX(), aiMove.getY()).paint(false);
            setAICellAtPos(aiMove.getX(), aiMove.getY(), 'O');
        }
    }
}

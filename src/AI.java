public class AI {
    private Character aiSymbol;
    private Character playerSymbol;

    public Character getAiSymbol() {
        return aiSymbol;
    }

    public AI(Character aiSymbol){
        this.aiSymbol = aiSymbol;
        if(aiSymbol == 'X')
            playerSymbol = 'O';
        else
            playerSymbol = 'X';
    }
    int minimax(Board board,int depth,boolean isMaximizersTurn){
        int score = board.evaluateGameState();
        if (score == 10 || score == -10)
            return score;
        if(!board.isMovesLeft())
            return 0;
        if(isMaximizersTurn){
            int bestMove = Integer.MIN_VALUE;
            for (int y = 0;y < board.BOARDSIZE;y++){
                for (int x = 0;x < board.BOARDSIZE;x++){
                    if (board.getAiCellAtPos(x,y) == '.'){
                        board.setAICellAtPos(x,y,aiSymbol);
                        bestMove = Math.max(bestMove,minimax(board,depth+1,!isMaximizersTurn));
                        board.setAICellAtPos(x,y,'.');
                    }
                }
            }
            return bestMove-depth;
        }
        else{
            int bestMove = Integer.MAX_VALUE;
            for (int y = 0;y < board.BOARDSIZE;y++){
                for (int x = 0;x < board.BOARDSIZE;x++){
                    if (board.getAiCellAtPos(x,y) == '.'){
                        board.setAICellAtPos(x,y,playerSymbol);
                        bestMove = Math.min(bestMove,minimax(board,depth+1,!isMaximizersTurn));
                        board.setAICellAtPos(x,y,'.');
                    }
                }
            }
            return bestMove+depth;
        }
    }
    Move getBestMove(Board board){
        Move bestMove = new Move();
        int bestValueMove = Integer.MIN_VALUE;
        for (int y = 0; y < board.BOARDSIZE;y++){
            for (int x = 0;x < board.BOARDSIZE;x++){
                if (board.getAiCellAtPos(x,y)=='.') {
                    board.setAICellAtPos(x, y, aiSymbol);
                    int moveValue = minimax(board, 0, false);
                    board.setAICellAtPos(x, y, '.');
                    if (moveValue > bestValueMove) {
                        bestValueMove = moveValue;
                        bestMove.setX(x);
                        bestMove.setY(y);
                    }
                }
            }
        }
        return bestMove;
    }
}

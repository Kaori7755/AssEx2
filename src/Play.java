public class Play {

    public static void main(String[] args) {
        //create 3 players: e , s , a
        //player positions are null because the player is not on the board yet
        Player e = new Player('E', null);
        Player s = new Player('S', null);
        Player a = new Player('A', null);
//        //create a board
//        //the player array of the board is null because no player is added on the board yet
        Board boardNoHuman = new Board(10,5,null);
//        //create the squares of the board
        boardNoHuman.createSquares();
//        //add the players on to the board
//        //squares must be created before adding players to the board, which is a two dimensional array of Square objects
        boardNoHuman.addPlayers(e);
        boardNoHuman.addPlayers(s);
        boardNoHuman.addPlayers(a);
//        //set delta of the squares
        boardNoHuman.getSquaresOnTheBoard(9,1).setDelta(-3);
        boardNoHuman.getSquaresOnTheBoard(6,0).setDelta(-1);
        boardNoHuman.getSquaresOnTheBoard(6,2).setDelta(-4);
        boardNoHuman.getSquaresOnTheBoard(6,3).setDelta(2);
        boardNoHuman.getSquaresOnTheBoard(6,4).setDelta(-1);
        boardNoHuman.getSquaresOnTheBoard(5,0).setDelta(-4);
        boardNoHuman.getSquaresOnTheBoard(3,1).setDelta(4);
        boardNoHuman.getSquaresOnTheBoard(3,4).setDelta(-1);
       //draw the board
        boardNoHuman.drawBoard();
       //play the game until a player wins
        boolean win = false;
        do{
           win = boardNoHuman.takeTurns(boardNoHuman);
        }while(win==false);
        //new round
        System.out.println("New Round.");
        //create new board - boardWithHuman
        Board boardWithHuman = new Board(10,5,null);
        boardWithHuman.createSquares();
        //add deltas to squares
        boardWithHuman.getSquaresOnTheBoard(9,1).setDelta(-3);
        boardWithHuman.getSquaresOnTheBoard(6,0).setDelta(-1);
        boardWithHuman.getSquaresOnTheBoard(6,2).setDelta(-4);
        boardWithHuman.getSquaresOnTheBoard(6,3).setDelta(2);
        boardWithHuman.getSquaresOnTheBoard(6,4).setDelta(-1);
        boardWithHuman.getSquaresOnTheBoard(5,0).setDelta(-4);
        boardWithHuman.getSquaresOnTheBoard(3,1).setDelta(4);
        boardWithHuman.getSquaresOnTheBoard(3,4).setDelta(-1);
        //create human player
        HumanPlayer human = new HumanPlayer('H', null);
        //add human player and computer players to new board - boardWithHuman
        boardWithHuman.addPlayers(human);
        boardWithHuman.addPlayers(e);
        boardWithHuman.addPlayers(s);
        boardWithHuman.addPlayers(a);
        //draw the new board
        boardWithHuman.drawBoard();
        //human player and other players play together
        win = false;
        win = false;
        do{
            win = boardWithHuman.takeTurns(boardWithHuman);
        }while(win==false);
    }



}

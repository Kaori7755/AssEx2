import java.util.Random;

public class Player {

    private final char identifier; //player identifier is final because it not going to be changed later on
    private Square playerSquare; //indicate the square the play is currently located at
    private Random r = new Random(); //for rolling a die

    public Player(char identifier,Square position){
        this.identifier = identifier;
        this.playerSquare = position;
    }


    //return a String representation of the player identifier
    public String toString() {
        String playerString = "" + this.identifier ;
        return playerString;
    }

    public char getIdentifier() {
        return identifier;
    }


    public Square getPlayerSquare() {
        return playerSquare;
    }

    public void setPosition(Square position) {
        this.playerSquare = position;
    }
    
    
    
    public boolean move(Board b) {
        int row = -1;
        int column = -1;
        //roll a die
        //r.nextInt will return the value between 0-5, so we need to +1 to get 1-6
        int count = r.nextInt(6)+1;
        //find the position the player should move to: where the player is at + count
        int newPositionInt = this.playerSquare.getSquarePosition() + count;
        System.out.println("Count is " + count );
        System.out.println("New position is "+newPositionInt);
        /*if new Position is >= the highest point, the winner wins
        the highest point is the number of row in the board times the number of column in the board, then minus 1, because the starting position is 0
         */
        if (newPositionInt>=(((b.getRow())*(b.getCol()))-1)){
            /*Put the player into the highest position if player reaches the highest point or higher, because it's easier for us to see who wins
              So the Player will always be at the highest position when the Player wins.
              Find the row and column of the highest point using findRowColOfPosition method from board,
              and put player to the square located in the provided row and column
             */
            int highestPoint = (b.getRow()*b.getCol()) -1;
            int[] squareLocation = b.findRowColOfPosition(highestPoint);
            row = squareLocation[0];
            column = squareLocation[1];
            this.setPosition(b.getSquaresOnTheBoard(row, column));
            //return a true value to indicate the player wins
            return true;
        }else {
            //find where the square of the new location is on the board
            int[] squareLocation = b.findRowColOfPosition(newPositionInt);
            row = squareLocation[0];
            column = squareLocation[1];
            //set this player position to the right square located in the provided row and column
            this.setPosition(b.getSquaresOnTheBoard(row, column));
            //if the square has delta, set new location according to the delta value and put payer to the corresponding address 
            if (this.playerSquare.getDelta() != 0) {
                newPositionInt = this.playerSquare.getSquarePosition() + (this.playerSquare.getDelta());
                //find where the new location is on the board
                squareLocation = b.findRowColOfPosition(newPositionInt);
                row = squareLocation[0];
                column = squareLocation[1];
                this.setPosition(b.getSquaresOnTheBoard(row, column));
                System.out.println("Delta, new position is " + newPositionInt);
            }
        }
        //return false to indicate no one wins
        return false;
     }




    public static void main(String[] args) {
        //demonstrate the creation of a player
        //Square object is not yet created so the value in position is null
        Player player = new Player('E', null);

        //demonstrate the creation of a square
        Square square28 = new Square(28, -5, player);


        //demonstrate the assignment of the Player to the Square
        //1. use setPosition method to set the position of Player
        player.setPosition(square28);
        //2. create and add new player - player1 to square28 using addPlayerToSquare method of Square
        Player player1 = new Player('S', square28);
        square28.addPlayersToSquare(player1);
        //demonstrate toString() method of player and square
        System.out.println(player);
        System.out.println(square28);


    }
}


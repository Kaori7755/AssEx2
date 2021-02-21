import java.util.Random;

public class Player {

    private char identifier;
    private Square playerSquare;
    private Random r = new Random();

    public Player(char identifier,Square position){
        this.identifier = identifier;
        this.playerSquare = position;
    }


    public String toString() {
        String playerString = "Player identifier is " + this.identifier + ".";
        if (this.playerSquare != null) {
            playerString += "Player position is " + this.playerSquare.getSquarePosition() + ".";
        }

        return playerString;
    }

    public char getIdentifier() {
        return identifier;
    }

    public void setIdentifier(char identifier) {
        this.identifier = identifier;
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
        //r.nextInt will return the value between 0-5, so we need to +1 to get 1-6
        int count = r.nextInt(6)+1;
        //find the position the player should move to: where the player is at + count
        int newPositionInt = this.playerSquare.getSquarePosition() + count;
        System.out.println("Count is " + count );
        System.out.println("New position is "+newPositionInt);
        //if new Position is >= the highest point, the winner wins
        if (newPositionInt>=(((b.getRow())*(b.getCol()))-1)){
            /*to make it easier to draw the board, put the player into the highest point
              find the row and column of the highest point using findRowColOfPosition,
              then convert it into int and put player to the square located in the provided row and column
             */
            int highestPoint = (b.getRow()*b.getCol()) -1;
            int[] squareLocation = b.findRowColOfPosition(highestPoint);
            row = squareLocation[0];
            column = squareLocation[1];
            this.setPosition(b.getSquaresOnTheBoard(row, column));

            return true;
        }else {
            //find where the square is on the board
            int[] squareLocation = b.findRowColOfPosition(newPositionInt);
            row = squareLocation[0];
            column = squareLocation[1];
            //set this position to the right square located in the provided row and column
            this.setPosition(b.getSquaresOnTheBoard(row, column));
            //if the square has delta, set new location according to the delta value and put payer to the corresponding address 
            if (this.playerSquare.getDelta() != 0) {
                newPositionInt = this.playerSquare.getSquarePosition() + (this.playerSquare.getDelta());
                //find where the square is on the board
                squareLocation = b.findRowColOfPosition(newPositionInt);
                row = squareLocation[0];
                column = squareLocation[1];
                this.setPosition(b.getSquaresOnTheBoard(row, column));
                System.out.println("Delta, new position is " + newPositionInt);
            }
        }
        return false;
     }




    public static void main(String[] args) {
        //demonstrate the creation of a player,
        //Square object is not yet created so the value in position is null
        Player player = new Player('E', null);
        //demonstrate the creation of a square
        Square square28 = new Square(28, 0, player);
        //demonstrate the assignment of the Player to the Square
        //use setPosition method to set the position of Player
        player.setPosition(square28);

        //demonstrate toString() method of player and square
        System.out.println(player);
        System.out.println(square28);


    }
}


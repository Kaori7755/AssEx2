import java.util.Scanner;

public class HumanPlayer extends Player {

    //use Scanner(System.in) to get the input, and assign the integer input to variable 'count'
    private Scanner s = new Scanner(System.in);

    public HumanPlayer(char identifier, Square position) {
        super(identifier, position);
    }


//override the move method
public boolean move(Board b){

    System.out.println("Please enter an integer between 1 - 6.");
    int count =  s.nextInt();
    //new position is current position + the input
    int newPositionInt = this.getPlayerSquare().getSquarePosition() + count;
    System.out.println("New position is "+newPositionInt);
        //if new Position is >= the highest point, the winner wins
        if (newPositionInt>=(((b.getRow())*(b.getCol()))-1)){
            /*to make it easier to draw the board, put the player into the highest point
              find the row and column of the highest point using findRowColOfPosition,
              then convert it into int and put player to the square located in the provided row and column
             */
            int highestPoint = (b.getRow()*b.getCol()) -1;
            String squareLocation = b.findRowColOfPosition(highestPoint);
            String[] tokens = squareLocation.split(",");
            int row = Integer.parseInt(tokens[0]);
            int column = Integer.parseInt(tokens[1]);
            this.setPosition(b.getSquaresOnTheBoard(row, column));
            return true;
        }else {
            //find where the square is on the board
            String squareLocation = b.findRowColOfPosition(newPositionInt);
            //split using the comma
            String[] tokens = squareLocation.split(",");
            //information about row is store in the first element in the array, we convert it to an integer with Integer.parseInt
            //information about row is store in the second element in the array, we convert it to an integer
            int row = Integer.parseInt(tokens[0]);
            int column = Integer.parseInt(tokens[1]);
            //set this position to the right square located in the provided row and column
            this.setPosition(b.getSquaresOnTheBoard(row, column));
            if (this.getPlayerSquare().getDelta() != 0) {
                newPositionInt = this.getPlayerSquare().getSquarePosition() + (this.getPlayerSquare().getDelta());
                //find where the square is on the board
                squareLocation = b.findRowColOfPosition(newPositionInt);
                tokens = squareLocation.split(",");
                row = Integer.parseInt(tokens[0]);
                column = Integer.parseInt(tokens[1]);
                this.setPosition(b.getSquaresOnTheBoard(row, column));
                System.out.println("Delta, new position " + newPositionInt);
            }
        }
        return false;
    }


}

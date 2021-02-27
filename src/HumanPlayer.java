import java.util.Scanner;

public class HumanPlayer extends Player {

    //use Scanner(System.in) to get the input, and assign the integer input to variable 'count'
    private Scanner s = new Scanner(System.in);

    public HumanPlayer(char identifier, Square position) {
        super(identifier, position);
    }


//override the move method
public boolean move(Board b){ 
    int row = -1; 
    int column = -1;
    //ask for the integer input
    System.out.println("Please enter an integer between 1 - 6.");
    int count =  s.nextInt();
    //new position is current position + the input
    int newPositionInt = this.getPlayerSquare().getSquarePosition() + count;
    System.out.println("New position is "+newPositionInt);
        //the rest is the same as move method in Player
    if (newPositionInt>=(((b.getRow())*(b.getCol()))-1)){
        int highestPoint = (b.getRow()*b.getCol()) -1;
        int[] squareLocation = b.findRowColOfPosition(highestPoint);
        row = squareLocation[0];
        column = squareLocation[1];
        this.setPosition(b.getSquaresOnTheBoard(row, column));
        return true;
    }else {
        int[] squareLocation = b.findRowColOfPosition(newPositionInt);
        row = squareLocation[0];
        column = squareLocation[1];
        this.setPosition(b.getSquaresOnTheBoard(row, column));
        if (this.getPlayerSquare().getDelta() != 0) {
            newPositionInt = this.getPlayerSquare().getSquarePosition() + (this.getPlayerSquare().getDelta());
            squareLocation = b.findRowColOfPosition(newPositionInt);
            row = squareLocation[0];
            column = squareLocation[1];
            this.setPosition(b.getSquaresOnTheBoard(row, column));
            System.out.println("Delta, new position is " + newPositionInt);
            }
        }
        return false;
    }


}

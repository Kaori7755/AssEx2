public class Board {
    private Player[] playersOnTheBoard;
    private Square[][] squaresOnTheBoard;
    private int row;
    private int col;

    public Board(int row, int column) {
        this.row = row;
        this.col = column;
    }

    public Square getSquaresOnTheBoard(int x, int y) {
        return squaresOnTheBoard[x][y];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public void createSquares() {
        //create the array of square object according to the number of row and column we want
        squaresOnTheBoard = new Square[row][col];
        //create a first array which elements is stored from left to right
        int position = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                squaresOnTheBoard[i][j] = new Square(position, 0, null); //[0][0] = 0
                position++;
            }
        }
        /*crete a new array in which element is copied from the first array,
        but elements in some tuples are stored in different direction so that the rows of the board can wrap
        */
        Square[][] newArray = new Square[row][col];
        for (int i = 0; i < row; i++) {
            //print the position of the square object from right to left if i is an even number
            if (i % 2 == 0) {
                for (int j = 0; j < col; j++) {
                    newArray[i][j] = squaresOnTheBoard[i][j];
                }
                //print the position of the square object from right to left if i is an odd number
            } else {
                int b = 1;
                for (int j = 0; j < col; j++) {
                    newArray[i][j] = squaresOnTheBoard[i][col - b];
                    b++;
                }
            }
        }
        squaresOnTheBoard = newArray;
    }

    public void assignPlayersToSquare() {
        int a = 0;
        /*
        to clear old record, we create a new Player array with nothing and assign it to every square of the board
         */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Player[] o = new Player[1];
                squaresOnTheBoard[i][j].setPlayersAtTheSquare(o);
            }
        }

        /*
        we then assign new player position to the squares
         */
        do {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if ((playersOnTheBoard[a].getPlayerSquare()) == (squaresOnTheBoard[i][j])) {
                        squaresOnTheBoard[i][j].addPlayers(playersOnTheBoard[a]);
                    }
                }
            }
            //'a' represent the each player reference, when a player is assigned to the corresponding square,
            // move to the next player (a++) and restart the loop until it finds the corresponding square, then assign the player to it
            a++;
        } while (a < playersOnTheBoard.length);
    }


    public void addPlayers(Player o) {
        Player[] newPlayers;
        try {
            //if an old Player array doesn't exist (ie.e no player on the board)
            //create a Player array with the length 1
            if (playersOnTheBoard == null) {
                newPlayers = new Player[1];
                newPlayers[0] = o;
                //Player always start at position 0
                newPlayers[0].setPosition(squaresOnTheBoard[0][0]);

            } else {
                //if an old Player array already exist
                //create a new array where array length is the old array +1
                newPlayers = new Player[playersOnTheBoard.length + 1];
                for (int i = 0; i < newPlayers.length - 1; i++) {
                    //copy elements from the old array
                    newPlayers[i] = playersOnTheBoard[i];
                }
                //add the new player into the last space in the new Players array
                newPlayers[newPlayers.length - 1] = o;
                //Player always start at position 0
                newPlayers[newPlayers.length - 1].setPosition(squaresOnTheBoard[0][0]);
            }
            playersOnTheBoard = newPlayers;
            //if NullPointerException is caught, print out a reminder message
        } catch (NullPointerException e) {
            System.out.println("Square is not created yet. Please create squares before adding Player.");
        }
    }


    public String toString() {
        //always update the player and square relationship before drawing the board
        assignPlayersToSquare();
        //draw the board using Square toString method
        String drawBoard ="";
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                drawBoard += squaresOnTheBoard[i][j].toString();
            } drawBoard+="\n";
        } return drawBoard;
    }



    public int[] findRowColOfPosition(int position) {
        int[] rowCol = new int[2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                /* Go through every square,
                 if the square position matches the parameter, store i to rowCol[0] and store j to rowCol[1].
                 RowCol[0] is the row of the position, rowCol[1] is the column of the position
                 */
                if (squaresOnTheBoard[i][j].getSquarePosition() == position) {
                    rowCol[0] = i;
                    rowCol[1]=j;
                }
            }
        }
        return rowCol;
    }

    public int findPositionInRowCol(int row, int col) {
        int position = 0;
        try {
            //find the square using the row and column provided, then get the position of the square using the getter
            position = squaresOnTheBoard[row][col].getSquarePosition();
        } catch (ArrayIndexOutOfBoundsException e) {
            //if ArrayIndexOutOfBoundsException is caught
            System.out.println("Please check your input. Row number should not be bigger than " + (this.row - 1) + " and column number should not be bigger than " + (this.col - 1) + ".");
        } finally {
            return position;
        }
    }

    public Square squareReference(int position) {
        /*Find the row and column of the given position, using findRowColOfPosition(),
        then store the result (the square object)in a new square reference, finally return the reference
         */
        int[] o = findRowColOfPosition(position);
        int row = o[0];
        int column = o[1];
        Square squareReference = squaresOnTheBoard[row][column];
        return squareReference;
    }

    public boolean takeTurns(Board b) {
        boolean win = false;
        //loop through the players and calls each of their move methods
        for (int i = 0; i < playersOnTheBoard.length; i++) {
            System.out.println(playersOnTheBoard[i].getIdentifier() + " 's turn");
            //if player is a human player, run human move method and check if human is wining
            if (playersOnTheBoard[i] instanceof HumanPlayer) {
                if (playersOnTheBoard[i].move(b) == true) {
                    //if the player wins the game, set variable win to true
                    win = true;
                    //draw the board after every move
                    System.out.print(b);
                    //if the human player wins, print out a win statement with the player identifier
                    System.out.println(playersOnTheBoard[i].getIdentifier() + " wins");
                    //then return true and takeTurns immediately terminate
                    return win;
                }else{
                    //draw the board after every move
                    System.out.print(b);
                }
            }else
                //if player is not a human, run computer move method and check if computer is wining
                if (playersOnTheBoard[i].move(b) == true) {
                    win = true;
                    System.out.print(b);
                    System.out.println(playersOnTheBoard[i].getIdentifier() + " wins");
                    return win;
                } else
                    //draw the board after every move
                    System.out.print(b);
            //if no one wins, return the variable win, which should be false at this point
            }return win;
        }



    public static void main(String[] args) {
        // create a board with 10 rows and 5 columns
        Board board = new Board(10, 5);
        board.createSquares();
        //create two player, here I created Player E and Player S
        //create Player E, position is null because Player is not on the board yet, so the player has no position yet
        Player e = new Player('E', null);
        //create Player S
        Player s = new Player('S', null);
        //add player E and S to the board
        board.addPlayers(e);
        board.addPlayers(s);
        //draw the board
        System.out.println(board);
    }
}

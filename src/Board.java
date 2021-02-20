public class Board {
    private Player[] playersOnTheBoard;
    private Square[][] squaresOnTheBoard;
    private int row;
    private int col;

    public Board(int row, int column, Player... players) {
        playersOnTheBoard = players;
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


//    public void createSquares() {
//        //create the array of square object according to the number of row and column we want
//        squaresOnTheBoard = new Square[row][col];
//        //create a first array which elements is stored from left to right
//        int a = 1;
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                squaresOnTheBoard[i][j] = new Square((row * col - a), 0,null);
//                if (playersOnTheBoard != null) {
//                    for(int x =0; x< playersOnTheBoard.length; i++){
//                        if ((playersOnTheBoard[x].getPosition()) == (squaresOnTheBoard[i][j])) {
//                            squaresOnTheBoard[i][j].addPlayers(playersOnTheBoard[x]);
//                        }
//                    }
//                }
//                a++;
//            }
//        }
//        /*crete a new array in which element is copy from the first array,
//        but elements in some tuples are stored in different direction so that the rows of the board can wrap
//        */
//        Square[][] newArray = new Square[row][col];
//        for (int i = 0; i < row; i++) {
//            //print the position of the square object from right to left if i is an even number
//            if (i % 2 == 0) {
//                for (int j = 0; j < col; j++) {
//                    newArray[i][j] = squaresOnTheBoard[i][j];
//                }
//                //print the position of the square object from right to left if i is an odd number
//            } else if (i % 2 != 0) {
//                int b = 1;
//                for (int j = 0; j < col; j++) {
//                    newArray[i][j] = squaresOnTheBoard[i][col - b];
//                    b++;
//                }
//            }
//        }
//        squaresOnTheBoard = newArray;
//    }

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
        /*crete a new array in which element is copy from the first array,
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
            } else if (i % 2 != 0) {
                int b = 1;
                for (int j = 0; j < col; j++) {
                    newArray[i][j] = squaresOnTheBoard[i][col - b];
                    b++;
                }
            }
        }
        squaresOnTheBoard = newArray;
    }

//    public void assignPlayersToSquare() {
//        int a = 0;
//        do {
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    if ((playersOnTheBoard[a].getPlayerPosition()) == (squaresOnTheBoard[i][j])) {
//                        squaresOnTheBoard[i][j].addPlayers(playersOnTheBoard[a]);
//                    }
//                }
//            }
//            a++;
//        } while (a < playersOnTheBoard.length);
//    }

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
                    if ((playersOnTheBoard[a].getPlayerPosition()) == (squaresOnTheBoard[i][j])) {
                        squaresOnTheBoard[i][j].addPlayers(playersOnTheBoard[a]);
                    }
                }
            }
            //a represent the each player reference, when a player is assigned to the corresponding square,
            // move to the next player (a++) and restart the loop until it finds the corresponding square, then assign the player to it
            a++;
        } while (a < playersOnTheBoard.length);
    }


    public void addPlayers(Player o) {
        Player[] newPlayers;
        try {
            //if an old Player array doesn't exist
            //create a Player array with the length 1
            if (playersOnTheBoard == null) {
                newPlayers = new Player[1];
                newPlayers[0] = o;
                //Player always start at position 0
                newPlayers[0].setPosition(squaresOnTheBoard[0][0]);
                playersOnTheBoard = newPlayers;
                //if an old Player array already exist
            } else if (playersOnTheBoard != null) {
                int playerLength = playersOnTheBoard.length;
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
                playersOnTheBoard = newPlayers;
            }
        } catch (NullPointerException g) {
            System.out.println("Square is not created yet. Please create squares before adding Player.");
        }
    }


    public void drawBoard() {
        //always update the player and square relationship before drawing the board
        assignPlayersToSquare();
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if (squaresOnTheBoard[i][j].getPlayersAtTheSquare()[0] == null) {
                    String delta = "(    )";
                    int position = squaresOnTheBoard[i][j].getSquarePosition();
                    if (squaresOnTheBoard[i][j].getDelta() == 0) {
                        System.out.print(String.format("%15d", position) + delta);
                    } else {
                        delta = "(" + String.format("%4d", squaresOnTheBoard[i][j].getDelta()) + ")";
                        System.out.print(String.format("%15d", position) + delta);
                    }
                } else if (squaresOnTheBoard[i][j].getPlayersAtTheSquare()[0] != null) {
                    //use String to store player identifier rather than char, because char type is not big enough to store more than one character
                    String playerIdentifier = "";
                    int position = -1;
                    String delta = "(    )";
                    for (int a = 0; a < squaresOnTheBoard[i][j].getPlayersAtTheSquare().length; a++) {
                        playerIdentifier += squaresOnTheBoard[i][j].getPlayersAtTheSquare()[a].getIdentifier() + " ";
                        position = squaresOnTheBoard[i][j].getSquarePosition();
                    }
                    String identifierAndPosition = playerIdentifier + position;
                    if (squaresOnTheBoard[i][j].getDelta() == 0) {
                        System.out.print(String.format("%15s", identifierAndPosition) + delta);
                    } else {
                        delta = "(" + String.format("%4d", squaresOnTheBoard[i][j].getDelta()) + ")";
                        System.out.print(String.format("%15s", identifierAndPosition) + delta);
                    }

                }

            }
            System.out.println();
        }
    }

//    public void setSquareDelta() {
//        this.squaresOnTheBoard[9][1].setDelta(-3);
//        this.squaresOnTheBoard[6][0].setDelta(-1);
//        this.squaresOnTheBoard[6][2].setDelta(-4);
//        this.squaresOnTheBoard[6][3].setDelta(2);
//        this.squaresOnTheBoard[6][4].setDelta(-1);
//        this.squaresOnTheBoard[5][0].setDelta(-4);
//        this.squaresOnTheBoard[3][1].setDelta(4);
//        this.squaresOnTheBoard[3][4].setDelta(-1);
//    }


    public String findRowColOfPosition(int position) {
        String rowCol = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (squaresOnTheBoard[i][j].getSquarePosition() == position) {
                    rowCol = i + "," + j;
                }
            }
        }
        return rowCol;
    }

    public int findPositionInRowCol(int row, int col) {
        int position = 0;
        try {
            position = squaresOnTheBoard[row][col].getSquarePosition();
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Please check your input. Row number should not be bigger than " + (this.row - 1) + " and column number should not be bigger than " + (this.col - 1) + ".");
        } finally {
            return position;
        }
    }

    public Square squareReference(int position) {
        //find the row and column of the given position
        String o = findRowColOfPosition(position);
        String[] tokens = o.split(",");
        int row = Integer.parseInt(tokens[0]);
        int column = Integer.parseInt(tokens[1]);
        Square squareReference = squaresOnTheBoard[row][column];
        return squareReference;
    }


    public void printPlayersOnTheBoard() {
        for(int i=0; i<playersOnTheBoard.length; i++){
        System.out.print( playersOnTheBoard[i]);
     }
    }

    public boolean takeTurns(Board b) {
        boolean win = false;
        for (int i = 0; i < playersOnTheBoard.length; i++) {
            System.out.println(playersOnTheBoard[i].getIdentifier() + " 's turn");
            //if player is a human player, run human move() and check if human is wining
            if (playersOnTheBoard[i] instanceof HumanPlayer) {
                if (((HumanPlayer) playersOnTheBoard[i]).move(b) == true) {
                    //if the player wins the game, set variable win to true
                    win = true;
                    //draw the board after every move
                    b.drawBoard();
                    //if the human player wins, print out a win statement with the player identifier
                    System.out.println(playersOnTheBoard[i].getIdentifier() + " wins");
                    return win;
                }else{
                    //draw the board after every move
                    b.drawBoard();
                }
            }else
                //if player is not a human, run computer move() and check if computer is wining
                if (playersOnTheBoard[i].move(b) == true) {
                    win = true;
                    b.drawBoard();
                    System.out.println(playersOnTheBoard[i].getIdentifier() + " wins");
                    return win;
                } else
                    b.drawBoard();
            //if no one wins, return the variable win, which should be false at this point
            }return win;
        }



    public static void main(String[] args) {
        // create a board with 10 rows and 5 columns
        Board board = new Board(10, 5);
        board.createSquares();
        //create two player, here I created Player E and Player S
        //create Player E, position is null because Player is not on the board yet, so the player has no position yet
        Player E = new Player('E', null);
        //create Player S
        Player S = new Player('S', null);
        //add player E and S to the board
        board.addPlayers(E);
        board.addPlayers(S);
        //draw the board
        board.drawBoard();
    }
}

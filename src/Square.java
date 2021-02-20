public class Square {
    private int position;
    private Player[] playersAtTheSquare = new Player[1];
    private int delta;

    /*
    delta indicates a ladder or snake stating at a square
    a positive number indicates a ladder
    a negative number indicates a snake
    0 indicates there is no ladder or snake at a square
     */
    public Square(int position, int delta, Player players) {
        this.position = position;
        this.delta = delta;
        playersAtTheSquare[0] = players;
    }


    public void setSquarePosition(int position) {
        this.position = position;
    }

    public void addPlayers(Player o) {
        Player[] newPlayers = null;
            if (playersAtTheSquare[0] == null) {
                newPlayers = new Player[playersAtTheSquare.length];
                newPlayers[0] = o;
            } else {
                newPlayers =new Player[playersAtTheSquare.length + 1];
                for (int i = 0; i < newPlayers.length-1; i++) {
                    //copy elements from the old array
                newPlayers[i] = playersAtTheSquare[i];
                //put the player we want to add into the list space in the new Players array
            } newPlayers[newPlayers.length - 1] = o;
        }
        //redirect the referent (playersAtTheSquare) to the new array
        playersAtTheSquare = newPlayers;
    }




    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getSquarePosition() {
        return this.position;
    }


    public void setPlayersAtTheSquare(Player[] o) {
        this.playersAtTheSquare = o;
    }

    public int getDelta() {
        return this.delta;
    }


    public Player[] getPlayersAtTheSquare() {
        return playersAtTheSquare;
    }



    public String toString() {
        String k = "";
        String square = "Square\n" + "Square position is " + this.position + ". The delta value of the square is " + this.getDelta() + ".";
        if (playersAtTheSquare[0] != null) {
            for (int i = 0; i < playersAtTheSquare.length - 1; i++) {
                k += playersAtTheSquare[i].getIdentifier() + ",";
            }
            k += playersAtTheSquare[playersAtTheSquare.length - 1].getIdentifier();
            square += " Player at this square is " +
                    k + ".";

        }
        return square;
    }



}


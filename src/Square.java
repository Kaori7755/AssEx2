public class Square {
    private int position;//indicate the square's position on the board
    private Player[] playersAtTheSquare = new Player[1]; //indicate the number of players currently on the square
    private int delta; //the delta of the square


    public Square(int position, int delta, Player players) {
        this.position = position;
        this.delta = delta;
        playersAtTheSquare[0] = players;
    }


    public void addPlayers(Player o) {
        //create the reference of a Player array, not pointing to any Player array yet
        Player[] newPlayers = null;
        /*
        If there is no player on the square (i.e. playerAtTheSquare[0] == null)
        create a new player array with the same length as the old one, using the reference we created earlier,
        and put the player we want to add in the first space of the array.
        If there is player on the square already, create a new array that the array length is old array + 1, use the reference we created earlier for the array,
        then copy elements from the old array, and finally add the player in the last space of the new array.
         */
            if (playersAtTheSquare[0] == null) {
                newPlayers = new Player[playersAtTheSquare.length];
                newPlayers[0] = o;
            } else {
                newPlayers =new Player[playersAtTheSquare.length + 1];
                for (int i = 0; i < newPlayers.length-1; i++) {
                    //copy elements from the old array
                newPlayers[i] = playersAtTheSquare[i];
                //put the player we want to add into the last space in the new Players array
            } newPlayers[newPlayers.length - 1] = o;
        }
        //redirect the old reference (playersAtTheSquare) to the new array
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


    public String toString() {
        /*
        Return a String representation of the square consisting of position, delta (if delta is not 0) and player.
         */
        String p = "";
        String s = "" + this.position;
        String whole= "";

        if (playersAtTheSquare[0] != null) {
            for (int i = 0; i < playersAtTheSquare.length ; i++) {
                p += playersAtTheSquare[i].toString() + " ";
            }
        }

        if(this.delta!=0) {
            s += "(" + String.format("%4d", this.delta) + ")";
        }
        else{
               s += "(    )";
            }
        whole = p+s;
        return String.format("%20s",whole);
    }



}


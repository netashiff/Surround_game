package Surrond;

public class Surround4Game {
    /** The basic board */
    private Cell[][] board;
    /** The basic board */
    private boolean[][] check1;
    /** The player*/
    private int player;
    /** The number of player*/
    private int numbers;
    /** array of players*/
    private int [] playersArray;

    /******************************************************************
     *  The is the default constuctor for surround game defind the board
     */
    public Surround4Game(int size,int playern, int chooseplayer, int[] array) {
        //super();
        board = new Cell[size][size];
        check1= new boolean[size][size];
        numbers = playern;
        this.player = chooseplayer;
        playersArray = array;

    }

    /******************************************************************
     *  This method reset all the cells to null for the start of the game
     *  *
     * @param size is the size of the bored from the user
     */
    public void reset(int size) {

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board[r][c] = null;
            }
        }
    }

    /******************************************************************
     *  This method return the cell value
     *  *
     * @param row is the row of the cell that we want to get
     * @param col is the colum that we want to check
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /******************************************************************
     *  Amethod that give the current player
     * *
     * @return the currect player
     */
    public int getCurrentPlayer() {
        return player;
    }

    /******************************************************************
     *  A method that giving the next player number
     * *
     * @return the number of the next player
     */
    public int nextPlayer() {
        do {
            player = (player + 1) % numbers;
        } while (playersArray[player] < 0);
        return player;
    }

    /******************************************************************
     *
     *   This method check if the cell is open and put the
     *   right number in the cell.
     **
     * @param row in the table that we refering to
     * @param  col is the colum in the table
     *
     */
    public boolean select(int row, int col) {
        if (board[row][col] == null ) {
            //|| (cats() && board[row][col].getPlayeNumber() != player)) {
            Cell c = new Cell(player);
            board[row][col] = c;
            return true;
        }
        else
            return false;
    }

    /******************************************************************
     * A method that check if someone won
     * *
     * @return the number of the player that won or -1 if no one won.
     */
    public int getWinner() {
        if(corners() != -2)
            return corners();
        if(sides() != -2)
            return sides();
        if(middle()!= -2)
            return middle();
        return -1;
    }

    public int getwinner2(int col,int row)
    {
        if(Complext(col,row,getCurrentPlayer())) {
            playersArray[getCurrentPlayer()]=-3;
            return getCurrentPlayer();
        }
        return -1;
    }

    public int getw()
    {
        int count =0;
        for( int i=0;i<playersArray.length;i++)
        {
            if(playersArray[i] <0)
                count++;
        }
        if(count == playersArray.length)
            return getCurrentPlayer();
        return -1;
    }

    /******************************************************************
     *
     *   This method checking the corners of the board
     **
     *
     * @return the player number if he won or -1 if he didnt
     *
     */
    public int corners()
    {
        //above left
        if (board[0][1] != null && board[1][0] != null) {
            if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber()
                    && board[0][1].getPlayerNumber() != board[0][0].getPlayerNumber())
                return board[0][1].getPlayerNumber();
        }
        if (board[0][board.length-2] != null && board[1][board.length-1] != null) {
            if(board[0][board.length-2].getPlayerNumber() == board[1][board.length-1].getPlayerNumber()
                    && board[1][board.length-1].getPlayerNumber() != board[0][board.length-1].getPlayerNumber())
                return board[0][board.length-2].getPlayerNumber();
        }
        if (board[board.length-2][0] != null && board[board.length-1][1] != null) {
            if (board[board.length-2][0].getPlayerNumber() == board[board.length-1][1].getPlayerNumber()
                    && board[board.length-1][1].getPlayerNumber() != board[board.length-1][0].getPlayerNumber())
                return board[board.length-1][1].getPlayerNumber();
        }
        if (board[board.length-1][board.length-2] != null && board[board.length-2][board.length-1] != null) {
            if(board[board.length-1][board.length-2].getPlayerNumber() == board[board.length-2][board.length-1].getPlayerNumber()
                    && board[board.length-2][board.length-1].getPlayerNumber() != board[board.length-1][board.length-1].getPlayerNumber())
                return board[board.length-1][board.length-2].getPlayerNumber();
        }
        return -2;

    }

    /******************************************************************
     *
     *   This method checking the 4 sides of the board
     **
     *
     * @return the player number if he won or -2 if he didnt
     *
     */
    public int sides()
    {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++) {
                // left-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length - 1 && col == 0)
                {
                    if (board[row - 1][col] != null && board[row][col + 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                                && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col+1].getPlayerNumber();
                }
                // right-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length-1 && col == board.length-1) {
                    if (board[row - 1][col] != null && board[row][col - 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber()
                                && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col-1].getPlayerNumber();
                }
                // above-border  (excluding corners - check 3 sides only)
                if(col!=0&&col!=board.length-1 && row ==0)
                {
                    if (board[row][col-1] != null && board[row][col + 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row][col-1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                                && board[row+1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col - 1].getPlayerNumber();
                }
                // bottom-border  (excluding corners - check 3 sides only)
                if(col!=0&&col!=board.length-1 && row == board.length-1)
                {
                    if (board[row][col-1] != null && board[row][col + 1] !=
                            null && board[row -1][col] != null)
                        if (board[row][col-1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                                && board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col - 1].getPlayerNumber();
                }

            }
        return -2;

    }

    /******************************************************************
     *
     *   This method checking the inside of the board
     **
     *
     * @return the player number if he won or -2 if he didnt
     *
     */
    public int middle()
    {
        // searching just the middle
        for (int row = 1; row < board.length-1; row++) {
            for (int col = 1; col < board.length-1; col++) {
                if (board[row][col - 1] != null && board[row][col + 1] != null && board[row - 1][col] != null
                        && board[row + 1][col] != null && board[row][col] != null)
                    if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                            && board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                            board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                            board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                        return board[row][col - 1].getPlayerNumber();
            }
        }
        return -2;

    }
//    public int checkRiskLevelCorners() {
//        //top-left corner case
//        if (board[0][1] != null && board[1][0] != null && board[0][0] == null) {
//            if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber()) {
//                return 1;
//            }
//        }
//
//        //top-right corner case
//        if (board[0][board.length - 2] != null && board[1][board.length - 1] != null && board[0][board.length - 1] == null) {
//            if (board[0][board.length - 2].getPlayerNumber() == board[1][board.length - 1].getPlayerNumber())
//                return 2;
//        }
//
//        //bottom-left corner case
//        if (board[board.length - 2][0] != null && board[board.length - 1][1] != null && board[board.length - 1][0] == null) {
//            if (board[board.length - 2][0].getPlayerNumber() == board[board.length - 1][1].getPlayerNumber())
//                return 3;
//        }
//
//        //bottom-right corner case
//        if (board[board.length - 1][board.length - 2] != null && board[board.length - 2][board.length - 1] != null && board[board.length - 1][board.length - 1] == null) {
//            if (board[board.length - 1][board.length - 2].getPlayerNumber() == board[board.length - 2][board.length - 1].getPlayerNumber())
//                return 4;
//        }
//        return 5;
//    }
//
//    public int checkRiskLevelMiddle() {
//        for(int i =0; i<board.length; i++)
//        {
//            for(int j =0; j<board.length; j++)
//            {
//
//            }
//        }
//        return 1;
//    }
//
//    /******************************************************************
//     * A method that check if someone won
//     * *
//     * @return the number of the player that won or -1 if no one won.
//     */
    public int getBurnOut() {
        if(corners1() != -2) {
            int i =playersArray[corners1()];
            playersArray[i] = -3;
            return i;
        }
        if(sides1() != -2) {
            int j=playersArray[sides1()];
            playersArray[j] = -3;
            return j;
        }
        if(middle1()!= -2)
        {
            int j= playersArray[middle1()];
            playersArray[j] = -3;
            return j;
        }
        return -1;
    }

    /******************************************************************
     *
     *   This method checking the corners of the board
     **
     *
     * @return the player number if he won or -1 if he didnt
     *
     */
    public int corners1()
    {
        //above left
        if (board[0][1] != null && board[1][0] != null) {
            if (board[0][1].getPlayerNumber() != board[0][0].getPlayerNumber()
                    && board[1][0].getPlayerNumber() != board[0][0].getPlayerNumber())
                return board[0][0].getPlayerNumber();
        }
        //left bottom
        if (board[0][board.length-2] != null && board[1][board.length-1] != null) {
            if(board[1][board.length-1].getPlayerNumber() != board[0][board.length-1].getPlayerNumber()&&
                    board[0][board.length-2].getPlayerNumber() != board[0][board.length-1].getPlayerNumber())
                return board[0][board.length-1].getPlayerNumber();
        }
        //right top
        if (board[board.length-2][0] != null && board[board.length-1][1] != null) {
            if (board[board.length-1][1].getPlayerNumber() != board[board.length-1][0].getPlayerNumber()&&
                    board[board.length-2][0].getPlayerNumber() != board[board.length-1][0].getPlayerNumber())
                return board[board.length-1][0].getPlayerNumber();
        }
        //right bottom
        if (board[board.length-1][board.length-2] != null && board[board.length-2][board.length-1] != null) {
            if(board[board.length-2][board.length-1].getPlayerNumber() !=
                    board[board.length-1][board.length-1].getPlayerNumber()&&
                    board[board.length-1][board.length-2].getPlayerNumber() !=
                            board[board.length-1][board.length-1].getPlayerNumber()) {
                return board[board.length - 1][board.length - 1].getPlayerNumber();
            }
        }
        return -2;

    }

    /******************************************************************
     *
     *   This method checking the 4 sides of the board
     **
     *
     * @return the player number if he won or -2 if he didnt
     *
     */
    public int sides1()
    {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++) {
                // left-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length - 1 && col == 0)
                {
                    if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()
                                && board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
                                board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()
                        )
                            return board[row][col].getPlayerNumber();
                }
                // right-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length-1 && col == board.length-1) {
                    if (board[row - 1][col] != null && board[row][col - 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() !=board[row][col].getPlayerNumber()
                                && board[row][col].getPlayerNumber()!= board[row + 1][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col].getPlayerNumber();
                }
                // above-border  (excluding corners - check 3 sides only)
                if(col!=0&&col!=board.length-1 && row ==0)
                {
                    if (board[row][col-1] != null && board[row][col + 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row][col].getPlayerNumber()== board[row][col + 1].getPlayerNumber()
                                && board[row+1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col].getPlayerNumber();
                }
                // bottom-border  (excluding corners - check 3 sides only)
                if(col!=0&&col!=board.length-1 && row == board.length-1)
                {
                    if (board[row][col-1] != null && board[row][col + 1] != null && board[row -1][col] != null)
                        if (board[row][col].getPlayerNumber()!= board[row][col + 1].getPlayerNumber()
                                && board[row-1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col - 1].getPlayerNumber();
                }

            }
        return -2;

    }

    /******************************************************************
     *
     *   This method checking the inside of the board
     **
     *
     * @return the player number if he won or -2 if he didnt
     *
     */
    public int middle1()
    {
        // searching just the middle
        for (int row = 1; row < board.length-1; row++) {
            for (int col = 1; col < board.length-1; col++) {
                if (board[row][col - 1] != null && board[row][col + 1] != null && board[row - 1][col] != null
                        && board[row + 1][col] != null && board[row][col] != null)
                    if (board[row][col].getPlayerNumber()!= board[row][col + 1].getPlayerNumber()
                            && board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
                            board[row][col].getPlayerNumber()!= board[row + 1][col].getPlayerNumber() &&
                            board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                        return board[row][col].getPlayerNumber();
            }
        }
        return -2;

    }


    public boolean Complext(int col, int row,int cplayer)
    {
        instantiateV();
        if(board[row+1][col]== null|| board[row][col+1] == null ||board[row][col-1]== null ||board[row-1][col]== null)
            return false;
        if(col+1< board.length&& !getV(col,row) && getCurrentPlayer() == cplayer) {
            setV(col,row);
            return Complext(col + 1, row,cplayer);
        }
        if(col-1< board.length&& !getV(col,row) && getCurrentPlayer() == cplayer) {
            setV(col,row);
            return Complext(col -1, row,cplayer);
        }
        if(row+1< board.length&& !getV(col,row) && getCurrentPlayer() == cplayer) {
            setV(col,row);
            return Complext(col , row + 1,cplayer);
        }
        if(row-1< board.length&& !getV(col,row) && getCurrentPlayer() == cplayer) {
            setV(col,row);
            return Complext(col, row-1,cplayer);
        }
        return true;

    }
    /**instantiate the check to false to start the test*/
    public void instantiateV(){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                check1[row][col] = false;
            }
        }
    }
    /**
     * set the specific cell to true if it was visit
     *
     * @param c the colum that we are looking
     * @param r the row that we changing
     * */
    public void setV(int c,int r){
        check1[r][c] = true;
    }
    /**
     * @return the value in the specific cell */
    public boolean getV(int c,int r)
    {
        return check1[r][c];
    }

    public int checkRiskLevelCorners() {
        //top-left corner case
        if (board[0][1] != null && board[1][0] != null &&
                board[0][0] == null) {
            if (board[0][1].getPlayerNumber() ==
                    board[1][0].getPlayerNumber()) {
                return 1;
            }
        }

        //top-right corner case
        if (board[0][board.length - 2] != null &&
                board[1][board.length - 1] != null &&
                board[0][board.length - 1] == null) {
            if (board[0][board.length - 2].getPlayerNumber() ==
                    board[1][board.length - 1].getPlayerNumber())
                return 2;
        }

        //bottom-left corner case
        if (board[board.length - 2][0] != null &&
                board[board.length - 1][1] != null &&
                board[board.length - 1][0] == null) {
            if (board[board.length - 2][0].getPlayerNumber() ==
                    board[board.length - 1][1].getPlayerNumber())
                return 3;
        }

        //bottom-right corner case
        if (board[board.length - 1][board.length - 2] != null &&
                board[board.length - 2][board.length - 1] != null &&
                board[board.length - 1][board.length - 1] == null) {
            if (board[board.length - 1][board.length - 2].getPlayerNumber() ==
                    board[board.length - 2][board.length - 1].getPlayerNumber())
                return 4;
        }

        //top-left corner case
        if (board[0][1] != null ||
                board[1][0] != null &&
                        board[0][0] == null) {
            return 5;
        }
        //top-right corner case
        if (board[0][board.length - 2] != null ||
                board[1][board.length - 1] != null &&
                        board[0][board.length - 1] == null) {
            return 6;
        }
        //bottom-left corner case
        if (board[board.length - 2][0] != null ||
                board[board.length - 1][1] != null &&
                        board[board.length - 1][0] == null) {
            return 7;
        }
        //bottom-right corner case
        if (board[board.length - 1][board.length - 2] != null ||
                board[board.length - 2][board.length - 1] != null &&
                        board[board.length - 1][board.length - 1] == null) {
            return 8;
        }

        return 9;
    }

    // //FIX MEEEEEEEEEEEEEEEEEEEEEEEEEEE????
    // only works towards left of panel becuase it runs through once and
    //returns only the topmost case... so not all yellows are yellow and not
    //all reds are red
    public int[] checkRiskLevelMiddleYELLOW(int playerTurn) {

        int[] rowCol = new int[2];

        // searching just the middle
        for (int row = 1; row < board.length - 1; row++) {
            for (int col = 1; col < board.length - 1; col++) {

                //check for only 2 surrounding
                if(board[row - 1][col] != null && board[row + 1][col] != null) {
                    if (board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                            board[row - 1][col].getPlayerNumber() != playerTurn) {
                        rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }
                }

                if(board[row][col - 1] != null && board[row - 1][col] != null)
                    if(board[row][col - 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber() &&
                            board[row][col - 1].getPlayerNumber() != playerTurn) {
                        rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }

                if (board[row][col - 1] != null && board[row + 1][col] != null)
                    if (board[row][col - 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                            board[row][col - 1].getPlayerNumber() != playerTurn) {
                        rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }

                if(board[row][col - 1] != null && board[row][col + 1] != null)
                    if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                            board[row][col - 1].getPlayerNumber() != playerTurn) {
                        rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }

                if(board[row][col + 1] != null && board[row - 1][col] != null)
                    if(board[row][col + 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber() &&
                            board[row][col + 1].getPlayerNumber() != playerTurn) {
                        rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }

                if (board[row][col + 1] != null && board[row + 1][col] !=null)
                    if(board[row][col + 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                            board[row][col + 1].getPlayerNumber() != playerTurn) {
                        rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }
            }

        }
        return null;
    }

    public int[] checkRiskLevelMiddleRED(int playerTurn){
//    int[] rowCol = new int[3];
        int[] urgentRowCol = new int[3];

        for (int row = 1; row < board.length - 1; row++) {
            for (int col = 1; col < board.length - 1; col++) {

                if (board[row - 1][col] != null && board[row + 1][col] != null)
                    if (board[row][col - 1] != null) {
                        if (board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber() &&
                                board[row - 1][col].getPlayerNumber() != playerTurn) {
                            urgentRowCol[0] = row;
                            urgentRowCol[1] = col;
                            return urgentRowCol;
                        }
                    } else if (board[row][col + 1] != null) {
                        if (board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row][col + 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber()  &&
                                board[row][col + 1].getPlayerNumber() != playerTurn) {
                            urgentRowCol[0] = row;
                            urgentRowCol[1] = col;
                            return urgentRowCol;
                        }
                    }

                if (board[row][col - 1] != null && board[row][col + 1] != null)
                    if (board[row - 1][col] != null) {
                        if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber()
                                && board[row][col - 1].getPlayerNumber() != playerTurn) {
                            urgentRowCol[0] = row;
                            urgentRowCol[1] = col;
                            return urgentRowCol;
                        }
                    } else if (board[row + 1][col] != null) {
                        if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row + 1][col].getPlayerNumber() != playerTurn) {
                            urgentRowCol[0] = row;
                            urgentRowCol[1] = col;
                            return urgentRowCol;
                        }
                    }
            }
        }
        return null;
    }

    public int[] checkRiskLevelBorders() {

        int[] rowCol = new int[2];

//    for (int row = 0; row < board.length; row++) {
//       for (int col = 0; col < board.length; col++) {
//
//          //left border
//          if ((row != 0) && (row != board.length - 1) && (col == 0)) {
//             if (board[row + 1][col] != null && board[row - 1][col] != null)
//                if (board[row + 1][col].getPlayerNumber() == board[row - 1][col].getPlayerNumber()) {
//                   rowCol[0] = row;
//                   rowCol[1] = col;
//                   return rowCol;
//                }
//          }
//
//          //right border
//          if ((row != 0) && (row != board.length - 1) && (col == board.length - 1)) {
//             if (board[row + 1][board.length - 1] != null && board[row - 1][board.length - 1] != null)
//                if (board[row + 1][board.length - 1].getPlayerNumber() == board[row - 1][board.length - 1].getPlayerNumber()) {
//                   rowCol[0] = row;
//                   rowCol[1] = col;
//                   return rowCol;
//                }
//          }
//
//          //top border
//          if ((col != 0) && (col != board.length - 1) && (row == 0)) {
//             if (board[0][col - 1] != null && board[0][col + 1] != null)
//                if (board[0][col - 1].getPlayerNumber() == board[0][col + 1].getPlayerNumber()) {
//                   rowCol[0] = row;
//                   rowCol[1] = col;
//                   return rowCol;
//                }
//          }
//
//          //bottom border
//          if ((col != 0) && (col != board.length - 1) && (row == board.length - 1)) {
//             if (board[board.length - 1][col - 1] != null && board[board.length - 1][col + 1] != null)
//                if (board[board.length - 1][col - 1].getPlayerNumber() == board[board.length - 1][col + 1].getPlayerNumber()) {
//                   rowCol[0] = row;
//                   rowCol[1] = col;
//                   return rowCol;
//                }
//          }
//       }
//    }
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++) {

                // left-border (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length - 1 && col == 0) {
                    if (board[row - 1][col] != null && board[row][col + 1] != null) {
//                && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber())
//                      && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
//                      board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    } else if (board[row - 1][col] != null && board[row + 1][col] != null) {
                        if (board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
                            rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }
                }

                // right-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length - 1 && col == board.length - 1) {
                    if (board[row - 1][col] != null && board[row][col - 1] != null) {
//                   && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber())
//                      && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
//                      board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    } else if (board[row - 1][col] != null && board[row + 1][col] != null){
                        if(board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
                            rowCol[0] = row;
                        rowCol[1] = col;
                        return rowCol;
                    }
                }
                // top-border  (excluding corners - check 3 sides only)
                if (col != 0 && col != board.length - 1 && row == 0) {
                    if (board[row][col - 1] != null && board[row][col + 1] != null) {
//                && board[row + 1][col] != null)
                        if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()) {
//                      && board[row + 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
//                      board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = row;
                            rowCol[1] = col;
                            return rowCol;
                        }
                    } else if (board[row][col - 1] != null && board[row + 1][col] != null)
                        if(board[row + 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber()){
                            rowCol[0] = row;
                            rowCol[1] = col;
                            return rowCol;
                        }
                }

                // bottom-border  (excluding corners - check 3 sides only)
                if (col != 0 && col != board.length - 1 && row == board.length - 1) {
                    if (board[row][col - 1] != null && board[row][col + 1] != null) {
//                && board[row - 1][col] != null)
                        if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()) {
//                      && board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
//                      board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = row;
                            rowCol[1] = col;
                            return rowCol;
                        }
                    } else if(board[row][col - 1] != null && board[row - 1][col] != null)
                        if(board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber()){
                            rowCol[0] = row;
                            rowCol[1] = col;
                            return rowCol;
                        }
                }
            }
//    return -1;

        return null;
    }

}




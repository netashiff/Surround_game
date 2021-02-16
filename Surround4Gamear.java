package Surrond;
import javax.swing.*;

public class Surround4Gameari {
    /**
     * The basic board
     */
    private Cell[][] board;

    /** The basic board */
    private boolean[][] check1;
    /**
     * Default Size of Board
     */
    private int size = 10;

    /**
     * Player
     */
    private int player = 0;

    /**
     * The number of players
     */
    private int numberPlayers = 2;

    /**
     * Player Array
     */
    private int[] playersArray;

    /*************************************************
     * This constructor creates the two dimensional
     * array of rows and columns depending on the size
     * that the player entered.
     * @param qSize size of board
     * @param playerNumber current player
     * @param choosePlayer player that starts
     * @param playersArray
     */
    public Surround4Gameari(int qSize, int playerNumber, int choosePlayer, int[] playersArray) {
//		super();
        size = qSize;
        board = new Cell[qSize][qSize];
        check1= new boolean[qSize][qSize];
        this.player = choosePlayer;
        numberPlayers = playerNumber;
        this.playersArray = playersArray;
    }

    /*************************************************
     * This method resets the board according to
     * the board size.
     */
    public void reset() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board[r][c] = null;
            }
        }
    }

    /*************************************************
     * This method returns the current cell.
     * @param row
     * @param col
     * @return Cell
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /*************************************************
     * This method returns the current player
     * @return integer number of current player
     */
    public int getCurrentPlayer() {
        return player;
    }

    /*************************************************
     * This method finds who the next player is
     * supposed to be
     * @return integer number of next player
     */
    public int nextPlayer() {
//		int count = 0;
//		for (int i = 0; i < playersArray.length; i++)
//			if (playersArray[i]<0)
//				count++;
//
//			if (
        do {
            player = (player + 1) % numberPlayers;
        } while (playersArray[player] < 0);
        return player;
    }


    /*************************************************
     * This method checks if a certain cell was
     * selected.
     * @param row to check
     * @param col to check
     * @return boolean true if param row and column
     * was selected
     * @return boolean false is param row and column
     * was not selected
     */
    public boolean select(int row, int col) {
        if (board[row][col] == null) {  //|| (cats() && board[row][col].getPlayeNumber() != player)) {
            Cell c = new Cell(player);
            board[row][col] = c;
            return true;
        } else
            return false;
    }

    /*************************************************
     * This method finds the ner of the game
     * by checking the corners, borders, and middle
     * cells.
     * @return integer number of winning player
     */
    public int getWinner() {
        //checks corner cells
        if (getCorners() != -1)
            return getCorners();

        //checks border cells
        if (getBorders() != -1)
            return getBorders();

        //checks middle cells
        if (getMiddle() != -1)
            return getMiddle();

        return -1;
    }

    /*************************************************
     * This method checks the corners to find who
     * wins the game
     * @return integer of winning player
     */
    public int getCorners() {
        //top-left corner case
        if (board[0][1] != null && board[1][0] != null&& board[0][0]!=null) {
            if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber()
                    && board[0][1].getPlayerNumber() != board[0][0].getPlayerNumber())
                return board[0][1].getPlayerNumber();
        }

        //top-right corner case
        if (board[0][board.length - 2] != null && board[1][board.length - 1] != null) {
            if (board[0][board.length - 2].getPlayerNumber() == board[1][board.length - 1].getPlayerNumber()
                    && board[1][board.length - 1].getPlayerNumber() != board[0][board.length - 1].getPlayerNumber())
                return board[0][board.length - 2].getPlayerNumber();
        }

        //bottom-left corner case
        if (board[board.length - 2][0] != null && board[board.length - 1][1] != null) {
            if (board[board.length - 2][0].getPlayerNumber() == board[board.length - 1][1].getPlayerNumber()
                    && board[board.length - 1][1].getPlayerNumber() != board[board.length - 1][0].getPlayerNumber())
                return board[board.length - 1][1].getPlayerNumber();
        }

        //bottom-right corner case
        if (board[board.length - 1][board.length - 2] != null && board[board.length - 2][board.length - 1] != null) {
            if (board[board.length - 1][board.length - 2].getPlayerNumber() ==
                    board[board.length - 2][board.length - 1].getPlayerNumber()
                    && board[board.length - 2][board.length - 1].getPlayerNumber() !=
                    board[board.length - 1][board.length - 1].getPlayerNumber())
                return board[board.length - 1][board.length - 2].getPlayerNumber();
        }

//	}

//
//		for (int row = 0; row < board.length; row++) {
//			for (int col = 0; col < board.length; col++) {
//
//			// top-left corner case (check 2 sides only)
//			if (board[0][1] != null && board[1][0] != null && board[0][0] != null)
//				if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber() &&
//						board[0][1].getPlayerNumber() != board[0][0].getPlayerNumber())
//					return board[0][1].getPlayerNumber();
//
//			//top-right corner case
//			if (board[0][board.length - 2] != null && board[1][board.length - 1] != null)
//				if (board[0][board.length - 2].getPlayerNumber() == board[1][board.length - 1].getPlayerNumber() &&
//						board[0][board.length - 2].getPlayerNumber() != board[0][board.length - 1].getPlayerNumber())
//					return board[0][board.length - 2].getPlayerNumber();
//
//			//bottom-left corner case
//			if (board[board.length - 2][0] != null && board[board.length - 1][1] != null)
//				if (board[board.length - 2][0].getPlayerNumber() == board[board.length - 1][1].getPlayerNumber() &&
//						board[board.length - 2][0].getPlayerNumber() != board[board.length - 1][0].getPlayerNumber())
//					return board[board.length - 2][0].getPlayerNumber();
//
//			//bottom-right corner case
//			if (board[board.length - 1][board.length - 2] != null && board[board.length - 2][board.length - 1] != null)
//				if (board[board.length - 1][board.length - 2].getPlayerNumber() == board[board.length - 2][board.length - 1].getPlayerNumber() &&
//						board[board.length - 1][board.length - 2].getPlayerNumber() != board[board.length - 1][board.length - 1].getPlayerNumber())
//					return board[board.length - 1][board.length - 2].getPlayerNumber();
//		}
//	}
        return -1;

    }

    /*************************************************
     * This method checks the borders to find who
     * wins the game
     * @return integer number of winning player
     */
    public int getBorders() {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++) {

                // left-border (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length - 1 && col == 0) {
                    if (board[row - 1][col] != null && board[row][col + 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                                && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col + 1].getPlayerNumber();
                }

                // right-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length - 1 && col == board.length - 1) {
                    if (board[row - 1][col] != null && board[row][col - 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber()
                                && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col - 1].getPlayerNumber();
                }

                // top-border  (excluding corners - check 3 sides only)
                if (col != 0 && col != board.length - 1 && row == 0) {
                    if (board[row][col - 1] != null && board[row][col + 1] !=
                            null && board[row + 1][col] != null)
                        if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                                && board[row + 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col - 1].getPlayerNumber();
                }

                // bottom-border  (excluding corners - check 3 sides only)
                if (col != 0 && col != board.length - 1 && row == board.length - 1) {
                    if (board[row][col - 1] != null && board[row][col + 1] !=
                            null && board[row - 1][col] != null)
                        if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                                && board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            return board[row][col - 1].getPlayerNumber();
                }

            }

//		System.out.print(board.length);
//
//		for (int row = 0; row < board.length; row++){
//			for (int col = 0; col < board.length; col++) {
//
//				if (board[row][col] != null) {
//					// left-border case (excluding corners - check 3 sides only) WORKING FOR 10 NOT FOR 5
//					if (row != 0 && row != board.length - 1 && col == 0)
//						if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null)
//							if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
//									board[row - 1][col].getPlayerNumber()== board[row][col + 1].getPlayerNumber() &&
//									board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
//								return board[row - 1][col].getPlayerNumber();
//
//					// right-border cases (excluding corners) WORKING FOR 10 NOT FOR 5
//					if (row != 0 && row != board.length - 1 && col == 9)
//						if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null)
//							if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
//									board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
//									board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
//								return board[row - 1][col].getPlayerNumber();
//
//					// bottom-border cases (excluding corners) WORKING FOR 10 NOT FOR 5
//					if (col != 0 && col != board.length - 1 && row == 9)
//						if (board[row][col - 1] != null && board[row][col + 1] != null && board[row - 1][col] != null)
//							if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
//									board[row - 1][col].getPlayerNumber()==board[row][col + 1].getPlayerNumber() &&
//									board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber())
//								return board[row][col - 1].getPlayerNumber();
//
//					// top-border cases (excluding corners) WORKING FOR 10 NOT FOR 5
//					if (col != 0 && col != board.length - 1 && row == 0)
//						if (board[row][col - 1] != null && board[row][col + 1] != null && board[row + 1][col] != null)
//							if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
//									board[row + 1][col].getPlayerNumber()==board[row][col - 1].getPlayerNumber()&&
//									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber())
//								return board[row][col - 1].getPlayerNumber();
//				}
//			}
//
//	}
        return -1;
    }

    /*************************************************
     * This method checks the middle of the board to
     * get the winning player
     * @return integer number of winning player
     */
    public int getMiddle() {
        // searching just the middle
        for (int row = 1; row < board.length - 1; row++) {
            for (int col = 1; col < board.length - 1; col++) {
                if (board[row][col - 1] != null && board[row][col + 1] != null && board[row - 1][col] != null
                        && board[row + 1][col] != null && board[row][col] != null)
                    if (board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber()
                            && board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                            board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                            board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                        return board[row][col - 1].getPlayerNumber();
            }

        }
        return -1;
    }



    /******************************************************************
     * A method that check if someone won
     * *
     * @return the number of the player that won or -1 if no one won.
     */
    public int getBurnOut() {

        if(corners1() != -2 && playersArray[corners1()] != -3) {
            int c = corners1();
            playersArray[corners1()] = -3;
            return c;
        }
        if(sides1() != -2 && playersArray[sides1()] != -3) {
            int s = sides1();
            playersArray[sides1()] = -3;
            return s;
        }
        if(middle1()!= -2 && playersArray[middle1()] != -3) {
            int m = middle1();
            playersArray[middle1()] = -3;
            return m;
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
        if (board[board.length-1][board.length-2] != null && board[board.length-2][board.length-1] != null&&
                board[board.length-1][board.length-1]!=null) {
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
                                board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            if(playersArray[board[row][col].getPlayerNumber()] != -3)
                                return board[row][col].getPlayerNumber();

                }
                // right-border  (excluding corners - check 3 sides only)
                if (row != 0 && row != board.length-1 && col == board.length-1) {
                    if (board[row - 1][col] != null && board[row][col - 1] !=
                            null && board[row + 1][col] != null&& board[row][col]!= null )
                        if (board[row - 1][col].getPlayerNumber() !=board[row][col].getPlayerNumber()
                                && board[row+1][col].getPlayerNumber()!= board[row][col].getPlayerNumber() &&
                                board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            if(playersArray[board[row][col].getPlayerNumber()] != -3)
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
                            if(playersArray[board[row][col].getPlayerNumber()] != -3)
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
                        if(playersArray[board[row][col].getPlayerNumber()] != -3){
                            return board[row][col].getPlayerNumber();
                        }
            }
        }
        return -2;

    }


    public boolean Complext(int col, int row,int cplayer)
    {

        if(row+1 > board.length-1 || board[row+1][col]== null)
            return false;
        if(col+1 > board.length -1|| board[row][col+1] == null)
            return false;
        if(col-1 <0 ||board[row][col-1]== null)
            return false;
        if(row-1 <0 || board[row-1][col]== null)
            return false;
        if(!getV(col,row))
            return false;
        setV(col,row);
        if(col+1< board.length - 1&& !getV(col+1,row) && board[row][col+1].getPlayerNumber() == cplayer) {
            setV(col+1,row);
            if(!Complext(col + 1, row, cplayer))
                return false;
        }
        if(col-1< board.length -1&& !getV(col-1,row) && board[row][col-1].getPlayerNumber() == cplayer) {
            setV(col-1,row);
            if(!Complext(col - 1, row, cplayer))
                return false;
        }
        if(row+1< board.length -1&& !getV(col,row+1) && board[row+1][col].getPlayerNumber() == cplayer) {
            setV(col,row+1);
            if(!Complext(col, row+1, cplayer))
                return false;
        }
        if(row-1< board.length -1&& !getV(col,row-1) && board[row-1][col].getPlayerNumber()  == cplayer) {
            setV(col,row-1);
            if(!Complext(col , row- 1, cplayer))
                return false;
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

    public int getwinner2(int col,int row)
    {
        instantiateV();
        for(int row1 =0;row1<board.length;row1++)
        {
            for(int col1 =0;col1<board.length;col1++) {
//                for(int h=0;h <numberPlayers;h++)
//                {
                if(board[row1][col1] != null) {
                    int c = board [row1][col1].getPlayerNumber();
                    if (Complext(col1, row1, c)) {
                        playersArray[c] = -3;
                        return c;
//                    }
                }
        }
            }
        }
        return -1;
    }

    public int getw()
    {
        int count =0;
        int WINP=0;
        for( int i=0;i<playersArray.length;i++)
        {
            if(playersArray[i] <0)
                count++;
            if(playersArray[i]>0)
                WINP= playersArray[i];
        }
        if(count == playersArray.length-1)
            return WINP;
        return -1;
    }



    public int checkRiskLevelCornerTL() {
        //top-left corner case RED
        if (board[0][1] != null && board[1][0] != null &&
                board[0][0] == null)
            if ((board[0][1].getPlayerNumber() ==
                    board[1][0].getPlayerNumber()) && board[0][1].getPlayerNumber() != getCurrentPlayer()) {
                return 1;
            }

        //top-left corner case YELLOW
        if ((board[0][1] != null && board[0][0] == null)
                && board[0][1].getPlayerNumber() != getCurrentPlayer())
            return 2;
        else if ((board[1][0] != null &&
                board[0][0] == null) && board[1][0].getPlayerNumber() != getCurrentPlayer()) {
            return 2;
        }
        return 9;
    }

    public int checkRiskLevelCornerTR() {
        //top-right corner case
        if (board[0][board.length - 2] != null &&
                board[1][board.length - 1] != null &&
                board[0][board.length - 1] == null)
            if ((board[0][board.length - 2].getPlayerNumber() ==
                    board[1][board.length - 1].getPlayerNumber()) && board[1][board.length - 1].getPlayerNumber() != getCurrentPlayer()){
                return 3;
            }

        //top-right corner case
        if ((board[0][board.length - 2] != null &&
                board[0][board.length - 1] == null) && board[0][board.length - 2].getPlayerNumber() !=
                getCurrentPlayer())
            return 4;

        else if((board[1][board.length - 1] != null &&
                board[0][board.length - 1] == null) && board[1][board.length - 1].getPlayerNumber()
                != getCurrentPlayer()){
            return 4;
        }
        return 9;
    }

    public int checkRiskLevelCornerBL() {
        //bottom-left corner case
        if (board[board.length - 2][0] != null &&
                board[board.length - 1][1] != null &&
                board[board.length - 1][0] == null)
            if ((board[board.length - 2][0].getPlayerNumber() ==
                    board[board.length - 1][1].getPlayerNumber()) &&
                    board[board.length - 2][0].getPlayerNumber() !=
                            getCurrentPlayer()){
                return 5;
            }

        //bottom-left corner case
        if ((board[board.length - 2][0] != null &&
                board[board.length - 1][0] == null) &&
                board[board.length - 2][0].getPlayerNumber() !=
                        getCurrentPlayer())
            return 6;
        else if ((board[board.length - 1][1] != null &&
                board[board.length - 1][0] == null) &&
                board[board.length - 1][1].getPlayerNumber() !=
                        getCurrentPlayer()){
            return 6;
        }
        return 9;
    }

    public int checkRiskLevelCornerBR() {
        //bottom-right corner case
        if (board[board.length - 1][board.length - 2] != null &&
                board[board.length - 2][board.length - 1] != null &&
                board[board.length - 1][board.length - 1] == null)
            if ((board[board.length - 1][board.length - 2].getPlayerNumber() ==
                    board[board.length - 2][board.length - 1].getPlayerNumber()) &&
                    board[board.length - 1][board.length - 2].getPlayerNumber() != getCurrentPlayer()){
                return 7;
            }

        //bottom-right corner case
        if ((board[board.length - 1][board.length - 2] != null &&
                board[board.length - 1][board.length - 1] == null) &&
                board[board.length - 1][board.length - 2].getPlayerNumber() !=
                        getCurrentPlayer())
            return 8;
        else if ((board[board.length - 2][board.length - 1] != null &&
                board[board.length - 1][board.length - 1] == null) &&
                board[board.length - 2][board.length - 1].getPlayerNumber() !=
                        getCurrentPlayer()){
            return 8;
        }
        return 9;
    }

    public int[] checkRiskLevelMiddleYELLOW(int row, int col) {

        int[] rowCol = new int[2];

        int numRow;
        int numCol;

        // searching just the middle
        for (numRow = row; numRow < board.length - 1; numRow++) {
            for (numCol = col; numCol < board.length - 1; numCol++) {

                //check for only 2 surrounding
                if (board[numRow - 1][numCol] != null && board[numRow + 1][numCol] != null &&
                        board[numRow][numCol] == null) {
                    if ((board[numRow - 1][numCol].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber())
                            && board[numRow - 1][numCol].getPlayerNumber() != getCurrentPlayer()) {
                        rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }
                }

                if (board[numRow][numCol - 1] != null && board[numRow - 1][numCol] != null
                        && board[numRow][numCol] == null)
                    if ((board[numRow][numCol - 1].getPlayerNumber() == board[numRow - 1][numCol].getPlayerNumber()) &&
                            board[numRow][numCol - 1].getPlayerNumber() != getCurrentPlayer()){
                        rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }

                if (board[numRow][numCol - 1] != null && board[numRow + 1][numCol] != null
                        && board[numRow][numCol] == null)
                    if ((board[numRow][numCol - 1].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber()) &&
                            board[numRow][numCol - 1].getPlayerNumber() != getCurrentPlayer()){
                        rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }

                if (board[numRow][numCol - 1] != null && board[numRow][numCol + 1] != null
                        && board[numRow][numCol] == null)
                    if ((board[numRow][numCol - 1].getPlayerNumber() == board[numRow][numCol + 1].getPlayerNumber()) &&
                            board[numRow][numCol - 1].getPlayerNumber() != getCurrentPlayer()){
                        rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }

                if (board[numRow][numCol + 1] != null && board[numRow - 1][numCol] != null
                        && board[numRow][numCol] == null)
                    if ((board[numRow][numCol + 1].getPlayerNumber() == board[numRow - 1][numCol].getPlayerNumber()) &&
                            board[numRow][numCol + 1].getPlayerNumber() != getCurrentPlayer()){
                        rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }

                if (board[numRow][numCol + 1] != null && board[numRow + 1][numCol] != null
                        && board[numRow][numCol] == null)
                    if ((board[numRow][numCol + 1].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber()) &&
                            board[numRow][numCol + 1].getPlayerNumber() != getCurrentPlayer()){
                        rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }
            }
        }
        return null;
    }

    public int[] checkRiskLevelMiddleRED(int row, int col) {

        int[] urgentRowCol = new int[2];

        int numRow;
        int numCol;

        for (numRow = row; numRow < board.length - 1; numRow++) {
            for (numCol = col; numCol < board.length - 1; numCol++) {

                if (board[numRow - 1][numCol] != null && board[numRow + 1][numCol] != null
                        && board[numRow][numCol] == null)
                    if (board[numRow][numCol - 1] != null) {
                        if (((board[numRow - 1][numCol].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber() &&
                                board[numRow][numCol - 1].getPlayerNumber() == board[numRow - 1][numCol].getPlayerNumber())) &&
                                board[numRow - 1][numCol].getPlayerNumber() != getCurrentPlayer()){
                            urgentRowCol[0] = numRow;
                            urgentRowCol[1] = numCol;
                            return urgentRowCol;
                        }
                    } else if (board[numRow][numCol + 1] != null && board[numRow][numCol] == null) {
                        if ((board[numRow - 1][numCol].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber() &&
                                board[numRow][numCol + 1].getPlayerNumber() == board[numRow - 1][numCol].getPlayerNumber()) &&
                                board[numRow - 1][numCol].getPlayerNumber() != getCurrentPlayer()){
                            urgentRowCol[0] = numRow;
                            urgentRowCol[1] = numCol;
                            return urgentRowCol;
                        }
                    }

                if (board[numRow][numCol - 1] != null && board[numRow][numCol + 1] != null
                        && board[numRow][numCol] == null)
                    if (board[numRow - 1][numCol] != null && board[numRow][numCol] == null) {
                        if ((board[numRow][numCol - 1].getPlayerNumber() == board[numRow][numCol + 1].getPlayerNumber() &&
                                board[numRow][numCol - 1].getPlayerNumber() == board[numRow - 1][numCol].getPlayerNumber()) &&
                                board[numRow][numCol - 1].getPlayerNumber() != getCurrentPlayer()){
                            urgentRowCol[0] = numRow;
                            urgentRowCol[1] = numCol;
                            return urgentRowCol;
                        }
                    } else if (board[numRow + 1][numCol] != null && board[numRow][numCol] == null) {
                        if ((board[numRow][numCol - 1].getPlayerNumber() == board[numRow][numCol + 1].getPlayerNumber() &&
                                board[numRow][numCol - 1].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber()) &&
                                board[numRow][numCol - 1].getPlayerNumber() != getCurrentPlayer()){
                            urgentRowCol[0] = numRow;
                            urgentRowCol[1] = numCol;
                            return urgentRowCol;
                        }
                    }
            }
        }
        return null;
    }

    public int[] checkRiskLevelBorders(int row, int col) {

        int[] rowCol = new int[2];

        int numRow;
        int numCol;

        for (numRow = row; numRow < board.length; numRow++) {
            for (numCol = col; numCol < board.length; numCol++) {
                // left-border (excluding corners - check 3 sides only)
                if (numRow != 0 && numRow != board.length - 1 && numCol == 0) {
                    if ((board[numRow - 1][numCol] != null && board[numRow][numCol + 1] != null
                            && board[numRow][numCol] == null)  &&
                            board[numRow - 1][numCol].getPlayerNumber() != getCurrentPlayer()){
//                && board[row + 1][col] != null)
                        if (board[numRow - 1][numCol].getPlayerNumber() == board[numRow][numCol + 1].getPlayerNumber()) {
//                      && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
//                      board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = numRow;
                            rowCol[1] = numCol;
                            return rowCol;
                        } else if ((board[numRow - 1][numCol] != null && board[numRow + 1][numCol] != null
                                && board[numRow][numCol] == null) && board[numRow - 1][numCol].getPlayerNumber() !=
                                getCurrentPlayer()){
                            if (board[numRow - 1][numCol].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber())
                                rowCol[0] = numRow;
                            rowCol[1] = numCol;
                            return rowCol;
                        }
                    }
                }

                // right-border  (excluding corners - check 3 sides only)
                if (numRow != 0 && numRow != board.length - 1 && numCol == board.length - 1) {
                    if ((board[numRow - 1][numCol] != null && board[numRow][numCol - 1] != null
                            && board[numRow][numCol] == null) &&
                            board[numRow - 1][numCol].getPlayerNumber() != getCurrentPlayer()) {
//                   && board[row + 1][col] != null)
                        if (board[numRow - 1][numCol].getPlayerNumber() == board[numRow][numCol - 1].getPlayerNumber())
//                      && board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
//                      board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    } else if ((board[numRow - 1][numCol] != null && board[numRow + 1][numCol] != null
                            && board[numRow][numCol] == null) && board[numRow - 1][numCol].getPlayerNumber() !=
                            getCurrentPlayer()){
                        if (board[numRow - 1][numCol].getPlayerNumber() == board[numRow + 1][numCol].getPlayerNumber())
                            rowCol[0] = numRow;
                        rowCol[1] = numCol;
                        return rowCol;
                    }
                }

                // top-border  (excluding corners - check 3 sides only)
                if (numCol != 0 && numCol != board.length - 1 && numRow == 0) {
                    if ((board[numRow][numCol - 1] != null && board[numRow][numCol + 1] != null
                            && board[numRow][numCol] == null) && board[numRow][numCol - 1].getPlayerNumber() !=
                            getCurrentPlayer()){
//                && board[row + 1][col] != null)
                        if (board[numRow][numCol - 1].getPlayerNumber() == board[numRow][numCol + 1].getPlayerNumber()) {
//                      && board[row + 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
//                      board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = numRow;
                            rowCol[1] = numCol;
                            return rowCol;
                        }
                    } else if ((board[numRow][numCol - 1] != null && board[numRow + 1][numCol] != null
                            && board[numRow][numCol] == null) && board[numRow][numCol - 1].getPlayerNumber() !=
                            getCurrentPlayer())
                        if (board[numRow + 1][numCol].getPlayerNumber() == board[numRow][numCol - 1].getPlayerNumber()) {
                            rowCol[0] = numRow;
                            rowCol[1] = numCol;
                            return rowCol;
                        }
                }

                // bottom-border  (excluding corners - check 3 sides only)
                if (numCol != 0 && numCol != board.length - 1 && numRow == board.length - 1) {
                    if ((board[numRow][numCol - 1] != null && board[numRow][numCol + 1] != null
                            && board[numRow][numCol] == null) && board[numRow][numCol - 1].getPlayerNumber() !=
                            getCurrentPlayer()){
//                && board[row - 1][col] != null)
                        if (board[numRow][numCol - 1].getPlayerNumber() == board[numRow][numCol + 1].getPlayerNumber()) {
//                      && board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
//                      board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber())
                            rowCol[0] = numRow;
                            rowCol[1] = numCol;
                            return rowCol;
                        }
                    } else if ((board[numRow][numCol - 1] != null && board[numRow - 1][numCol] != null
                            && board[numRow][numCol] == null) && board[numRow][numCol - 1].getPlayerNumber() !=
                            getCurrentPlayer())
                        if (board[numRow - 1][numCol].getPlayerNumber() == board[numRow][numCol - 1].getPlayerNumber()) {
                            rowCol[0] = numRow;
                            rowCol[1] = numCol;
                            return rowCol;
                        }
                }
            }
        }

//    return -1;

        return null;
    }
    public boolean checkcell(int row,int col) {

            if (playersArray[board[row][col].getPlayerNumber()] > 0)
                return true;



        return false;
    }




}


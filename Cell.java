package Surrond;

import javax.swing.*;

public class Cell {
    /** The player*/
    private int playerNumber;
    /*
     *  First, the Surround4 game class will only use the player
     *   number to determine a winner.
     *  Second, you are allowed to add new properties that you will
     *   need for the later steps in your project.
     *  	for example: add on a property color that changes with
     *      show different risk levels.
     *  		use red if the cell is about to surrounded, i.e., at high risk
     *  		or use green if the cell is at low risk.
     *
     *  for example: add on a int property color that indicates
     *  			1 means this would be a ok choose.
     *  			3 means this would be a poor choose.
     *
     */

    /******************************************************************
     * constructor of the cell - puting the player number
     * *
     * @param playerNumber the number of the player to put in the cell
     */
    public Cell(int playerNumber) {
        super();
        this.playerNumber = playerNumber;
    }
    public void setCell(){}
    /******************************************************************
     * get player number-checking the player number in the cell
     * *
     * @return playerNumber
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
}
package Surrond;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Surround4Panel extends JPanel {

    /** The default board size */
    private int boardSize = 10;

    /** The 2 dimensional JButton array board */
    private JButton[][] board;

    /** The panel */
    private JPanel panel1;

    /** The button listener */
    private ButtonListener listen;

    /** The menu Items to quite game */
    private JMenuItem quitItem;

    /** The menu Items for new game */
    private JMenuItem newGameItem;

    /** The game object */
    private Surround4Game game;

    /** The default number of players */
    private int numOfPlayers = 2;

    /** The player number */
    private int player;

    /** Array of Players */
    private int[] playersArray;

//    private Timer javatimer;
//    private TimerListener time;
//    /** The inteval for the timer*/
//    static int interval;
//    static Timer timer;


    /** The default beginning player */
    private int beginningPlayer = 0;

    /** Message Pane */
    private JOptionPane message;

    /** array of players wins*/
    private int [] playersWins;

    /*****************************************
     * Constructor for panel
     * @param pQuitItem Menu bar item to
     *allow user to quit game
     * @param pNewGameItem Menu bar item to
     * allow user to start new game
     * */
    public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem) {
        // Create quite and new game items in menu bar
        quitItem = pQuitItem;
        newGameItem = pNewGameItem;


        //Add button listener, add action listeners for each menu item,
        // and instantiate option pane
        listen = new ButtonListener();
        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);
        message = new JOptionPane();

        //Sets board layout and adds panel
        setLayout(new BorderLayout());
        panel1 = new JPanel();

        if(choseGame()) {
            numberPlayers(1, 5);
        }
        else
            numberPlayers(1,10);

        playersArray = new int[numOfPlayers];
        playersWins = new int [ numOfPlayers];
        instantiateArray();
        instantiateWArray();

        //Gets board size based on user input and creates board
        boardSize = getBoardSize();
        createBoard(boardSize);
        add(panel1, BorderLayout.CENTER);

        //Gets number of players
//        numOfPlayers = numberPlayers();

        //New surround4Game object with board size,
        //number of players, and beginning player number
        game = new Surround4Game(boardSize, numOfPlayers, beginningPlayer(), playersArray);
//        timer = new TimerListener();
//        add(new JLabel("Time left:"));
//        javatimer = new Timer(1,timer);
//        timer = new ;
//        javaTimer = new Timer(1, timer);
//        Clock();
////        add(time);
//        time.setText("time left: " + setInterval());
//        System.out.print(setInterval());
    }

    /*************************************************
     Inner Class To Watch User's Actions
     *************************************************/
    private class ButtonListener implements ActionListener {
        /*************************************************
         * This method creates the actions to be
         * taken when the quitItem, newGameItem,
         * or when a new cell is preses
         * @param e ActionEvent the action performed
         *          by player
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem)
                System.exit(1);

            if (e.getSource() == newGameItem) {
                if(choseGame())
                    numberPlayers(2,4);
                else
                    numberPlayers(2,10);

                instantiateArray();
                game.reset(boardSize);
                game = new Surround4Game(getBoardSize(), numOfPlayers, beginningPlayer(), playersArray);
                reCreateBoard(boardSize);
                displayBoard();
            }
            int row1=0,col1=0;
            for (int row = 0; row < boardSize; row++)
                for (int col = 0; col < boardSize; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            board[row][col].setText("" + game.getCurrentPlayer());
                            col1 = col;
                            row1 = row;
                            if(game.getBurnOut() != -1){
                                JOptionPane.showMessageDialog(null,"Player " +
                                        game.getBurnOut() + " Lost!");
                            }
                            if(game.getwinner2(col1,row1)!= -1) {
                                JOptionPane.showMessageDialog(null,"Player " +
                                        game.getBurnOut() + " Lost!");
                            }

//                            if(game.checkRiskLevelCorners() != 9 )
//                            {
////                               || game.checkRiskLevelMiddle() != 5) {
//                                if(game.checkRiskLevelCorners() == 1)
//                                    board[0][0].setBackground(Color.red);
//                                if(game.checkRiskLevelCorners() == 2)
//                                    board[0][board.length - 1].setBackground(Color.red);
//                                if(game.checkRiskLevelCorners() == 3)
//                                    board[board.length - 1][0].setBackground(Color.red);
//                                if(game.checkRiskLevelCorners() == 4)
//                                    board[board.length - 1][board.length - 1].setBackground(Color.red);
//                                if(game.checkRiskLevelCorners() == 5)
//                                    board[0][0].setBackground(Color.orange);
//                                if(game.checkRiskLevelCorners() == 6)
//                                    board[0][board.length - 1].setBackground(Color.orange);
//                                if(game.checkRiskLevelCorners() == 7)
//                                    board[board.length - 1][0].setBackground(Color.orange);
//                                if(game.checkRiskLevelCorners() == 8)
//                                    board[board.length - 1][board.length - 1].setBackground(Color.orange);
////                                if(game.checkRiskLevelMiddle() == 1)
////                                    board[row][col].setBackground(Color.red);
//                            }

                            player = game.nextPlayer();
//                            System.out.println(board[0].length);
                        } else
                            JOptionPane.showMessageDialog(null,
                                    "Not a valid square! Pick again.");
            displayBoard();
            int winner = game.getWinner();
            if (winner != -1 ) {
                playersWins[winner]++;
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                game = new Surround4Game(getBoardSize(), numOfPlayers, beginningPlayer(), playersArray);
                reCreateBoard(boardSize);
                displayBoard();
            }
            winner = game.getw();
            if (winner != -1 ) {
                playersWins[winner]++;
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                game = new Surround4Game(getBoardSize(), numOfPlayers, beginningPlayer(), playersArray);
                reCreateBoard(boardSize);
                displayBoard();
            }


        }
    }

    /*************************************************
     * This method instantiates the buttons of the
     * panel and adds listeners for each button
     * @param size number of buttons to
     *             be added
     */
    private void createBoard(int size) {

        board = new JButton[size][size];
//        panel1.invalidate();
//        panel1.setVisible(false);
        panel1.setLayout(new GridLayout(size, size));

        for (int row = 0; row < size; row++) // rows
            for (int col = 0; col < size; col++) {
                board[row][col] = new JButton("");
                board[row][col].setBackground(Color.green);
                board[row][col].setOpaque(true);
                board[row][col].addActionListener(listen);
                panel1.add(board[row][col]);
//                panel1.setVisible(true);
            }
    }

    private void reCreateBoard(int size){
        //remove buttons from previous
        //panel
        removeButtons();

        createBoard(size);
        //Create new button array
//        board = new JButton[size][size];
//
//        //invalidate previous panel
//        //and set layout of new panel
////        panel1.invalidate();
//        panel1.setLayout(new GridLayout(size, size));
//
//        //add jbuttons to array,
//        //action listeners, and
//        //buttons to panel
//        for (int row = 0; row < size; row++) {// rows
//            for (int col = 0; col < size; col++) {
//                board[row][col] = new JButton("");
//                board[row][col].addActionListener(listen);
//                panel1.add(board[row][col]);
//            }
//        }
        //validate the new panel
        panel1.validate();
    }

    public void removeButtons(){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
//                board[row][col].remove(board[row][col]);
//                board[row][col].remove(board[row][col]);
                panel1.remove(board[row][col]);
            }
        }
    }

    /*************************************************
     * This method displays the board based on
     * the cell clicked by whichever player
     * clicked it.
     */
    private void displayBoard() {

        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++) {
                Cell c = game.getCell(row, col);
                if (c != null)
                    board[row][col].setText("" + c.getPlayerNumber());
                else
                    board[row][col].setText("");
            }
    }

    /*************************************************
     * This method asks the player to choose
     * the size of the playing board.
     * If they enter a number lower than 3
     * or larger than 20, they will be asked to
     * re-enter the board size.
     * If they enter a negative number,
     * they will be asked to
     * re-enter the board size.
     * @return int size of board
     */
    public int getBoardSize() {
        //instantiate boardSize to default
        int boardSize = this.boardSize;

        //Ask player to input boardSize
        String boardSizeMessage = message.showInputDialog(null, "Enter Size of Board (Between 3 and 20)");

        //If user does not enter
        // an integer, boardsize
        // is set to default
        if(boardSizeMessage.isBlank())
            return boardSize;

        //Try to parse
        //If contains letter,
        //ask player to enter an integer
        try {
            boardSize = Integer.parseInt(boardSizeMessage);
        } catch (NumberFormatException e) {
            message.showMessageDialog(null, "Enter an integer");
            getBoardSize();
        }

        //if size is larger than 3 and
        //less than 20, set board size
        //to entered
        if (boardSize > 3 && boardSize < 20)
            return this.boardSize = boardSize;
        else
            message.showMessageDialog(null, "Enter a positive integer");
        getBoardSize();

        return boardSize;
    }

    /*************************************************
     * This method asks the player to choose
     * the number of players that will be
     * playing the game.
     * If they enter a number lower than 3
     * or larger than 20, they will be asked to
     * re-enter the number of players.
     * If they enter a negative number of players,
     * they will be asked to
     * re-enter the number of players.
     * @return integer number of players
     */
    private int numberPlayers(int lowLimit, int highLimit) {
        //instantiate variable to keep track
        //of number of players
        int numberPlayers = this.numOfPlayers;

        //Ask user to enter number of players
        String playerNumString = JOptionPane.showInputDialog(null,
                "Enter the number of players ");

        //If user does not enter
        // an integer, number of
        // players is set to default
        if(playerNumString.isBlank() || playerNumString.equals("") || playerNumString == null)
            return numberPlayers;

        //Try to change input into integer
        //If user enters a letter or non-parseable
        //integer, ask them to enter an integer
        try {
            numberPlayers = Integer.parseInt(playerNumString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Enter an integer");
            numberPlayers(lowLimit,highLimit);
        }

        if (numberPlayers < 0) {
            JOptionPane.showMessageDialog(null, "Enter a positive integer");
            numberPlayers(lowLimit,highLimit);
        } else if (numberPlayers > lowLimit && numberPlayers < highLimit) {
            this.numOfPlayers = numberPlayers;
        } else {
            JOptionPane.showMessageDialog(null, "Enter an integer between " + lowLimit + " and " + highLimit);
            numberPlayers(lowLimit, highLimit);
        }

        return this.numOfPlayers;
    }

    /*************************************************
     * This method asks the player to choose
     * the player who will begin the game.
     * If they enter a number lower than 0
     * or larger than the number of players,
     * they will be asked to
     * re-enter the beginning player.
     * If they enter a negative number,
     * they will be asked to
     * re-enter the beginning player.
     * @return int number of beginning player
     */
    private int beginningPlayer() {
        //instantiate variable of starting
        //player to default of 0
        int startingPlayer = beginningPlayer;

        //Ask player to input which
        //player will start the game
        String playerNumberString = JOptionPane.showInputDialog(null,
                "Which player starts the Game?");

        //Try parsing the integer entered
        //If they enter a letter,
        //ask them to enter an integer
        try{
            startingPlayer = Integer.parseInt(playerNumberString);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter an integer");
            beginningPlayer();
        }

        //If number entered is negative,
        //ask player to enter positive
        //integer. If number entered is
        //valid, set beginning player
        //number to that.
        if (startingPlayer < 0) {
            JOptionPane.showMessageDialog(null, "Enter a positive integer");
            beginningPlayer();
        } else if (startingPlayer <= this.numOfPlayers && startingPlayer > 0)
            beginningPlayer = startingPlayer;

        return startingPlayer;
    }
    /******************************************************************
     *  giving the user to chose the kind of game
     */
    private boolean choseGame() {
        try {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to play with burnout?");
            if (dialogResult == JOptionPane.YES_OPTION) {
                return true;
                // Saving code here
            } else {
                return false;
            }
        }
        catch(Exception e) {
            return false;
        }
    }
    /******************************************************************
     *  creating the array and put the number of the begining players
     */
    private void instantiateArray() {
        for(int i = 0; i < numOfPlayers ; i++) {
            if(numOfPlayers - 1 >= i) {
                playersArray[i] = i;
            }
            else {
                playersArray[i] = -3;
            }
        }
    }

    /******************************************************************
     *  creating the array of wins put every thing as 0 in the beggining
     */
    private void instantiateWArray() {
        for(int i = 0; i < numOfPlayers ; i++) {
            if(numOfPlayers - 1 >= i) {
                playersWins[i] = 0;
            }
            else {
                playersWins[i] = -3;
            }
        }
    }


}

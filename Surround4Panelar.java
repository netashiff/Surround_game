package Surrond;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class Surround4Panelari extends JPanel {
    /** The default board size */
    private int boardSize = 10;


    /** The inteval for the timer*/
    static int interval;
    static Timer timer;

    /** The 2 dimensional JButton array board */
    private JButton[][] board;

    /** The BUTTON panel */
    private JPanel panel1 = new JPanel();

    /** The LABEL panel */
    private JPanel panel2 = new JPanel();

    /** The button listener */
    private ButtonListener listen;

    /** The menu Items to quite game */
    private JMenuItem quitItem;

    /** The menu Items for new game */
    private JMenuItem newGameItem;

    /** The menu Items for new game */
    private JMenuItem UndoItem;
    /**undo places*/
    private int Urow= -1,Ucol= -1;
    /** arraylist save moves*/
    private Stack<int[]> moves;
    private int[] tempo=new int[2];

    /** The game object */
    private Surround4Gamea game;

    /** The default number of players */
    private int numOfPlayers = 2;

    /** The player number */
    private int player;

    /** Array of Players */
    private int[] playersArray = new int[4];

    /** The default beginning player */
    private int beginningPlayer = 0;

    /** Message Pane */
    private JOptionPane message;

    /** add clock label*/
    private JLabel clkLabel;

    /**counts how many times the player won*/
    private int[] playersWins;

    /**wins label*/
    private JLabel[] win;

    private boolean burnOut = false;

    private JLabel[] allLabels;

    private static boolean[] players;

    /*****************************************
     * Constructor for panel
     * @param pQuitItem Menu bar item to
     *allow user to quit game
     * @param pNewGameItem Menu bar item to
     * allow user to start new game
     * */
    public Surround4Panelari(JMenuItem pQuitItem, JMenuItem pNewGameItem, JMenuItem undoItem) {
        //Sets board layout and adds panel
        setLayout(new BorderLayout());

        // Create quite and new game items in menu bar
        quitItem = pQuitItem;
        newGameItem = pNewGameItem;
        UndoItem = undoItem;
//        panel1.add(labels, BorderLayout.CENTER);

        //Add button listener, add action listeners for each menu item,
        // and instantiate option pane
        listen = new ButtonListener();
        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);
        UndoItem.addActionListener(listen);
        message = new JOptionPane();
        moves = new Stack<>();

        if(choseGame()) {
            numOfPlayers = numberPlayers(1, 5);
            instantiateArray();
        }
        else
            numOfPlayers = numberPlayers(1,10);

        instantiateWArray();
        playersWins = new int[numOfPlayers];


        //Sets layout of Label Panel,
        //add labels to label panel,
        //and adds panel
        panel2.setLayout(new GridLayout(1,5));
        panel2.setBackground(Color.white);
        createLabels();
        add(panel2, BorderLayout.PAGE_START);

        //Gets board size based on user input
        // and creates board on button
        //panel
        boardSize = getBoardSize();
        createBoard(boardSize);
        add(panel1, BorderLayout.EAST);

        //New surround4Game object with board size,
        //number of players, and beginning player number
        game = new Surround4Gamea(boardSize, numOfPlayers, beginningPlayer(), playersArray);
    }

//    public Surround4Panel(){
//        //Sets layout of Label Panel
//        setLayout(new GridLayout(10,5));
//
//        //Instantiate playerWins array
//        playersWins = new int[4];
//        labels = new JLabel[4];
////        instantiateWA();
//
//        //Add Labels
//        createLabels();
//
//        //Add panel
//        add(panel2, BorderLayout.CENTER);
//    }

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

            int col1 = 0, row1 = 0;

            if (e.getSource() == newGameItem) {
                for(int i = 0; i < numOfPlayers; i++) {
                    panel2.remove(allLabels[i]);
                }

                if (choseGame())
                    numberPlayers(1, 5);
                else
                    numberPlayers(1, 10);

                instantiateArray();
                instantiateWArray();
                resetLabels();
                game.reset();
                game = new Surround4Gamea(getBoardSize(), numOfPlayers, beginningPlayer(), playersArray);
                reCreateBoard(boardSize);
                displayBoard();
            }
            if(e.getSource() == UndoItem)
            {
                if (moves.size() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Not a valid undo there isn't more plays");
                }
                tempo = moves.pop();
                if(game.checkcell(tempo[0],tempo[1]))
                {
                    board[tempo[0]][tempo[1]].setText("");
                    game.setCell(tempo[0], tempo[1]);
                    int p = game.getCurrentPlayer();
                    game.privousPlayer();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "Not a valid undo the player lost");
                }

//               board[row][col] = new JButton("");
//                board[row][col].setBackground(Color.green);
//                board[row][col].setOpaque(true);
//                board[tempo[0]][tempo[1]].setText("");
//                board[tempo[0]][tempo[1]]= null;



            }
            else {
                for (int row = 0; row < boardSize; row++)
                    for (int col = 0; col < boardSize; col++)
                        if (board[row][col] == e.getSource())
                            if (game.select(row, col)) {
                                board[row][col].setText("" + game.getCurrentPlayer());
                                tempo[0] = row;
                                tempo[1] = col;
                                tempo = moves.push(tempo);
                                col1 = col;
                                row1 = row;

                                player = game.nextPlayer();

                                if (numOfPlayers == 2)
                                    setColors();

//                            System.out.println(board[0].length);
                            } else
                                JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");

                displayBoard();

                if (numOfPlayers > 2) {
                    int burnt = game.getBurnOut();
                    if (burnt != -1) {
                        JOptionPane.showMessageDialog(null, "Player " + burnt + " Lost!");
//                    player = game.nextPlayer();
                    }

                    if (game.getwinner2(col1, row1) != -1) {
                        JOptionPane.showMessageDialog(null, "Player " +
                                game.getwinner2(col1, row1) + " Lost!");
//                    player = game.nextPlayer();
                    }

                    int winner = game.getw();
                    if (winner != -1) {
                        playersWins[winner]++;
                        JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                        game = new Surround4Gamea(getBoardSize(), numOfPlayers, beginningPlayer(), playersArray);
                        reCreateBoard(boardSize);
                        setLabels();
                        instantiateArray();
                        displayBoard();
                    }
                }

                int winner = game.getWinner();
                if (winner != -1) {
                    playersWins[winner]++;
                    JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                    game = new Surround4Gamea(getBoardSize(), numOfPlayers, beginningPlayer(), playersArray);
                    reCreateBoard(boardSize);
                    setLabels();
                    instantiateArray();
                    displayBoard();
                }
            }
        }
    }

    private void setLabels(){
        for(int i = 0; i < numOfPlayers; i++) {
            allLabels[i].setText("Total # of Wins For Player " + i + ": " + playersWins[i]);
        }
    }

    private void resetLabels(){
        createLabels();
        panel2.validate();
    }

    private void createLabels(){
        win = new JLabel[numOfPlayers];


        panel2.add(new JLabel ("Total Wins: "));

        allLabels = new JLabel[numOfPlayers];

        for(int i = 0; i < numOfPlayers; i++) {
            allLabels[i] = new JLabel("Player " + i + ": " + playersWins[i]);
            win[i] = new JLabel("");
            panel2.add(allLabels[i]);
//            panel2.add(win[i], BorderLayout.EAST);
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

        panel1.setLayout(new GridLayout(size, size));

        for (int row = 0; row < size; row++) // rows
            for (int col = 0; col < size; col++) {
                board[row][col] = new JButton("");
                board[row][col].setBackground(Color.green);
                board[row][col].setOpaque(true);
                board[row][col].addActionListener(listen);
                panel1.add(board[row][col]);
            }
    }

    private void reCreateBoard(int size) {
        //remove buttons from previous
        //panel
        removeButtons();

        createBoard(size);

        //validate the new panel
        panel1.validate();
    }

    public void removeButtons() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
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
//        instantiateWA();
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
//        instantiate boardSize to default
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

        return (this.numOfPlayers);
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
//        //instantiate variable of starting
////        //player to default of 0
////        int startingPlayer = beginningPlayer;
////
////        //Ask player to input which
////        //player will start the game
////        String playerNumberString = JOptionPane.showInputDialog(null,
////                "Which player starts the Game?");
////
////        //Try parsing the integer entered
////        //If they enter a letter,
////        //ask them to enter an integer
////        try{
////            startingPlayer = Integer.parseInt(playerNumberString);
////        } catch (NumberFormatException e){
////            JOptionPane.showMessageDialog(null, "Enter an integer");
////            beginningPlayer();
////        }
////
////        //If number entered is negative,
////        //ask player to enter positive
////        //integer. If number entered is
////        //valid, set beginning player
////        //number to that.
////        if (startingPlayer < 0) {
////            JOptionPane.showMessageDialog(null, "Enter a positive integer");
////            beginningPlayer();
////        } else if (startingPlayer <= this.numOfPlayers && startingPlayer > 0)
////            beginningPlayer = startingPlayer;
////
////        return startingPlayer;
        return 0;
    }

    /******************************************************************
     *  giving the user to chose the kind of game
     */
    private boolean choseGame() {
        try {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to play with burnout?");
            if (dialogResult == JOptionPane.YES_OPTION) {
                burnOut = true;
                return true;
                // Saving code here
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /******************************************************************
     *  creating the array and put the number of the beginning players
     */
    private void instantiateArray() {
        for (int i = 0; i < 4; i++) {
            if (numOfPlayers - 1 >= i) {
                playersArray[i] = i;
            } else {
                playersArray[i] = -3;
            }
        }
    }

    /******************************************************************
     *  creating the array of wins put every thing as 0 in the beginning
     */
    private void instantiateWArray() {
        playersWins = new int[numOfPlayers];

        for(int i = 0; i < numOfPlayers ; i++) {
            if(numOfPlayers - 1 >= i) {
                playersWins[i] = 0;
            }
            else {
                playersWins[i] = -3;
            }
        }
    }

    /******************************************************************
     *  creating the array of the labels of winerrs
     */
    private void instantiateWA() {
        win = new JLabel[numOfPlayers];
        for(int i = 0; i < numOfPlayers ; i++) {
            win[i] = new JLabel("");

            panel2.add(new JLabel ("Player "+ i + ": " + playersWins[i]));

            panel2.add(win[i], BorderLayout.AFTER_LAST_LINE);
        }
//        panel2.validate();
    }

    private void setColors() {
        //RESET BOARD TO GREEN BEFORE APPLYING
        //RISK COLORS
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col].setBackground(Color.green);
            }
        }

        //CHECKS CORNERS FOR RISK
        if (game.checkRiskLevelCornerTL() != 9) {
            if (game.checkRiskLevelCornerTL() == 1)
                board[0][0].setBackground(Color.red);

            if (game.checkRiskLevelCornerTL() == 2)
                board[0][0].setBackground(Color.orange);
        }

        if (game.checkRiskLevelCornerTR() != 9) {
            if (game.checkRiskLevelCornerTR() == 3)
                board[0][board.length - 1].setBackground(Color.red);

            if (game.checkRiskLevelCornerTR() == 4)
                board[0][board.length - 1].setBackground(Color.orange);
        }

        if (game.checkRiskLevelCornerBL() != 9) {
            if (game.checkRiskLevelCornerBL() == 5)
                board[board.length - 1][0].setBackground(Color.red);

            if (game.checkRiskLevelCornerBL() == 6)
                board[board.length - 1][0].setBackground(Color.orange);
        }

        if (game.checkRiskLevelCornerBR() != 9) {
            if (game.checkRiskLevelCornerBR() == 7)
                board[board.length - 1][board.length - 1].setBackground(Color.red);

            if (game.checkRiskLevelCornerBR() == 8)
                board[board.length - 1][board.length - 1].setBackground(Color.orange);
        }

        //CHECKS BORDERS FOR RISK
        if (game.checkRiskLevelBorders(0,0) != null) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board.length; col++) {
                    if(game.checkRiskLevelBorders(row,col) != null)
                        board[game.checkRiskLevelBorders(row,col)[0]][game.checkRiskLevelBorders(row,col)[1]].setBackground(Color.red);
                }
            }
        }

        //CHECKS MIDDLE FOR MEDIUM RISK
        if (game.checkRiskLevelMiddleYELLOW(1, 1) != null) {
            for (int row = 1; row < board.length - 1; row++) {
                for (int col = 1; col < board.length - 1; col++) {
                    if (game.checkRiskLevelMiddleYELLOW(row, col) != null) {
                        board[game.checkRiskLevelMiddleYELLOW(row, col)[0]][game.checkRiskLevelMiddleYELLOW(row, col)[1]].setBackground(Color.orange);
                    }
                }
            }

            //CHECKS MIDDLE FOR HIGH RISK
            if (game.checkRiskLevelMiddleRED(1, 1) != null) {
                for (int row = 1; row < board.length - 1; row++) {
                    for (int col = 1; col < board.length - 1; col++) {
                        if (game.checkRiskLevelMiddleRED(row, col) != null) {
                            board[game.checkRiskLevelMiddleRED(row, col)[0]][game.checkRiskLevelMiddleRED(row, col)[1]].setBackground(Color.red);
                        }
                    }
                }
            }
        }
    }
        /******************************************************************
     * checking with the timer if the player is out
     */
    public void Clock()
    {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = 8;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(setInterval());

            }
        }, delay, period);
    }
    private final int setInterval() {
        if (interval == 1) {
            timer.cancel();
            playersArray[player] = -3;
        }
        return --interval;
    }
}










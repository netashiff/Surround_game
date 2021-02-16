package Surrond;


import javax.swing.*;

public class Surrount4 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        /*  The JMenuBar is associated with the frame. The first step
         *  is to create the menu items, and file menu.
         */
        /** The menue bar*/
        JMenuBar menus;
        /** The file*/
        JMenu fileMenu;
        /** The quit bottun*/
        JMenuItem quitItem;
        /** The undo bottun*/
        JMenuItem undoItem;
        /** The new game option*/
        JMenuItem newGameItem;
        JLabel time;

//        JTextField time;

        // creating new frame
        JFrame frame = new JFrame("Surround");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // instantiate the menu
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("quit");
        newGameItem = new JMenuItem("new game");
        undoItem = new JMenuItem("Undo");


        // adding the option to the above
        fileMenu.add(newGameItem);
        fileMenu.add(quitItem);
        fileMenu.add(undoItem);

        menus = new JMenuBar();
        menus.add(fileMenu);


        frame.setJMenuBar(menus);
        // call to create the panel
        Surround4Panelari panel = new Surround4Panelari(quitItem, newGameItem,undoItem);
        frame.add(panel);
        //choosing the size of panel
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
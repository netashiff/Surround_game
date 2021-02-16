package Surrond;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/** class that counting the timer*/
public class timer extends JPanel {
    private Timer javaTimer;
    private TimerListener timer;

    public timer(){
        timer = new TimerListener();
        javaTimer = new Timer(1,timer);
    }

private class TimerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
}


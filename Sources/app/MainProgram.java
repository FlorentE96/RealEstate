package app;

import app.ui.LoginWindow;
import javax.swing.JFrame;

/**
 * <b>Main program class.</b>
 * <p>Is executed at the launch of the program.</p>
 *
 * @author  Florent
 * @version 1.0
 */
public class MainProgram {

    /**
     * Main method, input vector of the program.
     * The only thing that this method does is to run the login window.
     *
     * @param args arguments passed to the program.
     *
     * @see LoginWindow
     */
    public static void main(String[] args) {
        LoginWindow myLoginWindow = new LoginWindow();
        myLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
package app;

import app.ui.LoginWindow;
import app.ui.MainWindow;

import javax.swing.JFrame;

public class MainProgram {

    public static void main(String[] args) {
        LoginWindow myLoginWindow = new LoginWindow();
        myLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //app.Agent user = myLoginWindow.showLoginDialog();

        if(true)//user != null)
        {
            MainWindow myMainWindow = new MainWindow(new Agent("Florent", 34, 1256));
            myMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
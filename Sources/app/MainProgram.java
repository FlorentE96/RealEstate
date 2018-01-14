package app;

import app.ui.LoginWindow;

import javax.swing.JFrame;

public class MainProgram {

    public static void main(String[] args) {
        LoginWindow myLoginWindow = new LoginWindow();
        myLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
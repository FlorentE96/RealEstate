import javax.swing.JFrame;

public class MainProgram {

    public static void main(String[] args) {
        LoginWindow app = new LoginWindow();

        //O botão de fechar a janela fecha toda a aplicação!!
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
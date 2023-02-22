package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.Cleaner;

public class LogIn {

    private JFrame loginFrame;
    private JTextField textUserName;
    private JPasswordField passwordField;
    private JButton butonConnect;

    /**
     * Launch the application.
     */
/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogIn window = new LogIn();
                    window.loginFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    /**
     * Create the application.
     */
    public LogIn() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        loginFrame = new JFrame();
        loginFrame.setTitle("LoginSystem");
        loginFrame.setBounds(100, 100, 546, 568);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setLayout(null);

        JLabel labelTitilu = new JLabel("WELCOME");
        labelTitilu.setFont(new Font("Tahoma", Font.BOLD, 23));
        labelTitilu.setBounds(227, 55, 186, 62);
        loginFrame.getContentPane().add(labelTitilu);

        JLabel labelUserName = new JLabel("username :");
        labelUserName.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelUserName.setBounds(79, 180, 122, 39);
        loginFrame.getContentPane().add(labelUserName);

        textUserName = new JTextField();
        textUserName.setBounds(183, 229, 186, 39);
        loginFrame.getContentPane().add(textUserName);
        textUserName.setColumns(10);

        JLabel labelPassword = new JLabel("password :");
        labelPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelPassword.setBounds(79, 312, 122, 39);
        loginFrame.getContentPane().add(labelPassword);

        butonConnect = new JButton("Connect");
        butonConnect.setBounds(201, 460, 142, 32);

        loginFrame.setResizable(false);

        loginFrame.getContentPane().add(butonConnect);

        passwordField = new JPasswordField();
        passwordField.setBounds(183, 371, 186, 39);
        loginFrame.getContentPane().add(passwordField);
    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(JFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public JTextField getTextUserName() {
        return textUserName;
    }

    public void setTextUserName(JTextField textUserName) {
        this.textUserName = textUserName;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getButonConnect() {
        return butonConnect;
    }

    public void setButonConnect(JButton butonConnect) {
        this.butonConnect = butonConnect;
    }
}

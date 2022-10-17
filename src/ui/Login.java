package ui;

import service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Login {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel warnLabel;
    public JPanel rootPanel;


    private final AdminService adminService = new AdminService();

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (adminService.login(username, password)) {
                    warnLabel.setText("");
                    JFrame jFrame = new JFrame();

                    try {
                        jFrame.setContentPane(new Home().rootPanel);
                        jFrame.setSize(800, 800);
                        jFrame.setTitle("Home");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }


                    jFrame.setVisible(true);

                } else {
                    warnLabel.setText("Username or password is wrong");
                }


            }
        });
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == 10) {
                    passwordField.requestFocus();
                }
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == 10) {
                    loginButton.doClick();
                }
            }
        });
    }


    public static void main(String[] args) throws SQLException {

        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new Login().rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(500, 500);
        jFrame.setTitle("Login");
        jFrame.setLocation(300, 300);

        /*
        JLabel loginImage = new JLabel();
        Image img = new ImageIcon(Login.class.getResource("admin.jpg")).getImage();
        loginImage.setIcon(new ImageIcon(img));
        jFrame.getContentPane().add(loginImage);
        */
    }
}

package ui;

import dao.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel warnLabel;
    public JPanel rootPanel;


    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (databaseConnection.login(username, password)) {
                    warnLabel.setText("");
                    JFrame jFrame = new JFrame();
                    jFrame.setContentPane(new Home().rootPanel);

                    jFrame.setVisible(true);

                } else {
                    warnLabel.setText("Username or password wrong");
                }


            }
        });
    }

    public static void main(String[] args) throws SQLException {

        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new Login().rootPanel);
        jFrame.setVisible(true);


    }
}

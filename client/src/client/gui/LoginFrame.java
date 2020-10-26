package client.gui;

import client.LoggedUser;
import client.controller.UserController;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private JButton loginButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JPasswordField passField;
    private JLabel nameLabel;
    private JLabel passLabel;
    private JPanel mainPanel;

    public LoginFrame() {

        loginButton.addActionListener(e -> {
            // verific ca logarea se poate face cu succes si updatez LoggedUser
            if (UserController.getInstance()
                    .correctCredentials(nameField.getText(), String.valueOf(passField.getPassword()))) {
                LoggedUser.setId(UserController.getInstance()
                        .getByCredentials(nameField.getText(),
                                String.valueOf(passField.getPassword())).getId());
                new AccountFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this, "Wrong creditentials","Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            new StartFrame();
            dispose();
        });

        setTitle("Login Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

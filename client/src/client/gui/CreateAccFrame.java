package client.gui;

import client.controller.UserController;
import lib.dto.UserDto;

import javax.swing.*;

public class CreateAccFrame extends JFrame {
    private JTextField nameField;
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel passLabel;
    private JPasswordField passField;
    private JButton createButton;
    private JButton cancelButton;

    public CreateAccFrame() {

        createButton.addActionListener(e -> {
            // verific ca numele sa nu fie luat deja
            var user = new UserDto(nameField.getText(), String.valueOf(passField.getPassword()));
            if (UserController.getInstance().isTaken(user.getName())) {
                JOptionPane.showMessageDialog
                        (this, "Name taken","Error", JOptionPane.ERROR_MESSAGE);
            } else {
                UserController.getInstance().persist(user);
                new StartFrame();
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            new StartFrame();
            dispose();
        });

        setTitle("New Account Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

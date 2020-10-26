package client.gui;

import client.LoggedUser;
import client.controller.UserController;

import javax.swing.*;
import java.time.LocalDateTime;

public class AccountFrame extends JFrame {

    private JPanel mainPanel;
    private JButton addEventButton;
    private JButton seeEventsButton;
    private JButton deleteAccountButton;

    public AccountFrame() {

        // pentru remindere
        LoggedUser.popUpReminder(this);

        addEventButton.addActionListener(e -> {
            new NewEventFrame();
            dispose();
        });

        seeEventsButton.addActionListener(e -> {
            new SeeEventsFrame(LocalDateTime.now());
            dispose();
        });

        deleteAccountButton.addActionListener(e -> {
            UserController.getInstance().removeById(LoggedUser.getId());
            new StartFrame();
            dispose();
        });

        setTitle("Account Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

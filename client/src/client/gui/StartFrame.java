package client.gui;

import javax.swing.*;

public class StartFrame extends JFrame {
    private JButton createAccountButton;
    private JButton loginButton;
    private JPanel mainPanel;

    public StartFrame() {
        createAccountButton.addActionListener(e -> {
            new CreateAccFrame();
            dispose();
        });
        loginButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        setTitle("Start Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

package client.gui;

import client.LoggedUser;
import client.controller.EventController;
import lib.dto.ReminderDto;

import javax.swing.*;
import java.time.LocalDateTime;

public class NewReminderFrame extends JFrame {
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JButton addReminderButton;
    private JButton cancelButton;

    public NewReminderFrame(int eventDtoId) {

        LoggedUser.popUpReminder(this);

        for (Integer i = 1; i <= 60; i++) {
            comboBox1.addItem(i);
        }

        cancelButton.addActionListener(e -> {
            new SeeEventsFrame(LocalDateTime.now());
            dispose();
        });

        // adaug un reminder
        addReminderButton.addActionListener(e -> {
            int minutes = (Integer) comboBox1.getSelectedItem();
            ReminderDto reminderDto = new ReminderDto(minutes);
            EventController.getInstance().addReminderToEvent(eventDtoId, reminderDto);
            new SeeEventsFrame(LocalDateTime.now());
            dispose();
        });

        setTitle("New Reminder Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

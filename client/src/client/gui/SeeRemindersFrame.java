package client.gui;

import client.LoggedUser;
import client.controller.EventController;
import lib.dto.EventDto;
import lib.dto.ReminderDto;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeeRemindersFrame extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JButton deleteReminderButton;
    private JList list1;

    private DefaultListModel<ReminderDto> model = new DefaultListModel<>();

    public void refresh(int eventId) {
        List<ReminderDto> reminderDtos =
                EventController.getInstance().getReminderssByEvent(eventId);
        if (reminderDtos == null) {
            model.clear();
            return;
        }
        model.clear();
        reminderDtos.forEach(model::addElement);
    }

    // vad reminderele pentru evenimentul selectat
    public SeeRemindersFrame(int eventId) {

        LoggedUser.popUpReminder(this);

        list1.setModel(model);
        refresh(eventId);

        backButton.addActionListener(e -> {
            new SeeEventsFrame(LocalDateTime.now());
            dispose();
        });

        deleteReminderButton.addActionListener(e -> {
            ReminderDto reminderDto = (ReminderDto) list1.getSelectedValue();
            EventController.getInstance().removeReminderById(reminderDto.getId());
            refresh(eventId);
        });

        setTitle("Reminders Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}

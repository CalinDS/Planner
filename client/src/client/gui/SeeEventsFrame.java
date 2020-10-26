package client.gui;

import client.LoggedUser;
import client.controller.EventController;
import client.controller.UserController;
import lib.dto.EventDto;
import lib.dto.ReminderDto;
import lib.dto.UserDto;
import org.w3c.dom.events.Event;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeeEventsFrame extends JFrame {
    private JButton previousDayButton;
    private JButton nextDayButton;
    private JButton backButton;
    private JList list1;
    private JPanel mainPanel;
    private JButton deleteEventButton;
    private JButton inspectEventButton;
    private JButton addReminderButton;
    private JTextArea textArea1;

    private DefaultListModel<EventDto> model = new DefaultListModel<>();

    // pun evenimentele in lista
    public void refresh(LocalDateTime localDateTime) {
        List<EventDto> eventDtos = EventController.getInstance()
                .getEventsByUserAndDay(LoggedUser.getId(), localDateTime.toLocalDate());
        model.clear();
        eventDtos.forEach(model::addElement);
    }

    // construiesc acest frame pe baza zilei in care ma aflu,
    // sau in care ajung cu butoanele de next si prevoious day
    public SeeEventsFrame(LocalDateTime localDateTime) {

        LoggedUser.popUpReminder(this);

        textArea1.setText("Time: " + localDateTime.toString());

        list1.setModel(model);
        refresh(localDateTime);

        backButton.addActionListener(e -> {
            new AccountFrame();
            dispose();
        });

        deleteEventButton.addActionListener(e -> {
            EventDto eventDto = (EventDto) list1.getSelectedValue();
            EventController.getInstance().removeById(eventDto.getId());
            refresh(localDateTime);
        });

        addReminderButton.addActionListener(e -> {
            EventDto eventDto = (EventDto) list1.getSelectedValue();
            new NewReminderFrame(eventDto.getId());
            dispose();
            refresh(localDateTime);
        });

        inspectEventButton.addActionListener(e -> {
            EventDto eventDto = (EventDto) list1.getSelectedValue();
            new SeeRemindersFrame(eventDto.getId());
            dispose();
        });

        nextDayButton.addActionListener(e -> {
            new SeeEventsFrame(localDateTime.plusDays(1));
            dispose();
        });

        previousDayButton.addActionListener(e -> {
            new SeeEventsFrame(localDateTime.minusDays(1));
            dispose();
        });

        setTitle("Events Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

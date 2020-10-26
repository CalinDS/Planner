package client.gui;

import client.LoggedUser;
import client.controller.EventController;
import client.controller.UserController;
import lib.dto.EventDto;
import lib.dto.UserDto;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class NewEventFrame extends JFrame {
    private JButton addEventButton;
    private JButton cancelButton;
    private JTextField titleField;
    private JPanel mainPanel;
    private JComboBox comboBoxYear;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxDay;
    private JComboBox comboBoxHour;
    private JComboBox comboBoxMinute;

    public NewEventFrame() {

        LoggedUser.popUpReminder(this);

        initComboBoxes();

        addEventButton.addActionListener(e -> {
            String title = titleField.getText();
            LocalDateTime time = createLocalDateTime
                    (comboBoxYear, comboBoxMonth, comboBoxDay, comboBoxHour, comboBoxMinute);
            if (time == null) {
                JOptionPane.showMessageDialog(this, "Invalid date","Error", JOptionPane.ERROR_MESSAGE);
            } else {
                EventDto eventDto = new EventDto(title, time);
                EventController.getInstance().addEventToUser(LoggedUser.getId(), eventDto);
                new AccountFrame();
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            new AccountFrame();
            dispose();
        });

        setTitle("New Event Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComboBoxes() {
        for (Integer i = 2020; i < 2051; i++) {
            comboBoxYear.addItem(i);
        }
        for (Integer i = 1; i <= 12; i++) {
            comboBoxMonth.addItem(i);
        }
        for (Integer i = 1; i <= 31; i++) {
            comboBoxDay.addItem(i);
        }
        for (Integer i = 1; i <= 23; i++) {
            comboBoxHour.addItem(i);
        }
        for (Integer i = 0; i <= 59; i++) {
            comboBoxMinute.addItem(i);
        }
    }

    public LocalDateTime createLocalDateTime(
            JComboBox comboBoxYear, JComboBox comboBoxMonth, JComboBox comboBoxDay,
            JComboBox comboBoxHour, JComboBox comboBoxMinute) {
        // creez timpul evenimentului pe baza comboBox-urilor, si ma asigur ca e o data valida
        int year = (Integer) comboBoxYear.getSelectedItem();
        int month = (Integer) comboBoxMonth.getSelectedItem();
        int day = (Integer) comboBoxDay.getSelectedItem();
        int hour = (Integer) comboBoxHour.getSelectedItem();
        int minute = (Integer) comboBoxMinute.getSelectedItem();
        try {
            return LocalDateTime.of(year, month, day, hour, minute, 0);
        } catch (DateTimeException e) {
            return null;
        }
    }

}

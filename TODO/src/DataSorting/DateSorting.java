package DataSorting;

import Features.Actions;
import App.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateSorting implements Actions{

    public void showActionsInformation() {
        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    public String readUserInput() {
        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    public void executeAction(String command) {
        List<Map.Entry<String, Task>> entries = new ArrayList<>(TodoList.tasks.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Task>>() {
            @Override
            public int compare(Map.Entry<String, Task> task1, Map.Entry<String, Task> task2) {
                LocalDate dueDateFirstTask = task1.getValue().getDueDate();
                LocalDate dueDateSecondTask = task2.getValue().getDueDate();
                int result = dueDateFirstTask.compareTo(dueDateSecondTask);
                return result;
            }

        });

        TodoList.tasks.clear();
        entries.forEach((entry) -> {
            TodoList.tasks.put(entry.getKey(), entry.getValue());
        });

        System.out.println("Tasks successfully Sorted!");

    }

    public static boolean isDateValid(String format, String value) {
        DateTimeFormatter formattings = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate localDate = LocalDate.parse(value, formattings);

            String result = localDate.format(formattings);

            return result.equals(value);
        } catch (DateTimeParseException err) {

        }
        return false;
    }

    public static String convertDateToString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String result = null;
        try {
            result = date.format(formatter);

        } catch (DateTimeParseException e) {

        }
        return result;
    }

    public static LocalDate parseDate(String format, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(value, formatter);
        return localDate;
    }
}
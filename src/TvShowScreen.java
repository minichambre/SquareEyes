
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by esenav on 21/11/2017.
 */
public class TvShowScreen implements ActionListener {
    GUI theApp;
    Filter filter = new Filter();
    private static ArrayList<TvShow> filteredShows = new ArrayList<>();



    public TvShowScreen(GUI theApp) {
        this.theApp = theApp;
    }

    JTable tvshow;
    JScrollPane tvpane;
    JComboBox comboBox;
    private void updateTable(){

        theApp.centerField.removeAll();
        theApp.window_name1.setText("Tv Shows");
        tablescreen();
        theApp.centerField.add(comboBox, BorderLayout.NORTH);
        theApp.centerField.add(tvpane, BorderLayout.CENTER);
        theApp.centerField.updateUI();
        filteredShows.clear();
    }

    public void actionPerformed(ActionEvent event) {
        updateTable();
    }

    //method to get day/date from DateTime object
    public ArrayList<String> datesForComboBox() {
        ArrayList<String> comboBoxItems = new ArrayList<>();
        DateTime today = new DateTime();
        comboBoxItems.add(today.toLocalDate().toString());
        for (int i = 1; i <= 6; i++) {
            comboBoxItems.add(today.plusDays(i).toLocalDate().toString());
        }

        return comboBoxItems;
    }

    public void tablescreen() {

        ArrayList<TvShow> x = TvGuide.shows;

        String[] columnNames = {"Name", "Viewing Time", "Genre"};

        comboBox = new JComboBox(datesForComboBox().toArray());
        //comboBox.setSelectedIndex(0);

        ActionListener comboBoxListener = e -> {
            int comboBoxChoice = comboBox.getSelectedIndex();
            String date;

            switch (comboBoxChoice) {
                case 0:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    tablescreen();
                    break;
                case 1:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    tablescreen();
                    break;
                case 2:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    break;
                case 3:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    break;
                case 4:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    break;
                case 5:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    break;
                case 6:
                    date = comboBox.getSelectedItem().toString();
                    filteredShows = filter.filterByDate(stringToDateTime(date), x);
                    break;
            }


            updateTable();
        };

        comboBox.addActionListener(comboBoxListener);

        Object[][] rows;


        // add filter method to filter ArrayList of shows by date chosen by user form dropdown list
        if(filteredShows.isEmpty()){
            rows= populateRows(x);
        }else{
            rows= populateRows(filteredShows);
        }
        tvshow = new JTable(rows, columnNames);
        tvpane = new JScrollPane(tvshow);
        tvshow.setBounds(1, 1, theApp.centerField.getWidth(), theApp.centerField.getHeight());
        tvpane.setBounds(1, 1, theApp.centerField.getWidth(), theApp.centerField.getHeight());
        tvshow.setVisible(true);
        tvpane.setVisible(true);
    }



    public LocalDate stringToLocalDate(String stringDate) {
        LocalDate localDate = new LocalDate(stringDate);
        return localDate;
    }
    public DateTime localDateToDateTime(LocalDate localDate){
        DateTime dateTime = new DateTime(localDate.toDateTimeAtStartOfDay());
        return dateTime;
    }


    public DateTime stringToDateTime(String localDate) {
        System.out.println(localDate);
        LocalDate ld = stringToLocalDate(localDate);
        System.out.println(ld);
        DateTime dt = localDateToDateTime(ld);
        System.out.println(dt);
        return dt.plusMinutes(1);
    }
    public Object[][] populateRows(ArrayList<TvShow> populateRows){

        Object[][] rows = new Object[populateRows.size()][3];
        for (int i = 0; i < populateRows.size(); i++) {
            rows[i][0] = populateRows.get(i).name;
            rows[i][1] = populateRows.get(i).viewingTime.toDate();
            rows[i][2] = populateRows.get(i).genre.toString().replace("[", "").replace("]", "");
            if (populateRows.get(i).genre.isEmpty()) {
                rows[i][2] = "";
            }

        }
        return rows;
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dgowin on 05/12/2017.
 */
public class ViewingTimes extends JFrame{
    static Map<String,ArrayList<int[]>>  viewingHours = new HashMap<String,ArrayList<int[]>>();
    ArrayList<String> days = new ArrayList<String>();
    int j = 0;

    public ViewingTimes()
    {
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        this.setSize(700,600);
        this.setLayout(new BorderLayout());
        JPanel centre = new JPanel();
        centre.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;

        this.add(centre, BorderLayout.CENTER);
        JLabel mon = new JLabel(days.get(j));
        cons.gridx = 0;
        cons.gridy = 0;
        centre.add(mon, cons);

        JTextField startTime = new JTextField("From");
        startTime.setColumns(10);
        JTextField endTime = new JTextField("Til");
        endTime.setColumns(10);
        cons.gridx = 1;
        cons.gridy = 0;
        centre.add(startTime, cons);

        cons.gridx = 2;
        cons.gridy = 0;
        centre.add(endTime, cons);

        JButton prevDay = new JButton("Previous Day");
        cons.gridx = 0;
        cons.gridy = 1;
        centre.add(prevDay, cons);
        prevDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j==0){
                    j = 6;
                }
                else {
                    j--;
                }
                mon.setText(days.get(j));
            }
        });

        JButton nextDay = new JButton("Next Day");
        cons.gridx = 2;
        cons.gridy = 1;
        centre.add(nextDay, cons);
        nextDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j==6){
                    j = 0;
                }
                else {
                    j++;
                }
                mon.setText(days.get(j));
            }
        });

        JButton add = new JButton("Add this time period for this day");
        cons.gridx = 1;
        cons.gridy = 2;
        centre.add(add, cons);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] times = new int[2];
                try{
                    times[0] = Integer.parseInt(startTime.getText());
                    times[1] = Integer.parseInt(endTime.getText());
                }catch(NumberFormatException err){
                    System.out.println("Only numbers please");
                }
                ArrayList<int[]> values = viewingHours.get(days.get(j));
                if(values == null){
                    ArrayList<int[]> vals = new ArrayList<int[]>();
                    vals.add(times);
                    viewingHours.put(days.get(j), vals);
                }else{
                    values.add(times);
                    viewingHours.put(days.get(j),values);
                }
                for( int i = 0; i < viewingHours.get(days.get(j)).size(); i++){
                    System.out.println("---");
                    System.out.println(Arrays.toString(viewingHours.get(days.get(j)).get(i)));
                }

                //System.out.println(Arrays.toString(viewingHours.get("Monday")));

            }
        });

    }
}

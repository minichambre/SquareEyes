import javafx.scene.layout.Border;

import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by dgowin on 22/11/2017.
 */
public class RecommendedListener implements ActionListener {
    GUI theApp;
    JTable tvshow;
    JScrollPane tvpane, descScroll;
    JPanel showsInfo;
    JLabel pic;
    JEditorPane desc;
    JButton reminder;


    public RecommendedListener(GUI theApp ){
        this.theApp = theApp;

    }

    public void load(){
        theApp.centerField.removeAll();
        showsInfo = new JPanel();
        pic = new JLabel();
        desc = new JEditorPane();
        reminder = new JButton("Remind Me to Watch");
        reminder.setMinimumSize(new Dimension(140,150));
        desc.setOpaque(false);
        descScroll = new JScrollPane(desc);
        descScroll.setBounds(1,1, 600,150);
        desc.setBounds(1,1,600,150);

        showsInfo.add(pic, BorderLayout.LINE_START);
        showsInfo.add(desc, BorderLayout.NORTH);
        showsInfo.add(reminder, BorderLayout.LINE_END);
        String[] columnNames = {"Name","Viewing Time","Genre"};

        Object[][] rows = new Object[Training.preferredShows.size()][3];
        for (int i = 0; i < Training.preferredShows.size(); i++) {
            rows[i][0]= Training.preferredShows.get(i).name;
            rows[i][1]= Training.preferredShows.get(i).viewingTime.toDate();
            rows[i][2]= Training.preferredShows.get(i).genre.toString().replace("[", "").replace("]", "");
        }

        JButton applyPrefferedTimes = new JButton("Apply my preffered viewing times");
        applyPrefferedTimes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Training.preferredShows = Filter.filterByTime(Training.preferredShows, ViewingTimes.viewingHours);
                load();
            }
        });
        theApp.centerField.add(applyPrefferedTimes, BorderLayout.NORTH);


        tvshow = new JTable(rows,columnNames );

        tvshow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tvshow.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int rowIndex = tvshow.getSelectedRow();
                showImage(Training.preferredShows.get(rowIndex).imageRoot, pic);
                showDescription(Training.preferredShows.get(rowIndex).description);
                showsInfo.updateUI();
            }
        });
        tvpane = new JScrollPane(tvshow);
        tvshow.setBounds(1, 1, theApp.centerField.getWidth(), theApp.centerField.getHeight());
        tvpane.setBounds(1, 1, theApp.centerField.getWidth(), theApp.centerField.getHeight());
        tvshow.setVisible(true);
        tvpane.setVisible(true);
        showsInfo.setVisible(true);
        showsInfo.setPreferredSize(new Dimension(1000, 150));
        theApp.centerField.add(showsInfo, BorderLayout.SOUTH);
        theApp.centerField.add(tvpane, BorderLayout.CENTER);
        theApp.centerField.updateUI();
        theApp.window_name1.setText("Recommendations");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        load();
    }

    public void showImage(String imgsrc, JLabel l)
    {
        try {
            l.setIcon(Filter.getImage(imgsrc));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void showDescription(String source){
        desc.setText(source);
    }
}

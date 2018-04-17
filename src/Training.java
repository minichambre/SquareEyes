import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dgowin on 21/11/2017.
 */
public class Training implements ActionListener{

    GUI theApp;
    public static ArrayList<TvShow> preferredShows = new ArrayList<TvShow>();
    public static ArrayList<String> preferredNames = new ArrayList<>();
    public static ArrayList<String> preferredGenres = new ArrayList<>();
    public static ArrayList<String> preferredNetworks = new ArrayList<>();
    public static ArrayList<TvShow> userCollection = new ArrayList<TvShow>();

    public Training(GUI theApp){
        this.theApp = theApp;
    }

    JTable tvshow;
    JScrollPane tvpane;


    public Training() {
    }

    public HashSet<String> setOfGenres = new HashSet<String>();
    public HashSet<String> userPreferredGenres = new HashSet<String>();


    public  void getGenres(ArrayList<TvShow> listOfShows){
        for (TvShow show:listOfShows) {
            for (String genre: show.genre) {
                setOfGenres.add(genre);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.centerField.removeAll();
        userPreferredGenres.clear();
        getGenres(TvGuide.shows);
        ArrayList<JButton> genreButtons = new ArrayList<JButton>();

        for (String s:setOfGenres) {
            JButton jb = new JButton(s);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userPreferredGenres.add(s);
                    jb.setBackground(Color.WHITE);
                }
            });
            genreButtons.add(jb);
            theApp.window_name1.setText("Training");

        }

        JButton jButton = new JButton("Done");
        jButton.setBackground(Color.red);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCollection.clear();

                for (String s:userPreferredGenres) {

                    ArrayList<TvShow> temp = Filter.filterByGenre(s,TvGuide.shows);
                    for(TvShow tempTv : temp){
                        if(!userCollection.contains(tempTv)){
                            userCollection.add(tempTv);
                        }
                    }
                }
                String[] columnNames = {"Name","Viewing Time","Genre", "More of this?"};

                Object[][] rows = new Object[userCollection.size()][4];
                for (int i = 0; i < userCollection.size(); i++) {
                    rows[i][0]= userCollection.get(i).name;
                    rows[i][1]= userCollection.get(i).network;
                    rows [i][2] = userCollection.get(i).genre.toString().replace("[", "").replace("]", "");
                    rows [i][3] = new Boolean(false);
                }

                tvshow = new JTable(new TrainingTableModel(rows));
                //tvshow.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer());
                tvpane = new JScrollPane(tvshow);
                tvshow.setBounds(1, 1, theApp.centerField.getWidth(), theApp.centerField.getHeight());
                tvpane.setBounds(1, 1, theApp.centerField.getWidth(), theApp.centerField.getHeight());
                tvshow.setVisible(true);
                tvpane.setVisible(true);

                theApp.centerField.add(tvpane, BorderLayout.CENTER);
                theApp.centerField.updateUI();


            }
        });


        JPanel genrePanel = new JPanel();
        FlowLayout layout = new FlowLayout();
        genrePanel.setPreferredSize(new Dimension(1000,100));
        genrePanel.setLayout(layout);
        genrePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (JButton jb : genreButtons) {
            genrePanel.add(jb);
        }
        genrePanel.add(jButton, BorderLayout.SOUTH);

        JButton recommendButton = new JButton("Recommend me some shows");
        recommendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<TvShow> shows = new ArrayList<TvShow>();

                for (String name: Training.preferredNames)
                {
                    shows.addAll(Filter.filterByName(TvGuide.shows, name ));
                }

                for (String genre : Training.preferredGenres)
                {
                    ArrayList<TvShow> temp = new ArrayList<TvShow>();
                    temp = Filter.filterByGenre(genre, TvGuide.shows);
                    for(TvShow tempTv : temp){
                        if(!shows.contains(tempTv)){
                            shows.add(tempTv);
                        }
                    }
                }
                Training.preferredShows.clear();
                Training.preferredShows = shows;

                ViewingTimes vt = new ViewingTimes();
                vt.setVisible(true);
                theApp.window_name1.setText("Recommendations");
            }
        });

        theApp.centerField.add(genrePanel, BorderLayout.NORTH);
        theApp.centerField.add(recommendButton, BorderLayout.SOUTH);
        //theApp.centerField.add(jButton, BorderLayout.NORTH);
        theApp.centerField.updateUI();



    }
}
class TrainingTableModel extends AbstractTableModel
{
    private Object[][] rows;
    public TrainingTableModel(Object[][] rows)
    {
     this.rows = rows;
    }
    private String[] columnNames = {"Name","Channel","Genre", "More of this?"};
    //private Object[][] data = rows;
    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Class getColumnClass(int c)
    {
        return getValueAt(0,c).getClass();
    }

    public boolean isCellEditable(int row, int col)
    {
        if (col != 3)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col)
    {
        rows[row][col] = value;
        if ((Boolean) value)
        {
            Training.preferredNames.add((String)rows[row][0]);
            Training.preferredNetworks.add((String) rows[row][1]);
            if (rows[row][1] != null)
            {
                Training.preferredGenres.add((String) rows[row][2]);
            }
        }
        if ((Boolean) value == false)
        {
            if (Training.preferredNames.contains((String) rows[row][0]))
            {
                Training.preferredNames.remove((String) rows[row][0]);
            }

            if (Training.preferredNetworks.contains((String) rows[row][1]))
            {
                Training.preferredNetworks.remove((String) rows[row][1]);
            }

            if (Training.preferredGenres.contains((String) rows[row][2]))
            {
                Training.preferredGenres.remove((String) rows[row][2]);
            }


        }
        fireTableCellUpdated(row,col);

    }
}




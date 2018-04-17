import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Evaldas on 2017-11-14.
 */
public class GUI extends JFrame{
    JFrame window;
    JPanel tab,centerField ;
    JLabel window_name1, LoginName;
    protected int window_width = 1000;
    protected int window_height = 700;
    double weightx = 0.5;
    JTextField username ;
    JPasswordField passwordField;
    GridBagConstraints cons;
    public ReminderCheckingThread checkingThread;

    public GUI() {
        TvGuide tvGuide = new TvGuide();
        checkingThread = new ReminderCheckingThread();
        checkingThread.start();
        System.out.println("running");
        try {
            Filter f = new Filter();
            String[] days = f.getDates();
            for (int i = 0; i < days.length; i++)
            {
                tvGuide.jsonReader(f.loadShows(days[i]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        window = new JFrame("Give Me Square Eyes");
        window.setLayout(new BorderLayout());

        NavigationTab();
        LoginScreen();
        window.add(tab, BorderLayout.NORTH);
        window.add(centerField, BorderLayout.CENTER);
        window.setPreferredSize(new Dimension(window_width, window_height));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        GUI gui = new GUI();
        SystemNotification notification = new SystemNotification();
        notification.CheckTray();

    }
    public void NavigationTab(){
        tab = new JPanel();
        tab.setBackground(Color.DARK_GRAY);
        tab.setLayout(new GridBagLayout());
        cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
    }


    public void LoginScreen(){
        centerField = new JPanel();
        centerField.setLayout(new BorderLayout());
        centerField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerField.setBackground(Color.WHITE);
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(250, 350));
        JLabel spacer2 = new JLabel();
        spacer2.setPreferredSize(new Dimension(250, 350));
        JLabel spacer3 = new JLabel();
        spacer3.setPreferredSize(new Dimension(250, 150));
        username = new JTextField("UserName");
        username.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                username.setText("");
            }
        });
        passwordField = new JPasswordField("Password");
        passwordField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                passwordField.setText("");
            }
        });
        JLabel forgot = new JLabel("Forgot Password?");
        forgot.setMaximumSize(new Dimension(200,25));
        JButton register = new JButton("Register");
        register.addActionListener(new RegisterAction(this));
        JButton login = new JButton("Login");
        login.addActionListener(new LoginAction(this, new TvShowScreen(this)));
        username.setMaximumSize(new Dimension(200,25));
        passwordField.setMaximumSize(new Dimension(200, 25));
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5,1));
        center.setBackground(Color.WHITE);
        center.add(username);
        center.add(passwordField);
        center.add(login);
        center.add(register);
        center.add(forgot);
        centerField.add(spacer, BorderLayout.LINE_START);
        centerField.add(spacer2, BorderLayout.LINE_END);
        centerField.add(spacer3, BorderLayout.PAGE_START);
        centerField.add(center, BorderLayout.CENTER);
    }
    public void Nav_Buttons(){
        window_name1 = new JLabel("Give Me Square Eyes");
        window_name1.setForeground(Color.WHITE);
        window_name1.setPreferredSize(new Dimension(600,50));
        cons.weightx = weightx;
        cons.gridx = 0;
        cons.gridy = 0;
        tab.add(window_name1, cons);
        JButton window_button1 = new JButton("Tv Shows");
        window_button1.setBackground(Color.darkGray);
        cons.fill = GridBagConstraints.HORIZONTAL;
        window_button1.setForeground(Color.WHITE);
        cons.weightx =weightx;
        cons.gridy = 0;
        cons.gridx = 1;
        // cons.ipady = ipady; to set buttons tall;
        window_button1.addActionListener(new TvShowScreen(this));
        tab.add(window_button1, cons);

        JButton window_button2 = new JButton("Recommendations");
        window_button2.setBackground(Color.DARK_GRAY);
        cons.fill = GridBagConstraints.HORIZONTAL;
        window_button2.setForeground(Color.WHITE);
        cons.weightx =weightx;
        cons.gridy = 0;
        cons.gridx = 2;
        window_button2.addActionListener(new RecommendedListener(this));
        tab.add(window_button2, cons);

        JButton window_button3 = new JButton("Training");
        window_button3.setBackground(Color.DARK_GRAY);
        window_button3.setForeground(Color.WHITE);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx =weightx;
        cons.gridy = 0;
        cons.gridx = 3;
        window_button3.addActionListener(new Training(this));
        tab.add(window_button3, cons);

        JButton window_button4 = new JButton("Log out");
        window_button4.setBackground(Color.DARK_GRAY);
        window_button4.setForeground(Color.WHITE);
        cons.gridx = 4;
        window_button4.addActionListener(new LogOut(this));
        tab.add(window_button4, cons);

        LoginName = new JLabel("");
        cons.gridx = 5;
        LoginName.setForeground(Color.WHITE);
        tab.add(LoginName, cons);
    }
}

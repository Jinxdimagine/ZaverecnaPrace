import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign extends JFrame implements ActionListener {

    private JTextField FirstName,LastName,UserName;
    private JButton Submit,GoBack,Show;

    private JPasswordField Password;
    private boolean showned;

    Sign(){
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(200,200,600,600);
        this.setLayout(null);
        addForum();
        this.setVisible(true);
    }

    void addForum(){
        Panel panel=new Panel();
        panel.setLayout(new GridLayout(5,2,10,10));
        panel.setBounds(100,100,400,200);
        FirstName = new JTextField();
        LastName = new JTextField();
        UserName = new JTextField();
        Password =new JPasswordField();
        Submit =new JButton("Submit");
        GoBack =new JButton("Go Back");
        Show =new JButton("Show");
        Show.setBounds(500,227,70,30);
        Password.setEchoChar('*');
        Submit.addActionListener(this);
        GoBack.addActionListener(this);
        Show.addActionListener(this);
        JLabel firstname=new JLabel("First Name");
        JLabel lastname=new JLabel("Last Name");
        JLabel username=new JLabel("Username");
        JLabel password=new JLabel("Password");
        panel.add(firstname);
        panel.add(FirstName);
        panel.add(lastname);
        panel.add(LastName);
        panel.add(username);
        panel.add(UserName);
        panel.add(password);
        panel.add(Password);
        panel.add(Submit);
        panel.add(GoBack);
        this.add(panel);
        this.add(Show);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GoBack){
            this.dispose();
            Login login =new Login();
        }
        if (e.getSource() == Show){
            if (!showned) {
                Show.setText("Hide");
                Password.setEchoChar((char) 0);
                setShowned(true);
            } else {
                Password.setEchoChar('*');
                Show.setText("Show");
                setShowned(false);
            }
        }
    }

    public boolean isShowned() {
        return showned;
    }

    public void setShowned(boolean showned) {
        this.showned = showned;
    }
}

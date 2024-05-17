import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign extends JFrame implements ActionListener {

    private JTextField FirstName,LastName,UserName;
    private JButton Submit,GoBack;

    private JPasswordField Password;

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
        Submit.addActionListener(this);
        GoBack.addActionListener(this);
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
        panel.add(GoBack);
        panel.add(Submit);
        this.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GoBack){
            this.dispose();
            Login login =new Login();
        }
    }
}

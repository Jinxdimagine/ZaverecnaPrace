import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField username,password;
    private JButton submit,sign;
    Login(){
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,200,600,600);
        this.setLayout(null);
        addTextfield();
        this.setVisible(true);
    }

    void addTextfield(){
        Panel panel=new Panel();
        username = new JTextField();
        password = new JTextField();
        JLabel TextUsername=new JLabel("Username");
        JLabel TextPassword=new JLabel("Password");
        panel.setBounds(50,50,300,80);
        panel.setLayout(new GridLayout(2,2));
        panel.add(TextUsername);
        panel.add(username);
        panel.add(TextPassword);
        panel.add(password);
        this.add(panel);
    }
}

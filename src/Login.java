import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JTextField username,password;
    private JButton login,sign;
    Login(){
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,200,600,600);
        this.setLayout(null);
        addForum();
        this.setVisible(true);
    }

    void addForum(){
        Panel panel=new Panel();
        username = new JTextField();
        password = new JTextField();
        login=new JButton("Login");
        sign=new JButton("Sign up");
        login.addActionListener(this);
        sign.addActionListener(this);
        JLabel TextUsername=new JLabel("Username");
        JLabel TextPassword=new JLabel("Password");
        panel.setBounds(100,100,400,200);
        panel.setLayout(new GridLayout(3,2,10,10));
        panel.add(TextUsername);
        panel.add(username);
        panel.add(TextPassword);
        panel.add(password);
        panel.add(login);
        panel.add(sign);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == sign){
          Sign sign1=new Sign();
      }
    }
}

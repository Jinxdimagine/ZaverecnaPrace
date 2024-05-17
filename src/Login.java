import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {
    private JTextField Username;
    private JPasswordField Password;
    private JButton login,sign,Show;
    private boolean showned=false;
    private Database database;
    Login() throws IOException, ClassNotFoundException {
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,200,600,600);
        this.setLayout(null);
        addForum();
        setDatabase(new Database());
        this.setVisible(true);
    }

    void addForum(){
        Panel panel=new Panel();
        Username = new JTextField();
        Password = new JPasswordField();
        login=new JButton("Login");
        sign=new JButton("Sign up");
        login.addActionListener(this);
        sign.addActionListener(this);
        JLabel TextUsername=new JLabel("Username");
        JLabel TextPassword=new JLabel("Password");
        panel.setBounds(100,100,400,200);
        panel.setLayout(new GridLayout(3,2,10,10));
        panel.add(TextUsername);
        panel.add(Username);
        panel.add(TextPassword);
        panel.add(Password);
        panel.add(login);
        panel.add(sign);
        Show =new JButton("Show");
        Show.addActionListener(this);
        Show.setBounds(500,185,70,30);
        Password.setEchoChar('*');
        this.add(Show);
        this.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sign){
            this.dispose();
            Sign sign1=new Sign(database);
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

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}

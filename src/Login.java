import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {
    private JTextField Username,Check;
    private JPasswordField Password;
    private JButton login,sign,Show,ForgetPasswordButton,Submit,GoBack;
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
    /**
     *Method addForum will add JTextFields and buttons for user to log in into system.
     * */
    void addForum(){
        Panel panel=new Panel();
        Username = new JTextField();
        Password = new JPasswordField();
        login=new JButton("Login");
        sign=new JButton("Sign up");
        ForgetPasswordButton=new JButton("Forget Password");
        login.addActionListener(this);
        sign.addActionListener(this);
        ForgetPasswordButton.addActionListener(this);
        JLabel TextUsername=new JLabel("Username");
        JLabel TextPassword=new JLabel("Password");
        panel.setBounds(100,100,400,200);
        panel.setLayout(new GridLayout(4,2,10,10));
        panel.add(TextUsername);
        panel.add(Username);
        panel.add(TextPassword);
        panel.add(Password);
        panel.add(login);
        panel.add(sign);
        panel.add(ForgetPasswordButton);
        Show =new JButton("Show");
        Show.addActionListener(this);
        Show.setBounds(500,160,70,30);
        Password.setEchoChar('*');
        this.add(Show);
        this.add(panel);
    }

    void ForgetPassword(){
        Check=new JTextField();
        Submit=new JButton("Submit");
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(2,1));
        Submit.addActionListener(this);
        Check.setText("Write your Username to check");
        panel.add(Check);
        panel.add(Submit);
        panel.setBounds(100,100,400,200);
        this.add(panel);
    }

    void info(char[] Password, String Username){
        JLabel password=new JLabel();
        JLabel username=new JLabel();
        password.setText("Password :"+String.valueOf(Password));
        username.setText("Username :"+Username);
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(1,2,0,0));
        panel.add(username);
        panel.add(password);
        panel.setBounds(100,100,400,200);
        this.add(panel);
    }

    void Reflash(){
        this.repaint();
        this.setVisible(true);
    }
    void ButtonGoBack(){
        GoBack=new JButton("Go Back");
        GoBack.addActionListener(this);
        GoBack.setBounds(480,510,100,50);
        this.add(GoBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sign){
            this.dispose();
            Sign sign1=new Sign(database);
        }
        /**
         * If button Show is pressed JPasswordFiled will reveal the text in field else it will hide the text.
         */
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
        /**
         * If button login is pressed it will take text in JtextFields and send it into databases using method match to
         * find Account with same Username and Password.
         * If method match will return false it means match wasn't found.
         * Else it disposes current frame and start class window where user will be able to access functions.
         * */
        if(e.getSource() ==login){
            if (getDatabase().match(Username.getText(),Password.getPassword())){
                System.out.println("success");
                this.dispose();
                Window window=new Window(database);
            }else {
                Username.setText("Invalid username or password");
                Password.setText("");
            }
        }
        if (e.getSource()==ForgetPasswordButton){
         this.getContentPane().removeAll();
         ForgetPassword();
         ButtonGoBack();
         Reflash();
        }
        if (e.getSource()==Submit){
            if (database.check(Check.getText())){
                this.getContentPane().removeAll();
                info(database.getAccount().getPassword(),database.getAccount().getUserName());
                ButtonGoBack();
                Reflash();
            }else {
                Check.setText("Invalid Username");
            }
        }
        if (e.getSource()==GoBack){
            this.getContentPane().removeAll();
            addForum();
            Reflash();
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

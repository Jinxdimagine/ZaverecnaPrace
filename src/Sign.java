import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Sign extends JFrame implements ActionListener {

    private JTextField FirstName,LastName,UserName;
    private JButton Submit,GoBack,Show;

    private JPasswordField Password;
    private boolean showned;
    private Database database;

    Sign(Database database){
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(200,200,600,600);
        this.setLayout(null);
        addForum();
        setDatabase(database);
        this.setVisible(true);
    }
    /**
     * Method addForum add Jtextfields and buttons for user to register into database.
     */
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
        /**
        * If button GoBack is pressed it will dispose current Frame and start class Login for user to login into system.
         */
        if (e.getSource() == GoBack){
            this.dispose();
            try {
                Login login =new Login();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        /**
         * If button Show is pressed JPasswordFiled will reaveal the text in field else it will hide the text.
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
         * If button Submit is pressed it will try to add the account into database.
         * If database method add return false it means that account with same Firstname and Lastname.
         * Also all textfields will be reset.
         * * */
        if (e.getSource() ==Submit){
            Account account=new Account();
            try {
                account.setFirstName(FirstName.getText());
                account.setLastName(LastName.getText());
                account.setUserName(UserName.getText());
                account.setPassword(Password.getPassword());
                if (database.add(account)){
                    System.out.println(database.getDatabase().toString());
                    this.dispose();
                    Login login=new Login();
                }else {
                    FirstName.setText("Account already exits");
                    LastName.setText("");
                    UserName.setText("");
                    Password.setText("");
                    System.out.println(database.getDatabase().toString());
                }


            } catch (Exception ex) {
                throw new RuntimeException(ex);
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

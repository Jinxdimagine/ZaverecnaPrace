import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Settings extends JFrame implements ActionListener {
   private Account account;
    private JTextField FirstName,LastName,UserName;
    private JButton Change,GoBack,Show;

    private JPasswordField Password;
    private ArrayList<JRadioButton> radioButtons;
    private Database database;

   public Settings(Account account,Database database){
       this.setResizable(false);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setBounds(200, 200, 600, 600);
       this.setLayout(null);
       this.setAccount(account);
       this.setDatabase(database);
       addMenu();
       this.setVisible(true);
   }

   void addMenu(){
       JPanel panel=new JPanel();
       radioButtons=new ArrayList<>();
       panel.setLayout(new GridLayout(5,4,0,0));
       panel.setBounds(0,0,400,300);
       JLabel LabelFirstName=new JLabel("First Name :");
       JLabel LabelLastName=new JLabel("Last Name :");
       JLabel LabelUsername=new JLabel("UserName :");
       JLabel LabelPassword=new JLabel("Password :");
       JLabel AccountFirstName=new JLabel(account.getFirstName());
       JLabel AccountLastName=new JLabel(account.getLastName());
       JLabel AccountUsername=new JLabel(account.getUserName());
       JLabel AccountPassword=new JLabel(String.valueOf(account.getPassword()));
       FirstName = new JTextField();
       LastName = new JTextField();
       UserName = new JTextField();
       Password =new JPasswordField();
       Change=new JButton("Change");
       GoBack=new JButton("Go Back");
       Change.addActionListener(this);
       GoBack.addActionListener(this);
       addJradioButtons();
       panel.add(LabelFirstName);
       panel.add(AccountFirstName);
       panel.add(FirstName);
       panel.add(radioButtons.get(0));
       panel.add(LabelLastName);
       panel.add(AccountLastName);
       panel.add(LastName);
       panel.add(radioButtons.get(1));
       panel.add(LabelUsername);
       panel.add(AccountUsername);
       panel.add(UserName);
       panel.add(radioButtons.get(2));
       panel.add(LabelPassword);
       panel.add(AccountPassword);
       panel.add(Password);
       panel.add(radioButtons.get(3));
       panel.add(GoBack);
       panel.add(Change);
       this.add(panel);
   }
    void addJradioButtons(){
        for (int x=0;x<4;x++){
         JRadioButton jRadioButton=new JRadioButton();
         radioButtons.add(jRadioButton);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==GoBack){
           this.dispose();
           Window window=new Window(database);
       }
       if (e.getSource()==Change){
           if (!FirstName.getText().equals("") && LastName.getText().equals("") && UserName.getText().equals("") && Password.getPassword() == null){
               try {
                   for (int x=0;x<4;x++){
                       if (radioButtons.get(x).isSelected()){
                           switch (x){
                               case 0->{
                                   account.setFirstName(FirstName.getText());
                               }
                               case 1->{
                                  account.setLastName(LastName.getText());
                               }
                               case 2->{
                                  account.setUserName(UserName.getText());
                               }
                               case 3->{
                                   account.setPassword(Password.getPassword());
                               }
                           }
                       }
                   }
               }catch (Exception exception){
                   System.out.println(exception.getMessage());
               }

           }
       }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}

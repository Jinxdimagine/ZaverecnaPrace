import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Settings extends JFrame implements ActionListener {
   private Account account;
    private JTextField FirstName,LastName,UserName;
    private JButton Change,GoBack,Show;
    private JLabel AccountFirstName,AccountLastName,AccountUsername,AccountPassword;
    private JPasswordField Password;
    private ArrayList<JRadioButton> radioButtons;
    private Database database;
    private boolean show;

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
       AccountFirstName=new JLabel(account.getFirstName());
       AccountLastName=new JLabel(account.getLastName());
       AccountUsername=new JLabel(account.getUserName());
       AccountPassword=new JLabel(String.valueOf(account.getPassword()));
       FirstName = new JTextField();
       LastName = new JTextField();
       UserName = new JTextField();
       Password =new JPasswordField();
       Change=new JButton("Change");
       GoBack=new JButton("Go Back");
       Show=new JButton("Show");
       Change.addActionListener(this);
       GoBack.addActionListener(this);
       Show.addActionListener(this);
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
       panel.add(Show);
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
       if (e.getSource()==Show){
           if (!show) {
               Show.setText("Hide");
               Password.setEchoChar((char) 0);
               setShow(true);
           } else {
               Password.setEchoChar('*');
               Show.setText("Show");
               setShow(false);
           }
       }
       if (e.getSource()==Change){
           if (!FirstName.getText().equals("") || LastName.getText().equals("") || UserName.getText().equals("") || Password.getPassword() == null){
               try {
                   for (JRadioButton radioButton:radioButtons){
                       if (radioButton.isSelected()){
                           switch (radioButtons.indexOf(radioButton)){
                               case 0->{
                                   account.setFirstName(FirstName.getText());
                                   AccountFirstName.setText(account.getFirstName());
                                   System.out.println(radioButtons.indexOf(radioButton));
                                   FirstName.setText("");
                               }
                               case 1->{
                                   account.setLastName(LastName.getText());
                                   AccountLastName.setText(account.getLastName());
                                   System.out.println(radioButtons.indexOf(radioButton));
                                   LastName.setText("");
                               }
                               case 2->{
                                   if (!database.check(UserName.getText())){
                                       account.setUserName(UserName.getText());
                                       AccountUsername.setText(account.getUserName());
                                       System.out.println(radioButtons.indexOf(radioButton));
                                       UserName.setText("");
                                   }else {
                                       UserName.setText("Username is taken");
                                   }

                               }
                               case 3->{
                                   account.setPassword(Password.getPassword());
                                   AccountPassword.setText(String.valueOf(account.getPassword()));
                                   System.out.println(radioButtons.indexOf(radioButton));
                                   Password.setText("");
                               }
                           }
                       }
                   }
                   database.save();
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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}

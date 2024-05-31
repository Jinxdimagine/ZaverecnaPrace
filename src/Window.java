import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.List;

public class Window extends JFrame implements ActionListener {

    private Database database;

    private Account account;
    private JLabel Balance;
    private JButton PayButton,Send;
    private ArrayList<JRadioButton> radioButtons=new ArrayList<>();
    private JTextField Amount;
    private JPanel panel;
    public Window(Database database) {
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, 600, 600);
        this.setLayout(null);
        setDatabase(database);
        setAccount(database.getAccount());
        addPanel();
        this.setVisible(true);
    }

    void addPanel(){
        Balance=new JLabel();
        Balance.setText(account.getBalance()+"  Kč");
        PayButton =new JButton("New Payment");
        Balance.setFont(new Font("Arial",Font.PLAIN,60));
        PayButton.setBounds(150,100,150,50);
        Balance.setBounds(50,0,400,100);
        PayButton.addActionListener(this);
        this.add(Balance);
        this.add(PayButton);
        System.out.println(account.getHistory().toString());
    }

    void display() {
        ButtonGroup buttonGroup=new ButtonGroup();
        panel = new JPanel();
        Amount=new JTextField(15);
        Send=new JButton("Send");
        ArrayList<Account> Contacts=new ArrayList<>(database.getDatabase());
        Contacts.remove(account);
        panel.setLayout(new GridLayout(Contacts.size(), 1, 0, 0));
        for (Account account1 : Contacts) {
            JRadioButton jRadioButton=new JRadioButton(account1.toString());
            jRadioButton.addActionListener(this);
            if (!radioButtons.contains(jRadioButton)){
                radioButtons.add(jRadioButton);
                buttonGroup.add(jRadioButton);
            }
        }
        for (JRadioButton jRadioButton:radioButtons){
            panel.add(jRadioButton);
        }
        panel.setBounds(250, 100, 300, 150);
        Send.setBounds(20,100,100,50);
        Amount.setBounds(20,50,150,50);
        Send.addActionListener(this);
        this.add(Send);
        this.add(Amount);
        this.add(panel);
    }
    public boolean control(String text){
        return text.matches("^\\d+$");
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() ==PayButton){
           this.remove(Balance);
           this.remove(PayButton);
           this.repaint();
           display();
           this.setVisible(true);
       }
       if (e.getSource()==Send){
           if (control(Amount.getText())){
               int amount= Integer.parseInt(Amount.getText());
               System.out.println(amount);
               for (JRadioButton jRadioButton:radioButtons){
                   if (jRadioButton.isSelected()){
                       try {
                           if (database.send(account,amount, jRadioButton.getText())){
                               this.getContentPane().removeAll();
                               addPanel();
                               this.repaint();
                               this.setVisible(true);
                               System.out.println("payment Sucessful");
                           }else {
                               Amount.setText("Invalid number");
                           }
                       } catch (IOException ex) {
                           throw new RuntimeException(ex);
                       }
                   }else {

                   } Amount.setText("Choose Person  you want to send money");
               }
           }else {
               Amount.setText("Invalid number");
           }

       }
    }
}

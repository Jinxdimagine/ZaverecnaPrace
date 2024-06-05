import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Window extends JFrame implements ActionListener {

    private Database database;

    private Account account;
    private JButton NewPayment,Send,GoBack;
    private ArrayList<JRadioButton> radioButtons;
    private JTextField Amount;

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
    /**
     * Method addPanel will add Jlabel Balance and button NewPayment into frame.
     * Jlabel balance will show the balance on the account.
     * */
    void addPanel(){
        JLabel balance = new JLabel();
        JPanel panel=new JPanel();
        panel.setBounds(350,0,180,300);
        panel.setLayout(new GridLayout(account.getHistory().size(), 1, 0, 0));
        balance.setText(account.getBalance()+"  Kč");
        NewPayment =new JButton("New Payment");
        balance.setFont(new Font("Arial",Font.PLAIN,60));
        NewPayment.setBounds(150,100,150,50);
        balance.setBounds(50,0,400,100);
        NewPayment.addActionListener(this);
        for (Payment payment: account.getHistory()){
            JPanel panel2=new JPanel();
            panel2.setLayout(new GridLayout(1,2,0,20));
            JLabel name=new JLabel();
            JLabel amount=new JLabel();
            if (payment.getTypOfPayment()==TypOfPayment.RECEIVING){
                name.setText(payment.getSender().toString());
                amount.setText(String.valueOf(payment.getAmount()));
                amount.setForeground(Color.GREEN);
            }else {
                name.setText(payment.getReceiver().toString());
                amount.setText(String.valueOf(payment.getAmount()));
            }
            name.setHorizontalAlignment(JLabel.CENTER);
            amount.setHorizontalAlignment(JLabel.CENTER);
            panel2.add(name);
            panel2.add(amount);
            panel2.setBackground(Color.lightGray);
            panel.add(panel2);
        }
        this.add(panel);
        this.add(balance);
        this.add(NewPayment);
    }
    /**
     * Method display will add JPanel panel,JTextField Amount and Button Send.
     * Also, it will copy database into new Arraylist Contacts and remove the account that is currently used.
     * After that it will create Radiobutton and set the text in the buttons toString of Accounts.
     * Also, it will check if the Radiobutton doesn't already in the ArrayList radioButtons.
     * then it will add buttons into ButtonGroup because it allowed only  one button to be pressed.
     * After all that buttons will be added into panel and added int frame.
     * */
    void display() {
        ButtonGroup buttonGroup=new ButtonGroup();
        JPanel panel = new JPanel();
        Amount=new JTextField(15);
        Send=new JButton("Send");
        GoBack=new JButton("Go Back");
        ArrayList<Account> Contacts=new ArrayList<>(database.getDatabase());
        radioButtons=new ArrayList<>();
        Contacts.remove(account);
        panel.setLayout(new GridLayout(Contacts.size(), 1, 0, 0));
        for (Account account1 : Contacts) {
            JRadioButton jRadioButton=new JRadioButton(account1.toString());
            jRadioButton.addActionListener(this);
            jRadioButton.setBackground(Color.CYAN);
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
        GoBack.setBounds(120,100,100,50);
        Amount.setBounds(20,50,150,50);
        Send.addActionListener(this);
        GoBack.addActionListener(this);
        this.add(Send);
        this.add(Amount);
        this.add(panel);
        this.add(GoBack);
    }
    /**
     * Method control will check if the imported text only have numbers.
     * */
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
       if (e.getSource() == NewPayment){
           this.getContentPane().removeAll();
           this.repaint();
           display();
           System.out.println(radioButtons.size());
           this.setVisible(true);
       }
       if (e.getSource()==Send){
           /**
            *If button Send is pressed it will import text from Amount TextField and check if text only has numbers.
            * After it will convert Text into Integer.Then it searched for button that is selected.
            * Then it will import Account,Integer and Text into method send that return if payment was successful.
            * If payment was successful it will remove all Components and start method addPanel.
            * */
           if (control(Amount.getText())){
               int amount= Integer.parseInt(Amount.getText());
               for (JRadioButton jRadioButton:radioButtons){
                   if (jRadioButton.isSelected()){
                       try {
                           if (database.send(account,amount, jRadioButton.getText())){
                               this.getContentPane().removeAll();
                               addPanel();
                               this.repaint();
                               this.setVisible(true);
                               System.out.println("payment Successful");
                           }else {
                               System.out.println("payment Unsuccessful");
                           }
                       } catch (IOException ex) {
                           throw new RuntimeException(ex);
                       }
                   }else {
                       Amount.setText("Choose Person  you want to send money");
                   }
               }
           }else {
               Amount.setText("Invalid number");
           }

       }
       /*
         If button GoBack is press it will remove everything and go back to menu.
         */
       if (e.getSource()==GoBack){
           this.getContentPane().removeAll();
           addPanel();
           this.repaint();
           this.setVisible(true);
       }
    }
}

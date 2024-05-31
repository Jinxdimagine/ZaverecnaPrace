import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Account implements Serializable {
    private String FirstName;
    private String LastName;
    private int id;

    private String UserName;
    private char[] Password;

    private int Balance;
    private ArrayList<Payment> History=new ArrayList<>();

    Account(){
    }

    void addPayment(Payment payment,TypOfPayment typOfPayment){
        if (typOfPayment==TypOfPayment.SENDING){
            setBalance(getBalance()- payment.getAmount());
            payment.setTypOfPayment(TypOfPayment.SENDING);
        }
        else {
            setBalance(getBalance()+ payment.getAmount());
            payment.setTypOfPayment(TypOfPayment.RECEIVING);
        }
        getHistory().add(payment);
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) throws Exception{
        if (firstName.matches("^[a-zA-Z]{2,}$")){
            FirstName = firstName;
        }else {
            throw new Exception("Invalid First Name");
        }
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) throws Exception{
        if (lastName.matches("^[a-zA-Z]{2,}$")){
            LastName = lastName;
        }else {
            throw new Exception("Invalid Last Name");
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) throws Exception{
        if (userName.matches("^[a-zA-Z]{2,}$")){
            UserName = userName;
        }else {
            throw new Exception("Invalid UserName");
        }

    }

    public char[] getPassword() {
        return Password;
    }

    public void setPassword(char[] password) throws Exception{
        if (password.length >=5){
            Password = password;
        }else {
            throw new Exception("Invalid password (reguire longer than 5 characters)");
        }
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return FirstName+"  "+LastName;
    }

    public ArrayList<Payment> getHistory() {
        return History;
    }

    public void setHistory(ArrayList<Payment> history) {
        History = history;
    }
}

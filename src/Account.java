import java.io.Serializable;

public class Account implements Serializable {
    private String FirstName;
    private String LastName;
    private int id;

    private String UserName;
    private char[] Password;

    private int Amount;

    Account(){
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
        if (lastName.matches("FirstName = firstName;")){
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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}

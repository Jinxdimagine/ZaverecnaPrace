import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void addPayment() {
        Payment payment=new Payment();
        payment.setTypOfPayment(TypOfPayment.SENDING);
        Account account=new Account();
        account.addPayment(payment,TypOfPayment.SENDING);
        assertEquals(TypOfPayment.SENDING,account.getHistory().get(0).getTypOfPayment());
    }

    @Test
    void setFirstName() throws Exception {
        Account account=new Account();
        account.setFirstName("Honza");
        assertEquals("Honza",account.getFirstName());
    }

    @Test
    void setLastName() throws Exception {
        Account account=new Account();
        account.setLastName("Velky");
        assertEquals("Velky",account.getLastName());
    }

    @Test
    void setUserName() throws Exception {
        Account account=new Account();
        account.setUserName("HOnza");
        assertEquals("HOnza",account.getUserName());
    }

    @Test
    void setBalance() {
        Account account=new Account();
        account.setBalance(10000);
        assertEquals(10000,account.getBalance());
    }
}
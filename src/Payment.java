public class Payment {


    private Account sender;
    private Account receiver;
    private int amount;
    private TypOfPayment typOfPayment;

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TypOfPayment getTypOfPayment() {
        return typOfPayment;
    }

    public void setTypOfPayment(TypOfPayment typOfPayment) {
        this.typOfPayment = typOfPayment;
    }
}

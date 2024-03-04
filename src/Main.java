public class Main {
    public static void main(String[] args) {
        Account a1 = new Account("account1");
        a1.setName("newaccount1");
        a1.setAmount(Currency.RUB, 1000);
        a1.setAmount(Currency.CNY, 2000);
        a1.printCurrAmount();
    }
}

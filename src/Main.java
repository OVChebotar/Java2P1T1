public class Main {
    public static void main(String[] args) throws NothingToUndo {
        Account a1 = new Account("account1");
        a1.setAmount(Currency.RUB, 1000);
        a1.setAmount(Currency.CNY, 2000);
        a1.setAmount(Currency.USD, 3000);
        a1.setName("account2");
        a1.setTypeAccount(TypeAccount.PREMIUM);
        System.out.println(a1.getName());
        a1.printCurrAmount();
        System.out.println(a1.getTypeAccount());
        System.out.println("-------------------");
        a1.undo();
        a1.undo();
        a1.undo();
        System.out.println(a1.getName());
        a1.printCurrAmount();
        System.out.println(a1.getTypeAccount());
        System.out.println("-------------------");
        Loadable qu1 = a1.save();
        a1.setAmount(Currency.RUB, 0);
        a1.setAmount(Currency.CNY, 0);
        a1.setAmount(Currency.USD, 0);
        a1.printCurrAmount();
        System.out.println("-------------------");
        qu1.load();
        a1.printCurrAmount();
    }
}


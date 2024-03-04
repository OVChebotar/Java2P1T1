import java.util.HashMap;

enum Currency {RUB, USD, EURO, CNY}
public class Account {
    private String name;
    private HashMap<Currency, Integer> curramount;

    private Account() {}

    public Account(String name) {
        this.setName(name);
        this.curramount = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printCurrAmount() {
        this.curramount.values().stream().forEach(System.out::println);
    }

    public HashMap<Currency, Integer> getCurr() {
        return new HashMap<>(this.curramount);
    }

    public void setAmount(Currency currency, Integer val) {
        if(val < 0) throw new IllegalArgumentException();
        this.curramount.put(currency, val);
    }
}

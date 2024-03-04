import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

interface Loadable {
    void load();
}
class NothingToUndo extends Exception{}
interface Command{
    public void perform();
}
enum Currency {RUB, USD, EURO, CNY}
enum TypeAccount {ORDINARY, PREMIUM}
public class Account {
    private String name;
    private HashMap<Currency, Integer> curramount;
    private TypeAccount typeAccount;
    private Deque<Command> commands = new ArrayDeque<>();
    private class Snapshot implements Loadable {
        private String name;
        private HashMap<Currency, Integer> curramount;
        private TypeAccount typeAccount;
        private Deque<Command> commands = new ArrayDeque<>();
        public Snapshot() {
            this.name = Account.this.name;
            this.typeAccount = Account.this.typeAccount;
            this.curramount = new HashMap<>(Account.this.curramount);
        }
        @Override
        public void load() {
            Account.this.name = this.name;
            Account.this.typeAccount = this.typeAccount;
            Account.this.curramount = new HashMap<>(this.curramount);
        }
    }

    private Account() {}

    public Account(String name) {
        this(name, TypeAccount.ORDINARY);
    }

    public Account(String name, TypeAccount typeAccount) {
        this.setName(name);
        this.setTypeAccount(typeAccount);
        this.curramount = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        String oldName = this.name;
        this.commands.push(()->{this.name = oldName;});
        this.name = name;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        TypeAccount oldTypeAccount = this.typeAccount;
        this.commands.push(()->{this.typeAccount = oldTypeAccount;});
        this.typeAccount = typeAccount;
    }

    public void printCurrAmount() {
        this.curramount.values().stream().forEach(System.out::println);
    }

    public HashMap<Currency, Integer> getCurr() {
        return new HashMap<>(this.curramount);
    }

    public void setAmount(Currency currency, Integer val) {
        if(val < 0) throw new IllegalArgumentException();
        if (curramount.containsKey(currency)) {
            this.commands.push(()->{this.curramount.put(currency, val);});
        } else {
            this.commands.push(()->{this.curramount.remove(currency);});
        }
        this.curramount.put(currency, val);
    }
    public Account undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();
        return this;
    }
    public Loadable save() {return new Snapshot();}
}

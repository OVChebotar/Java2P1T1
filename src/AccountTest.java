import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getName() {
        Account account = new Account("Acc1");
        Assert.assertEquals("Acc1", account.getName());
    }

    @Test
    void setName() {
        Account account = new Account("Acc1");
        account.setName("Acc2");
        Assert.assertEquals("Acc1", account.getName());
    }

    @Test
    void getTypeAccount() {
        Account account = new Account("Acc1", TypeAccount.PREMIUM);
        Assert.assertEquals(TypeAccount.PREMIUM, account.getTypeAccount());
    }

    @Test
    void setTypeAccount() {
        Account account = new Account("Acc1", TypeAccount.PREMIUM);
        account.setTypeAccount(TypeAccount.ORDINARY);
        Assert.assertEquals(TypeAccount.ORDINARY, account.getTypeAccount());
    }

    @Test
    void setAmount() {
        Account account = new Account("Acc1");
        account.setAmount(Currency.RUB, 1000);
        Assert.assertEquals((long)account.getCurr().get(Currency.RUB), 1000);
    }

    @Test
    void undo() throws NothingToUndo {
        Account account = new Account("Qwerty");
        String oldName = account.getName();
        account.setName("ASDFG");
        account.undo();
        Assert.assertEquals(oldName, account.getName());
    }

    @Test
    void save() {
        Account account = new Account("Qwerty");
        Loadable qu1 = account.save();
        account.setName("Acc2");
        qu1.load();
        Assert.assertEquals("Acc1", account.getName());
    }
}
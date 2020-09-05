import domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CLIENT")
public class Client extends BaseEntity {

    @Column
    private  String name;

    @Column
    private  String lastname;

    @Column
    private  String email;

    @Column
    private  String password;

    @Column(name = "account_balance")
    private int accountBalance;

    public Client() {
    }

    public Client(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        accountBalance = 0;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void addMoney(int amount) {
        if (amount > 0) {
            accountBalance += amount;
        } else {
            System.out.println("Can't deposit this amount");
        }

    }

    public void pay(int amount) {
        accountBalance -= amount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return email.equals(client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

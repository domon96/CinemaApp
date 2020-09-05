import domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENTS_HISTORY")
public class PaymentsHistory extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    private int amount;

    public PaymentsHistory(Client client, int amount) {
        this.client = client;
        this.amount = amount;
    }

    public PaymentsHistory() {
    }

    public int getClientId() {
        return client.getId();
    }

    @Override
    public String toString() {
        return "PaymentsHistory{" +
                "amount=" + amount +
                '}';
    }
}

package atm.server.entity;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client_account_list")
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "accountNum")
    private String accountNum;
    @Column(name = "isoCode")
    private String isoCode;
    @Column(name = "balance")
    private int balance;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client_id;

    @OneToMany(mappedBy = "account_id")
    private Set<CardEntity> cardList;
}

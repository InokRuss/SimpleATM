package atm.server.entity;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card_list")
@NoArgsConstructor
@Getter
@Setter
public class CardEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pinCode")
    private String pinCode;
    @Column(name = "cardNum")
    private String cardNum;
    @Column(name = "expireDate")
    private Date expireDate;
    @Column(name = "cvcCode")
    private int cvcCode;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account_id;
}

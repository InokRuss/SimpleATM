package atm.server.entity;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client_list")
@NoArgsConstructor
@Getter
@Setter
public class ClientEntity {
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy = "client_id")
    private Set<AccountEntity> accountList;
}

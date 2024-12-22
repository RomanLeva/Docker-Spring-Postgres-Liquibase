package group.demoapp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long uuid;

    private Integer amount;

}

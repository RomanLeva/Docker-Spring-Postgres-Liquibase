package group.demoapp.repository;

import group.demoapp.repository.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletJpaRepo extends JpaRepository<Wallet, Long> {
    Wallet findByUuid(Long uuid);
}

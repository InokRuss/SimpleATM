package atm.server.repository;

import atm.server.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {
    Optional<CardEntity> findByCardNum(String cardNum);
}

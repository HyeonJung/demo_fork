package framework.com.example.demo.domain.holder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {
}

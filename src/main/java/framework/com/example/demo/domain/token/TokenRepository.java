package framework.com.example.demo.domain.token;

import org.apache.ibatis.session.SqlSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository  extends JpaRepository<Token, Long> {
}

package framework.com.example.demo.token.dao.repository;

import framework.com.example.demo.token.TokenInfo;
import framework.com.example.demo.token.TokenInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenInfoRepository extends JpaRepository<TokenInfo, TokenInfoPK> {
}

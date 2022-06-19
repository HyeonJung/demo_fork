package framework.com.example.demo.token.dao.repository;

import framework.com.example.demo.token.TokenInfo;
import framework.com.example.demo.token.TokenInfoPK;
import framework.com.example.demo.token.TokenMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenMappingRepository extends JpaRepository<TokenMapping, TokenInfoPK> {
}

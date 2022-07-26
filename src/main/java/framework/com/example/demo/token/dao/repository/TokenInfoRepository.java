package framework.com.example.demo.token.dao.repository;

import framework.com.example.demo.token.TokenInfo;
import framework.com.example.demo.token.TokenInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenInfoRepository extends JpaRepository<TokenInfo, TokenInfoPK> {
}

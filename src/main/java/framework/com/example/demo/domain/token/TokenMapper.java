package framework.com.example.demo.domain.token;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
        void insertToken(tokenVO vo);

        tokenVO findByTokenId(tokenVO vo);
}

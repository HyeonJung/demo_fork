package framework.com.example.demo.domain.token.tokenTransaction;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface TransactionMapper {
        void insertTransaction(TransactionVO vo);

        TransactionVO findTransactionById(TransactionVO vo);
        void updateTransaction(TransactionVO vo);

        ArrayList<TransactionVO> readTransActionAmount(String nftCode);

}

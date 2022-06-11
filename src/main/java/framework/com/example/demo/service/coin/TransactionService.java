package framework.com.example.demo.service.coin;

import framework.com.example.demo.domain.token.tokenTransaction.TransactionMapper;
import framework.com.example.demo.domain.token.tokenTransaction.TransactionVO;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.request.coin.TransactionApiRequest;
import framework.com.example.demo.model.network.response.coin.TransactionApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TransactionService {

    @Autowired
    TransactionMapper transactionMapper;

    public void create(TransactionVO vo) throws Exception {


        TransactionVO findVO = transactionMapper.findTransactionById(vo);
        if (findVO == null) {
            try {
                transactionMapper.insertTransaction(vo);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }else{
            try {
                findVO.setAmount(vo.getAmount());
                findVO.setModifiedDate(LocalDateTime.now());
                transactionMapper.updateTransaction(findVO);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    public ArrayList<TransactionVO> read(String nftCode) {
       return transactionMapper.readTransActionAmount(nftCode);
    }

    public Header<TransactionApiResponse> update(Header<TransactionApiRequest> request) {
        return null;
    }

    public Header delete(Long id) {
        return null;
    }

    private Header<TransactionApiResponse> response(TransactionVO transactionVO){
        //user -> userApiResponse
        TransactionApiResponse transactionApiResponse = TransactionApiResponse.builder()
                .nftCode(transactionVO.getNftCode())
                .date(transactionVO.getDate())
                .amount(transactionVO.getAmount())
                .build();

        return Header.OK(transactionApiResponse);
    }
}

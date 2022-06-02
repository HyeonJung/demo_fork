package framework.com.example.demo.service.coin;

import framework.com.example.demo.domain.holder.Holder;
import framework.com.example.demo.domain.holder.HolderMapper;
import framework.com.example.demo.model.dao.holder;
import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.request.coin.HolderApiRequest;
import framework.com.example.demo.model.network.response.coin.HolderApiResponse;
import framework.com.example.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class HolderApiService extends BaseService<HolderApiRequest, HolderApiResponse, Holder> {

    @Autowired
    HolderMapper holderMapper;


    @Override
    public Header<HolderApiResponse> create(Header<HolderApiRequest> request) {
        HolderApiRequest holderApiRequest = request.getData();

        holder findHolder = holderMapper.findByAddress(holderApiRequest.getAddress());
        if (findHolder == null) {
            Holder holder = Holder.builder()
                    .address(holderApiRequest.getAddress())
                    .amount(holderApiRequest.getAmount())
                    .amountHeld(holderApiRequest.getAmountHeld())
                    .build();

            Holder newHolder = baseRepository.save(holder);
            return response(newHolder);
        } else {
            Optional<Holder> optional = baseRepository.findById(findHolder.getId());

            return optional.map(holder -> {
                        if(holderApiRequest.getAmountHeld() != null ) {
                            holder.setAmountHeld(holderApiRequest.getAmountHeld());
                        }else{
                            holder.setAmountHeld(optional.get().getAmountHeld());
                        }

                        if(holderApiRequest.getChkYN() != null ) {
                            holder.setChkYN(holderApiRequest.getChkYN());
                        }else{
                            holder.setChkYN(optional.get().getChkYN());
                        }
                         return holder;
            }).map(holder-> baseRepository.save(holder))
              .map(holder->response(holder))
              .orElseGet(()->Header.ERROR("데이터 없음"));
        }
    }

    @Override
    public Header<HolderApiResponse> create(HolderApiRequest request) {

        Holder holder = Holder.builder()
                .address(request.getAddress())
                .amount(request.getAmount())
                .amountHeld(request.getAmountHeld())
                .build();

        Holder newHolder = baseRepository.save(holder);
        return response(newHolder);
    }

    @Override
    public Header<HolderApiResponse> read(Long id) {
        return null;



    }

    public Header<HolderApiResponse> read() {
        return null;
    }
    @Override
    public Header<HolderApiResponse> update(Header<HolderApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<HolderApiResponse> response(Holder holder) {
        //user -> userApiResponse
        HolderApiResponse holderApiResponse = HolderApiResponse.builder()
                .id(holder.getId())
                .address(holder.getAddress())
                .amount(holder.getAmount())
                .amountHeld(holder.getAmountHeld())
                .chkYN(holder.getChkYN())
                .build();

        return Header.OK(holderApiResponse);
    }
}

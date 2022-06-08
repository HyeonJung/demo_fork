package framework.com.example.demo.domain.holder;

import framework.com.example.demo.model.dao.holder;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface HolderMapper {
   ArrayList<holder> selectHolder();

   holder findByAddress(String address, String nftCode);

}

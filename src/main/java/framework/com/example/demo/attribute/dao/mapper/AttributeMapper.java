package framework.com.example.demo.attribute.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttributeMapper {
    List<Map<String, Object>> getAllAttributeList();
}

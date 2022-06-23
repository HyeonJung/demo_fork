package framework.com.example.demo.attribute.dao.repository;

import framework.com.example.demo.attribute.AttrDetail;
import framework.com.example.demo.attribute.DetailPK;
import framework.com.example.demo.token.FloorPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttrDetailRepository extends JpaRepository<AttrDetail, DetailPK> {
}

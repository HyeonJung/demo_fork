package framework.com.example.demo.attribute.dao.repository;

import framework.com.example.demo.attribute.AttrDetail;
import framework.com.example.demo.attribute.AttrHeader;
import framework.com.example.demo.attribute.DetailPK;
import framework.com.example.demo.attribute.HeaderPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttrHeaderRepository extends JpaRepository<AttrHeader, HeaderPK> {
}

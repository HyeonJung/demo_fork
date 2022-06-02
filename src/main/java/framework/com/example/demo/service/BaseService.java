package framework.com.example.demo.service;

import framework.com.example.demo.Interface.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

}

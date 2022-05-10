package framework.com.example.demo.service;

import framework.com.example.demo.Interface.CoinItemInterface;
import framework.com.example.demo.Interface.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class CoinBaseService<Item> implements CoinItemInterface<Item> {
}

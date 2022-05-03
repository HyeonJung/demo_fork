package framework.com.example.demo.repository;

import framework.com.example.demo.model.entity.Member;
import framework.com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepostory extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String userEmail);

    Optional<Member> findByUserid(String userId);

}

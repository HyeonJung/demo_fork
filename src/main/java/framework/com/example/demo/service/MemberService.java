package framework.com.example.demo.service;

import framework.com.example.demo.model.dto.MemberDto;
import framework.com.example.demo.service.Role.Role;
import framework.com.example.demo.model.entity.Member;
import framework.com.example.demo.repository.MemberRepostory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepostory memberRepostory;

    @Transactional
    public Long joinUser(MemberDto user) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return memberRepostory.save(user.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepostory.findByUserid(userId);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}

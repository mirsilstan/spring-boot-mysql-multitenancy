package com.mirsilstan.multitenant.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        Iterable<UserEntity> entities = userRepository.findAll(Pageable.ofSize(10));
        return StreamSupport.stream(entities.spliterator(), false)
                .map(entity -> User.builder()
                        .username(entity.getUsername())
                        .email(entity.getEmail())
                        .phone(entity.getPhone())
                        .build()
                )
                .collect(Collectors.toList());
    }
}

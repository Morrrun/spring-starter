package org.alexsandrov.spring.service;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.alexsandrov.spring.dto.UserCreateEditDto;
import org.alexsandrov.spring.dto.UserReadDto;
import org.alexsandrov.spring.mapper.UserCreateEditMapper;
import org.alexsandrov.spring.mapper.UserReadMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long userId) {
        return userRepository.findById(userId)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long userId, UserCreateEditDto userDto) {
        return userRepository.findById(userId)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long userId) {
        return userRepository.findById(userId)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }



}

package com.example.rsasignature;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyPairRepository extends JpaRepository<KeyPairEntity, Long> {
}
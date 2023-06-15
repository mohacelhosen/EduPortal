package com.mohacel.edu.repository;

import com.mohacel.edu.model.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Integer> {
}

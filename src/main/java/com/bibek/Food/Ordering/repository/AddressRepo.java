package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}

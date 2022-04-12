package com.pcapi.repositories;

import com.pcapi.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop,Long> {



}

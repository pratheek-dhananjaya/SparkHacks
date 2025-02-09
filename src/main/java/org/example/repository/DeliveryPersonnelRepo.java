package org.example.repository;

import org.example.entity.DeliveryPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonnelRepo extends JpaRepository<DeliveryPersonnel, Integer> {

    DeliveryPersonnel findByUin(Integer uin);


}

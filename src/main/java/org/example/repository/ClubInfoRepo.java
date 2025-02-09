package org.example.repository;

import org.example.entity.ClubInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubInfoRepo extends JpaRepository<ClubInfo, Integer> {

    @Query(value = "SELECT * FROM ClubInfo WHERE clubname = ?1", nativeQuery = true)
    List<ClubInfo> findAllByClubname(String clubname);

    List<ClubInfo> findAllByClubnameNotContaining(String clubname);

    @Query(value = "SELECT price FROM clubinfo WHERE productname = ?2 AND clubname = ?1", nativeQuery = true)
    double findAllByClubnameAndProductname(String clubname, String productname);
}

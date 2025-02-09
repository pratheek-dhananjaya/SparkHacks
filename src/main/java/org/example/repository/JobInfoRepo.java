package org.example.repository;

import org.example.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobInfoRepo extends JpaRepository<JobInfo, Integer> {

    @Query("SELECT j FROM JobInfo j WHERE DATE(j.createdAt) = CURRENT_DATE AND j.islisted = 1")
    List<JobInfo> findTodayListedJobs();

    @Query(value = "SELECT * FROM JobInfo WHERE driverid = ?1", nativeQuery = true)
    List<JobInfo> findByDriverid(Integer id);

    @Query(value = "SELECT * FROM JobInfo WHERE clubname = ?1", nativeQuery = true)
    List<JobInfo> findAllByClubname(String clubname);

    @Query(value = "SELECT * FROM JobInfo WHERE productname = ?2 AND clubname = ?1", nativeQuery = true)
    JobInfo changeValues(String clubname, String productname);

    JobInfo findByClubnameAndProductname(String clubname, String productname);
}

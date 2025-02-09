package org.example.service;

import org.example.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryService {
    DeliveryPersonnelBody registerDriver(DeliveryPersonnelBody deliveryPersonnelBody);

    List<JobInfo> dashboardDelivery(Integer uin);

    JobInfo jobCreation(JobInfo jobInfo);

    List<ClubInfo> dashboardClubs(String clubname);

    ClubInfo clubCreation(ClubInfo clubInfo);

    List<ClubInfo> dashboardBuyer(String clubname);

    double deliveryConsignment(ClubBody clubBody);
}

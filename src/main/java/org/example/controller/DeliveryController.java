package org.example.controller;
import org.example.entity.*;
import org.example.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping("/registerDeliveryPersonnel")
    public DeliveryPersonnelBody registerDeliveryPersonnel(@RequestBody DeliveryPersonnelBody deliveryPersonnelBody) {

        DeliveryPersonnelBody deliveryPersonnelBody1 = new DeliveryPersonnelBody();
        try {
            deliveryPersonnelBody1 = deliveryService.registerDriver(deliveryPersonnelBody);
            return deliveryPersonnelBody1;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/dashboardDelivery")
    public List<JobInfo> dashboardDelivery(@RequestBody DeliveryPersonnel deliveryPersonnel) {
        List<JobInfo> jobInfos = new ArrayList<>();
        try {
            jobInfos = deliveryService.dashboardDelivery(deliveryPersonnel.getUin());
            return jobInfos;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/jobCreation")
    public JobInfo jobCreation(@RequestBody JobInfo jobInfo) {
        JobInfo jobInfo1 = new JobInfo();
        try {
            jobInfo1 = deliveryService.jobCreation(jobInfo);
            return jobInfo1;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/dashboardClubs")
    public List<ClubInfo> dashboardClubs(@RequestBody ClubBody clubBody) {
        List<ClubInfo> clubInfos = new ArrayList<>();
        try {
            clubInfos = deliveryService.dashboardClubs(clubBody.getClubname());
            return clubInfos;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/clubProductCreation")
    public ClubInfo ClubProductCreation(@RequestBody ClubInfo clubInfo) {
        ClubInfo clubInfo1 = new ClubInfo();
        try {
            clubInfo1 = deliveryService.clubCreation(clubInfo);
            return clubInfo1;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/dashboardBuyer")
    public List<ClubInfo> dashboardBuyer(@RequestBody ClubBody clubBody) {
        List<ClubInfo> clubInfos = new ArrayList<>();
        try {
            clubInfos = deliveryService.dashboardBuyer(clubBody.getClubname());
            return clubInfos;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deliveryConsignment")
    public String DeliveryConsignment(@RequestBody ClubBody clubBody) {
        try {
            double total = deliveryService.deliveryConsignment(clubBody);
            return "Total cost = " + total + "\n Thank you for using our service";
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package org.example.service.impl;

import org.example.entity.*;
import org.example.repository.ClubInfoRepo;
import org.example.repository.DeliveryPersonnelRepo;
import org.example.repository.JobInfoRepo;
import org.example.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryPersonnelRepo deliveryPersonnelRepo;

    @Autowired
    JobInfoRepo jobInfoRepository;

    @Autowired
    private ClubInfoRepo clubInfoRepo;

//    @Autowired
//    DeliveryPersonnel deliveryPersonnel;


    @Override
    public DeliveryPersonnelBody registerDriver(DeliveryPersonnelBody deliveryPersonnelBody) {
        DeliveryPersonnel deliveryPersonnel = new DeliveryPersonnel();
        try {
            deliveryPersonnel.setName(deliveryPersonnelBody.getName());
            deliveryPersonnel.setUin(deliveryPersonnelBody.getUin());
            deliveryPersonnel.setVehicletype(deliveryPersonnelBody.getVehicletype());
            deliveryPersonnel.setPhonenumber(deliveryPersonnelBody.getPhonenumber());
            deliveryPersonnelRepo.save(deliveryPersonnel);
            return deliveryPersonnelBody;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<JobInfo> dashboardDelivery(Integer uin) {
        try {
            if (deliveryPersonnelRepo.findByUin(uin).getIsactive()==Boolean.FALSE) {
                return jobInfoRepository.findTodayListedJobs();
            }
            else {
                System.out.println(deliveryPersonnelRepo.findByUin(uin).getId());
                return jobInfoRepository.findByDriverid(deliveryPersonnelRepo.findByUin(uin).getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JobInfo jobCreation(JobInfo jobInfo) {
        JobInfo jobInfo1 = new JobInfo();
        if (jobInfoRepository.findByClubnameAndProductname(jobInfo.getClubname(), jobInfo.getProductname()) != null)
            jobInfo1 = jobInfoRepository.findByClubnameAndProductname(jobInfo.getClubname(), jobInfo.getProductname());
        try {
            jobInfo1.setProductname(jobInfo.getProductname());
            jobInfo1.setDeliverycost(jobInfo.getDeliverycost());
            jobInfo1.setDeliveryaddress(jobInfo.getDeliveryaddress());
            jobInfo1.setProductaddress(jobInfo.getProductaddress());
            jobInfo1.setIslisted(1);
            jobInfoRepository.save(jobInfo1);
            return jobInfo1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClubInfo> dashboardClubs(String clubname) {
        try {
            return clubInfoRepo.findAllByClubname(clubname);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClubInfo clubCreation(ClubInfo clubInfo) {
        ClubInfo clubInfo1 = new ClubInfo();
        JobInfo jobInfo = new JobInfo();
        try {
            clubInfo1.setClubname(clubInfo.getClubname());
            clubInfo1.setProductname(clubInfo.getProductname());
            clubInfo1.setPrice(clubInfo.getPrice());
            clubInfo1.setAddress(clubInfo.getAddress());
            clubInfoRepo.save(clubInfo1);
            jobInfo.setClubname(clubInfo.getClubname());
            jobInfo.setProductname(clubInfo.getProductname());
            jobInfo.setIslisted(0);
            jobInfo.setDeliveryaddress(" ");
            jobInfo.setProductaddress(" ");
            jobInfo.setDeliverycost(0);
            jobInfoRepository.save(jobInfo);
            return clubInfo1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClubInfo> dashboardBuyer(String clubname) {
        try {
            return clubInfoRepo.findAllByClubnameNotContaining(clubname);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double deliveryConsignment(ClubBody clubBody) {
        deliveryPersonnelRepo.findByUin(Integer.valueOf(clubBody.getUin())).setIsactive(Boolean.TRUE);
        JobInfo jobInfo = jobInfoRepository.changeValues(clubBody.getClubname(), clubBody.getProductname());
        jobInfo.setIslisted(0);
        jobInfo.setDriverid(deliveryPersonnelRepo.findByUin(Integer.valueOf(clubBody.getUin())).getId());
        jobInfoRepository.save(jobInfo);
        return jobInfo.getDeliverycost()+clubInfoRepo.findAllByClubnameAndProductname(clubBody.getClubname(), clubBody.getProductname());
    }
}

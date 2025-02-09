package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DeliveryPersonnelBody {

    private String name;

    private String phonenumber;

    private String vehicletype;

    private Integer uin;

}

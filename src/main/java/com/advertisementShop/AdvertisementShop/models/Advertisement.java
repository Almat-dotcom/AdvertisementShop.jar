package com.advertisementShop.AdvertisementShop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_advertisement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "picUrl")
    private String picUrl;

    @Column(name = "t_started")
    private Boolean started;

    @Column(name = "t_time_started")
    private Date timeStarted;

    @Column(name = "active")
    private Boolean isActive;

    @Column(name = "t_temp_price")
    private Long tempPrice;

    @OneToOne
    private Users futureOwner;
}

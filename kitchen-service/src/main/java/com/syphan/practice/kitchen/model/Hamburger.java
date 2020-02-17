package com.syphan.practice.kitchen.model;

import com.syphan.pratice.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "hamburgers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hamburger extends BaseEntity {

    @Column(name = "hamburger_type", length = 10)
    private String hamburgerType;

    private int quantity;

    private BigDecimal price;
}

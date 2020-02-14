package com.syphan.practice.house.model;

import com.syphan.pratice.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saga_logger")
@Builder
public class SagaLogger extends BaseEntity {

    @NotNull
    @Column(name = "transaction_id")
    private String transactionId;

    @NotNull
    @Column(name = "class_name")
    private String className;

    @Column(name = "data_json", columnDefinition = "json")
    private String dataJson;

    @NotNull
    @Column(name = "operation")
    private String operation;


}

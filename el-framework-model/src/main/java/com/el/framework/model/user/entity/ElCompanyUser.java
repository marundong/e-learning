package com.el.framework.model.user.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * el_company_user
 * @author 
 */
@Table(name = "el_company_user")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
@ToString
public class ElCompanyUser implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String companyId;

    private String userId;

    private static final long serialVersionUID = 1L;

}
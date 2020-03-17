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
import java.util.Date;

/**
 * el_role
 * @author mrd
 */
@Table(name = "el_role")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
@ToString
public class ElRole implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String roleName;

    private String roleCode;

    private String description;

    private Date createTime;

    private Date updateTime;

    private String status;

    private static final long serialVersionUID = 1L;

}
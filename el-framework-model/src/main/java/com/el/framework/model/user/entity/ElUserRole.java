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
 * el_user_role
 * @author mrd
 */
@Table(name = "el_user_role")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
@ToString
public class ElUserRole implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String userId;

    private String roleId;

    private Date createTime;

    private String creator;

    private static final long serialVersionUID = 1L;
}
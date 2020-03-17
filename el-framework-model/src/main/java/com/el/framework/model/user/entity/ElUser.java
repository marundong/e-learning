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
 * el_user
 * @author mrd
 */
@Table(name = "el_user")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
@ToString
public class ElUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String username;

    private String password;

    private String salt;

    private String name;

    /**
     * 头像
     */
    private String userpic;

    private String utype;

    private Date birthday;

    private String gender;

    private String email;

    private String phone;

    private String qq;

    /**
     * 用户状态
     */
    private String status;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;


}
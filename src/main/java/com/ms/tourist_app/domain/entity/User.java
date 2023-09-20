package com.ms.tourist_app.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.tourist_app.application.constants.AppStr;
import com.ms.tourist_app.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = AppStr.User.tableUser)
public class User extends BaseEntity {

    @Column(name = AppStr.User.firstName)
    @Nationalized
    private String name;





    @Column(name = AppStr.User.email)
    private String email;


    @Column(name = AppStr.User.password)
    private String password;

    @ManyToMany(mappedBy = AppStr.User.tableUser)
    @JsonIgnore
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "id_department")
    @JsonIgnore
    private Department department;


    public User(String email, String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
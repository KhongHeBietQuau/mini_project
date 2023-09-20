package com.ms.tourist_app.domain.entity;

import com.ms.tourist_app.application.constants.AppStr;
import com.ms.tourist_app.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = AppStr.Department.tableDepartment)
public class Department extends BaseEntity {
    @Column(name = "name_department")
    @Nationalized
    private String nameDepartment;

    @OneToMany(mappedBy = "department")
    private List<User> users;
}

package com.ms.tourist_app.domain.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.tourist_app.application.constants.AppStr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @JsonProperty(AppStr.Base.id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

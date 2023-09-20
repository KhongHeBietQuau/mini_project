package com.ms.tourist_app.application.mapper;

import com.ms.tourist_app.application.constants.AppStr;
import com.ms.tourist_app.application.input.departments.DepartmentDataInput;
import com.ms.tourist_app.application.output.departments.DepartmentDataOutput;
import com.ms.tourist_app.application.output.users.UserDataOutput;
import com.ms.tourist_app.domain.entity.Department;
import com.ms.tourist_app.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mappings(
            {
                    @Mapping(target = "nameDepartment",source = "input.nameDepartment"),
                    @Mapping(target = "id",source = "id")
            }
    )
    Department toDepartment(DepartmentDataInput input,Long id);

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "nameDepartment",source = "nameDepartment"),
    })
    DepartmentDataOutput toDepartmentDataOutput(Department department);
}

package com.ms.tourist_app.application.service;

import com.ms.tourist_app.application.input.departments.DepartmentDataInput;
import com.ms.tourist_app.application.input.departments.GetListDepartmentInput;
import com.ms.tourist_app.application.output.departments.DepartmentDataOutput;
import jdk.dynalink.linker.LinkerServices;

import java.awt.print.Pageable;
import java.util.List;

public interface DepartmentService {
    DepartmentDataOutput creatDepartment(DepartmentDataInput input);
    DepartmentDataOutput updateDepartment(DepartmentDataInput input,Long id);
    DepartmentDataOutput deleteDepartment(Long id);
    List<DepartmentDataOutput> getDepartment(GetListDepartmentInput input);
}

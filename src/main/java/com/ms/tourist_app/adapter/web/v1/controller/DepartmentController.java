package com.ms.tourist_app.adapter.web.v1.controller;

import com.ms.tourist_app.adapter.web.base.ResponseUtil;
import com.ms.tourist_app.adapter.web.base.RestApiV1;
import com.ms.tourist_app.application.constants.UrlConst;
import com.ms.tourist_app.application.input.departments.DepartmentDataInput;
import com.ms.tourist_app.application.input.users.UserDataInput;
import com.ms.tourist_app.application.output.departments.DepartmentDataOutput;
import com.ms.tourist_app.application.output.users.UserDataOutput;
import com.ms.tourist_app.application.service.DepartmentService;
import com.ms.tourist_app.application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestApiV1
public class DepartmentController {
    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
   // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDataInput input) {

        DepartmentDataOutput output = departmentService.creatDepartment(input);

        return ResponseUtil.restSuccess(output);
    }
}

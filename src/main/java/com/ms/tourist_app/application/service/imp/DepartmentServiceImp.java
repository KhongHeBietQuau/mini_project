package com.ms.tourist_app.application.service.imp;

import com.ms.tourist_app.application.constants.AppStr;
import com.ms.tourist_app.application.dai.DepartmentRepository;
import com.ms.tourist_app.application.dai.UserRepository;
import com.ms.tourist_app.application.input.departments.DepartmentDataInput;
import com.ms.tourist_app.application.input.departments.GetListDepartmentInput;
import com.ms.tourist_app.application.mapper.DepartmentMapper;
import com.ms.tourist_app.application.output.departments.DepartmentDataOutput;
import com.ms.tourist_app.application.service.DepartmentService;
import com.ms.tourist_app.config.exception.BadRequestException;
import com.ms.tourist_app.config.exception.NotFoundException;
import com.ms.tourist_app.domain.entity.Department;
import com.ms.tourist_app.domain.entity.User;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentServiceImp(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DepartmentDataOutput creatDepartment(DepartmentDataInput input) {
        Department oldDepartment = departmentRepository.findByNameDepartment(input.getNameDepartment());
        if (oldDepartment != null) {
            throw new BadRequestException(AppStr.Department.tableDepartment + AppStr.Base.whiteSpace + AppStr.Exception.duplicate);
        }
        Department department = departmentMapper.toDepartment(input,null);


        departmentRepository.save(department);
        return new DepartmentDataOutput(department.getId(),department.getNameDepartment());
    }

    @Override
    public DepartmentDataOutput updateDepartment(DepartmentDataInput input, Long id) {
        return null;
    }

    @Override
    public DepartmentDataOutput deleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            throw new NotFoundException(AppStr.User.user + AppStr.Base.whiteSpace + AppStr.Exception.notFound);
        }
        List<User> listUser = department.get().getUsers();
        for (int i=0 ;i<listUser.size();i++){
            User user = listUser.get(i);
            user.setDepartment(null);
            userRepository.save(user);
        }
    DepartmentDataOutput departmentDataOutput = departmentMapper.toDepartmentDataOutput(department.get());

        return departmentDataOutput;
    }

    @Override
    public List<DepartmentDataOutput> getDepartment(GetListDepartmentInput input) {
        return null;
    }
}

package com.ms.tourist_app.application.service.imp;

import com.ms.tourist_app.application.constants.AppStr;
import com.ms.tourist_app.application.dai.*;
import com.ms.tourist_app.application.input.users.UserDataInput;
import com.ms.tourist_app.application.input.users.GetListUserInput;
import com.ms.tourist_app.application.mapper.UserMapper;
import com.ms.tourist_app.application.output.users.UserDataOutput;
import com.ms.tourist_app.application.service.UserService;
import com.ms.tourist_app.application.utils.JwtUtil;
import com.ms.tourist_app.config.exception.BadRequestException;
import com.ms.tourist_app.config.exception.NotFoundException;
import com.ms.tourist_app.domain.entity.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    private final JwtUtil jwtUtil;


    public UserServiceImp(UserRepository userRepository,
                        RoleRepository roleRepository,  JwtUtil jwtUtil) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;

        this.jwtUtil = jwtUtil;
    }


    private boolean checkUserEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;

    }


    private boolean checkUserExists(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent();
    }

    @Override
    @Transactional
    public UserDataOutput createUser(UserDataInput input) {

        if (checkUserEmailExists(input.getEmail())) {
            throw new BadRequestException(AppStr.User.email + AppStr.Base.whiteSpace + AppStr.Exception.duplicate);
        }
        User user = userMapper.toUser(input,null);


        userRepository.save(user);
        return new UserDataOutput(user.getId(),user.getName(), user.getEmail(), user.getPassword());
    }
    @Override
    @Transactional
    public List<UserDataOutput> getListUserOutPut(GetListUserInput input) {

        List<User> users = new ArrayList<>();
        if(input.getKeyword().trim().isBlank()){
            users = userRepository.findAll(PageRequest.of(input.getPage(), input.getSize())).getContent();
        }
        if(!input.getKeyword().trim().isBlank()){
            users = userRepository.search(input.getKeyword().trim(),PageRequest.of(input.getPage(), input.getSize()));
        }
        List<UserDataOutput> userDataOutputs = new ArrayList<>();
        for (User u : users) {
            UserDataOutput userDataOutput = userMapper.toUserDataOutput(u);

            userDataOutputs.add(userDataOutput);
        }
        return userDataOutputs;
    }

    @Override
    @Transactional
    public UserDataOutput getUserDataOutput(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!checkUserExists(id)) {
            throw new NotFoundException(AppStr.User.user + AppStr.Base.whiteSpace + AppStr.Exception.notFound);
        }
        UserDataOutput userDataOutput = userMapper.toUserDataOutput(user.get());

        return userDataOutput;
    }

    @Override
    @Transactional
    public UserDataOutput editUser(Long id, UserDataInput input) {
        if (!checkUserExists(id)) {
            throw new NotFoundException(AppStr.User.user + AppStr.Base.whiteSpace + AppStr.Exception.notFound);
        }
        User user = userMapper.toUser(input,id);

        user.setId(id);

        userRepository.save(user);
        UserDataOutput userDataOutput = userMapper.toUserDataOutput(user);

        return userDataOutput;
    }

    @Override
    @Transactional
    public UserDataOutput deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!checkUserExists(id)) {
            throw new NotFoundException(AppStr.User.user + AppStr.Base.whiteSpace + AppStr.Exception.notFound);
        }
        for (Role role : user.get().getRoles()) {
            role.getUsers().remove(user.get());
        }
        user.get().getRoles().clear();
        userRepository.delete(user.get());
        UserDataOutput userDataOutput = userMapper.toUserDataOutput(user.get());

        return userDataOutput;
    }





}

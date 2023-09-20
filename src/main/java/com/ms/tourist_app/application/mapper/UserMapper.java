package com.ms.tourist_app.application.mapper;



import com.ms.tourist_app.application.input.users.UserDataInput;
import com.ms.tourist_app.application.output.users.UserDataOutput;
import com.ms.tourist_app.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "name",source = "input.name"),
            @Mapping(target = "email",source = "input.email"),
            @Mapping(target = "id",source = "id")
    })
    User toUser(UserDataInput input, Long id);





    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "email",source = "email"),
    })
    UserDataOutput toUserDataOutput(User user);


}

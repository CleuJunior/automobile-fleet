//package com.automobilefleet.util.mapper;
//
//import com.automobilefleet.api.request.UserRequest;
//import com.automobilefleet.api.response.UserResponse;
//import com.automobilefleet.entities.User;
//import com.automobilefleet.enums.RoleType;
//import lombok.experimental.UtilityClass;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@UtilityClass
//public class UserMapperUtils {
//
//    public static User toUser(UserRequest request) {
//        return new User(
//                request.getUsername(),
//                new BCryptPasswordEncoder().encode(request.getPassword()),
//                RoleType.ROLE_USER
//        );
//    }
//
//    public static UserResponse toUserResponse(User user) {
//        return new UserResponse(user.getId(), user.getUsername());
//    }
//}

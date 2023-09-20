package com.ms.tourist_app.application.constants;

public class AppStr {
    private AppStr() {

    }


    public static final class Exception {
        public static final String duplicate = "Duplicate";
        public static final String notFound = "Not found";
        public static final String forbidden = "You are not logged in";

        public Exception() {
        }
    }

    public static final class Base {
        public static final String whiteSpace = " ";
        public static final String dash = "-";
        public static final String id = "id";
        public static final String createBy = "createBy";
        public static final String createAt = "createAt";
        public static final String updateBy = "updateBy";
        public static final String updateAt = "updateAt";
        public static final String status = "status";

        public Base() {
        }
    }

    public static final class User {
        public User() {
        }

        public static final String tableUser = "users";
        public static final String user = "User";
        public static final String firstName = "first_name";
        public static final String lastName = "last_name";
        public static final String dateOfBirth = "date_of_birth";
        public static final String address = "address";
        public static final String telephone = "telephone";
        public static final String email = "email";
        public static final String password = "password";
        public static final String favoriteUser = "favoriteUsers";
        public static final String joinTableUser = "user";
        public static final String idAddress = "id_address";
        public static final String idDestination = "id_destination";
        public static final String userFavDestination = "This destination already existed in your list of favorite";
        public static final String notFoundFavoriteDestination = "This destination" + AppStr.Base.whiteSpace + AppStr.Exception.notFound + AppStr.Base.whiteSpace + "in your list of favorite";
    }
public static final class Department{
    public Department() {
    }

    public static final String tableDepartment = "department";
}



    public static final class Role {
        public Role() {
        }

        public static final String tableRole = "role";
        public static final String name = "name";
        public static final String joinTableUserRole = "user_role";
        public static final String userRole = "ROLE_USER";
        public static final String adminRole = "ROLE_ADMIN";
    }




    public static final class Response {
        public Response() {
        }

        public static final String getDataSuccess = "Get data success";
        public static final String getDataError = "Get data error";
        public static final String uploadImageFailed = "Upload image failed";
    }

    public static final class Auth {
        public static final String authorization = "Authorization";
        public static final String bearer = "Bearer";
        public static final String inCorrectLogin = "Incorrect user name or password";
        public static final String emailDuplicate = "Email duplicate";
        public static final String notFoundUser = "Not found user";
    }



    public static final class AuthorityConstant {
        public AuthorityConstant() {
        }

        public static final String anonymousUser = "anonymous";
        public static final String claimUUID = "username";
        public static final String claimId = "id";
        public static final String refreshToken = "refresh_token";
    }



}

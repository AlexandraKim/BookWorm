package Utils;

import Persistence.UserEntity;

public class Auth {
    private static UserEntity _user;

    Auth(){}

    public static void setUser(UserEntity user){
        _user = user;
    }

    public static UserEntity getUser(){
        return _user;
    }
}

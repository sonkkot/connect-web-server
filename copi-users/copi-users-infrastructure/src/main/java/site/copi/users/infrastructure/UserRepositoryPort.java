package site.copi.users.infrastructure;

import site.copi.users.infrastructure.model.UserModel;

public interface UserRepositoryPort {
    UserModel load(String oAUth2Id);

    UserModel loadOrDefault(String oAUth2Id);

    void save(UserModel userModel);
}
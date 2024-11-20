package site.copi.users.infrastructure.adapter.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.copi.users.infrastructure.UserRepositoryPort;
import site.copi.users.infrastructure.model.UserModel;

@Service
@RequiredArgsConstructor
class UserOutputService implements UserRepositoryPort {
    private final UserJpaRepository userJpaRepository;
    private final UserModelMapper userModelMapper;

    @Override
    public UserModel load(String oAUth2Id) {
        return userModelMapper.convert(
            userJpaRepository.loadByOAuth2Id(oAUth2Id)
        );
    }

    @Override
    public UserModel loadOrDefault(String oAUth2Id) {
        return userModelMapper.convert(
            userJpaRepository.findByOAuth2Id(oAUth2Id)
                .orElse(UserJpaEntity.init())
        );
    }

    @Override
    public void save(UserModel userModel) {
        userJpaRepository.save(
            userModelMapper.convert(userModel)
        );
    }
}
package uz.muhammad.jira.services.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.UserRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.UserCreateVO;
import uz.muhammad.jira.vo.auth.UserUpdateVO;
import uz.muhammad.jira.vo.auth.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ErrorVO;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class UserService extends AbstractRepository<UserRepository, BaseMapper> implements
        GenericCRUDService<UserVO, UserCreateVO, UserUpdateVO, UserCriteria, Long> {

    private static UserService instance;


    private UserService(UserRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull UserCreateVO dto) {
        User user = new User();
        Optional<User> userOptional = repository.findByUsername(dto.getUserName());
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("User Name '%s' already taken".formatted(dto.getUserName()))
                    .status(400)
                    .build()));
        }

        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        repository.create(user);

        return new ResponseEntity<>(new Data<>(user.getId()));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> update(@NonNull UserUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<UserVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<UserVO>>> findAll(@NonNull UserCriteria criteria) {

        List<UserVO> userList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(UserVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(userList, userList.size()));
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(
                    ApplicationContextHolder.getBean(UserRepository.class),
                    ApplicationContextHolder.getBean(BaseMapper.class)
            );
        }
        return instance;
    }

}

package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.mappers.UserMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.UserRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.userVO.UserCreateVO;
import uz.muhammad.jira.vo.auth.userVO.UserUpdateVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ErrorVO;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */

public class UserService extends AbstractRepository<UserRepository, UserMapper> implements
        GenericCRUDService<UserVO, UserCreateVO, UserUpdateVO, UserCriteria, Long> {

    private static UserService instance;

    private UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull UserCreateVO dto) {
        Optional<User> userOptional = repository.findByUsername(dto.getUserName());
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("User Name '%s' already taken".formatted(dto.getUserName()))
                    .status(400)
                    .build()));
        }
        UserVO userVO = new UserVO();
        userVO.setPassword(dto.getPassword());
        userVO.setUserName(dto.getUserName());

        repository.create(mapper.getUser(userVO));

        return new ResponseEntity<>(new Data<>(userVO.getId()));
    }

    @Override
    public ResponseEntity<Data<String>> delete(@NonNull Long id) {
        Optional<User> userOptional = repository.findById(id);

        ResponseEntity<Data<String>> response;

        if (userOptional.isEmpty()){
            return new ResponseEntity<>(new Data<>(new ErrorVO("ID nor found", "ID not found", 500)));
        }
        UserVO userVO = mapper.getUserVO(userOptional.get());
        repository.deleteByID(mapper.getUser(userVO).getId());
        Data<String> data = new Data<>("User deleted");
         response = new ResponseEntity<>(data);
        return response;
    }

    @Override
    public ResponseEntity<Data<String>> update(@NonNull UserUpdateVO dto) {

        Optional<User> userOptional = repository.findById(dto.getId());

        if (userOptional.isEmpty()){
            return new ResponseEntity<>(new Data<>(new ErrorVO("ID not found", "ID not found", 500)));
        }

        UserVO userVO = mapper.getUserVO(userOptional.get());
        userVO.setUserName(dto.getUserName());
        userVO.setPassword(dto.getPassword());
        userVO.setUpdatedAt(LocalDateTime.now());
        repository.update(mapper.getUser(userVO));
        Data<String> data = new Data<>("User updated");
        ResponseEntity<Data<String>> response = new ResponseEntity<>(data);

        return response;

    }


    @Override
    public ResponseEntity<Data<UserVO>> findById(@NonNull Long id) {

        Optional<User> userOptional = repository.findById(id);

        ResponseEntity<Data<UserVO>> response;
        if (userOptional.isPresent()){
            response = new ResponseEntity<>(new Data<>(mapper.getUserVO(userOptional.get())));
            return response;
        }
        ErrorVO errorVO = new ErrorVO("User not found", "User not found", 400);
        response = new ResponseEntity<>(new Data<>(errorVO));
        return response;
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
                    ApplicationContextHolder.getBean(UserMapper.class));
        }
        return instance;
    }

}

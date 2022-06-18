package uz.muhammad.jira.mappers;

import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.vo.auth.userVO.UserVO;


import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   10:23   (Thursday)
 */
public class UserMapper implements BaseMapper {

    private static UserMapper instance;
    public  User getUser(UserVO userVO){
        User user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
        user.setId(System.currentTimeMillis());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public  UserVO getUserVO(User user){
        UserVO userVO = new UserVO();
        userVO.setUserName(user.getUserName());
        userVO.setPassword(user.getPassword());
        userVO.setId(System.currentTimeMillis());
        userVO.setCreatedAt(LocalDateTime.now());
        return userVO;
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }


}

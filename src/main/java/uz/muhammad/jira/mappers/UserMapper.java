package uz.muhammad.jira.mappers;

import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.vo.auth.UserVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   10:23   (Thursday)
 */
public class UserMapper implements BaseMapper {
    public static User getUser(UserVO userVO){
        User user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
        user.setId(System.currentTimeMillis());
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

}

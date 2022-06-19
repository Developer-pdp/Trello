package uz.muhammad.jira.utils;

import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.services.auth.UserService;
import uz.muhammad.jira.ui.MainMenu;
import uz.muhammad.jira.vo.auth.userVO.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22   18:28   (Friday)
 */
public class Tools {

    private final static UserService userService = ApplicationContextHolder.getBean(UserService.class);

    public static void clear(){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static boolean isUniqueEmail(String email) {

        ResponseEntity<Data<List<UserVO>>> all = userService.findAll(new UserCriteria());

        for (UserVO userVO : all.getData().getBody()) {
            if (userVO.getEmail().equals(email)){
                return false;
            }
        }

        return true;

    }

    public static void userInfo(String username){
        Writer.printlnRight(username, 80, ' ', Color.PURPLE);
        Writer.println("");
    }

    public static void userInfo(String username, String role){
        Writer.printlnRight(username+" (" + role + ")", 80, ' ', Color.PURPLE);
        Writer.println("");
    }
    public static Map<Long, AtomicLong> increments = new HashMap<>();

    public static Long getId(Long id) {
        if (increments.get(id)==null){
            increments.put(id, new AtomicLong(1));
        }
        return increments.get(id).getAndIncrement();
    }
}

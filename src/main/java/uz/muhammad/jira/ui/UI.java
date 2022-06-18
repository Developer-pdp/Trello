package uz.muhammad.jira.ui;

import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.services.auth.UserService;
import uz.muhammad.jira.utils.Color;
import uz.muhammad.jira.utils.Reader;
import uz.muhammad.jira.utils.Writer;
import uz.muhammad.jira.vo.auth.userVO.UserCreateVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.List;
import java.util.Scanner;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   11:31   (Thursday)
 */

public class UI {

    private final static UserService userService = ApplicationContextHolder.getBean(UserService.class);

    public static Session session = new Session();

    public static void main(String[] args) {
        Writer.println("Sign up -> 1");
        Writer.println("Sign in -> 2");
        String choice = new Scanner(System.in).next();
        if (choice.equals("1")) signUp();
        else if (choice.equals("2")) signIn();
        else System.exit(0);
        main(args);
    }

    private static void signIn() {
        Session session = new Session();
        UserVO.UserVOBuilder builder = UserVO.childBuilder();
        builder.userName(Reader.readLine("Username : "));
        builder.password(Reader.readLine("Password : "));
        UserVO userVO = builder.build();
        ResponseEntity<Data<List<UserVO>>> responseData = userService.findAll(new UserCriteria());
        Data<List<UserVO>> data = responseData.getData();

        for (UserVO vo : data.getBody()) {
            if (vo.getUserName().equals(userVO.getUserName()) && vo.getPassword().equals(userVO.getPassword())) {
                session.setUserName(vo.getUserName());
                session.setPassword(vo.getPassword());
                if (data.isSuccess()) {
                    MainMenu.control();
                    Writer.println(responseData.getData().getBody(), Color.GREEN);
                    return;
                }
            }
        }
        Writer.println(data.getError(), Color.RED);

    }

    /**
     * UI method for getting user list
     */
    private static void userList() {
        ResponseEntity<Data<List<UserVO>>> responseData = userService.findAll(new UserCriteria());
        Data<List<UserVO>> data = responseData.getData();
        if (data.isSuccess()) {
            Writer.println(responseData.getData(), Color.GREEN);
        } else {
            Writer.println(data.getError(), Color.RED);
        }
    }


    /**
     * UI method for creating user
     */
    private static void signUp() {

        UserCreateVO.UserCreateVOBuilder builder = UserCreateVO.builder();
        builder.userName(Reader.readLine("Username : "));
        builder.password(Reader.readLine("Password : "));
        UserCreateVO userCreateVO = builder.build();

        ResponseEntity<Data<Long>> responseData = userService.create(userCreateVO);
        if (responseData.getData().isSuccess()) {
            Writer.println(responseData.getData(), Color.GREEN);
        } else {
            Writer.println(responseData.getData().getError(), Color.RED);
        }

    }
}

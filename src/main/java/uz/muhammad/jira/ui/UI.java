package uz.muhammad.jira.ui;

import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.services.auth.UserService;
import uz.muhammad.jira.utils.Color;
import uz.muhammad.jira.utils.Reader;
import uz.muhammad.jira.utils.Writer;
import uz.muhammad.jira.vo.auth.UserCreateVO;
import uz.muhammad.jira.vo.auth.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.List;
import java.util.Scanner;

public class UI {

    private final static UserService userService = ApplicationContextHolder.getBean(UserService.class);

    public static void main(String[] args) {
        Writer.println("User Create -> 1");
        Writer.println("User List -> 2");
        String choice = new Scanner(System.in).next();
        if (choice.equals("1")) userCreate();
        else if (choice.equals("2")) userList();
        else System.exit(0);
        main(args);
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
    private static void userCreate() {

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

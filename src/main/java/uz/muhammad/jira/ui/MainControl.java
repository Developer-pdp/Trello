package uz.muhammad.jira.ui;

import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.services.auth.OrgService;
import uz.muhammad.jira.services.auth.UserService;
import uz.muhammad.jira.utils.Color;
import uz.muhammad.jira.utils.Reader;
import uz.muhammad.jira.utils.Writer;
import uz.muhammad.jira.vo.auth.OrgCreateVO;
import uz.muhammad.jira.vo.auth.UserCreateVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.Scanner;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   11:54   (Thursday)
 */
public class MainControl {
    private final static OrgService orgService = ApplicationContextHolder.getBean(OrgService.class);

    public static Session session = new Session();
    public static void control(){
        Writer.println("1. Add organization");
        String choice = new Scanner(System.in).next();
        if (choice.equals("1")) addOrganization();
        else System.exit(0);

    }

    private static void addOrganization() {

        OrgCreateVO.OrgCreateVOBuilder builder = OrgCreateVO.builder();
        builder.name(Reader.readLine("Username : "));
        OrgCreateVO orgCreateVO = builder.build();

        ResponseEntity<Data<Long>> responseData = orgService.create(orgCreateVO);
        if (responseData.getData().isSuccess()) {
            Writer.println(responseData.getData().getBody(), Color.GREEN);
        } else {
            Writer.println(responseData.getData().getError(), Color.RED);
        }

    }
}

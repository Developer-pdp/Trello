package uz.muhammad.jira.ui;

import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.services.auth.OrgService;
import uz.muhammad.jira.utils.Color;
import uz.muhammad.jira.utils.Reader;
import uz.muhammad.jira.utils.Writer;
import uz.muhammad.jira.vo.auth.OrgCreateVO;
import uz.muhammad.jira.vo.auth.OrgVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.List;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   11:54   (Thursday)
 */
public class MainControl {

    private final static OrgService orgService = ApplicationContextHolder.getBean(OrgService.class);

    public static void control(){
        Writer.println("1. Add organization");
        Writer.println("2. Show my organizations");
        Writer.println("0. Back");


        String choice = Reader.readLine(" => ");

        switch (choice){
            case "1" -> addOrganization();
            case "2" -> showMyOrganizations();
        }

    }

    private static void showMyOrganizations() {

        ResponseEntity<Data<List<OrgVO>>> responseData = orgService.findAll(new OrgCriteria());
        Data<List<OrgVO>> data =  responseData.getData();
        if(data.isSuccess()){
            Writer.println(responseData.getData().getBody(), Color.GREEN);
        } else {
            Writer.println(responseData.getData().getError(), Color.RED);
        }
    }

    private static void addOrganization() {

        OrgCreateVO.OrgCreateVOBuilder builder = OrgCreateVO.builder();
        builder.name(Reader.readLine("Enter organization name: "));
        OrgCreateVO orgCreateVO = builder.build();

        ResponseEntity<Data<Long>> responseData = orgService.create(orgCreateVO);

        if (responseData.getData().isSuccess()) {
            Writer.println(responseData.getData().getBody(), Color.GREEN);

        } else {
            Writer.println(responseData.getData().getError(), Color.RED);
        }

    }
}

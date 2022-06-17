package uz.muhammad.jira.ui;

import uz.muhammad.jira.utils.Color;
import uz.muhammad.jira.utils.Tools;
import uz.muhammad.jira.utils.Writer;
import uz.muhammad.jira.vo.auth.orgVO.OrgVO;

import java.util.List;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22   16:36   (Thursday)
 */
public class OrgMenu {

    public static void showAll(List<OrgVO> orgs) {
        Tools.printMiddle("All organizations", 100, ' ', Color.CYAN);

        int order = 1;
        for (OrgVO org : orgs) {
            Writer.println("" + order + ") " + org.getName() + " (" + org.getCreatedAt() + ") ");
        }

    }
}

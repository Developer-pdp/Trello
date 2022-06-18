package uz.muhammad.jira.mappers;

import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.vo.auth.orgVO.OrgVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  15:22 (Friday)
 */
public class OrgMapper implements BaseMapper {

    private static OrgMapper instance;

    public static Organization getOrganization(OrgVO orgVO){
        Organization organization = new Organization();
        organization.setName(orgVO.getName());
        organization.setId(System.currentTimeMillis());
        organization.setCreatedAt(LocalDateTime.now());
        organization.setCreatedBy(orgVO.getId());

        return organization;
    }

    public static OrgMapper getInstance() {
        if (instance == null){
            instance = new OrgMapper();
        }
        return instance;
    }
}

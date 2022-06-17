package uz.muhammad.jira.mappers;

import uz.muhammad.jira.domains.auth.Project;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.vo.auth.projectVO.ProjectVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  15:22 (Friday)
 */
public class ProjectMapper implements BaseMapper {
    public static Project getProject(ProjectVO projectVO){
        Project project = new Project();
        project.setName(projectVO.getName());
        project.setId(System.currentTimeMillis());
        project.setCreatedAt(LocalDateTime.now());
        project.setCreatedBy(projectVO.getId());

        return project;
    }

}

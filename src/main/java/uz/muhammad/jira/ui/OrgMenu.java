package uz.muhammad.jira.ui;

import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.enums.MemberStatus;
import uz.muhammad.jira.mappers.UserMapper;
import uz.muhammad.jira.repository.auth.UserRepository;
import uz.muhammad.jira.services.auth.OrgService;
import uz.muhammad.jira.services.auth.ProjectService;
import uz.muhammad.jira.services.auth.UserService;
import uz.muhammad.jira.utils.Color;
import uz.muhammad.jira.utils.Reader;
import uz.muhammad.jira.utils.Tools;
import uz.muhammad.jira.utils.Writer;
import uz.muhammad.jira.vo.auth.orgVO.OrgVO;
import uz.muhammad.jira.vo.auth.projectVO.ProjectCreateVO;
import uz.muhammad.jira.vo.auth.projectVO.ProjectVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22   16:36   (Thursday)
 */
public class OrgMenu {

    private static UserService userService = ApplicationContextHolder.getBean(UserService.class);
    private static ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);
    private static OrgService orgService = ApplicationContextHolder.getBean(OrgService.class);

    public static void showOrg(OrgVO org) {

        if (org.getMembers().get(Session.userId).equals(MemberStatus.ADMIN.name())){
            showOrgAdminMenu(org);
        } else {
            showOrgUserMenu(org);
        }

    }

    private static void showOrgUserMenu(OrgVO org) {

        Tools.userInfo(Session.userName, org.getMembers().get(Session.userId));
        Writer.printlnMiddle("Projects -> 1", 80, ' ', Color.CYAN);
        Writer.printlnMiddle("Back     -> 0", 80, ' ', Color.RED);

        switch (Reader.readInt(" => ")){
            case 0: return;
            case 1: projectsUser(org);  break;
            default: Writer.printlnMiddle(" Wrong option", 80);
        }

        showOrgUserMenu(org);

    }

    private static void showOrgAdminMenu(OrgVO org) {

        Tools.userInfo(Session.userName, org.getMembers().get(Session.userId));
        Writer.printlnMiddle("Projects -> 1", 80, ' ', Color.CYAN);
        Writer.printlnMiddle("Members  -> 2", 80, ' ', Color.CYAN);
        Writer.printlnMiddle("Settings -> 3", 80, ' ', Color.CYAN);
        Writer.printlnMiddle("Back     -> 0", 80, ' ', Color.RED);

        switch (Reader.readInt(" => ")){
            case 0: return;
            case 1: projects(org);  break;
            case 2: members(org); break;
            case 3: settings(org); break;
            default: Writer.printlnMiddle(" Wrong option", 80);
        }

        showOrgAdminMenu(org);

    }

    private static void settings(OrgVO org) {



    }

    private static void members(OrgVO org) {

        Tools.userInfo(Session.userName, org.getMembers().get(Session.userId));
        Writer.printlnMiddle("All Members", 80, ' ', Color.GREEN);

        Set<Long> userIds = org.getMembers().keySet();

        List<UserVO> users = userService.getUsersByIds(userIds);

        int order=1;
        for (UserVO user : users) {
            Writer.println("\t\t" + order + ") " + user.getUserName() + "\t\t\t role: " + org.getMembers().get(user.getId()));
        }
        Writer.println("\t\t 0. Back");
        int choice = Reader.readInt(" => ");

        if(choice==0){
            return;
        }

        memberSettings(users.get(choice-1));

    }

    private static void memberSettings(UserVO userVO) {

    }

    private static void projectsUser(OrgVO org) {

        List<ProjectVO> projects = new ArrayList<>();
        for (ProjectVO project : projectService.getProjectsByIds(org.getProjects()).getData().getBody()) {
            if (project.getMembers().contains(Session.userId)){
                projects.add(project);
            }
        }

        if (projects.size()==0){
            Writer.printlnMiddle("No projects", 80);
            Writer.printlnMiddle("0 -> Back", 80);
        }

        int order = 1;

        for (ProjectVO project : projects) {
            Writer.println("\t\t " + order + project.getName(),Color.GREEN);
        }

        int choice = Reader.readInt(" => ");

        if (choice==0) return;

        if (projects.get(choice-1).getMembers().indexOf(Session.userId)==0){
            ProjectMenu.managerMenu(projects.get(choice-1));
            return;
        }
        ProjectMenu.userMenu(projects.get(choice-1));
        return;
    }

    private static void projects(OrgVO org) {


        List<ProjectVO> projects = projectService.getProjectsByIds(org.getProjects()).getData().getBody();

        int order = 1;
        for (ProjectVO project : projects) {
            Writer.println("\t\t " + order++ + project.getName(),Color.GREEN);
        }
        Writer.printlnMiddle(order++ + ". Add Project",80, ' ', Color.GREEN);
        Writer.printlnMiddle("0 -> Back ",80,' ', Color.GREEN);

        int choice = Reader.readInt(" => ");
        if (choice==0) return;
        if (choice==order-1){
            addProject(org);
        }
        showOrgAdminMenu(org);
    }

    private static void addProject(OrgVO org) {

        ProjectCreateVO createVO = new ProjectCreateVO();
        createVO.setName(Reader.readLine(" Enter project name: "));
        createVO.setCreatedBy(Session.userId);

        int days = Reader.readInt(" How many days project will take: ");
        while (days<1) {
            days = Reader.readInt(" How many days project will take: ");
        }

        createVO.setDeadline(LocalDateTime.now().plusDays(days));

        Set<Long> userIds = org.getMembers().keySet();
        List<UserVO> users = userService.getUsersByIds(userIds);

        int order = 1;
        for (UserVO user : users) {
            Writer.printlnMiddle(order++ +") " + user.getUserName(), 80, ' ', Color.GREEN);
        }

        Writer.printlnMiddle(" 0. Back ", 80,' ', Color.GREEN);

        int choice = Reader.readInt(" Choose manager for your project: ");
        while (choice<1){
            choice = Reader.readInt(" Choose manager for your project: ");
        }

        Long id = users.get(choice-1).getId();
        List<Long> members = createVO.getMembers();
        members.add(id);
        createVO.setMembers(members);

        ResponseEntity<Data<Long>> response = projectService.create(createVO);

        if (response.getData().isSuccess()){

            orgService.addProjectToOrg(response.getData().getBody(), org.getId());
            return;
        }
        Writer.println(response.getData().getError());
        return;
    }
}

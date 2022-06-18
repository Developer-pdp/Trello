package uz.muhammad.jira.configs;

import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.repository.auth.*;
import uz.muhammad.jira.services.auth.MemberService;
import uz.muhammad.jira.services.auth.*;

public class ApplicationContextHolder {

    public static <T> T getBean(Class<T> clazz) {
        return switch (clazz.getSimpleName()) {
            case "UserService" -> (T) UserService.getInstance();
            case "UserRepository" -> (T) UserRepository.getInstance();


            case "OrganizationService" -> (T) OrgService.getInstance();
            case "OrganizationRepository" -> (T) OrgRepository.getInstance();

            case "ProjectService" -> (T) ProjectService.getInstance();
            case "ProjectRepository" -> (T) ProjectRepository.getInstance();

            case "ProjectColumnService" -> (T) ColumnService.getInstance();
            case "ProjectColumnRepository" -> (T) ColumnRepository.getInstance();

            case "TaskService" -> (T) TaskService.getInstance();
            case "TaskRepository" -> (T) TaskRepository.getInstance();

            case "CommentService" -> (T) CommentService.getInstance();
            case "CommentRepository" -> (T) CommentRepository.getInstance();

            case "MemberService" -> (T) MemberService.getInstance();
            case "MemberRepository" -> (T) MemberRepository.getInstance();


            case "BaseMapper" -> (T) new BaseMapper() {
            };

            default -> throw new RuntimeException("Bean with name '%s' not found".formatted(clazz.getSimpleName()));
        };
    }

}
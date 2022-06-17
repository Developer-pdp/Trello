package uz.muhammad.jira.mappers;

import uz.muhammad.jira.domains.auth.Project;
import uz.muhammad.jira.domains.auth.Task;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.vo.auth.taskVO.TaskVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  15:22 (Friday)
 */
public class TaskMapper implements BaseMapper {
    public static Task getTask(TaskVO taskVO){
        Task task = new Task();
        task.setName(taskVO.getName());
        task.setId(System.currentTimeMillis());
        task.setCreatedAt(LocalDateTime.now());
        task.setCreatedBy(taskVO.getId());

        return task;
    }

}
package uz.muhammad.jira.vo.auth.taskVO;

import lombok.*;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.Task;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  14:44 (Friday)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskVO extends GenericVO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;

    public TaskVO(Task task) {
        super(task.getId());
        this.name = task.getName();
        this.createdAt = task.getCreatedAt();
        this.createdBy = task.getCreatedBy();
    }

    @Builder(builderMethodName = "childBuilder")
    public TaskVO(Long id, String name, LocalDateTime createdAt, Long createdBy) {
        super(id);
        this.name = name;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}

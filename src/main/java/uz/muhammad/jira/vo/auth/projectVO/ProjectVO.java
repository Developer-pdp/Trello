package uz.muhammad.jira.vo.auth.projectVO;

import lombok.*;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.Project;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  14:43 (Friday)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectVO extends GenericVO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;

    public ProjectVO(Project project) {
        super(project.getId());
        this.name = project.getName();
        this.createdAt = project.getCreatedAt();
        this.createdBy = project.getCreatedBy();
    }

    @Builder(builderMethodName = "childBuilder")
    public ProjectVO(Long id, String name, LocalDateTime createdAt, Long createdBy) {
        super(id);
        this.name = name;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
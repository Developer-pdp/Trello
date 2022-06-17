package uz.muhammad.jira.vo.auth.orgVO;

import lombok.*;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.Project;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22   15:59   (Thursday)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrgVO extends GenericVO {
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private Long id;
    private String name;

    public OrgVO(Organization organization) {
        super(organization.getId());
        this.name = organization.getName();
        this.createdAt = organization.getCreatedAt();
        this.createdBy = organization.getCreatedBy();
    }

    public OrgVO(Long id, LocalDateTime createdAt, Long createdBy, Long id1, String name) {
        super(id);
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.id = id1;
        this.name = name;
    }
}

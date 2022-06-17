package uz.muhammad.jira.vo.auth.orgVO;

import lombok.*;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.User;
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
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;

    public OrgVO(Organization organization) {
        super(organization.getId());
        this.name = organization.getName();
        this.createdAt = organization.getCreatedAt();
        this.createdBy = organization.getCreatedBy();
    }

    @Builder(builderMethodName = "childBuilder")
    public OrgVO(Long id, String name, LocalDateTime createdAt, Long createdBy) {
        super(id);
        this.name = name;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}

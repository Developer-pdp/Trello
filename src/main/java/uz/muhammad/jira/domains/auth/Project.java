package uz.muhammad.jira.domains.auth;

import lombok.*;
import uz.muhammad.jira.domains.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   11:31   (Thursday)
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Project implements BaseEntity {
    private Long id;
    private String name;
    private List<Column> columns;
    private List<Member> members;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private boolean blocked;
    private boolean deleted;
}

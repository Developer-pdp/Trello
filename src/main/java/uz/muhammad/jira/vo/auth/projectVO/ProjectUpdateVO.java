package uz.muhammad.jira.vo.auth.projectVO;

import lombok.*;
import uz.muhammad.jira.domains.auth.Column;
import uz.muhammad.jira.domains.auth.Member;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;
import java.util.List;

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

public class ProjectUpdateVO  extends GenericVO {

    private String name;
    private List<Column> columns;
    private List<Member> members;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private boolean blocked;
    private boolean deleted;
}

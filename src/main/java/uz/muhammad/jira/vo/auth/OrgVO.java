package uz.muhammad.jira.vo.auth;

import lombok.*;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22  15:02 (Thursday)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrgVO extends GenericVO {
    private String name;
    private LocalDateTime createdAt;

    public OrgVO(User user) {
        super(user.getId());
        this.name = user.getUserName();
        this.createdAt = user.getCreatedAt();
    }

    @Builder(builderMethodName = "childBuilder")
    public OrgVO(Long id, String name, LocalDateTime createdAt) {
        super(id);
        this.name = name;
        this.createdAt = createdAt;
    }
}

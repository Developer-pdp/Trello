package uz.muhammad.jira.vo.auth;

import lombok.*;
import uz.muhammad.jira.vo.BaseVO;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22   15:58   (Thursday)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class OrgCreateVO implements BaseVO {
    private String name;
}

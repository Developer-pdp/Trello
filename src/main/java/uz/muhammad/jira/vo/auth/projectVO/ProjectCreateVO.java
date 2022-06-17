package uz.muhammad.jira.vo.auth.projectVO;

import lombok.*;
import uz.muhammad.jira.vo.BaseVO;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  14:43 (Friday)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class ProjectCreateVO implements BaseVO {
    private String name;
}

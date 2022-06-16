package uz.muhammad.jira.vo.auth.userVO;

import lombok.*;
import uz.muhammad.jira.vo.BaseVO;

/**
 * @author Team <Developers>
 * @project TrelloBY
 * @since 16/06/22   11:31   (Thursday)
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class UserCreateVO implements BaseVO {
    private String userName;
    private String password;
}

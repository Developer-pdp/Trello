package uz.muhammad.jira.domains.auth;

import lombok.*;
import uz.muhammad.jira.domains.BaseEntity;

import java.time.LocalDateTime;

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
public class User implements BaseEntity {
    private Long id;
    private String userName;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

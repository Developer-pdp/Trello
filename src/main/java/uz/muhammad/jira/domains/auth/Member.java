package uz.muhammad.jira.domains.auth;

import lombok.*;
import uz.muhammad.jira.domains.BaseEntity;
import uz.muhammad.jira.enums.MemberStatus;

import java.time.LocalDateTime;

/**
 * @author Yusupov Muhammadqodir
 * @project TrelloBY
 * @since 15/06/22   23:29   (Wednesday)
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements BaseEntity {
    private Long id;
    private Long userId;
    private MemberStatus status;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private boolean blocked;
    private boolean deleted;
}

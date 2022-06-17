package uz.muhammad.jira.domains.auth;

import lombok.*;
import uz.muhammad.jira.domains.BaseEntity;

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
public class Comment implements BaseEntity {
    private Long id;
    private Long ownerId;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private boolean blocked;
    private boolean deleted;
}

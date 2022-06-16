package uz.muhammad.jira.domains.auth;

import lombok.*;
import uz.muhammad.jira.domains.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yusupov Muhammadqodir
 * @project TrelloBY
 * @since 15/06/22   23:21   (Wednesday)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Column implements BaseEntity {
    private Long id;
    private String name;
    private List<Task> tasks;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private boolean blocked;
    private boolean deleted;
}

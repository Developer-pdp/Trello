package uz.muhammad.jira.domains.auth;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yusupov Muhammadqodir
 * @project TrelloBY
 * @since 15/06/22   23:21   (Wednesday)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Project {
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

package uz.muhammad.jira.domains.auth;

import lombok.*;
import uz.muhammad.jira.domains.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Organization implements BaseEntity {
    private Long id;
    private String name;
    private Long ownerId;
    private List<Project> projectList;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private Boolean deleted;
    private Boolean blocked;
}

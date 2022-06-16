package uz.muhammad.jira.domains.auth;

import uz.muhammad.jira.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yusupov Muhammadqodir
 * @project TrelloBY
 * @since 15/06/22   23:24   (Wednesday)
 */
public class Task {
    private Long id;
    private String name;
    private List<Comment> comments;
    private List<Member> members;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private TaskStatus status;
    private boolean deleted;
}

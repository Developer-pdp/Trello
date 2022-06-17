package uz.muhammad.jira.vo.auth.commentVO;

import lombok.Builder;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

public class CommentVO extends GenericVO {
    private LocalDateTime createdAt;
    private Long createdBy;

    public CommentVO(Long id, LocalDateTime createdAt, Long createdBy) {
        super(id);
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    @Builder(builderMethodName = "childBuilder")
    public CommentVO(LocalDateTime createdAt, Long createdBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
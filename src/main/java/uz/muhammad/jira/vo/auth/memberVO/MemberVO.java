package uz.muhammad.jira.vo.auth.memberVO;

import lombok.Builder;
import uz.muhammad.jira.domains.auth.Member;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

public class MemberVO extends GenericVO {
    private LocalDateTime createdAt;
    private Long createdBy;

    public MemberVO(Long id, LocalDateTime createdAt, Long createdBy) {
        super(id);
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    @Builder(builderMethodName = "childBuilder")
    public MemberVO(LocalDateTime createdAt, Long createdBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public MemberVO(Member member) {
    }
}

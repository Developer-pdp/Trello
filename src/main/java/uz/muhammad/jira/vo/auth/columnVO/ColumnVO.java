package uz.muhammad.jira.vo.auth.columnVO;

import lombok.Builder;
import uz.muhammad.jira.domains.auth.Column;
import uz.muhammad.jira.vo.GenericVO;

import java.time.LocalDateTime;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22   15:59   (Thursday)
 */
public class ColumnVO extends GenericVO {
    private LocalDateTime createdAt;
    private Long createdBy;

    public ColumnVO(Long id, LocalDateTime createdAt, Long createdBy) {
        super(id);
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
@Builder(builderMethodName = "childBuilder")
    public ColumnVO(LocalDateTime createdAt, Long createdBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public ColumnVO(Column column) {

    }
}

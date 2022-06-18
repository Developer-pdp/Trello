package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.CommentCriteria;
import uz.muhammad.jira.criteria.TaskCriteria;
import uz.muhammad.jira.domains.auth.Comment;
import uz.muhammad.jira.domains.auth.Task;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.mappers.TaskMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.CommentRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.commentVO.CommentCreateVO;
import uz.muhammad.jira.vo.auth.commentVO.CommentUpdateVO;
import uz.muhammad.jira.vo.auth.commentVO.CommentVO;
import uz.muhammad.jira.vo.auth.taskVO.TaskVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ErrorVO;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  16:15 (Friday)
 */
public class CommentService extends AbstractRepository<CommentRepository, BaseMapper> implements
        GenericCRUDService<CommentVO, CommentCreateVO, CommentUpdateVO, CommentCriteria, Long> {

    private static CommentService instance;

    protected CommentService(CommentRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }


    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService(
                    ApplicationContextHolder.getBean(CommentRepository.class),
                    ApplicationContextHolder.getBean(BaseMapper.class)
            );
        }
        return instance;
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull CommentCreateVO dto) {
            return null;

    }

    @Override
    public ResponseEntity<Data<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> update(@NonNull CommentUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<CommentVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<CommentVO>>> findAll(@NonNull CommentCriteria criteria) {
        List<CommentVO> commentList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(CommentVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(commentList, commentList.size()));
    }
}

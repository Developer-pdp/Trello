package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.ColumnCriteria;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.domains.auth.Column;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.ColumnRepository;
import uz.muhammad.jira.repository.auth.OrgRepository;
import uz.muhammad.jira.repository.auth.UserRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.columnVO.ColumnCreateVO;
import uz.muhammad.jira.vo.auth.columnVO.ColumnUpdateVO;
import uz.muhammad.jira.vo.auth.columnVO.ColumnVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgCreateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgUpdateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgVO;
import uz.muhammad.jira.vo.auth.userVO.UserVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ErrorVO;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22  15:00 (Thursday)
 */
public class ColumnService extends AbstractRepository<ColumnRepository, BaseMapper> implements
        GenericCRUDService<ColumnVO, ColumnCreateVO, ColumnUpdateVO, ColumnCriteria, Long> {

    private static ColumnService instance;

    private ColumnService(ColumnRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull ColumnCreateVO dto) {
        Column column = new Column();
        Optional<Column> columnOptional = repository.findByUsername(dto.getName());
        if (columnOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("Column Name '%s' already taken".formatted(dto.getName()))
                    .status(400)
                    .build()));
        }

        column.setName(dto.getName());
        repository.create(column);

        return new ResponseEntity<>(new Data<>(column.getId()));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> update(@NonNull ColumnUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<ColumnVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<ColumnVO>>> findAll(@NonNull ColumnCriteria criteria) {
        List<ColumnVO> columnList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(ColumnVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(columnList, columnList.size()));
    }

    public static ColumnService getInstance() {
        if (instance == null) {
            instance = new ColumnService(
                    ApplicationContextHolder.getBean(ColumnRepository.class),
                    ApplicationContextHolder.getBean(BaseMapper.class)
            );
        }
        return instance;
    }
}

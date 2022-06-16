package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.OrgRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.OrgCreateVO;
import uz.muhammad.jira.vo.auth.OrgUpdateVO;
import uz.muhammad.jira.vo.auth.OrgVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.List;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22  15:00 (Thursday)
 */
public class OrgService extends AbstractRepository<OrgRepository, BaseMapper> implements
        GenericCRUDService<OrgVO, OrgCreateVO, OrgUpdateVO, OrgCriteria, Long> {
    protected OrgService(OrgRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull OrgCreateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> update(@NonNull OrgUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<OrgVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<OrgVO>>> findAll(@NonNull OrgCriteria criteria) {
        return null;
    }
}

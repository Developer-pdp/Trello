package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.mappers.OrgMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.OrgRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.orgVO.OrgCreateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgUpdateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgVO;
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
public class OrgService extends AbstractRepository<OrgRepository, BaseMapper> implements
        GenericCRUDService<OrgVO, OrgCreateVO, OrgUpdateVO, OrgCriteria, Long> {

    private static OrgService instance;
    protected OrgService(OrgRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull OrgCreateVO dto) {
        OrgVO orgVO = new OrgVO();
        Optional<Organization> orgOptional = repository.findByUsername(dto.getName());
        if (orgOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("Organization Name '%s' already taken".formatted(dto.getName()))
                    .status(400)
                    .build()));
        }

        orgVO.setName(dto.getName());
        repository.create(OrgMapper.getOrganization(orgVO));

        return new ResponseEntity<>(new Data<>(orgVO.getId()));
    }

    @Override
    public ResponseEntity<Data<String>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<String>> update(@NonNull OrgUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<OrgVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<OrgVO>>> findAll(@NonNull OrgCriteria criteria) {
        List<OrgVO> orgList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(OrgVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(orgList, orgList.size()));
    }

    public static OrgService getInstance() {
        if (instance == null) {
            instance = new OrgService(
                    ApplicationContextHolder.getBean(OrgRepository.class),
                    ApplicationContextHolder.getBean(OrgMapper.class)
            );
        }
        return instance;
    }
}

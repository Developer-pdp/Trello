package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.ProjectCriteria;
import uz.muhammad.jira.domains.auth.Project;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.mappers.ProjectMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.ProjectRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.projectVO.ProjectCreateVO;
import uz.muhammad.jira.vo.auth.projectVO.ProjectUpdateVO;
import uz.muhammad.jira.vo.auth.projectVO.ProjectVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ErrorVO;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  14:49 (Friday)
 */
public class ProjectService extends AbstractRepository<ProjectRepository, BaseMapper> implements
        GenericCRUDService<ProjectVO, ProjectCreateVO, ProjectUpdateVO, ProjectCriteria, Long> {

    private static ProjectService instance;

    protected ProjectService(ProjectRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull ProjectCreateVO dto) {
        Optional<Project> projectOptional = repository.findByUsername(dto.getName());
        if (projectOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("Task name '%s' already taken".formatted(dto.getName()))
                    .status(400)
                    .build()));
        }
        ProjectVO projectVO = new ProjectVO();
        projectVO.setName(dto.getName());

        repository.create(ProjectMapper.getProject(projectVO));

        return new ResponseEntity<>(new Data<>(projectVO.getId()));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> update(@NonNull ProjectUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<ProjectVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<ProjectVO>>> findAll(@NonNull ProjectCriteria criteria) {
        List<ProjectVO> projectList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(ProjectVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(projectList, projectList.size()));
    }

    public static ProjectService getInstance() {
        if (instance == null) {
            instance = new ProjectService(
                    ApplicationContextHolder.getBean(ProjectRepository.class),
                    ApplicationContextHolder.getBean(BaseMapper.class)
            );
        }
        return instance;
    }
}

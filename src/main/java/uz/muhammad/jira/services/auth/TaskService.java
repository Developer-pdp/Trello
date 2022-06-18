package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.TaskCriteria;
import uz.muhammad.jira.domains.auth.Task;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.mappers.TaskMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.TaskRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.taskVO.TaskCreateVO;
import uz.muhammad.jira.vo.auth.taskVO.TaskUpdateVO;
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
 * @since 17/06/22  14:49 (Friday)
 */
public class TaskService extends AbstractRepository<TaskRepository, BaseMapper> implements
        GenericCRUDService<TaskVO, TaskCreateVO, TaskUpdateVO, TaskCriteria, Long> {

    private static TaskService instance;

    protected TaskService(TaskRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull TaskCreateVO dto) {
        Optional<Task> taskOptional = repository.findByUsername(dto.getName());
        if (taskOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("Task name '%s' already taken".formatted(dto.getName()))
                    .status(400)
                    .build()));
        }
        TaskVO taskVO = new TaskVO();
        taskVO.setName(dto.getName());

        repository.create(TaskMapper.getTask(taskVO));

        return new ResponseEntity<>(new Data<>(taskVO.getId()));
    }

    @Override
    public ResponseEntity<Data<String>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<String>> update(@NonNull TaskUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<TaskVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<TaskVO>>> findAll(@NonNull TaskCriteria criteria) {
        List<TaskVO> taskList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(TaskVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(taskList, taskList.size()));
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService(
                    ApplicationContextHolder.getBean(TaskRepository.class),
                    ApplicationContextHolder.getBean(BaseMapper.class)
            );
        }
        return instance;
    }
}

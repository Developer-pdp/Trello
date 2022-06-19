package uz.muhammad.jira.repository.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.muhammad.jira.criteria.ProjectCriteria;
import uz.muhammad.jira.domains.auth.Project;
import uz.muhammad.jira.repository.GenericCRUDRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  14:50 (Friday)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectRepository implements GenericCRUDRepository<Project, ProjectCriteria, Long> {

    private static ProjectRepository instance;

    private static final List<Project> projects = load();

    private static List<Project> load() {
        // TODO: 6/15/2022 load data from file here

        return new ArrayList<>();
    }
    @Override
    public void create(Project entity) {
        projects.add(entity);
    }

    @Override
    public void update(Project entity) {
        projects.add(entity);
    }

    @Override
    public void deleteByID(Long id) {
        for (Project project : projects) {
            if(project.getId().equals(id)){
                projects.remove(project);
                break;
            }
        }
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<List<Project>> findAll(ProjectCriteria criteria) {
        return Optional.of(projects);
    }

    public static ProjectRepository getInstance() {
        if (instance == null) {
            instance = new ProjectRepository();
        }
        return instance;
    }

    public Optional<Project> findByUsername(String name) {
        return projects.stream()
                .filter(project -> project.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
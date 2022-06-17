package uz.muhammad.jira.repository.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.repository.GenericCRUDRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22  14:55 (Thursday)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrgRepository implements GenericCRUDRepository<Organization, OrgCriteria, Long> {

    private static OrgRepository instance;

    private static final List<Organization> organizations = load();

    private static List<Organization> load() {
        // TODO: 6/15/2022 load data from file here

        return new ArrayList<>();
    }
    @Override
    public void create(Organization entity) {
        entity.setId(System.currentTimeMillis());
        entity.setCreatedAt(LocalDateTime.now());
        organizations.add(entity);
    }

    @Override
    public void update(Organization entity) {
        organizations.add(entity);
    }

    @Override
    public void deleteByID(Long id) {
        for (Organization organization : organizations) {
            if(organization.getId().equals(id)){
                organizations.remove(organization);
                break;
            }
        }
    }

    @Override
    public Optional<Organization> findById(Long id) {
        return organizations.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<List<Organization>> findAll(OrgCriteria criteria) {
        return Optional.of(organizations);
    }

    public static OrgRepository getInstance() {
        if (instance == null) {
            instance = new OrgRepository();
        }
        return instance;
    }

    public Optional<Organization> findByUsername(String name) {
        return organizations.stream()
                .filter(organization -> organization.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}

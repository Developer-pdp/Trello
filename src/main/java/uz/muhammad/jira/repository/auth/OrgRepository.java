package uz.muhammad.jira.repository.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.criteria.UserCriteria;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.domains.auth.User;
import uz.muhammad.jira.repository.GenericCRUDRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22  14:55 (Thursday)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrgRepository implements GenericCRUDRepository<Organization, OrgCriteria, Long> {
    @Override
    public void create(Organization entity) {

    }

    @Override
    public void update(Organization entity) {

    }

    @Override
    public void deleteByID(Long aLong) {

    }

    @Override
    public Optional<Organization> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Organization>> findAll(OrgCriteria criteria) {
        return Optional.empty();
    }
}

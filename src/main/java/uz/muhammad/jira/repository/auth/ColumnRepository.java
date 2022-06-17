package uz.muhammad.jira.repository.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.muhammad.jira.criteria.ColumnCriteria;
import uz.muhammad.jira.domains.auth.Column;
import uz.muhammad.jira.repository.GenericCRUDRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnRepository implements GenericCRUDRepository<Column, ColumnCriteria, Long>{

    private static ColumnRepository instance;

    private static final List<Column> columns = load();

    private static List<Column> load() {
        return new ArrayList<>();
    }


    @Override
    public void create(Column entity) {
        entity.setId(System.currentTimeMillis());
        entity.setCreatedAt(LocalDateTime.now());
        columns.add(entity);
    }

    @Override
    public void update(Column entity) {

    }

    @Override
    public void deleteByID(Long aLong) {

    }

    @Override
    public Optional<Column> findById(Long id) {
       return columns.stream()
               .filter(column -> column.getId().equals(id))
               .findFirst();
    }

    @Override
    public Optional<List<Column>> findAll(ColumnCriteria criteria) {
        return Optional.of(columns);
    }

    public static ColumnRepository getInstance(){
        if (instance == null){
            instance= new ColumnRepository();
        }
        return instance;
    }

    public Optional<Column> findByUsername(String name){
        return columns.stream()
                .filter(column -> column.getName().equals(name))
                .findFirst();
    }
}


package uz.muhammad.jira.repository.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.muhammad.jira.criteria.CommentCriteria;
import uz.muhammad.jira.domains.auth.Comment;
import uz.muhammad.jira.repository.GenericCRUDRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRepository implements GenericCRUDRepository<Comment, CommentCriteria, Long> {

    private static CommentRepository instance;
    private static final List<Comment> comments = load();

    private static List<Comment> load() {
        return new ArrayList<>();
    }

    @Override
    public void create(Comment entity) {
        entity.setId(System.currentTimeMillis());
        entity.setCreatedAt(LocalDateTime.now());
        comments.add(entity);
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void deleteByID(Long aLong) {

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<List<Comment>> findAll(CommentCriteria criteria) {
        return Optional.of(comments);
    }

    public static CommentRepository getInstance(){
        if (instance == null){
            instance= new CommentRepository();
        }
        return instance;
    }
}

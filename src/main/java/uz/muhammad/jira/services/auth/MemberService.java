package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.MemberCriteria;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.MemberRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.vo.auth.memberVO.MemberCreateVO;
import uz.muhammad.jira.vo.auth.memberVO.MemberUpdateVO;
import uz.muhammad.jira.vo.auth.memberVO.MemberVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22  16:15 (Friday)
 */
public class MemberService extends AbstractRepository<MemberRepository, BaseMapper> implements
        GenericCRUDService<MemberVO, MemberCreateVO, MemberUpdateVO, MemberCriteria, Long> {

    private static MemberService instance;

    protected MemberService(MemberRepository repository, BaseMapper mapper) {
        super(repository, mapper);
    }


    public static MemberService getInstance() {
        if (instance == null) {
            instance = new MemberService(
                    ApplicationContextHolder.getBean(MemberRepository.class),
                    ApplicationContextHolder.getBean(BaseMapper.class)
            );
        }
        return instance;
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull MemberCreateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> update(@NonNull MemberUpdateVO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<MemberVO>> findById(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<MemberVO>>> findAll(@NonNull MemberCriteria criteria) {
        List<MemberVO> memeberList = repository.findAll(criteria)
                .orElse(new ArrayList<>())
                .stream().map(MemberVO::new)
                .toList();

        return new ResponseEntity<>(new Data<>(memeberList, memeberList.size()));
    }
}

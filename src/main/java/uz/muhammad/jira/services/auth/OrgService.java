package uz.muhammad.jira.services.auth;

import lombok.NonNull;
import uz.muhammad.jira.configs.ApplicationContextHolder;
import uz.muhammad.jira.criteria.OrgCriteria;
import uz.muhammad.jira.domains.auth.Member;
import uz.muhammad.jira.domains.auth.Organization;
import uz.muhammad.jira.enums.MemberStatus;
import uz.muhammad.jira.enums.UserRole;
import uz.muhammad.jira.mappers.BaseMapper;
import uz.muhammad.jira.mappers.OrgMapper;
import uz.muhammad.jira.repository.AbstractRepository;
import uz.muhammad.jira.repository.auth.OrgRepository;
import uz.muhammad.jira.services.GenericCRUDService;
import uz.muhammad.jira.ui.Session;
import uz.muhammad.jira.utils.Tools;
import uz.muhammad.jira.vo.auth.memberVO.MemberCreateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgCreateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgUpdateVO;
import uz.muhammad.jira.vo.auth.orgVO.OrgVO;
import uz.muhammad.jira.vo.response.Data;
import uz.muhammad.jira.vo.response.ErrorVO;
import uz.muhammad.jira.vo.response.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 16/06/22  15:00 (Thursday)
 */
public class OrgService extends AbstractRepository<OrgRepository, OrgMapper> implements
        GenericCRUDService<OrgVO, OrgCreateVO, OrgUpdateVO, OrgCriteria, Long> {

    private static OrgService instance;
    protected OrgService(OrgRepository repository, OrgMapper mapper) {
        super(repository, mapper);
    }

    private static MemberService memberService = ApplicationContextHolder.getBean(MemberService.class);
    private static UserService userService = ApplicationContextHolder.getBean(UserService.class);

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull OrgCreateVO dto) {

        /**
         *
         * id;
         * g name;
         * ownerId;
         * Long> projects = new ArrayList<>();
         * Long> members = new ArrayList<>();
         * DateTime createdAt;
         * createdBy;
         * DateTime updatedAt;
         * updatedBy;
         * an deleted  = false;
         * an blocked = false;
         *
         */

        OrgVO orgVO = new OrgVO();
        Optional<Organization> orgOptional = repository.findByUsername(dto.getName());
        if (orgOptional.isPresent()) {
            return new ResponseEntity<>(new Data<>(ErrorVO
                    .builder()
                    .friendlyMessage("Organization Name '%s' already taken".formatted(dto.getName()))
                    .status(400)
                    .build()));
        }

        orgVO.setId(System.currentTimeMillis());
        orgVO.setName(dto.getName());
        Map<Long, String> members = orgVO.getMembers();
        members.put(Session.userId, MemberStatus.ADMIN.name());
        orgVO.setMembers(members);
        orgVO.setCreatedAt(LocalDateTime.now());
        orgVO.setCreatedBy(dto.getCreatedBy());


//        MemberCreateVO createVO = MemberCreateVO.builder()
//                .id(Tools.getId(orgVO.getId()))
//                .organizationId(orgVO.getId())
//                .userId(orgVO.getOwnerId())
//                .status(MemberStatus.SUPER_USER)
//                .build();

//        memberService.create(createVO);
        repository.create(OrgMapper.getOrganization(orgVO));
//        userService.addOrgToUser(Session.userId, orgVO.getId());

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
        List<OrgVO> orgList = new ArrayList<>();

        for (Organization organization : repository.findAll(new OrgCriteria()).get()) {
            orgList.add(mapper.getOrgVO(organization));
        }

        return new ResponseEntity<>(new Data<>(orgList, orgList.size()));
    }

    public ResponseEntity<Data<List<OrgVO>>> findAllByIds(List<Long> ids) {

        List<OrgVO> orgVOS = new ArrayList<>();

        for (OrgVO orgVO : findAll(new OrgCriteria()).getData().getBody()) {
            if(ids.contains(orgVO.getId())){
                orgVOS.add(orgVO);
            }
        }

        return new ResponseEntity<>(new Data<>(orgVOS, orgVOS.size()));
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

    public ResponseEntity<Data<String>> addProjectToOrg(Long projectId, Long orgId) {
        for (Organization organization : repository.findAll(new OrgCriteria()).get()) {
            if (organization.getId()==orgId){
                List<Long> projects = organization.getProjects();
                projects.add(projectId);
                organization.setProjects(projects);
                repository.update(organization);
                return new ResponseEntity<>(new Data<>("organization added"));
            }
        }
        return new ResponseEntity<>(new Data<>(new ErrorVO("Org not found", "Org not found", 400)));
    }
}

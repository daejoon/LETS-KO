package dd2.local.busi.member.service;

import dd2.com.dao.GenericDAO;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.member.service.dao.MemberDAO;
import dd2.local.entity.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오후 12:19
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class MemberServiceImpl extends GenericHibernateService<Member, Long> implements  MemberService {
    private static final Log logger = LogFactory.getLog(MemberServiceImpl.class);

    @Autowired
    private MemberDAO memberDAO;

    @Override
    protected GenericDAO<Member, Long> getGenericDAO() {
        return memberDAO;
    }

    @Override
    public Member getMemberWithRoles(Long id) {
        Member member = memberDAO.findById(id);
        if ( member != null ) {
            Hibernate.initialize(member.getRoles());
        }
        return member;
    }

    @Override
    public Member getMemberByName(String username) {
        return memberDAO.getMemberByName(username);
    }

    @Override
    public List<Member> getMemberAll() {
        return memberDAO.getMemberAll();
    }
}

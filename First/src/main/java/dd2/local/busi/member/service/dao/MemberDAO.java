package dd2.local.busi.member.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.Member;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 25
 * Time: 오전 10:29
 * To change this template use File | Settings | File Templates.
 */
public interface MemberDAO extends GenericDAO<Member, Long> {
    List<Member> getMemberAll();
    Member getMemberByName(String username);
}

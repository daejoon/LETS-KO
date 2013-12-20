package dd2.local.busi.member.service;

import dd2.com.service.GenericService;
import dd2.local.entity.Member;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오후 12:19
 * To change this template use File | Settings | File Templates.
 */
public interface MemberService extends GenericService<Member,Long> {
    Member getMemberWithRoles(Long id);
    Member getMemberByName(String username);
    List<Member> getMemberAll();
}

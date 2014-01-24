package dd2.local.busi.board.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.local.entity.Comment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 16
 * Time: 오후 12:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CommentDAOHibernate extends GenericHibernateDAO<Comment, Long> implements CommentDAO {
    private static final Log logger = LogFactory.getLog(CommentDAOHibernate.class);

    public List getCommentList(Long boardId) {
        return this.getCriteria("a")
                .createAlias("member", "b", JoinType.INNER_JOIN)
                .createAlias("board", "c", JoinType.INNER_JOIN)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("a.id"        ).as("id"))
                        .add(Projections.property("a.contents"  ).as("contents"))
                        .add(Projections.property("a.createDate").as("createDate"))
                        .add(Projections.property("a.updateDate").as("updateDate"))
                        .add(Projections.property("b.id"        ).as("memberId"))
                        .add(Projections.property("b.username"  ).as("username"))
                        .add(Projections.property("c.id"        ).as("boardId"))
                )
                .add(Restrictions.eq("c.id", boardId))
                .addOrder(Order.asc("a.createDate"))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }
}

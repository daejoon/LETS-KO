package dd2.com.util;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 9:38
 * To change this template use File | Settings | File Templates.
 */
public final class New {
    /**
     * 맵을 생성한다.
     * @return
     */
    public static <K,V> Map<K,V> map() {
        return new HashMap<K,V>();
    }

    /**
     * 리스트를 생성한다.
     * @return
     */
    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    /**
     * 링크드 리스트를 생성한다.
     * @return
     */
    public static <T> LinkedList<T> lList() {
        return new LinkedList<T>();
    }

    /**
     * 셋을 생성한다.
     * @return
     */
    public static <T> Set<T> set() {
        return new HashSet<T>();
    }

    /**
     * 큐를 생성한다.
     * @return
     */
    public static <T> Queue<T> queue() {
        return new LinkedList<T>();
    }
}

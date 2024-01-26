package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // static으로 선언했기 때문에 MemberRepository 객체가 다수 생성되어도 딱 1개만 생성된다.
    // 싱글톤으로 생성하면 사실 static을 생략해도 된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    // 싱글톤으로 생성. 톰캣을 띄울 때를 제외하고 스프링을 사용하지 않으므로
    // 싱글톤 생성 시에는 private로 생성자를 막아야 한다.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store에 있는 모든 값들을 꺼내서 새로운 ArrayList에 담아서 넘긴다.
        // ArrayList에 값을 넣거나 밖에서 수정해도 store.values()는 변경하지 않기 위해 (store 자체를 보호)
        // 물론 이렇게 해도 store의 member를 직접 가져와서 안의 값을 수정하면 수정이 된다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

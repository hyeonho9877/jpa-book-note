package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager entityManager) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        entityManager.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member result = entityManager.find(Member.class, id);
        System.out.println("RESULT : "+result);

        List<Member> members = entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();

        System.out.println("member size : " + members.size());

        entityManager.remove(member);
    }
}

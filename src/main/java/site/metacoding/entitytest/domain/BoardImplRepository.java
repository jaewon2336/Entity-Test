package site.metacoding.entitytest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
public class BoardImplRepository {
    // Impl : implement 구현한
    // interface라서 new 안됨
    // interface는 변수가 모두 static이다.
    // 복잡한 쿼리 만드려고 JPQL 사용 실습

    private final EntityManager em;

    // createQuery -> DB 모델과 타입이 같아야한다.
    // TypedQuery 쓸거면 그냥 BoardRepository 사용하자 차라리
    // createNativeQuery -> 진짜 내 마음대로 쿼리 (=PrepareStatement)
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM board b WHERE id = ?";
        // 상세보기에 좋아요 하는지 안하는지 정보를 줘야함
        Query query = em.createNativeQuery(sql)
                .setParameter(1, id);

        // Object 타입이기 때문에 하나씩 꺼내줘야함
        Object[] result = (Object[]) query.getSingleResult();

        Integer boardId = (Integer) result[0];
        String title = (String) result[1];
        String content = (String) result[2];
        Boolean isLove = (Boolean) result[3];

        BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);

        System.out.println("dddd");

        return dto; // 1, 제목1, 내용1, true
    }

    public List<BoardDetailRespDto> mFindAll() {
        List<BoardDetailRespDto> dtos = new ArrayList<>();

        String sql = "SELECT b.*, true FROM board b";
        // 상세보기에 좋아요 하는지 안하는지 정보를 줘야함
        Query query = em.createNativeQuery(sql);

        // Object 타입이기 때문에 하나씩 꺼내줘야함
        List<Object[]> results = (List<Object[]>) query.getResultList();

        for (Object[] result : results) {
            Integer boardId = (Integer) result[0];
            String title = (String) result[1];
            String content = (String) result[2];
            Boolean isLove = (Boolean) result[3];

            BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);
            dtos.add(dto);
        }

        System.out.println("dddd");

        return dtos; // 1, 제목1, 내용1, true
    }

    // QLRM 라이브러리
    public List<BoardDetailRespDto> mFindAllQLRM() {

        String sql = "SELECT b.*, true FROM board b";
        // 상세보기에 좋아요 하는지 안하는지 정보를 줘야함
        Query query = em.createNativeQuery(sql);

        JpaResultMapper mapper = new JpaResultMapper();
        List<BoardDetailRespDto> dtos = mapper.list(query, BoardDetailRespDto.class);

        return dtos; // 1, 제목1, 내용1, true
    }
}

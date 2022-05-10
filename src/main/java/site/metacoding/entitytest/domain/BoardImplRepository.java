package site.metacoding.entitytest.domain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM board b WHERE id = ?";
        // 상세보기에 좋아요 하는지 안하는지 정보를 줘야함
        TypedQuery<BoardDetailRespDto> query = em.createQuery(sql, BoardDetailRespDto.class)
                .setParameter(1, id);

        BoardDetailRespDto dto = query.getSingleResult();

        return dto; // 1, 제목1, 내용1, true
    }
}

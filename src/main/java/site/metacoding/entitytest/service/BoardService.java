package site.metacoding.entitytest.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.domain.Board;
import site.metacoding.entitytest.domain.BoardImplRepository;
import site.metacoding.entitytest.domain.BoardRepository;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;
import site.metacoding.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository; // API (JPA)
    private final BoardImplRepository boardImplRepository; // JPQL

    public BoardRespDto 상세보기(Integer id) {
        Board boardEntity = boardRepository.findById(id).get();

        BoardRespDto dto = new BoardRespDto(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getContent());

        Hibernate.initialize(boardEntity); // 미리 LAZY loading = open-in-view 의미 없음

        return dto; // lazy loading 발생 안함 : 서비스단에서 필요한 데이터를 다 만들어서 가져감 (비영속 상태로!!)
    }

    public BoardDetailRespDto 좋아요포함상세보기(Integer id) {
        return boardImplRepository.mFindDetail(id);
    }

    public List<BoardDetailRespDto> 전체보기() {
        return boardImplRepository.mFindAll();
        // return boardImplRepository.mFindAllQLRM();
    }
}

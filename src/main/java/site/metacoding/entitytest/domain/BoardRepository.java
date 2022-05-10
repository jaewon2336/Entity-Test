package site.metacoding.entitytest.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    // jpaRepository는 interface임 CRUD를 제공해줌, @Repository가 내부적으로 붙어있음
    // interface 구현되지 않은 추상 메서드를 갖고있음 -> implements 구현하겠다!
}

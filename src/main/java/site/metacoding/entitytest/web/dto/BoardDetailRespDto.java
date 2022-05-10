package site.metacoding.entitytest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDetailRespDto { // 엔티티를 리턴하지 않기 위해 dto 생성
    private Integer id;
    private String title;
    private String content;
    private boolean isLove; // Board 엔티티를 넣지않고 dto로 만드니까 isLove 추가 가능

    // 만약 user 오브젝트를 넣어야한다면 내부 클래스로 UserDto를 만들어준다.
    // 필요한게 있다면 내부 클래스로 만들자

    // private BoardDto board;
    // private boolean isLove;

    // class BoardDto {
    // private Integer id;
    // private String title;
    // private String content;
    // }
}

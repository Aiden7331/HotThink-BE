package skhu.ht.hotthink.api.domain;

import lombok.Builder;
import lombok.Data;
import skhu.ht.hotthink.api.domain.enums.BoardType;

import javax.persistence.*;

@Data
@Entity(name = "Like")
@Table(name = "TB_LIKE")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LK_SEQ")
    private Long seq;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="UR_SEQ")
    private User user;
    @Column(name="BOARD_SEQ")
    private Long bdSeq;
    @Enumerated(EnumType.STRING)
    @Column(name="BOARD_TYPE")
    private BoardType boardType;

    @Builder(builderClassName = "ByCreateBuilder", builderMethodName = "ByCreateBuilder")
    public Like(User user, Long bdSeq, BoardType boardType) {
        this.user = user;
        this.bdSeq = bdSeq;
        this.boardType = boardType;
    }
}

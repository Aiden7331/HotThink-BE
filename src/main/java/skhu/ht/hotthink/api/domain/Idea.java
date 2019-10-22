package skhu.ht.hotthink.api.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TB_IDEA")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEQ")
    private Long idSeq;
    private Long seq;
    private Integer hits;
    private String title;
    @Enumerated(EnumType.STRING)
    private IdeaState state;
    private Integer myScore;
    private Integer sellerScore;
    private Date createAt;
    private Date updateAt;
    private String contents;
    private String review;
    private String pmaterial;

    @ManyToOne
    @JoinColumn(name = "CT_CODE")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "UR_SEQ")
    private User user;

}

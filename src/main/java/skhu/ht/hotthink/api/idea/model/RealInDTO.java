package skhu.ht.hotthink.api.idea.model;

import lombok.Data;
import skhu.ht.hotthink.api.domain.IdeaState;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class RealInDTO {
    private String title;
    private String contents;
    @Enumerated(EnumType.STRING)
    private IdeaState state;
    private String review;
    private String pmaterial;
    private String image;
}
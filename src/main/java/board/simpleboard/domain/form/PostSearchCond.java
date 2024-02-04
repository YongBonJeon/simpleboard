package board.simpleboard.domain.form;

import lombok.Data;

@Data
public class PostSearchCond {
    private String title;

    public PostSearchCond() {
    }

    public PostSearchCond(String title) {
        this.title = title;
    }
}

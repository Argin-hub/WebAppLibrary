package my.library.entity;

import java.util.Date;

public class Topic extends BaseEntity {
    private String name;
private String comment;
private int numbUserForum;

    public int getNumbUserForum() {
        return numbUserForum;
    }

    public void setNumbUserForum(int numbUserForum) {
        this.numbUserForum = numbUserForum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

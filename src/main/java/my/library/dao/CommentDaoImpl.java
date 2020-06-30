package my.library.dao;

import my.library.entity.Comment;
import my.library.entity.Topic;
import my.library.entity.User;
import my.library.util.SqlDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends BaseDao<Comment>{

    private static final String INSERT = "insert into topic_message values(id_forum_user,?, ?, ?, ?)";
    private static final String DELETE = "delete from topic_message where id_forum_user = ?";
    private static final String FIND_ALL_COMMENTS = "select id_forum_user, comment, date, user_id from topic_message where forum_id = ?";

    public List<Comment> allComments(int id){
        List<Comment> comments = new ArrayList<>();

        try {
            try(PreparedStatement statement = getConnection().prepareStatement(FIND_ALL_COMMENTS)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    Comment comment = new Comment();
                  //  Topic topic = new Topic();
                    User user = new User();
                    comment.setNumbTopic(resultSet.getInt(1));
                    comment.setMessage(resultSet.getString(2));
                    comment.setDate(resultSet.getDate(3));
                    user.setId(resultSet.getInt(4));
                    comment.setUser(user);
                    comments.add(comment);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }


    @Override
    public Comment insert(Comment item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT)) {
                statement.setString(1,item.getMessage());
                statement.setDate(2, SqlDate.currentDateAndTime());
                //   statement.setInt(3, item.getUser().getId());
                statement.setInt(3, item.getUser().getId());
                statement.setInt(4, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public Comment findById(int id) throws Exception {
        return null;
    }

    @Override
    public void update(Comment item) throws Exception {

    }

    @Override
    public void delete(Comment item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't delete author " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }



}

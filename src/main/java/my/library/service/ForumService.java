package my.library.service;

import my.library.dao.*;
import my.library.entity.Comment;
import my.library.entity.Topic;
import my.library.entity.Person;
import my.library.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ForumService {

    public void createTopic(String item){
        try {
            try(DaoFactory daoFactory = new DaoFactory()) {
                TopicDaoImpl topicDao = daoFactory.getTopicDaoImpl();
                Topic topic = new Topic();
                topic.setName(item);
                topicDao.insert(topic);
                //mySqlAuthor.insert(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Topic> showAllTopic(){
        List<Topic> topics = new ArrayList<>();
        try {
            try(DaoFactory daoFactory = new DaoFactory()) {
                TopicDaoImpl topicDao = daoFactory.getTopicDaoImpl();
                topics = topicDao.allTopics();
                //mySqlAuthor.insert(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topics;
    }

    public List<Comment> showAllComments(int id){
        List<Comment> comments;
        List<Comment> comments2 = new ArrayList<>();
        try {
            try(DaoFactory daoFactory = new DaoFactory()) {
                CommentDaoImpl commentDao = daoFactory.getCommentDaoImpl();
                UserDaoImpl userDaoImpl = daoFactory.getUserDao();

                comments = commentDao.allComments(id);
                for(Comment comment : comments){
                    User user;
                    Comment mess = new Comment();
                    user = userDaoImpl.findById(comment.getUser().getId());
                    fillUser(user);
                    mess.setNumbTopic(comment.getNumbTopic());
                    mess.setUser(user);
                    mess.setDate(comment.getDate());
                    mess.setMessage(comment.getMessage());
                    comments2.add(mess);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments2;
    }

    private void fillUser(User user) throws Exception {
        try {
            if (user != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {

                    PersonDaoImpl personDaoImpl = daoFactory.getPersonDao();
                    UserRoleImplDao userRoleImplDao = daoFactory.getUserRoleDao();

                    Person person = personDaoImpl.findByUser(user);
                    user.setPerson(person);
                    user.setUserRole(userRoleImplDao.findByUser(user));
                }
            }
        } catch (Exception e) {
            throw new Exception("Can't fill user ", e);
        }
    }

    public void createComment(Comment item){
        try {
            try(DaoFactory daoFactory = new DaoFactory()) {
                CommentDaoImpl commentDao = daoFactory.getCommentDaoImpl();
                commentDao.insert(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteComment(Comment item){

        try {
            try(DaoFactory daoFactory = new DaoFactory()) {
                CommentDaoImpl commentDao = daoFactory.getCommentDaoImpl();
                commentDao.delete(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package main;


import main.java.model.dao.GroupDao;
import main.java.model.dao.UsersDao;
import main.java.model.entity.Group;
import main.java.model.entity.Users;
import main.java.model.impl.GroupDaoImpl;
import main.java.model.impl.UsersDaoImpl;
import main.java.services.DataSourceFactory;

import javax.sql.DataSource;

public class Main {

    private static UsersDao studentDao;
    private static GroupDao groupDao;
//    private static LessonDao lessonDao;
//    private static JournalDao journalDao;

    public static void main(String[] args) {

        DataSource dataSource = DataSourceFactory.getMyPGDataSource();
        groupDao = new GroupDaoImpl(dataSource);
        studentDao = new UsersDaoImpl(dataSource);
//        lessonDao = new LessonDaoImpl(dataSource);
//        journalDao = new JournalDaoImpl(dataSource);

       for (Group group : groupDao.findAll()) {
           System.out.println(group);
           System.out.println();
        }

        for (Users student : studentDao.findAll()) {
            System.out.println(student);
//            if (users.getGroup() != null) {
//                System.out.println(users.getGroup());
            }
            System.out.println();

        }

//        for (Lesson lesson : lessonDao.findAll()) {
//            System.out.println(lesson);
//            if (lesson.getGroup() != null) {
//                System.out.println(lesson.getGroup());
//            }
//            System.out.println();
//        }
//
//        for (Journal journal : journalDao.findAll()) {
//            System.out.println(journal);
//            if (journal.getLesson() != null)
//                System.out.println(journal.getLesson());
//
//            if (journal.getStudent() != null)
//                System.out.println(journal.getStudent());
//            System.out.println();
//        }
    }

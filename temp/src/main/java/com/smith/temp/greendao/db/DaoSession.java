package com.smith.temp.greendao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.smith.temp.greendao.Student;
import com.smith.temp.greendao.Teacher;

import com.smith.temp.greendao.db.StudentDao;
import com.smith.temp.greendao.db.TeacherDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig teacherDaoConfig;

    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(Teacher.class, teacherDao);
    }
    
    public void clear() {
        studentDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

}

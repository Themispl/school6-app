package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;

public class TeacherSAOImpl implements ITeacherDAO{
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDaoException {
        return null;
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDaoException {
        return null;
    }

    @Override
    public void delete(Integer id) throws TeacherDaoException {

    }

    @Override
    public Teacher getById(Integer id) throws TeacherDaoException {
        return null;
    }

    @Override
    public List<Teacher> getByLastName(String lastname) throws TeacherDaoException {
        return null;
    }
}

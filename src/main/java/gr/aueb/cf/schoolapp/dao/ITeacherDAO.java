package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;

public interface ITeacherDAO {
    Teacher insert(Teacher teacher) throws TeacherDaoException;
    Teacher update(Teacher teacher) throws TeacherDaoException;
    void delete(Integer id) throws  TeacherDaoException;
    Teacher getById(Integer id) throws TeacherDaoException;
    List<Teacher> getByLastName(String lastname) throws TeacherDaoException;
}

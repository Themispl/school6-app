package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDaoException;
    Teacher updateTeacher(TeacherInsertDTO dto) throws TeacherDaoException, TeacherNotFoundException;
    void deleteTeacher(Integer id) throws TeacherDaoException, TeacherNotFoundException;
    Teacher getTeacherById(Integer id) throws TeacherDaoException, TeacherNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws TeacherDaoException;
}



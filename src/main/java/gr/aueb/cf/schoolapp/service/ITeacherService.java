package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherNotFoundException, TeacherDAOException;
    void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException;
}



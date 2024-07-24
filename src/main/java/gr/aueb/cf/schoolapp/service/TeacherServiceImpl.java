package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService{
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDaoException {
        Teacher teacher;
        try{
            teacher = mapToTeacher(dto);
            return teacherDAO.insert(teacher);

        }catch(TeacherDaoException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherInsertDTO dto) throws TeacherDaoException, TeacherNotFoundException {
        Teacher teacher;
        try{
            teacher = mapToTeacher(dto);

            if(teacherDAO.getById(teacher.getId()) == null){
                throw new TeacherNotFoundException(teacher);
            }

            return teacherDAO.update(teacher);

        }catch(TeacherDaoException | TeacherNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherDaoException, TeacherNotFoundException {
        try{
            if(teacherDAO.getById(id) == null){
                throw new TeacherNotFoundException("Not found");
            }
            teacherDAO.delete(id);
        }catch(TeacherDaoException | TeacherNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) throws TeacherDaoException, TeacherNotFoundException {
       Teacher teacher;
        try{
        teacher = teacherDAO.getById(id);
        if(teacher == null){
            throw new TeacherNotFoundException("Teacher Not found with id" + id);
        }
        return teacher;
    }catch(TeacherDaoException | TeacherNotFoundException e){
        e.printStackTrace();
        throw e;
    }
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws TeacherDaoException {
        List<Teacher> teachers;
        try{
            teachers = teacherDAO.getByLastName(lastname);
            return teachers;
        }catch(TeacherDaoException e){
            e.printStackTrace();
            throw e;
        }
    }

    private Teacher mapToTeacher(TeacherInsertDTO dto){
        return new Teacher(null, dto.getFirstname(), dto.getLastname());
    }
    private Teacher mapToTeacher(TeacherUpdateDTO dto){
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}

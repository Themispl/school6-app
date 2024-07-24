package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.io.Serial;

public class TeacherNotFoundException  extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(Teacher teacher){
        super("teacher with id: " + teacher.getId() + "Not found");

    }

    public TeacherNotFoundException(String s){
        super(s);
    }
}

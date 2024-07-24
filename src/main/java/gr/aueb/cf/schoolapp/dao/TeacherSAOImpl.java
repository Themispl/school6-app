package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDaoException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherSAOImpl implements ITeacherDAO{
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDaoException {
        String sql ="INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            //extract model info
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            int n = ps.executeUpdate();
            //Logging prepei na ginete se kate CRUS praxi
            return teacher;
        }catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDaoException("SQL Error in teacher insert: "+ teacher);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDaoException {
        String sql ="UPDATE INTO TEACHERS SET FIRSTNAME=? , LASTNAME =? WHERE ID= ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            //extract model info
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int n = ps.executeUpdate();

            //Logging prepei na ginete se kate CRUD praxis
            return teacher;
        }catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDaoException("SQL Error in teacher update: "+ teacher);
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDaoException {
            String sql = "DELETE FROM TEACHERS WHERE ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDaoException("SQL Error in teacher delete with id: "+ id);
        }
    }

    @Override
    public Teacher getById(Integer id) throws TeacherDaoException {
        String sql = "SELECT * FROM TEACHERS WHERE ID = ?";
        Teacher teacher = null;
        ResultSet rs;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()){
                teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"));
            }

            return  teacher;
        }catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDaoException("SQL Error in teacher get by id with id: "+ id);
        }
    }

    @Override
    public List<Teacher> getByLastName(String lastname) throws TeacherDaoException {
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"));
            teachers.add(teacher);
        }return teachers;
        }catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDaoException("SQL Error in teacher delete with id: "+ lastname);
        }
    }
}

package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void constractorGettersAndSetters(){
        Teacher teacher = new Teacher();

        teacher.setId(1);
        assertEquals(1, teacher.getId());

        teacher.setFirstname("Themis");
        assertEquals("Themis", teacher.getFirstname());

        teacher.setLastname("pilarinos");
        assertEquals("pilarinos", teacher.getLastname());
    }

    @Test
    void constractorOveloadAndToString() {
        Teacher teacher = new Teacher(1, "Anna", "Gianoutsou");
        assertEquals(1, teacher.getId());
        assertEquals("Anna", teacher.getFirstname());
        assertEquals("Gianoutsou", teacher.getLastname());

        String expected = "id=1, firstname=Anna, lastname=Gianoutsou";
        assertEquals(expected, teacher.toString());

    }
}
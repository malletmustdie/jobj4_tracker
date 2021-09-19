package ru.job4j.stream.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class SchoolTest {

    @Test
    public void whenCollectClassA() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(40, "Surname4"),
                new Student(50, "Surname5"),
                new Student(70, "Surname7"),
                new Student(90, "Surname9")
        );
        School sc = new School();
        Predicate<Student> pr = student -> student.getScore() >= 70 && student.getScore() < 100;
        List<Student> rsl = sc.collect(students, pr);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(70, "Surname7"));
        expected.add(new Student(90, "Surname9"));
        Assert.assertThat(rsl, Matchers.is(expected));
    }

    @Test
    public void whenCollectClassB() {
        List<Student> students = List.of(
                new Student(20, "Surname2"),
                new Student(30, "Surname3"),
                new Student(50, "Surname5"),
                new Student(60, "Surname6"),
                new Student(80, "Surname8")
        );
        School sc = new School();
        Predicate<Student> pr = student -> student.getScore() >= 50 && student.getScore() < 70;
        List<Student> rsl = sc.collect(students, pr);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(50, "Surname5"));
        expected.add(new Student(60, "Surname6"));
        Assert.assertThat(rsl, Matchers.is(expected));
    }

    @Test
    public void whenCollectClassV() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(30, "Surname3"),
                new Student(40, "Surname4"),
                new Student(60, "Surname6"),
                new Student(90, "Surname9")
        );
        School sc = new School();
        Predicate<Student> pr = student -> student.getScore() > 0 && student.getScore() < 50;
        List<Student> rsl = sc.collect(students, pr);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(10, "Surname1"));
        expected.add(new Student(30, "Surname3"));
        expected.add(new Student(40, "Surname4"));
        Assert.assertThat(rsl, Matchers.is(expected));
    }

    @Test
    public void whenCollectStudentsListToMap() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(10, "Surname1"),
                new Student(20, "Surname2"),
                new Student(30, "Surname3"),
                new Student(40, "Surname4"),
                new Student(40, "Surname4"),
                new Student(40, "Surname4"),
                new Student(50, "Surname5")
        );
        School sc = new School();
        Map<String, Student> actual = sc.collectToMap(students);
        Assert.assertNotNull(actual.values());
        Assert.assertEquals(5, actual.size());
        Assert.assertEquals(students.get(5), actual.get(students.get(5).getSurname()));
    }

}
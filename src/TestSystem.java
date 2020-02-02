// CLASS: TestSystem
//
// Author: Patel Zil, 7876456
//
// REMARKS: Tests on data structure and other
//          implemented objects and its methods
//
//-----------------------------------------

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestSystem
{
    //====================== DATA STRUCTURE TESTS ======================
    @Test
    public void testCreateList()
    {
        List testList = new List();

        // check to see that the list has been created by making the top of the list point to null
        assertNull(testList.getTop(), "Should return null.");
    }

    @Test
    public void testInsert() {
        List testList = new List();

        Tutor testTutor = new Tutor("tut1", 10);
        Tutor testTutor2 = new Tutor("tut2", 10);
        Tutor testTutor3 = new Tutor("tut3", 10);

        testList.insertFront(testTutor);
        testList.insertFront(testTutor2);
        testList.insertFront(testTutor3);

        // check that all three were inserted
        assertEquals(3, testList.length(), "Length of the list should be 3.");

        // check that it does not insert duplicate
        testList.insertFront(testTutor2);
        assertEquals(3, testList.length(), "Length of the list should be 3 after adding duplicate tutor.");

    }

    @Test
    public void testLength()
    {
        List testList = new List();
        assertEquals(0, testList.length(), "Length should be zero when list is initialized.");

        Student testStudent = new Student("stud1");
        Student testStudent2 = new Student("stud2");
        Student testStudent3 = new Student("stud3");
        testList.insertFront(testStudent);
        assertEquals(1, testList.length(), "Length of the list should be 1.");

        testList.insertFront(testStudent2);
        testList.insertFront(testStudent3);
        assertEquals(3, testList.length(), "Length of the list should be 3.");
    }

    @Test
    public void testSearch()
    {
        List testList = new List();

        Student testStudent = new Student("stud1");
        Student testStudent2 = new Student("stud2");
        Student testStudent3 = new Student("stud3");

        testList.insertFront(testStudent);
        testList.insertFront(testStudent2);
        testList.insertFront(testStudent3);

        // check for students in the list
        assertEquals("stud1", testList.search("stud1").getData().getName() , "Should return 'stud1' from the list.");
        assertEquals("stud3", testList.search("stud3").getData().getName() , "Should return 'stud3' from the list.");

        // check for student not in the list
        assertNull(testList.search("stud4"), "Should return null.");
    }

    @Test
    public void testGetTop()
    {
        List testList = new List();

        Tutor testTutor = new Tutor("tut1" , 10);
        Tutor testTutor2 = new Tutor("tut2", 10);
        Tutor testTutor3 = new Tutor("tut3", 10);

        testList.insertFront(testTutor);
        testList.insertFront(testTutor2);
        testList.insertFront(testTutor3);

        // confirm the top tutor is the correct one.
        assertEquals("tut3", testList.getTop().getData().getName(), "Should be \"tut3\" when top is retrieved");

        // check to see if the first Tutor inserted is not at the top.
        assertNotEquals("tut1", testList.getTop().getData().getName(), "Should be \"tut3\" when top is retrieved" );
    }

    @Test
    public void testGetData()
    {
        List testList = new List(); // to store all types of objects in one list

        Student testStudent = new Student("TestStud1");
        Tutor testTutor = new Tutor("TestTutor", 20);
        Topic testTopic = new Topic("OO", 11);

        testList.insertFront(testStudent);
        testList.insertFront(testTutor);

        // check if the first node returns Tutor object
        assertSame(testTutor, testList.getTop().getData(), "Should be a Tutor object");

        // check to see if the 2nd node returns a Student object
        assertSame(testStudent, testList.getTop().getNext().getData(), "Should be a Student object");

        // check if after a third insert the returned object is correct
        testList.insertFront(testTopic);
        assertNotSame(testTutor, testList.getTop().getData(), "Should be a Topic object.");
    }

    //============================= OTHER TESTS ===============================
    //-------------------- Test the objects and its methods -------------------

    @Test
    public void testStudent()
    {
        Student testStudent = new Student("Student1");

        // Test if the userID is equal to the object's
        assertTrue(testStudent.equals("Student1"), "Should be equal.");
        assertFalse(testStudent.equals("Student2"), "Should not be equal.");
    }

    @Test
    public void testTutor()
    {
        Tutor testTutor = new Tutor("Tutor1", 15);
        Topic testTopic = new Topic("OO", 12);
        Topic testTopic1 = new Topic("Java", 12);

        // Test (addTopic) if topic is added to the Tutor's list of topics
        testTutor.addTopic(testTopic);
        assertSame(testTopic, testTutor.getTopics().getTop().getData(), "Should be Topic");

        // Test if the tutor teaches a topic
        testTutor.addTopic(testTopic1);
        assertTrue(testTutor.hasTopic("Java"), "Should contain 'Java' as a topic.");

        // Test (hasTopic) to see if a Topic that is not in our topicList is shown as a topic for the tutor
        assertFalse(testTutor.hasTopic("Statistics"), "Should not contain 'Statistics'");
    }

    @Test
    public void testTopic()
    {
        Topic testTopic = new Topic("OO", 12);

        // Test equals method for Topic
        assertTrue(testTopic.equals("OO"), "Should be equal.");
        assertFalse(testTopic.equals("Stats"), "Should not be equal.");
    }

    @Test
    public void testRequest()
    {
        List tutors = new List();
        List students = new List();

        Tutor testTutor1 = new Tutor("Tutor1", 30);
        Tutor testTutor2 = new Tutor("Tutor2",  35);
        Tutor testTutor3 = new Tutor("Tutor3", 20);
        Tutor testTutor4 = new Tutor("Tutor4", 20);

        Student student1 = new Student("Student1");

        Topic testTopic1 = new Topic("OO", 6);
        Topic testTopic2 = new Topic("Space X", 12);
        Topic testTopic3 = new Topic("OO", 7);

        tutors.insertFront(testTutor1);
        tutors.insertFront(testTutor2);
        tutors.insertFront(testTutor3);
        tutors.insertFront(testTutor4);

        students.insertFront(student1);

        testTutor1.addTopic(testTopic1);
        testTutor2.addTopic(testTopic2);
        testTutor3.addTopic(testTopic3);
        testTutor4.addTopic(testTopic3);

        Request request1 = new Request("OO", 30);
        List topicList1 = request1.findTopicTutor(tutors);
        List topicList2 = request1.findLeastRateTutor(topicList1);
        List topicList3 = request1.findMostHoursTutor(topicList2);
        List topicList4 = request1.scheduleHours(tutors, student1);

        // Test if list of Tutors teaching OO is null or not
        assertNotNull(topicList1, "Should not give a null list.");
        // Test if list of Tutors with least rate is not null
        assertNotNull(topicList2, "Should not give a null list.");
        // Test if list of Tutors with most hours is not null
        assertNotNull(topicList3, "Should not give a null list.");
        // Test to check whether the tutor assigned is the one we are looking for
        assertSame(testTutor1, topicList4.getTop().getData(), "Should be the right Tutor.");

        Request request2 = new Request("Java" , 20);
        List topicList5 = request2.scheduleHours(tutors, student1);
        // Test if the topic requested does not exist.
        assertNull(topicList5.getTop(), "Should be null.");

        // Test to see if request is processed with more than available hours.
        Request request3 = new Request("OO" , 80);
        List topicList6 = request3.scheduleHours(tutors, student1);
        assertNull(topicList6.getTop(), "Should be null.");
    }

    @Test
    public void testStudentReport()
    {
        List tutors = new List();
        List students = new List();

        Tutor testTutor1 = new Tutor("Tutor1", 30);

        Student testStudent = new Student("student1");

        tutors.insertFront(testTutor1);
        students.insertFront(testStudent);

        Topic testTopic1 = new Topic("OO", 6);

        testTutor1.addTopic(testTopic1);

        Request request1 = new Request("OO", 30);
        request1.processRequest(tutors, students,"student1");
        // test to check if StudentReport creates a valid StudentReport obj and then adds to list in a tutor
        assertEquals("student1", testTutor1.getStudentList().getTop().getData().getName(), "Should be the same." );
    }

    @Test
    public void testTutorReport()
    {
        List tutors = new List();
        List students = new List();

        Tutor testTutor1 = new Tutor("Tutor1", 30);

        Student testStudent = new Student("student1");

        tutors.insertFront(testTutor1);
        students.insertFront(testStudent);

        Topic testTopic1 = new Topic("OO", 6);

        testTutor1.addTopic(testTopic1);

        Request request1 = new Request("OO", 20);
        request1.processRequest(tutors, students,"student1");
        // test to check if TutorReport creates a valid TutorReport obj and then adds to list in student
        assertEquals("Tutor1", testStudent.getTutorList().getTop().getData().getName(), "Should be the same." );
    }
}// end TestSystem class

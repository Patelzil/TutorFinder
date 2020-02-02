//CLASS: Student
//
// Author: Patel Zil, 7876456
//
// REMARKS: Create a StudentReport object to store
////         the Student that was assigned to a tutor during the request
//
//-----------------------------------------

public class StudentReport extends ListData
{
    private Student myStudent;
    private int totalCost;
    private int hours;
    private String topic;

    public StudentReport(Student student, int cost, int hour, String newTopic)
    {
        myStudent = student;
        totalCost = cost;
        hours = hour;
        topic = newTopic;
    }

    public Student getMyStudent() { return myStudent; }
    public String getName(){ return myStudent.getName(); }
    public int getHours() { return hours; }

    public void printDetail()
    {
        System.out.println("Appointment: Student: " + myStudent.getName() + ", topic: " + topic
                    + ", hours: " + hours + ", total cost: " + totalCost);
    }
}// end StudentReport class

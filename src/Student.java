// CLASS: Student
//
// Author: Patel Zil, 7876456
//
// REMARKS: Create a student and store the tutors
//          that the student will be taught by.
//          Also print all of the students details
//
//-----------------------------------------

public class Student extends ListData
{
    private String userID;
    private int totalCost;
    private List tutorList;

    // constructor
    public Student(String newID)
    {
        userID = newID;
        tutorList = new List();
        totalCost = 0;
    }

    // getters
    public String getName() { return userID; }
    public List getTutorList() { return tutorList; }

    // setters
    public void setTotalCost(int totalCost) { this.totalCost += totalCost; }

    public boolean equals(String key)
    {
        return this.userID.equals(key);
    }// end equals

    public void processStudent(List list)
    {
        if(list.search(userID) == null)
        {
            System.out.println("Student with userID " + userID + " successfully created.");
            list.insertFront(this);
        }
        else
        {
            System.out.println("Duplicate Student with userID " + userID + ".");
        }
    }

    public void studentReport()
    {
        List.ListIterator iterator = tutorList.iterator();
        int totalHours = 0;

        while(iterator.hasNext())
        {
            TutorReport myTutor = (TutorReport) iterator.next().getData();
            totalHours += myTutor.getHours();
            myTutor.printDetail();
        }

        System.out.println("Total number of hours of tutoring: " + totalHours + "\nTotal cost of Tutoring: " + totalCost + "\n");
    }

    public void printDetail()
    {
        System.out.println("Student: " + userID);

    }// end printDetail

}// end Student class

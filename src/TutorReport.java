//CLASS: Student
//
// Author: Patel Zil, 7876456
//
// REMARKS: Create a TutorReport object to store
//         the tutor that was chosen during the request
//
//-----------------------------------------

public class TutorReport extends ListData
{
    private Tutor myTutor;
    private int totalRevenue;
    private int hours;
    private String topic;

    public TutorReport(Tutor tutor, int revenue, int hour, String newTopic)
    {
        myTutor = tutor;
        totalRevenue = revenue;
        hours = hour;
        topic = newTopic;
    }

    public Tutor getMyTutor() { return myTutor; }
    public String getName(){ return myTutor.getName(); }
    public int getHours() { return hours; }

    public void printDetail()
    {
        System.out.println("Appointment: Tutor: " + myTutor.getName() + ", topic: " + topic + ", hours: " +
               hours + ", total cost: " + totalRevenue);
    }
}// end TutorReport class

// CLASS: Request
//
// Author: Patel Zil, 7876456
//
// REMARKS: Processes the whole request for a student
//         to find tutors for a given topic and given
//         number of hours.
//
//-----------------------------------------

// check in processRequest is wrong
public class Request
{
    private String topicRequested;
    private int hoursRequested;

    public Request(){  }

    public Request(String topic, int hours)
    {
        topicRequested = topic;
        hoursRequested = hours;
    }// end Request


    /*
     * processRequest()
     *
     * Purpose: Checks if the request was complete or not and prints the necessary statements.
     *
     */
    public void processRequest( List tutorList, List studentList, String stdName)
    {
        Student student;

        if (studentList.search(stdName) == null)
        {
            System.out.println("Student " + stdName + " not found.");
        }
        else
        {
            student = (Student) studentList.search(stdName).getData();
            System.out.println("\nAttempting to fulfil request for " + student.getName() + " to receive " + hoursRequested +
                    " hours of tutoring in topic " + topicRequested + ".");
            List result = scheduleHours(tutorList, student);
            if (result.getTop() != null)
            {
                System.out.println("SUCCESS in completing request");
            }
            else if (result.getTop() == null)
            {
                System.out.println("No tutors available for student " + student.getName() + " for "
                        + hoursRequested + " hours in topic " + topicRequested + ".\nFAILED to complete request.");

            }
        }
    }// end processRequest

    /*
     * scheduleHours
     *
     * return suitable tutor list if a student was able to be designated all the required hours
     * or else it returns null
     *
     */
    public List scheduleHours(List tutorList, Student student)
    {
        List temp1, temp2, temp3;
        Tutor tutor1, tutor2;
        List result = new List();

        temp1 = findTopicTutor(tutorList);
        int sum = sumHours(temp1);
        while (hoursRequested > 0 && sum >= hoursRequested)
        {
            if (temp1.length() > 1)
            {
                temp2 = findLeastRateTutor(temp1);
                if (temp2.length() > 1)
                {
                    temp3 = findMostHoursTutor(temp2);
                    if (temp3.length() > 1)
                    {
                        tutor1 = findFirstTutor(temp3);
                        result.insertFront(tutor1);
                        processHours(tutor1, student, Math.min(tutor1.getNumHours(), hoursRequested));
                        printRequest(tutor1, student);
                    }
                    else if (temp3.length() == 1)
                    {
                        if (temp3.getTop().getData() instanceof Tutor)
                        {
                            tutor2 = (Tutor) temp3.getTop().getData();
                            result.insertFront(tutor2);
                            processHours(tutor2, student, Math.min(tutor2.getNumHours(), hoursRequested));
                            printRequest(tutor2, student);
                        }
                    }
                }
                else if(temp2.length() == 1)
                {
                    if (temp2.getTop().getData() instanceof Tutor)
                    {
                        tutor2 = (Tutor) temp2.getTop().getData();
                        result.insertFront(tutor2);
                        processHours(tutor2, student, Math.min(tutor2.getNumHours(), hoursRequested));
                        printRequest(tutor2, student);
                    }
                }
            }
            else if (temp1.length() == 1)
            {
                if (temp1.getTop().getData() instanceof Tutor)
                {
                    tutor2 = (Tutor) temp1.getTop().getData();
                    result.insertFront(tutor2);
                    processHours(tutor2, student, Math.min(tutor2.getNumHours(), hoursRequested));
                    printRequest(tutor2, student);
                }
            }
            temp1 = findTopicTutor(tutorList);
            sum = sumHours(temp1);
        }

        return result;
    }// end scheduleHours

    /*
     * deductHours
     *
     * reduces hours from the tutor and the student's requested hours
     */
    public void processHours(Tutor newTutor, Student student,int hours)
    {
        Topic topic = (Topic) newTutor.getTopics().search(topicRequested).getData();
        hoursRequested = hoursRequested - hours;

        // process the request in Tutor
        newTutor.setNumHours(newTutor.getNumHours()-hours);
        newTutor.setHoursUsed(hours);
        newTutor.setTotalRevenue(hours*topic.getRatePerHour());
        newTutor.getStudentList().insertFront(new StudentReport(student, hours*topic.getRatePerHour(), hours, topicRequested));

        // process the request in the student
        student.setTotalCost(hours*topic.getRatePerHour());
        student.getTutorList().insertFront(new TutorReport(newTutor, (hours*topic.getRatePerHour()), hours, topicRequested));
    }// end deductHours

    /*
     * findTopicTutor
     *
     * returns linkedList of the tutors that teach the requested topic,
     * and have more than 0 hours to tutor
     */
    public List findTopicTutor(List tutorList)
    {
        List.ListIterator iterator = tutorList.iterator();
        List tutors = new List();

        while(iterator.hasNext())
        {
            Tutor myTutor = (Tutor) iterator.next().getData();
            if (myTutor.hasTopic(topicRequested) && myTutor.getNumHours() > 0 )
            {
                tutors.insertFront(myTutor);
            }
        }

        return tutors;
    }// end findTopicTutor

    /*
     * findLeastRateTutor
     *
     * (1st criteria while requesting hours from available tutors for a topic)
     *
     *  return a linked List of tutors with the least amount of rate per hour for the requested topic
     */
    public List findLeastRateTutor(List tutorList)
    {
        List.ListIterator iterator = tutorList.iterator();
        List list = new List();
        int rate = 1000;

        // find the minimum rate
        while(iterator.hasNext())
        {
            Tutor currTutor = (Tutor) iterator.next().getData();
            Topic tutorTopic = (Topic) currTutor.getTopics().search(topicRequested).getData();

            if(tutorTopic.getRatePerHour() <= rate)
            {
                rate = tutorTopic.getRatePerHour();
            }
        }

        // find all the tutors that have the lowest and same rate for a topic
        iterator = tutorList.iterator();
        while(iterator.hasNext())
        {
            Tutor currTutor = (Tutor) iterator.next().getData();
            Topic tutorTopic = (Topic) currTutor.getTopics().search(topicRequested).getData();

            if(tutorTopic.getRatePerHour() == rate)
            {
                list.insertFront(currTutor);
            }
        }

        return list;
    }

    /*
     * findMostHoursTutor
     *
     * returns linked list of tutors with more hours to offer from
     * a list of tutors with the least rate per hour for a topic
     */
    public List findMostHoursTutor(List tutorList)
    {
        List.ListIterator iterator = tutorList.iterator();
        List list = new List();
        int tempHour = 0;

        while(iterator.hasNext())
        {
            Tutor currTutor = (Tutor) iterator.next().getData();
            if(currTutor.getNumHours() >= tempHour)
            {
                tempHour = currTutor.getNumHours();
            }
        }

        iterator = tutorList.iterator();
        while(iterator.hasNext())
        {
            Tutor currTutor = (Tutor) iterator.next().getData();
            if(currTutor.getNumHours() == tempHour)
            {
                list.insertFront(currTutor);
            }
        }

        return list;
    }// findMostHoursTutor

    /*
     * findFirstTutor
     *
     * checks for the tutor whose name appears first alphabetically
     * and returns the tutor
     */
    public Tutor findFirstTutor(List tutorList)
    {
        Node curr = tutorList.getTop();
        Tutor tutorAns = (Tutor) curr.getData();

        while(curr != null)
        {
            if(curr.getNext() != null)
            {
                if(tutorAns.getName().compareTo(curr.getNext().getData().getName()) > 0)
                {
                    tutorAns = (Tutor) curr.getNext().getData();
                }
            }
            curr = curr.getNext();
        }

        return tutorAns;
    }// end findFirstTutor

    public int sumHours(List tutorList)
    {
        List.ListIterator iterator = tutorList.iterator();
        int result = 0;

        while(iterator.hasNext())
        {
            result += ((Tutor) iterator.next().getData()).getNumHours();
        }

        return result;
    }// end sumHours

    public void printRequest(Tutor tutor, Student student)
    {
        System.out.println("Tutor " + tutor.getName() + " will tutor " + student.getName() + " for " +
                tutor.getHoursUsed() + " hours in " + topicRequested + " at a rate of " +
                ((Topic) tutor.getTopics().search(topicRequested).getData()).getRatePerHour());
    }

}// end Request class

// CLASS: Simulator
//
// Author: Patel Zil, 7876456
//
// REMARKS: Read a file of commands and perform requested
//         operations.
//
//-----------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Simulator
{
    List studentList;
    List tutorList;

    public Simulator()
    {
        studentList = new List();
        tutorList = new List();
    }

    /*
     * processFile
     *
     * purpose: Prompts for file from the user and reads it
     */
    public void processFile()
    {
        Scanner keyboard;
        String fileName;
        BufferedReader inFile;
        String nextLine;

        keyboard = new Scanner( System.in );
        System.out.println( "\nEnter the input file name (.txt files only): " );
        fileName = keyboard.nextLine();

        try
        {
            inFile = new BufferedReader(new FileReader(fileName));
            nextLine = inFile.readLine();

            while (nextLine != null)
            {
                processLine( nextLine);
                nextLine = inFile.readLine();
            } // end while

            if(nextLine == null)
            {
                System.out.println("'END' command is missing!!");
            }

        }
        catch ( IOException e )
        {
            System.out.println( "Error reading file: " + fileName );
        }
    }// end processFile

    /*
     * processLine
     *
     * purpose: Processes each line from the file
     */
    public void processLine(String line)
    {
        String[] inTokens;
        int hours, price;
        Tutor tutor;
        Student student;
        Topic topic;
        Request request;

        inTokens = line.trim().split( " +" ); // remove leading and trailing white spaces
        if(!inTokens[0].equals("#")) {
            if (inTokens[0].equals("TUTOR"))
            {
                hours = Integer.parseInt(inTokens[2]);
                tutor = new Tutor(inTokens[1], hours);
                tutor.processTutor(tutorList);
            }
            else if (inTokens[0].equals("STUDENT"))
            {
                student = new Student(inTokens[1]);
                student.processStudent(studentList);
            }
            else if (inTokens[0].equals("TOPIC")) // command-topicName-id-price
            {
                price = Integer.parseInt(inTokens[3]);
                topic = new Topic(inTokens[1], price);
                topic.processTopic(tutorList, inTokens[2]);
            }
            else if (inTokens[0].equals("REQUEST"))
            {
                hours = Integer.parseInt(inTokens[3]);
                request = new Request(inTokens[2], hours);
                request.processRequest(tutorList, studentList, inTokens[1]);
            }
            else if (inTokens[0].equals("STUDENTREPORT"))
            {
                processStudentReport(inTokens[1]);
            }
            else if (inTokens[0].equals("TUTORREPORT"))
            {
                processTutorReport(inTokens[1]);

            }
            else if (inTokens[0].equals("END"))
            {
                System.out.println("\nBYE");
                System.exit(0);
            }
        }
    }// end processLine

    /*
     * processStudentReport
     *
     * purpose: processes student report
     *
     */
    public void processStudentReport(String name)
    {
        if(studentList.search(name) == null)
        {
            System.out.println("Student " + name + " not found.");
        }
        else
        {
            Student student = (Student) studentList.search(name).getData();
            System.out.println("\nReport for Student " + name + "\n-------------------------");
            student.studentReport();
        }
    }

    /*
     * processTutorReport
     *
     * purpose: processes Tutor report
     *
     */
    public void processTutorReport(String name)
    {
        if(tutorList.search(name) == null)
        {
            System.out.println("Tutor " + name + " not found.");
        }
        else
        {
            Tutor tutor = (Tutor) tutorList.search(name).getData();
            System.out.println("\nReport for Tutor " + name + "\n-------------------------");
            tutor.tutorReport();
        }
    }
}// end Simulator class


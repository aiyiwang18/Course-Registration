package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Readers {

	public static List<Courses> readCoursesFromCSV(String fileName) {
        List<Courses> courseList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
               
                if (values.length >= 7) {
                    String courseID = values[0];
                    String courseName = values[1];
                    
                    // Handle "NULL" or empty values for maximumStudents and courseSectionNumber
                    int maximumStudents = parseIntOrDefault(values[2], 0);
                    int courseSectionNumber = parseIntOrDefault(values[4], 0);
                    
                    String courseInstructor = values[3];
                    String courseLocation = values[5];
                   
                    int currentStudents = 0; // Default value for current students
                    List<String> listOfNames = new ArrayList<>();
            
                    Courses course = new Courses(courseName, courseID, maximumStudents, currentStudents, listOfNames,
                            courseInstructor, courseSectionNumber, courseLocation);

                    courseList.add(course);
                } else {
                    System.out.println("Invalid row format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return courseList;
    }

    private static int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PHARMACY
 */
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Questionnaire {
    Question[] questions;
    int[] answers;
    int size;
    
    void list_all(){
        int iterator = 0;
        for (Question q:questions){
            iterator++;
            System.out.println("Question # "+iterator);
            q.display();
        }
    }
    
    void ask()throws Exception{
        int iterator = 0;
        for (Question q:questions){
            iterator++;
            System.out.println("Question # "+iterator);
            q.display();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter your response.");
            
            int response;
            //add a while loop of some kind here to reject any inputs that aren't 1-9
            //use regex I guess
            response = Integer.parseInt(reader.readLine());
            answers[iterator] = response;
            
        }
    }
    
    void show_results(){
        System.out.println("The respondent's answers were: ");
        for (int a: answers){
            System.out.print(a + " ");
        }
    }
    
    Questionnaire(BufferedReader input)throws Exception{
        size = Integer.parseInt(input.readLine());
        //System.out.println(size);
        questions = new Question[size];
        answers = new int[size+1];
        int iterator = 0;
        while (input.ready()){   
            if (input.readLine().charAt(0) == '*'){
                questions[iterator] = new Question(input.readLine(),Integer.parseInt(input.readLine()), input);
                //System.out.println();
                iterator++;
            }

        }
    }
}

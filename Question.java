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
public class Question {
    String qtext;
    String[] rtext;
    char response;
    int nr;
    
    void display(){
            //System.out.println("This should echo the question and responses entered below.");        
            int iterator = 0;
            while (iterator < rtext.length){
                System.out.println(iterator +1+".) "+ rtext[iterator]); 
                iterator++;
        }
    }
    
    void answer(){
        Scanner in = new Scanner(System.in);
        System.out.println("Answer me!");
        response = in.nextLine().charAt(0);
    }
    
    Question(String question_text, int num_responses, BufferedReader input)throws Exception{
        nr = num_responses;
        //System.out.println(question_text + " " + num_responses +" " );
        qtext = question_text;
        rtext = new String[num_responses];
        int iterator = 0;
        while (iterator < num_responses){
            rtext[iterator] = input.readLine();
            //System.out.println(rtext[iterator]);
            iterator++;
        }
    }

}

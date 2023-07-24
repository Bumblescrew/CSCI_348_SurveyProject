/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
public class Survey {    
    public static void main(String[] args) throws Exception{
        BufferedReader infile = new BufferedReader(new FileReader(args[0]));
        System.out.println(args[0]);
        Questionnaire test = new Questionnaire(infile);
        test.ask();
        test.show_results();
        //test.questions[0].display();
        }
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey_server;

import java.util.Scanner;

/**
 *
 * @author Al Amin Bin Shafiq
 */


import java.io.*;
import java.net.*;

// Server class
class Survey_server {
	public static void main(String[] args)
	{
            ServerSocket server = null;
            Scanner sc= new Scanner(System.in);
            System.out.print("1- Start Server \n2- Create New Survey Form: \n");  
            int opt= sc.nextInt(); 
            if(opt == 1)
            {
                 System.out.println("Server is Started...");
                try 
                {
                    server = new ServerSocket(1234);
                    server.setReuseAddress(true);


                    while (true) 
                    {
                        Socket client = server.accept();

                        System.out.println("New client connected" + client.getInetAddress().getHostAddress());

                        ClientHandler clientSock = new ClientHandler(client);

                        new Thread(clientSock).start();
                    }
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                finally 
                {
                    if (server != null) 
                    {
                        try 
                        {
                            server.close();
                        }
                        catch (IOException e) 
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else if(opt == 2)
            {
                System.out.print("How many question you want to insert in survey: ");  
                int questions= sc.nextInt(); 
                String [][] data1 = new String[10][6];
                for (int i=0; i<questions; i++)
                {
                    System.out.print("");  
                    String name= sc.nextLine();
                    System.out.print("Enter Question: ");  
                    String q= sc.nextLine();
                    System.out.print("Enter Option1: ");  
                    String o1= sc.nextLine();
                    System.out.print("Enter Option2: ");  
                    String o2= sc.nextLine();
                    System.out.print("Enter Option3: ");  
                    String o3= sc.nextLine();
                    System.out.print("Enter Option4: ");  
                    String o4= sc.nextLine();

                    data1[i][0] = q;
                    data1[i][1] = o1;
                    data1[i][2] = o2;
                    data1[i][3] = o3;
                    data1[i][4] = o4;
                    data1[i][5] = "All above option";
                }
                for (int i=0; i<questions; i++)
                {
                    int x = i+1;
                    System.out.println("Q"+x+" :"+data1[i][0]);
                    System.out.println(data1[i][1]);
                    System.out.println(data1[i][2]);
                    System.out.println(data1[i][3]);
                    System.out.println(data1[i][4]);
                    System.out.println(data1[i][5]);
                    System.out.println("---------------------");
                }
            }
	}

	// ClientHandler class
	private static class ClientHandler implements Runnable 
        {
            private final Socket clientSocket;

            public ClientHandler(Socket socket)
            {
		this.clientSocket = socket;
            }

            public void run()
            {
		PrintWriter out = null;
		BufferedReader in = null;
		try 
                {
                    
                    out = new PrintWriter(clientSocket.getOutputStream(), true);

                    in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));

                    String line;
                    while ((line = in.readLine()) != null) 
                    {

			System.out.printf(" Sent from the client: %s\n", line);
                        out.println(line);
                    }
		}
                catch (IOException e) 
                {
                    e.printStackTrace();
		}
		finally 
                {
                    try 
                    {
			if (out != null) 
                        {
                            out.close();
			}
			if (in != null) 
                        {
                            in.close();
                            clientSocket.close();
			}
                    }
                    catch (IOException e) 
                    {
			e.printStackTrace();
                    }
		}
            }
	}
    }
//
//public class Survey_server {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        Workbook workbook = new Workbook();
//
//        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
//        System.out.print("How many question you want to insert in survey: ");  
//        int questions= sc.nextInt(); 
//        String [][] data1 = new String[10][6];
//        for (int i=0; i<questions; i++)
//        {
//            System.out.print("");  
//            String name= sc.nextLine();
//            System.out.print("Enter Question: ");  
//            String q= sc.nextLine();
//            System.out.print("Enter Option1: ");  
//            String o1= sc.nextLine();
//            System.out.print("Enter Option2: ");  
//            String o2= sc.nextLine();
//            System.out.print("Enter Option3: ");  
//            String o3= sc.nextLine();
//            System.out.print("Enter Option4: ");  
//            String o4= sc.nextLine();
//            
//            data1[i][0] = q;
//            data1[i][1] = o1;
//            data1[i][2] = o2;
//            data1[i][3] = o3;
//            data1[i][4] = o4;
//            data1[i][5] = "All above option";
//        }
//        for (int i=0; i<questions; i++)
//        {
//            int x = i+1;
//            System.out.println("Q"+x+" :"+data1[i][0]);
//            System.out.println(data1[i][1]);
//            System.out.println(data1[i][2]);
//            System.out.println(data1[i][3]);
//            System.out.println(data1[i][4]);
//            System.out.println(data1[i][5]);
//            System.out.println("---------------------");
//        }
//    }
//
//    private static class Workbook {
//
//        public Workbook() {
//        }
//    }
//    
//}

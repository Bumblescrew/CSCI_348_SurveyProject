//package survey_server;

import java.util.Scanner;

/**
 *
 * @author Al Amin Bin Shafiq
 */


import java.io.*;
import java.net.*;

// Server class
class Survey_server {
        public static String [][] data1 = new String[10][6];
        public static int [] responses = new int [10];
        public static int questions_num = 0;
	public static void main(String[] args)
	{
            
            boolean up = true;
            while (up){
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

                            System.out.println("New client connected " + client.getInetAddress().getHostAddress());

                            ClientHandler clientSock = new ClientHandler(client);

                            new Thread(clientSock).start();
                            //clientSock.run();

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
                    //String [][] data1 = new String[10][6];
                    questions_num = questions;
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
                //System.out.println("Sentinel2");
		DataOutputStream out = null;
		DataInputStream in = null;
		try 
                {
                    
                    out = new DataOutputStream(clientSocket.getOutputStream());

                    in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                    
                    out.writeUTF(Integer.toString(questions_num));
                    int q = 0;
                    while (q < questions_num){
                        for (int i = 0; i < data1[0].length; i++){                        
                            out.writeUTF(data1[0][i]);                   
                        }
                            String line;
                            while ((line = in.readUTF()) != null) 
                            {
                                System.out.printf(" Sent from the client: %s\n", line);
                                responses[0] = Integer.parseInt(line);
                            }
                            q++;
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

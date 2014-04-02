import java.io.*;
import java.util.*;
import java.net.*;  

public class ComputeIndexes {

	Double LoadIndex, PerformanceIndex;
        static Double PerformanceMetric;
        static int i =6000;
        static ServerSocket listen;

	public static Double Calculate_Load() throws NumberFormatException,
			IOException {
		BufferedReader br;
		String strLine, tokenise;
		StringTokenizer tokens;
		Double LoadIndex = 0.0;

		br = new BufferedReader(new FileReader("C:\\mem.txt"));

		while ((strLine = br.readLine()) != null) {

			if (strLine.contains("Cpu(s):")) {
				tokenise = strLine.substring(8);

				tokens = new StringTokenizer(tokenise, ",");
				while (tokens.hasMoreTokens()) {
					String tokencpu = tokens.nextToken();

					if (tokencpu.contains("id")) {
						LoadIndex = Double
								.parseDouble(tokencpu.substring(0, 4));

					}

				}
			}

		}
		return LoadIndex;
	}

	public static Double Calculate_Performance() throws NumberFormatException,
			IOException {
		BufferedReader br;
		String strLine, tokenise;
		StringTokenizer tokens;
		Double LoadIndex = 0.0;
		Double PerformanceIndex = 0.0;
		int Memfree = 0;
		int Memtotal = 0;

		br = new BufferedReader(new FileReader("C:\\mem.txt"));

		while ((strLine = br.readLine()) != null) {

			if (strLine.contains("Mem:")) {
				tokenise = strLine.substring(6);
				tokens = new StringTokenizer(tokenise, ",");
				while (tokens.hasMoreTokens()) {
					String token = tokens.nextToken();

					if (token.contains("k free")) {
						Memfree = Integer.parseInt(token.substring(1,
								token.length() - 6));
					}

					if (token.contains("total")) {

						Memtotal = Integer.parseInt(token.substring(0,
								token.length() - 7));
					}

				}
			}

		}

		PerformanceIndex = (double) ((Memfree / Memtotal) * 100);
		return PerformanceIndex;
	}

	public static void Calculate_PerformanceMetric()
			throws NumberFormatException, IOException {
		Double Loadindex, Performanceindex;
		Loadindex = Calculate_Load();
		Performanceindex = Calculate_Performance();
		PerformanceMetric = 0.7 * Performanceindex + .3 * Loadindex;
	  

	}

	public static void run_top() throws IOException, InterruptedException
	{
		Process t=Runtime.getRuntime().exec("/bin/sh /users/aakella/shscript.sh");
		t.waitFor();
		
	}

        
        public static void send() throws IOException
        {
             
        }
        
        public void run() throws InterruptedException, NumberFormatException, IOException
        {
           
            
            listen = new ServerSocket(i);
                            System.out.println("Listening");
            while(true)
                            {   run_top();
                                Calculate_PerformanceMetric();
                                Socket sendsocket = listen.accept();
                                PrintWriter out = new PrintWriter(sendsocket.getOutputStream(), true); 
                                out.println(PerformanceMetric);   
                                sendsocket.close();
                             
                        Thread.currentThread().sleep(5000);
           	    }
        }
               
                 
	public static void main(String args[]) throws IllegalArgumentException,
			IOException, InterruptedException 
        {
                
            ComputeIndexes compute=new ComputeIndexes();
            compute.run();
                       
        }
}

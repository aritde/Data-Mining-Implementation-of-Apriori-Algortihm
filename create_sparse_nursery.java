package dm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class create_sparse_nursery {
	public static void main(String[] args) {

		create_sparse_nursery obj = new create_sparse_nursery();
		obj.run();

	  }

	  public void run() {

		String csvFile = "C:/Transferred/b565/Assignment 4/4/nursery_real.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		//PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/car_real_result.csv"));
		//System.setOut(out);
		

		try {
			PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/4/nursery_real_result.csv"));
			System.setOut(out);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] thisline = line.split(cvsSplitBy);
				for ( int hj = 0; hj < thisline.length; hj ++)
				{
					//System.out.println("");
					if (hj == 0)
					{	//System.out.print(thisline[hj]+"_buying,");
						out.print(thisline[hj]+"_parents,");
						//System.out.println("");
					}
					if (hj == 1)
					{
						//System.out.print(thisline[hj]+"_maint,");
						out.print(thisline[hj]+"_has_nurs,");
						//System.out.println("");
					}
					if (hj == 2)
					{
						//System.out.print(thisline[hj]+"_doors,");
						out.print(thisline[hj]+"_form,");
						//System.out.println("");
					}
					if (hj == 3)
					{
						//System.out.print(thisline[hj]+"_persons,");
						out.print(thisline[hj]+"_children,");
						//System.out.println("");
					}
					if (hj == 4)
					{
						//System.out.print(thisline[hj]+"_lug_boot,");
						out.print(thisline[hj]+"_housing,");
						//System.out.println("");
					}
					if (hj == 5)
					{
						//System.out.print(thisline[hj]+"_safety");
						out.print(thisline[hj]+"_finance,");
						
					}
					if (hj == 6)
					{
						//System.out.print(thisline[hj]+"_safety");
						out.print(thisline[hj]+"_social,");
						
					}
					if (hj == 7)
					{
						//System.out.print(thisline[hj]+"_safety");
						out.print(thisline[hj]+"_health,");
					}
					if (hj == 8)
					{
						//System.out.print(thisline[hj]+"_safety");
						out.print(thisline[hj]+"_class");
						System.out.println("");
					}
					//System.out.println("");
					/*System.out.println("thisline [code= " + thisline[4] 
	                              + " , name=" + thisline[5] + "]");*/
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//System.out.println("Done");
	  }


}

package dm;
/*This file reads the datafile in raw format and handles the situations where two different columns have same attribute values and stores the new dataset in a new file*/ 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.io.PrintStream;

public class create_chess {

  public static void main(String[] args) {

	create_chess obj = new create_chess();
	obj.run();

  }

  public void run() {

	String csvFile = "C:/Transferred/b565/Assignment 4/4/chess_real.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	//PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/car_real_result.csv"));
	//System.setOut(out);
	

	try {
		PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/4/chess_real_result.csv"));
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
					out.print(thisline[hj]+"_bkblk,");
					//System.out.println("");
				}
				if (hj == 1)
				{
					//System.out.print(thisline[hj]+"_maint,");
					out.print(thisline[hj]+"_bknwy,");
					//System.out.println("");
				}
				if (hj == 2)
				{
					//System.out.print(thisline[hj]+"_doors,");
					out.print(thisline[hj]+"_bkon8,");
					//System.out.println("");
				}
				if (hj == 3)
				{
					//System.out.print(thisline[hj]+"_persons,");
					out.print(thisline[hj]+"_bkona,");
					//System.out.println("");
				}
				if (hj == 4)
				{
					//System.out.print(thisline[hj]+"_lug_boot,");
					out.print(thisline[hj]+"_bkspr,");
					//System.out.println("");
				}
				if (hj == 5)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_bkxbq,");
					
				}
				if (hj == 6)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_bkxcr,");
					
				}
				if (hj == 7)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_bkxwp,");
					
				}
				if (hj == 8)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_blxwp,");
					
				}
				if (hj == 9)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_bxqsq,");
					
				}
				if (hj == 10)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_cntxt,");
					
				}
				if (hj == 11)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_dsopp,");
					
				}
				if (hj == 12)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_dwipd,");
					
				}
				if (hj == 13)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_hdchk,");
					
				}
				if (hj == 14)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_katri,");
					
				}
				if (hj == 15)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_mulch,");
					
				}
				if (hj == 16)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_qxmsq,");
					
				}
				if (hj == 17)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_r2ar8,");
					
				}
				if (hj == 18)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_reskd,");
					
				}
				if (hj == 19)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_reskr,");
					
				}
				if (hj == 20)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_rimmx,");
					
				}
				if (hj == 21)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_rkxwp,");
					
				}
				if (hj == 22)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_rxmsq,");
					
				}
				if (hj == 23)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_simpl,");
					
				}
				if (hj == 24)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_skach,");
					
				}
				if (hj == 25)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_skewr,");
					
				}
				if (hj == 26)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_skrxp,");
					
				}
				if (hj == 27)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_spcop,");
					
				}
				if (hj == 28)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_stlmt,");
					
				}
				if (hj == 29)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_thrsk,");
					
				}
				if (hj == 30)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_wkcti,");
					
				}
				if (hj == 31)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_wkna8,");
					
				}
				if (hj == 32)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_wknck,");
					
				}
				if (hj == 33)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_wkovl,");
					
				}
				if (hj == 34)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_wkpos,");
					
				}
				if (hj == 35)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_wtoeg,");
					
				}
				if (hj == 36)
				{
					//System.out.print(thisline[hj]+"_safety");
					out.print(thisline[hj]+"_class");
					System.out.println("");
				}
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
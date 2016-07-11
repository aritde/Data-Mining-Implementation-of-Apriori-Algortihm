/*Takes the file created in create_sparse.java and generates the sparse matrix containing zero's and one's*/
/* The columns are unique values in the entire dataset */
/* The rows are the transactions */
package dm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
public class form_sparse {

  /*public static void main(String[] args) {

	form_sparse obj = new form_sparse();
	obj.run();

  }*/

  public int[][] run() 
  {

	/*********************** Below Line : To be hashed in for Car Evaluation and Hashed out for other datasets****************/
	//String csvFile = "C:/Transferred/b565/Assignment 4/4/car_real_result.csv";
	/*********************** Below Line :To be hashed in for nursery dataset and Hashed out for other datasets***************/
	String csvFile = "C:/Transferred/b565/Assignment 4/4/nursery_real_result.csv";
	/*********************** Below Line :To be hashed in for Chess dataset and Hashed out for other datasets***************/
	//String csvFile = "C:/Transferred/b565/Assignment 4/4/chess_real_result.csv";
	/************************************************************************************************************************/
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	HashSet<String> colnames = new HashSet<String>();
	//Set<String> set = new HashSet<String>();
	int[][] sparse_copy=null;


	try {
		/*********************** Below Line needs to be unhashed only the first time: To be hashed in for Car Evaluation and Hashed out for other datasets****************/
	//	PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/4/matrix.csv"));
		/*********************** Below Line needs to be unhashed only the first time: To be hashed in for Nursery Dataset and Hashed out for other datasets****************/
		PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/4/matrix_nursery.csv"));
		/*********************** Below Line needs to be unhashed only the first time: To be hashed in for Chess Dataset and Hashed out for other datasets****************/
		//PrintStream out = new PrintStream(new File("C:/Transferred/b565/Assignment 4/4/matrix_chess.csv"));
		br = new BufferedReader(new FileReader(csvFile));
		String[] thisline = null;
		int rownum=0;
		while ((line = br.readLine()) != null) 
		{
		        // use comma as separator
			thisline = line.split(cvsSplitBy);
			for ( int hj = 0; hj < thisline.length; hj ++)
			{
				
				colnames.add(thisline[hj]);
				/*System.out.println("thisline [code= " + thisline[4] 
                            + " , name=" + thisline[5] + "]");*/
			}
			rownum ++;
		}
		//System.out.println(colnames);
		//System.out.println(rownum); = 1728
		br = new BufferedReader(new FileReader(csvFile));
		thisline = null;
		line = "";
		int traversal = 0;
		int sparse[][]= new int[rownum][colnames.size()];
		List<String> nameList = new ArrayList<>(colnames);
		//System.out.println(nameList.size());// Prints 21
		while ((line = br.readLine()) != null)// Iterating 1728 times
		{	
			//traversal ++;
			thisline = line.split(cvsSplitBy);
			for ( int hj = 0; hj < thisline.length; hj ++)
			{
				for (String s : nameList)
				{
					if(s.equals(thisline[hj]))
					{
						sparse[traversal][nameList.indexOf(s)]=1;
						break;
					}
				}
				/*int k=0;
				for (String a :nameList)
				{
					if (sparse[traversal][k]!=1)
							{
								sparse[traversal][k]=0;
							}
					k++;
				}*/
			}
			traversal++;
			
			sparse_copy = sparse;
			
		}
		/***************To be executed for Car Evaluation Dataset**********************/
	/*for ( int i=0;i<1728;i++)
		{
			for(int j=0;j<nameList.size();j++)
			{
				out.print(sparse_copy[i][j]+",");
			}
			out.println("");
		}*/
		/*****************To be executed for Nursery Dataset **********************/
		
		for ( int i=0;i<12960;i++)
		{
			for(int j=0;j<nameList.size();j++)
			{
				out.print(sparse_copy[i][j]+",");
			}
			out.println("");
		}
		/*****************To be executed for Chess Dataset **********************/
		
		/*for ( int i=0;i<3196;i++)
		{
			for(int j=0;j<nameList.size();j++)
			{
				out.print(sparse_copy[i][j]+",");
			}
			out.println("");
		}*/
		System.out.println(colnames);
		
		
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

	System.out.println("Done");
	return sparse_copy;
  }
  
}
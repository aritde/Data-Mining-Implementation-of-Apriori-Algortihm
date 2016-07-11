// Implementation of the Apriori Algortihm.
package dm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
public class FI_apriori {

  public static void main(String[] args) {

	//apriori obj = new apriori();
	//obj.itemset();
	form_sparse matrix= new form_sparse();
	/**************************************************************************************************************************/
	int[][] sparse_matrix = matrix.run();
	FI freqitemset = new FI();
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the support threshold to be considered : (as decimal(eg.:0.01 for 1%))");
	double minsup = input.nextDouble();
	Vector<FI> allitemsforkminus1cross1 = new Vector<FI>();
	Vector<FI> allitemsforkminus1crosskminus1 = new Vector<FI>();
	int totalcount=0;
	ArrayList<TreeSet<Integer>> rule_left=new ArrayList<TreeSet<Integer>>();
	ArrayList<TreeSet<Integer>> rule_right = new ArrayList<TreeSet<Integer>>();
	ArrayList<TreeSet<Integer>> rule_left_lift=new ArrayList<TreeSet<Integer>>();
	ArrayList<TreeSet<Integer>> rule_right_lift = new ArrayList<TreeSet<Integer>>();
	ArrayList<Double> lift = new ArrayList<Double>();
	ArrayList<Double> confidence = new ArrayList<Double>();
	totalcount = itemset(sparse_matrix,freqitemset,minsup,totalcount);
	int totalcount_2 = totalcount;
	//System.out.println(totalcount);
	allitemsforkminus1cross1.add(freqitemset);
	allitemsforkminus1crosskminus1.add(freqitemset);
	kminus1cross1 (allitemsforkminus1cross1,freqitemset,minsup,sparse_matrix,totalcount);
	kminus1crosskminus1 (allitemsforkminus1crosskminus1,freqitemset,minsup,sparse_matrix,totalcount_2);
	closedfrequentitemset(allitemsforkminus1crosskminus1);
	maximalfrequentitemset(allitemsforkminus1crosskminus1);
	totalnumberofitemsetsgenerated(allitemsforkminus1crosskminus1);
	generate_rules(allitemsforkminus1crosskminus1,rule_left_lift,rule_right_lift,rule_left,rule_right,confidence,lift);

}
/* This function generates the rules using confidence and lift measures */
public static void generate_rules(Vector<FI> allitemsforkminus1crosskminus1,ArrayList<TreeSet<Integer>> rule_left_lift,ArrayList<TreeSet<Integer>> rule_right_lift,ArrayList<TreeSet<Integer>> rule_left,ArrayList<TreeSet<Integer>> rule_right,ArrayList<Double> conf,ArrayList<Double> lift)
{
	int i=0;
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the confidence threshold to be considered (as decimal(eg.:0.01 for 1%)) ");
	double mincon = input.nextDouble();
	//List<TreeSet<Integer>> complete_powerset=new ArrayList<TreeSet<Integer>>();
	List<List<Integer>> temp = new ArrayList<List<Integer>>();
	for (i=1 ;i<allitemsforkminus1crosskminus1.size();i++)
	{
		for (TreeSet<Integer> kitemset :allitemsforkminus1crosskminus1.get(i).freqitemsets)
		{
			//complete_powerset =
			double conf_value=0.0;
			double lift_value = 0.0;
			temp = new ArrayList<List<Integer>>();
			temp.addAll(poweritemset(kitemset));//,complete_powerset);
			for (List<Integer> tmpindividualitemset : temp)
			{
				//for (int k=0;k<temp.size();k++)
				//{
					ArrayList<Integer> antecedant=new ArrayList<Integer>();
					ArrayList<Integer> consequent=new ArrayList<Integer>();
					antecedant.addAll(tmpindividualitemset);
					TreeSet<Integer> intermediate = new TreeSet<Integer>();
					intermediate.addAll(kitemset);
					intermediate.removeAll(tmpindividualitemset);
					consequent.addAll(intermediate);

					int size_of_antecedant = antecedant.size()-1;
					int size_of_rule = kitemset.size()-1;
					int size_of_consequent = consequent.size()-1;
					TreeSet<Integer> antecedant_treeset = new TreeSet<Integer>(antecedant);
					TreeSet<Integer> consequent_treeset = new TreeSet<Integer>(consequent);
					Vector<TreeSet<Integer>> ant = allitemsforkminus1crosskminus1.get(size_of_antecedant).freqitemsets;
					Vector<TreeSet<Integer>> rul = allitemsforkminus1crosskminus1.get(size_of_rule).freqitemsets;
					Vector<TreeSet<Integer>> con = allitemsforkminus1crosskminus1.get(size_of_consequent).freqitemsets;
					double sup_a=return_support(allitemsforkminus1crosskminus1,ant,antecedant_treeset,size_of_antecedant);
					double sup_r=return_support(allitemsforkminus1crosskminus1,rul,kitemset,size_of_rule);
					double sup_c=return_support(allitemsforkminus1crosskminus1,con,consequent_treeset,size_of_consequent);
					//System.out.println(sup_a);
					//System.out.println(sup_r);
					conf_value = sup_r/sup_a;
					lift_value = sup_r/(sup_a * sup_c);
					if (conf_value >= mincon)
					{
						conf.add(conf_value);
						rule_left.add(antecedant_treeset);
						rule_right.add(consequent_treeset);
						/*************Removing the below two lines will display all rules enumerated using Confidence **********/
						//System.out.println("Confidence Rule : " + antecedant + "------->" + consequent);
						//System.out.println("Confidence value of above rule :" + conf_value);
					}
					//System.out.println("LIFT");
					lift.add(lift_value);
					rule_left_lift.add(antecedant_treeset);
					rule_right_lift.add(consequent_treeset);
					/******* Removing the below two lines will display all rules enumerated using Lift***********/
					//System.out.println("Lift Rule :" + antecedant + "------->" + consequent);
					//System.out.println("Lift Value of above rule:" + lift_value);

					//sort_lift_rules(rule_left_lift,rule_right_lift,lift);

			 }

		}

	}
	sort_rules (rule_left,rule_right,conf);
	sort_rules_for_lift (rule_left_lift,rule_right_lift,lift);
}
public static void sort_rules_for_lift (ArrayList<TreeSet<Integer>> rule_left_lift,ArrayList<TreeSet<Integer>> rule_left_right,ArrayList<Double> lift)
{
	ArrayList<Integer> as = new ArrayList<Integer>();
	ArrayList<Double> lift_copy = new ArrayList<Double>();
	//ArrayList<TreeSet<Integer>> rule_left_lift_copy = new ArrayList<TreeSet<Integer>>();
	//ArrayList<TreeSet<Integer>> rule_left_right_copy = new ArrayList<TreeSet<Integer>>();
	lift_copy.addAll(lift);
	int count = 0;int flag=1,i;
	double max = lift_copy.get(0);
	while (count <10)
	{
	for ( i=0;i<lift_copy.size();i++)
	{
		if (lift_copy.get(i)>max)
		{	max = lift_copy.get(i);
			flag=0;
		}
	}
	if (flag==0)
	{
		int pos = lift_copy.indexOf(max);
		as.add(pos);
		//System.out.println(max);
		lift_copy.set(pos,-99999.00);
		//rule_left_lift_copy.remove(pos);
		//rule_left_right_copy.remove(pos);
		max=lift_copy.get(0);
		i=0;
	}
	count ++;
	}
	System.out.println(" Top 10 Rules using Lift ");
	System.out.println("*************************");
	for (int k=0;k<as.size();k++)
	{
		System.out.println(rule_left_lift.get(as.get(k)) + "----->" + rule_left_right.get(as.get(k)) );
		System.out.println(lift.get(as.get(k)));
	}

}
/* This function outputs the top 10 interesting rules */
public static void sort_rules (ArrayList<TreeSet<Integer>> rule_left_lift,ArrayList<TreeSet<Integer>> rule_left_right,ArrayList<Double> lift)
{
	ArrayList<Integer> as = new ArrayList<Integer>();
	ArrayList<Double> lift_copy = new ArrayList<Double>();
	//ArrayList<TreeSet<Integer>> rule_left_lift_copy = new ArrayList<TreeSet<Integer>>();
	//ArrayList<TreeSet<Integer>> rule_left_right_copy = new ArrayList<TreeSet<Integer>>();
	lift_copy.addAll(lift);
	int count = 0;int flag=1,i;
	double max = lift_copy.get(0);
	while (count <10)
	{
	for ( i=0;i<lift_copy.size();i++)
	{
		if (lift_copy.get(i)>max)
		{	max = lift_copy.get(i);
			flag=0;
		}
	}
	if (flag==0)
	{
		int pos = lift_copy.indexOf(max);
		as.add(pos);
		//System.out.println(max);
		lift_copy.set(pos,-99999.00);
		//rule_left_lift_copy.remove(pos);
		//rule_left_right_copy.remove(pos);
		max=lift_copy.get(0);
		i=0;
	}
	count ++;
	}
	System.out.println(" Top 10 Rules using Confidence ");
	System.out.println("*************************");
	for (int k=0;k<as.size();k++)
	{
		System.out.println(rule_left_lift.get(as.get(k)) + "----->" + rule_left_right.get(as.get(k)) );
		System.out.println(lift.get(as.get(k)));
	}

}
/* This function is used for fetching the support value to calculate the confidence */
public static double return_support(Vector<FI> allitemsforkminus1crosskminus1 ,Vector<TreeSet<Integer>> ant,TreeSet<Integer> antecedant_treeset,int size_of_antecedant)
{
		double sup_a=0.0;
		int a=0;
		//for (int ai=0;ai<ant.size();ai++)
		//{
			for (TreeSet<Integer> aitemset: ant)
			{

				if (aitemset.equals(antecedant_treeset))
				{
				//rule_left.add(antecedant_treeset);
				sup_a = allitemsforkminus1crosskminus1.get(size_of_antecedant).support.get(a);
				break;
				}
				a++;
			}
			return sup_a;
		//}

}



				/*
						conf_value=sup_r/sup_a;
						//conf.add(conf_value);
						if (conf_value>0.1)
						{
							System.out.println(antecedant + "---->" + consequent);
							System.out.println(conf_value);
						}
					}

			}*/
					//break;
					//System.out.println(antecedant + "---->" + consequent);
				//}
			//System.out.println(temp);
			//break;
			//System.out.println(complete_powerset);

		//System.out.println("Next itemset");
		//System.out.println(complete_powerset);
/* This function generates the powerset of each itemset */
public static List<List<Integer>> poweritemset(TreeSet<Integer> list)// List<List<Integer>> returned_ps )
{
    List<List<Integer>> ps = new ArrayList<List<Integer>>();
    ps.add(new ArrayList<Integer>());   // start with the empty set


    for (Integer item : list) {
      List<List<Integer>> newPs = new ArrayList<List<Integer>>();

      for (List<Integer> subset : ps) {

        newPs.add(subset);


        List<Integer> newSubset = new ArrayList<Integer>(subset);
        newSubset.add(item);
        newPs.add(newSubset);
      }


      ps = newPs;

    }
    ps.remove(0);
    ps.remove(ps.size()-1);
    return ps;
    //returned_ps = ps;
    //System.out.println(ps);

  }
/* This function calculates the total number of rules generated using the Brute Force Method */
public static void totalnumberofitemsetsgenerated (Vector<FI> allitemsforkminus1crosskminus1)
{
	int i=0;
	int totalnumberofrulesgenerated = 0;
	for (i=1 ;i<allitemsforkminus1crosskminus1.size();i++)
	{
		for (TreeSet<Integer> kitemset :allitemsforkminus1crosskminus1.get(i).freqitemsets)
		{
			totalnumberofrulesgenerated += Math.pow(2,kitemset.size()) -2;
		}
	}
	System.out.println("Total number of rules generated using the brute force method =" + totalnumberofrulesgenerated);
}
/*This function calculates the number of Frequent Maximal ItemSets */
public static void maximalfrequentitemset(Vector<FI> kminus1crosskminus1)
{
	int countofmaximalfrequentitemset =0;
	boolean flag;
	int i=0;
	while (i < kminus1crosskminus1.size()-1)
	{
		for(TreeSet<Integer> itemsetunderconsideration: kminus1crosskminus1.get(i).freqitemsets)
		{
			flag = false;
			for(TreeSet<Integer> nextlevelofitemsetunderconsideration: kminus1crosskminus1.get(i+1).freqitemsets)
			{
				if (nextlevelofitemsetunderconsideration.containsAll(itemsetunderconsideration))
				{
					flag = true;
					break;
				}
			}
			if(flag == false)
					countofmaximalfrequentitemset++;
		}
		i++;
	}
	countofmaximalfrequentitemset += kminus1crosskminus1.get(kminus1crosskminus1.size()-1).freqitemsets.size();
	System.out.println ("Maximal Frequent Itemset =" + countofmaximalfrequentitemset );
}
/* This function calculates the number of Total Closed Itemsets */
public static void closedfrequentitemset(Vector<FI> kminus1crosskminus1)
 {

	 int countofclosedfrequentitemset =0;
	 boolean flag;
	 int i=0;
	 double supportofitemsetunderconsideration,supportofnextitemsetunderconsideration;
	 while (i < kminus1crosskminus1.size()-1)
	 {
		 //System.out.println(i);
		 int x=0,y=0;
		 for(TreeSet<Integer> itemsetunderconsideration: kminus1crosskminus1.get(i).freqitemsets)
		 {
			 //System.out.println(i);
			 flag = false;
			 supportofitemsetunderconsideration = kminus1crosskminus1.get(i).supportcount.get(x);
			 y=0;
			 for(TreeSet<Integer> nextlevelofitemsetunderconsideration: kminus1crosskminus1.get(i+1).freqitemsets)
			 {
				 if (nextlevelofitemsetunderconsideration.containsAll(itemsetunderconsideration))
					{
					 	supportofnextitemsetunderconsideration=kminus1crosskminus1.get(i+1).supportcount.get(y);
					 	if (supportofitemsetunderconsideration == supportofnextitemsetunderconsideration)
					 	{
					 		flag =true;
					 		break;
					 	}
					}
				 y++;
			 }
			 if (flag == false)
			 {
				 countofclosedfrequentitemset++;
			 }
			 x++;
		 }
		 i++;
	 }
	 countofclosedfrequentitemset += kminus1crosskminus1.get(kminus1crosskminus1.size()-1).freqitemsets.size();
	 System.out.println ("Closed Frequent Itemset =" + countofclosedfrequentitemset );
 }
/* Implements the kminus1 * kminus 1 Method */
public static void kminus1crosskminus1(Vector<FI> allitemsforkminus1crosskminus1,FI freqitemset,double minsup, int[][] sparse_matrix, int totalcount_2)
{
	int count = 0;
	int itemsetnumber = 1;
	int totalConsideredItemsets=totalcount_2;
	FI freqitemset2 = new FI();
	while (true)
	{
		freqitemset2 = nitemsetkminus1crosskminus1(allitemsforkminus1crosskminus1.get(count), count);  // Generates all k-itemsets for a particular k at a level
		totalConsideredItemsets += freqitemset2.freqitemsets.size();

		//System.out.print("Generated Itemsets" + freqitemset2.freqitemsets + " ");
		pruning(freqitemset2,minsup,sparse_matrix);
		//System.out.println("Pruned Itemsets" + freqitemset2.freqitemsets);
		if ((compare(allitemsforkminus1crosskminus1.get(count),freqitemset2))==0)
		{
			allitemsforkminus1crosskminus1.add(freqitemset2);
		}
		else
			break;
		count++ ;
	}
	int intermediatecandidatecount =0;
	System.out.println("*********************F(k-1) X F(k-1) Method *********************");
	for (FI itemsets : allitemsforkminus1crosskminus1)
	{
		//System.out.println(itemsetnumber + " ItemSet"  );
		//System.out.println("xxxxxxxxxxxxxxxxxx");
		//System.out.println(itemsets.freqitemsets);
		//System.out.println(itemsets.support);
		intermediatecandidatecount += itemsets.freqitemsets.size();
		itemsetnumber++;
	}
	System.out.println("Total number of frequent itemsets generated using F(k-1) X F(k-1) Method : " + intermediatecandidatecount);
	System.out.println("Total number of itemsets generated F(k-1) X F(k-1) Method: " + totalConsideredItemsets);
  }

public static FI nitemsetkminus1crosskminus1(FI allitemsforkminus1crosskminus1, int count)
{
	Vector<TreeSet<Integer>> fItems = allitemsforkminus1crosskminus1.freqitemsets;
	int i,j;
	Vector<TreeSet<Integer>> result = new Vector<TreeSet<Integer>>();
	TreeSet<Integer> intermediate_result = null;
	FI freqitems=new FI();
	for (i=0;i<fItems.size()-1;i++)
	{
		TreeSet<Integer> intermediate1 = new TreeSet<Integer>();
		intermediate1=fItems.get(i);
		for (j=i+1;j<fItems.size();j++)
		{
			TreeSet<Integer> intermediate1_copy = new TreeSet<Integer>();
			intermediate1_copy.addAll(intermediate1);
			TreeSet<Integer> intermediate2 = new TreeSet<Integer>();
			intermediate2=fItems.get(j);
			TreeSet<Integer> intermediate2_copy = new TreeSet<Integer>();
			intermediate2_copy.addAll(intermediate2);
			if (count !=0)
			{
				int last_i=intermediate1_copy.pollLast();
				int last_j=intermediate2_copy.pollLast();
				intermediate1_copy.removeAll(intermediate2_copy);
				if (intermediate1_copy.size()!=0)
				{
					continue;
				}
			}
			intermediate_result = new TreeSet<Integer>();
			intermediate_result.addAll(fItems.get(i));
			intermediate_result.addAll(fItems.get(j));
			if(intermediate_result != null)
			{
				result.add(intermediate_result);
			}

		}
	}
		freqitems.freqitemsets = result;
		return freqitems;
}
/* This function implements the Kminus1 * 1 Method */
public static void kminus1cross1 (Vector<FI> allitemsforkminus1cross1, FI freqitemset,double minsup, int[][] sparse_matrix, int totalcount)
{
	int count = 0;
	int itemsetnumber = 1;
	int totalConsideredItemsets=totalcount;
	FI freqitemset2 = new FI();
	while (true)
	{
		freqitemset2 = nitemsetkminus1cross1(freqitemset,allitemsforkminus1cross1.get(count));  // Generates all k-itemsets for a particular k at a level
		totalConsideredItemsets += freqitemset2.freqitemsets.size();
		//System.out.println("Entering Pruning");
		//System.out.println("Before Pruning" + freqitemset2.freqitemsets);
		pruning(freqitemset2,minsup,sparse_matrix);
		//System.out.println("After Pruning" + freqitemset2.freqitemsets);
		if ((compare(allitemsforkminus1cross1.get(count),freqitemset2))==0)
		{
			allitemsforkminus1cross1.add(freqitemset2);
		}
		else
			break;
		count++ ;
	}
	int intermediatecandidatecount =0;
	System.out.println("********************* F(k-1) X 1 Method *********************");
	for (FI itemsets : allitemsforkminus1cross1)
	{
		//System.out.println(itemsetnumber + " ItemSet"  );
		//System.out.println("xxxxxxxxxxxxxxxxxx");
		//System.out.println(itemsets.freqitemsets);
		//System.out.println(itemsets.support);
		intermediatecandidatecount += itemsets.freqitemsets.size();
		itemsetnumber++;
	}
	System.out.println("*************************************************************************");
	System.out.println("Total number of frequent itemsets generated using F(k-1) X 1 Method :    " + intermediatecandidatecount);
	System.out.println("Total number of itemsets generated F(k-1) X 1 Method: " + totalConsideredItemsets);
  }
/* This function prunes the total generated itemsets to frequent itemsets */
public static void pruning (FI kCandidatesAtALevel, double minsup, int[][] sparse_matrix)
{
	double support_count = 0.0;
	boolean flag = false;
	if(kCandidatesAtALevel.freqitemsets.size() == 0)
		return ;
	Vector<TreeSet<Integer>> allItemSetsAtLevelK = kCandidatesAtALevel.freqitemsets;
	Vector<Double> support_value = new Vector<Double>();
	Vector<Double> support_Count = new Vector<Double>();
	Vector<TreeSet<Integer>> toBeRemoved=new Vector<TreeSet<Integer>>();
	for (TreeSet<Integer> eachItemSetInAllItemSetsAtLevelK : allItemSetsAtLevelK)
	{
		support_count = 0.0;
		for (int i=0;i<sparse_matrix.length;i++)
		{
			flag = false;
			for (int eachElement : eachItemSetInAllItemSetsAtLevelK)
			{
				if (sparse_matrix[i][eachElement]==1)
				{
					continue;
				}
				//else
				//{
					flag = true;
					break;
				//}
			}
			if (flag == false)
				support_count ++;
		}
		if((support_count/sparse_matrix.length)>=minsup)
		{
			support_value.add(support_count/sparse_matrix.length);
			support_Count.add(support_count);
		}
		else
			 toBeRemoved.add(eachItemSetInAllItemSetsAtLevelK);
	}
	allItemSetsAtLevelK.removeAll(toBeRemoved);
	kCandidatesAtALevel.freqitemsets = allItemSetsAtLevelK;
	kCandidatesAtALevel.support = support_value;
	kCandidatesAtALevel.supportcount = support_Count;
}
public static int compare (FI i1, FI i2)
{
	if ((i1.freqitemsets.size()<i2.freqitemsets.size()) || (i1.freqitemsets.size()>i2.freqitemsets.size()))
	{
		return 0; //True
	}
	if (i2.freqitemsets.size() == 0)
	{
		return 1;
	}
	Vector<TreeSet<Integer>> temp1 = i1.freqitemsets;
	Vector<TreeSet<Integer>> temp2 = i2.freqitemsets;
	for (TreeSet<Integer> temp : temp1)
	{
		if(!temp2.contains(temp))

		{
			return 1;
		}
	}
	return 0;
}
public static FI nitemsetkminus1cross1(FI itemset1,FI itemset2)
{
	Vector<TreeSet<Integer>> i1 ;
	Vector<TreeSet<Integer>> i2 ;
	i1 = itemset1.freqitemsets;
	i2 = itemset2.freqitemsets;
	FI obj2= new FI();
	Vector<TreeSet<Integer>> result = new Vector<TreeSet<Integer>>();
	System.out.println();
	for ( TreeSet<Integer> eachTreesetini2 : i2)
	{
		for ( TreeSet<Integer> eachTreesetini1 : i1 )
		{
			TreeSet<Integer> temp = new TreeSet<Integer>();
			if (eachTreesetini1.last() > eachTreesetini2.last() )
			{
				temp.addAll(eachTreesetini1);
				temp.addAll(eachTreesetini2);

				if(temp != null)
				{
					//System.out.println(temp);
					result.add(temp);}
			}

		}
	}
	obj2.freqitemsets = result;
	return obj2;
}
/* This function generates frequent one itemset */
public static int itemset(int[][] sparse_matrix, FI new_item, double minsup, int totalcount)
{


		//Vector<Double> as = new Vector<Double>();
		Vector<Double>support_value = new Vector<Double>();
		Vector<Double>support_count = new Vector<Double>();
		Vector<TreeSet<Integer>> result = new Vector<TreeSet<Integer>>();
		double count = 0;
		for ( int i=0;i<sparse_matrix[0].length;i++)
		{
			count =0.0;
			//ts = new TreeSet<Integer>();
			TreeSet<Integer> ts= new TreeSet<Integer>();
			for (int j=0;j<sparse_matrix.length;j++)
			{
				if (sparse_matrix[j][i]==1)
				{
					count ++;
				}

			}
			totalcount++;
			/* Support threshold is declared here */
			if ((count/sparse_matrix.length) >= minsup )
			{
				//TreeSet<Integer> ts= new TreeSet<Integer>();
				ts.add(i);
				result.add(ts);
				//System.out.print(i + " ");
				//as.add(count/sparse_matrix.length);
				support_value.add(count/sparse_matrix.length);
				support_count.add(count);
			}
		}
		//System.out.print(as);
		//System.out.println("");
		//System.out.println(result);
		new_item.freqitemsets=result;
		new_item.support=support_value;
		new_item.supportcount=support_count;
		//return result;
		return totalcount;
}

}
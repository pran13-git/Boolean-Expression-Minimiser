package Analyser;
import java.util.*;
public class Reducer
{
	int numOfVar, numOfMin;
	public Reducer(int numOfVar, int numOfMin)
	{
		this.numOfVar=numOfVar;
		this.numOfMin=numOfMin;
	}
	public static void init(int a[][],int val)
	{
		int i,j;
		for(i=0;i<a.length;i++)
			for(j=0;j<a[i].length;j++)
				a[i][j]=val;
	}
	
	public static void init(int a[],int val)		// function overloading
	{
		int i;
		for(i=0;i<a.length;i++)
		a[i]=val;
	}
	
	public static String decode(int a[][],int row,int numOfVar,char var[])//will convert the final essential pi and pi into switching variables
	{
		int i;
		String s="";
		int ctr=0;
		for(i=0;i<a[row].length;i++)
		{
		    if(a[row][i]==2)
		    {
		    	ctr++;
		    	continue;
		    }
		    else if(a[row][i]==1)
		    	s+=var[i];
		    else
		    	s+=var[i]+"'";
		}
		if (ctr==numOfVar)
			return "1";
		return s;
	}
	
	public static boolean match(int minterm,int a[][],int row,int numOfVar)//this will identify the prime implicants with the minterms
	{
		int b[]=new int[numOfVar],i=numOfVar-1,c=0;
		init(b,0);
		while(minterm>0)
		{
		    b[i]=minterm%2;
		    minterm/=2;
		    i--;
		}
		for(i=0;i<numOfVar;i++)
		{
		    if(a[row][i]==2)
		    	continue;
		    if(a[row][i]!=b[i])
		    	c++;
		}
		if(c==0)
			return true;
		return false;
	}
	
  	
}

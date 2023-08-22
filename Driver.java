import java.io.*;
import java.util.*;
import Analyser.*;

interface Error
{
 	void display();
}

class ExcessMintermsException extends Exception implements Error 
{
	int n;
	
	ExcessMintermsException(int n)
	{
		this.n=n;	
	}

	public void display()
	{
 		System.out.println("\nExcess minterms exception encountered:\n");
 	}
	public String toString()
	{
		return("Minterm/Don't Care entered exceeds bounds. Only "+ (int)Math.pow(2,n)+ " terms possible for "+ n+ " variables!");
	}
}

class MintermExistsException extends Exception implements Error 
{
	
	public void display()
	{
 		System.out.println("\nMinterms exists exception encountered:\n");
 	}
	public String toString()
	{
		return("Minterm/Don't Care repeated!");
	}
}

class MintermDontCareClashException extends Exception implements Error 
{
	
	public void display()
	{
 		System.out.println("\nMinterm Don't care clash exception encountered:\n");
 	}
	
	public String toString()
	{
		return("Entered Don't care found in minterms!");
	}
}

class ExcessQuantityException extends Exception
{
	ExcessQuantityException()
	{
		super("Number of Minterms/Don't cares exceeds permissible limits!\n");
	}
	
}


//---------------------------------------------driver main---------------------------------------------
class Driver
{
	
	public static void printTallyMap( Map map, int numOfVar)
	{
		Map<Integer,List<String>> tallyMap = new HashMap<>();
		Set<String> set=map.entrySet();
		System.out.println("\n=================================================================================================================");
		
		//System.out.println("\nSet of minterms:"+ set);
		//System.out.println("\nMinterms in binary forms");
		//System.out.println("\nMINTERMS IN BINARY: \n"+ set);
		
		/*for(String s : set)
		{
			System.out.println(s);
		}
		*/
		//Set set2= new HashSet <String> ();
		Iterator i = set.iterator();
     		
     		char ch[];
      		int ctr, key;

	      	
	      	while(i.hasNext())
	      	{
			Map.Entry me = (Map.Entry)i.next();
			ctr=0;
			String s=(String)me.getValue();
			ch= s.toCharArray();
			for( int j=0; j< ch.length; ++j)
				if(ch[j]=='1')
					ctr++;
			key=ctr;
			String value=s;
			if(tallyMap.containsKey(key))
			{
			    tallyMap.get(key).add(value);
			} 
			else 
			{
			
			   List<String> list = new ArrayList<>();
			   list.add(value);
			   tallyMap.put(key, list);
			}
		}
		
		System.out.println("\nThe Grouping Stage:");
		System.out.println("\nTALLY GROUPS:");
		for (Integer keyName: tallyMap.keySet()) 
		{
		    key = keyName;
		    List<String> value = tallyMap.get(keyName);
		    System.out.println(key + ":" + value);
		}
		
	}
	public static void main( String args[])
	{
		//initialize variables
		Scanner sc= new Scanner (System.in);
		Scanner sc2= new Scanner(System.in);
		int inp, numOfMin=0, numOfDont=0;
		int i=0,j, y,flag=0,ctr=0,c,flag2=0,ctr2=0,flag1=0, ctr1=0,ctr3=0, k, pos=0, x, numOf2;		
		//start of display
		System.out.println("\n-----------------------------------------------JAVA MINI PROJECT-----------------------------------------------");
		System.out.println("\n*Minimization of Boolean expression using Quine-Mccluskey(QM) Method*\n");
		//System.out.println("This method mainly uses Minterms, Don't cares also included\n");
		System.out.println("This program takes inputs for Minterms and/or Don't cares and prints the minimised Boolean expression\n");
		
		System.out.println("\nAPPLICATION:\n");
		
		//input
		System.out.print("Enter the number of variables:");
		int numOfVar= sc.nextInt();
		ArrayList<Integer> a=new ArrayList<Integer> ((int)Math.pow(2,numOfVar));
		Map map= new HashMap();
				
		do
		{
			//input no. of minterms
			System.out.print("Enter the number of minterms you want to add:");
			try
			{
				numOfMin= sc.nextInt();
				if (numOfMin>Math.pow(2,numOfVar))
					throw new ExcessQuantityException();
			}
			
			catch (ExcessQuantityException e)
			{
				System.out.println(e);	
			}
		}
		while(numOfMin>Math.pow(2,numOfVar));
		
		//input
		System.out.print("Enter the minterms (in numbers from 0 to "+ (int)(Math.pow(2,numOfVar)-1) +" separated by spaces):");
		Reducer red= new Reducer (numOfVar, numOfMin);	//reducer class object from Analyser package
		
		for( i=0; i<numOfMin; ++i)
		{	
			inp=sc.nextInt();
					
			a.add(inp);
			while(inp>(Math.pow(2,numOfVar)-1) || Collections.frequency(a, inp)==2)
			{
				try
				{
					
					
					//System.out.println(a);
					
					if(inp>(Math.pow(2,numOfVar)-1))
					{
						throw new ExcessMintermsException(numOfVar);
					}
					
					else if(Collections.frequency(a, inp)==2)
					{

						throw new MintermExistsException();
					}
					
				}
			
				catch( ExcessMintermsException e)
				{
					System.out.println("\n"+e+"\tList: "+a);
					System.out.print("Please check the input and enter minterm " + (i+1)+ " again:");
					inp=sc2.nextInt();
					a.set(i, inp);
					
				}
			
				catch( MintermExistsException e)
				{
					System.out.println("\n"+e+"\tList: "+a);
					System.out.print("Please check the input and enter minterm " + (i+1)+ " again:");
					inp=sc2.nextInt();
					a.set(i, inp);
					
					
				}
			}
				
		}
		
		ArrayList<Integer> d=new ArrayList<Integer> ((int)Math.pow(2,numOfVar));
		
		System.out.print("\n\nDo you want to enter Don't cares? (y/n):");
		String choice=sc.next();
		
		if(choice.equals("y"))
		{
			do
			{
				System.out.print("Enter the number of Don't Cares you want to add:");
				try
				{
					numOfDont= sc.nextInt();
					if (numOfMin+numOfDont>Math.pow(2,numOfVar))
						throw new ExcessQuantityException();
				}
				
				catch (ExcessQuantityException e)
				{
					System.out.println(e);	
				}
			}while(numOfMin+numOfDont>Math.pow(2,numOfVar));
			
			System.out.print("Enter the Don't cares (in numbers from 0 to "+ (int)(Math.pow(2,numOfVar)-1) +" separated by spaces):");
			for( i=0; i<numOfDont; ++i)
			{	
				inp=sc.nextInt();
				
				d.add(inp);
				while(inp>(Math.pow(2,numOfVar)-1) || Collections.frequency(d, inp)==2 || a.contains(inp) )
				{
					try
					{
						
						
						//System.out.println(a);
						
						if(inp>(Math.pow(2,numOfVar)-1))
						{
							throw new ExcessMintermsException(numOfVar);
						}
						
						else if(Collections.frequency(d, inp)==2)
						{

							throw new MintermExistsException();
						}
						
						else if(a.contains(inp))
							throw new MintermDontCareClashException();
						
					}
				
					catch( ExcessMintermsException e)
					{
						System.out.println("\n"+e+"\tList: "+d);
						System.out.print("Please check the input and enter term " + (i+1)+ " again:");
						inp=sc2.nextInt();
						d.set(i, inp);
						
					}
				
					catch( MintermDontCareClashException e)
					{
						System.out.println("\n"+e+"\nMinterms List: "+a +"\tDon't Cares list: "+ d);
						System.out.print("Please check the input and enter term " + (i+1)+ " again:");
						inp=sc2.nextInt();
						d.set(i, inp);
						
						
					}
					
					catch( MintermExistsException e)
					{
						System.out.println("\n"+e+"\tList: "+d);
						System.out.print("Please check the input and enter term " + (i+1)+ " again:");
						inp=sc2.nextInt();
						d.set(i, inp);
						
					}
				}
					
			}
			
		}	
		
		System.out.println("\nThe Minterms entered are: "+a);
		System.out.println("\nThe Don't cares entered are: "+d);
		
		int minterms[]=new int[numOfMin+numOfDont];
		i=0;
		ListIterator<Integer> mintermsIterator = a.listIterator();
		ListIterator<Integer> DcIterator = d.listIterator();
		while (mintermsIterator.hasNext())
		{
			minterms[i++]= mintermsIterator.next();
			map.put(i-1, Integer.toBinaryString(minterms[i-1]) );
		}
		
		while (DcIterator.hasNext())
		{
			minterms[i++]= DcIterator.next();
			map.put(i-1, Integer.toBinaryString(minterms[i-1]) );
		}
		
		
		printTallyMap( map, numOfVar);
		
		// have worst case dimensions
		int numOfTerms= numOfMin+numOfDont;
		int mt[][]=new int[numOfTerms*(numOfTerms+1)/2][numOfVar];	//minterms chart
		int combined[][]=new int[numOfTerms*(numOfTerms+1)/2][numOfVar];
		int pi[][]=new int[numOfTerms*(numOfTerms+1)/2][numOfVar];	//prime implicants chart
		int checker[]=new int[numOfTerms*(numOfTerms+1)/2];
		
		
		
		red.init(mt,-1);
		for(i=0;i<numOfTerms;i++)
        		for(j=0;j<numOfVar;j++)
        			mt[i][j]=0;
        	
        	// to convert to binary forms and store in mt matrix		
		for(i=0;i<numOfTerms;i++)
		{
			 pos=numOfVar-1;
			for(x=minterms[i]; x>0; x/=2)
			{
				mt[i][pos--]=x%2;
				
			}
		}
	
	
		/*	System.out.println("\n=================================================================================================================");
		//System.out.println("\nMinterms in binary forms");
		System.out.println("MINTERMS IN BINARY:");
		for(i=0;i<numOfTerms;i++)
		{
			System.out.print((i+1) + ") ");
			for(j=0;j<numOfVar;j++)
				System.out.print(mt[i][j]);
			System.out.println();
		}
		*/
		//grouping stage
		while(true)
		{
			ctr=0;
			flag=0;
			red.init(combined,-1);//creating new empty matrix combined every iteration
			red.init(checker,-1);
			for(i=0;i<mt.length;i++)
			{
				if(mt[i][0]==-1)
					break;
					
				for(j=i+1;j<mt.length;j++)
				{
					c=0;
					if(mt[j][0]==-1)
						break;
					for(k=numOfVar-1;k>=0;k--)
						if(mt[i][k]!=mt[j][k])
						{
							pos=k;
							c++;
						}
					if(c==1)
					{
						ctr++;
						checker[i]++;
						checker[j]++;
						for(k=numOfVar-1;k>=0;k--)
							combined[flag][k]=mt[i][k];
						combined[flag][pos]=2;
						flag++;
					}
				}
			}
			for(j=0;j<i;j++)
			{
				if(checker[j]==-1)
				{
					for(k=0;k<numOfVar;k++)
						pi[flag2][k]=mt[j][k];
					ctr3=0;
					//checking if there is any repetation of prime implicants 
					for(x=flag2-1;x>=0;x--)
					{
						ctr1=0;
						for(y=0;y<numOfVar;y++)
						{
							if(pi[x][y]!=pi[flag2][y])
								ctr1++;
						}
						if(ctr1==0)
						{	
					    		ctr3++;
					    		break;
						}
					}
					if(ctr3==0)
						flag2++;
				}
			}
			if(ctr==0)//if in a table there is no term carried forward then we will stop
					break;//ctr 0 signifies that no elements are combined to move to next pass so process will terminate
			/*
			for(i=0;i<combined.length;i++)
			{
				if(combined[i][0]==-1)
					break;
				for(j=0;j<numOfVar;j++)
				{
					if(combined[i][j]==2)
						System.out.print("_");
					else
						System.out.print(combined[i][j]);
				}
				System.out.println();
			}
			*/
			//System.out.println("\n=================================================================================================================");
			//the above will display the new table created in every pass*/
			for(i=0;i<combined.length;i++)
				for(j=0;j<combined[i].length;j++)
					mt[i][j]=combined[i][j];	
			//copying the matrix combined to mt
			flag1++;
		}
		System.out.println("\n=================================================================================================================");
		System.out.println("\nPRIME IMPLICANTS:");
		for(i=0;i<flag2;i++)
		{
		    for(j=0;j<numOfVar;j++)
		    {
		        if(pi[i][j]==2)
		        	System.out.print("_");
		        else
		        	System.out.print(pi[i][j]);
		    }
		    System.out.println();
		}
        	System.out.println("\n=================================================================================================================");
        	int dash[]=new int[numOfVar];//this will have the dash values of each pi
        	red.init(dash,-1);
        	int piCoverage[][]=new int[flag2][numOfMin];	//pi coverage table 
        	red.init(piCoverage,0);
        	boolean present;
		for(i=0;i<flag2;i++)
		{
		    for(j=0;j<numOfMin;j++)
		    {
		        present=red.match(minterms[j],pi,i,numOfVar);
		        if(present==true)
		        	piCoverage[i][j]=1;
		    }    
		}
		System.out.println("\nPRIME IMPLICANTS COVERAGE CHART:");
		for(i=0;i<numOfMin;i++)
			System.out.print(minterms[i]+"\t");
		System.out.println();
		for(i=0;i<piCoverage.length;i++)
		{
		    for(j=0;j<numOfMin;j++)
		    {
		        if(piCoverage[i][j]==1)
		        	System.out.print((char)(piCoverage[i][j]+87)+"\t");
		        else
		        	System.out.print(" "+"\t");
		    }
		    System.out.println();
		}
		checker=new int[flag2];
		dash=new int[numOfMin];
		ctr=0;
		red.init(checker,-1);
		red.init(dash,-1);
		for(j=0;j<numOfMin;j++)
		{
		    ctr=0;
		    for(i=0;i<flag2;i++)
		    {
		        if(piCoverage[i][j]==1)
		        {
		            pos=i;
		            ctr++;
		        }
		    }
		    if(ctr==1)
		    	checker[pos]++;
		}
		System.out.println("\n=================================================================================================================");
	        System.out.println("\nESSENTIAL PRIME IMPLICANTS:");
		for(i=0;i<flag2;i++)
		{
		    if(checker[i]!=-1)
		    {
		        for(j=0;j<numOfVar;j++)
		        {
		            if(pi[i][j]==2)
		            	System.out.print("_");
		            else
		            	System.out.print(pi[i][j]);
		        }
		        System.out.println();
		    }   
		}
		System.out.println("\n=================================================================================================================");
		for(i=0;i<flag2;i++)
		{
		    if(checker[i]!=-1)
		    {
		        for(j=0;j<numOfMin;j++)
		        {
		            if(piCoverage[i][j]==1)
		            	dash[j]++;
		        }
		        for(j=0;j<numOfMin;j++)
		        	piCoverage[i][j]=-1;
		    }
		}
		for(j=0;j<numOfMin;j++)
		{
		    if(dash[j]!=-1)
		    {
		        for(i=0;i<flag2;i++)
		        	piCoverage[i][j]=-1;
		    }
		}
		//System.out.println("\nAfter Eliminating Essential Prime Implicants");
		System.out.println("\nColumns which aren't covered by EPI's are: \n");
		System.out.println("REMAINING PRIME IMPLICANTS COVERAGE CHART:");
		for(i=0;i<numOfMin;i++)
			System.out.print(minterms[i]+"\t");
		System.out.println();
		for(i=0;i<piCoverage.length;i++)
		{
		    for(j=0;j<numOfMin;j++)
		    {
		        if(piCoverage[i][j]==1)
		        	System.out.print((char)(piCoverage[i][j]+87)+"\t");
		        else
		        	System.out.print(" "+"\t");
		    }
		    System.out.println();
		}
		while(true)
		{
		    ctr=0;
		    //remove column dominance
		    for(j=0;j<numOfMin;j++)
		    {
		        for(k=j+1;k<numOfMin;k++)
		        {
		            ctr1=0;
		            ctr2=0;
		            ctr3=0;
		            for(i=0;i<flag2;i++)
		            {
		                if(piCoverage[i][j]==1 && piCoverage[i][k]==1)
		                	ctr1++;
		                if(piCoverage[i][j]==1 && piCoverage[i][k]==0)
		                	ctr2++;
		                if(piCoverage[i][j]==0 && piCoverage[i][k]==1)
		                	ctr3++;
		            }
		            if(ctr2>0 && ctr3>0)
		            {
		                break;
		            }
		            if(ctr1>0 && ctr2>0 && ctr3==0)
		            {
		                for(numOf2=0;numOf2<flag2;numOf2++)
		                	piCoverage[numOf2][j]=-1;
		                ctr++;
		            }
		            if(ctr1>0 && ctr3>0 && ctr2==0)
		            {
		                for(numOf2=0;numOf2<flag2;numOf2++)
		                	piCoverage[numOf2][k]=-1;
		                ctr++;
		            }
		            if(ctr1>0 && ctr2==0 && ctr3==0)
		            {
		                for(numOf2=0;numOf2<flag2;numOf2++)
		                	piCoverage[numOf2][j]=-1;
		                ctr++;
		            }
		        }
		    }
		    //remove row dominance
		    for(i=0;i<flag2;i++)
		    {
		        for(j=i+1;j<flag2;j++)
		        {
		            ctr1=0;
		            ctr2=0;
		            ctr3=0;
		            for(k=0;k<numOfMin;k++)
		            {
		                if(piCoverage[i][k]==1 && piCoverage[j][k]==1)
		                	ctr1++;
		                if(piCoverage[i][k]==1 && piCoverage[j][k]==0)
		                	ctr2++;
		                if(piCoverage[i][k]==0 && piCoverage[j][k]==1)
		                	ctr3++;
		            }
		            if(ctr2>0 && ctr3>0)
		            	break;
		            if(ctr1>0 && ctr2>0 && ctr3==0)
		            {
		                for(numOf2=0;numOf2<numOfMin;numOf2++)
		                	piCoverage[j][numOf2]=-1;
		                ctr++;
		            }
		            if(ctr1>0 && ctr3>0 && ctr2==0)
		            {
		                for(numOf2=0;numOf2<numOfMin;numOf2++)
		                	piCoverage[i][numOf2]=-1;
		                ctr++;
		            }
		            if(ctr1>0 && ctr2==0 && ctr3==0)
		            {
		                for(numOf2=0;numOf2<numOfMin;numOf2++)
		                	piCoverage[j][numOf2]=-1;
		                ctr++;
		            }
		        }
		    }
		    if(ctr==0)//will stop when there is no more row or column dominance , ctr will be 0
		    	break;
		}
	System.out.println("\n=================================================================================================================");
	
	System.out.println("\nAfter removing row and column dominance:");
	System.out.println("Prime implicants that are required (Other than EPI's): \n");// not epi's // epi's are already there
        System.out.println("FINAL PRIME IMPLICANTS CHART");
        for(i=0;i<numOfMin;i++)
        	System.out.print(minterms[i]+"\t");
        System.out.println();
        for(i=0;i<piCoverage.length;i++)
        {
            for(j=0;j<numOfMin;j++)
            {
                if(piCoverage[i][j]==1)
                	System.out.print((char)(piCoverage[i][j]+87)+"\t");
                else
                	System.out.print(" "+"\t");
            }
            System.out.println();
        }
        for(i=0;i<piCoverage.length;i++)
        {
            for(j=0;j<numOfMin;j++)
            	if(piCoverage[i][j]==1)
            		checker[i]++;
        }
        char var[]=new char[numOfVar];// converting back to variables
        for(i=0;i<numOfVar;i++)
        	var[i]=(char)(65+i);
        	
        System.out.println("\n=================================================================================================================");
        System.out.print("\n\nTHE RESULT:");
        /*for(i=0;i<numOfVar;i++)
        	System.out.print(var[i]+" ");*/
        System.out.println();
        System.out.println("The Final Simplified Expression: \n");
        if( numOfMin==0)
		System.out.println("0"); 
        for(i=0;i<flag2;i++)
        {
            if(checker[i]!=-1 )
            {
            	System.out.print(red.decode(pi,i,numOfVar,var));
            	if (i!=flag2-1)
            		System.out.print(" + ");
            }
        }
        
        System.out.println("\n\n-----------------------------------------------END OF APPLICATION-----------------------------------------------");
        
    }
	
	
}

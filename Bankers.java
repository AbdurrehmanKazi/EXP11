// Bankers algorithm

import java.util.Scanner;
public class Bankers
{
	private int need[][], allocate[][], max[][], avail[][], np, nr;
	private void input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of processes and resources");
		np=sc.nextInt();   // process
		nr=sc.nextInt();   // resources
		need=new int [np][nr];     // initialize array
		max=new int [np][nr];
		allocate=new int [np][nr];
		avail=new int [1][nr];

		System.out.println("Enter allocation matrix--->");
		for (int i=0;i<np;i++)
			for (int j=0;j<nr;j++)
				allocate[i][j]=sc.nextInt();   // allocate matrix

		System.out.println("Enter max matrix--->");
		for (int i=0;i<np;i++)
			for (int j=0;j<nr;j++)
				max[i][j]=sc.nextInt();   // max matrix

		System.out.println("Enter available matrix--->");
		for (int j=0;j<nr;j++)
				avail[0][j]=sc.nextInt();   // available matrix
		
		sc.close();
	}

	
	private int[][] calc_need()
	{
		System.out.println("Enter max matrix--->");
		for (int i=0;i<np;i++)
			for (int j=0;j<nr;j++)
				need[i][j]=max[i][j]-allocate[i][j];  // calculating need matrix
		return need;
	}

	private boolean check(int i)
	{
		// checking if all resources for ith process can be allocated.
		for (int j=0;j<nr;j++)
				if(avail[0][j]<need[i][j])
					return false;
		return true;
	}

	public void isSafe()
	{
		input();
		calc_need();
		boolean done[]=new boolean[np];
		int j=0;
		while(j<np)
		{
			// untill all process allocated
			boolean allocated=false;
			for (int i=0;i<np;i++)
				if(!done[i]&& check(i))
					{
						// trying to allocate
						for (int k=0;k<nr;k++)
							avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
						System.out.println("Allocated process : "+i);
						allocated=done[i]=true;
						j++;
					}
				if(!allocated)		// if no allocation
					break;
		}
		
		if(j==np)				// if all process are allocated
			
			System.out.println("safely allocated");
		else
			System.out.println("All process cant be safely allocated");
	}

	public static void main(String args[])
	{
		new Bankers().isSafe();
	}
}
						
import java.util.*;
public class sdk 
{
	public boolean dead;
	public int[][] boxNumber;
	public boolean[][] boxIsFixed;
	public int step;
	public int prevStep;
	public boolean[] finishStatus;
	/*
	 * 
	 * easy
000600107
680951300
003002568
040810020
000000850
090065073
409003085
162009030
500706000
	*
	* expert
805070020
000100000
000030000
200300800
000020090
040000065
010050000
430087000
600000070


800000000
003600000
070090200
050007000
000045700
000100030
001000068
008500010
090000400


	 */

	public sdk()
	{
		dead=false;
		prevStep=-1;
		step=0;
		boxNumber= new int[9][9];
		boxIsFixed= new boolean[9][9];
		finishStatus = new boolean[27];
		for (int i=0;i<27;i++)
			finishStatus[i]=false;
	}
	
	public sdk(int[][] boxNumber, boolean[][] boxIsFixed, int step)
	{
		this.step=step;
		dead=false;
		prevStep=-1;
		step=0;
		this.boxNumber= boxNumber;
		this.boxIsFixed= boxIsFixed;
		finishStatus = new boolean[27];
		for (int i=0;i<27;i++)
			finishStatus[i]=false;
	}
	
	public String getStr(int n)
	{
		int a=n%100;
		switch(a)
		{
		case 11:
		case 12:
		case 13:
			return n+"th";
		default:
			switch(a%10)
			{
			case 1:
				return n+"st";
			case 2:
				return n+"nd";
			case 3:
				return n+"rd";
			default:
				return n+"th";
			}
		}
		
	}
	
	public void letUserInput()
	{
		String temp="";
		Scanner scan = new Scanner(System.in);
		for (int i=0;i<9;i++)
		{
			System.out.println("Please enter the "+getStr(i+1)+" line. (Enter 0 for unknowns.)");
			temp=scan.nextLine();
			for (int j=0;j<9;j++)
			{
				boxNumber[i][j]=Integer.parseInt(temp.charAt(j)+"");
				boxIsFixed[i][j]=(boxNumber[i][j]==0)?false:true;
			}
		}
	}

	public boolean isFinish(int n)
	{
		if (finishStatus[n-1]) 
			return true;
		int a=0,b=0;
		if (n<=9)
		{
			for (int i=0;i<9;i++)
			{
				if(!boxIsFixed[i][n-1])
					return false;
			}
			finishStatus[n-1]=true; 
			return true;
		}
		else if (n<=18)
		{
			for (int i=0;i<9;i++)
			{
				if(!boxIsFixed[18-n][i])
					return false;
			}
			finishStatus[n-1]=true; 
			return true;
		}
		a=(n-19)/3;
		b=(n-19)-3*a;
		a*=3;
		b*=3;
		for (int i=a;i<a+3;i++)
		{
			for (int j=b;j<b+3;j++)
			{
				if(!boxIsFixed[i][j])
					return false;
			}
		}
		finishStatus[n-1]=true; 
		return true;
	}

	public void fillAndFix(int x, int y, int n)
	{
		System.out.println("fill "+n+" at ( "+x+" , "+y+" )");
		boxNumber[x][y]=n;
		boxIsFixed[x][y]=true;
		step++;
		print();
	}
	
	public boolean isConflict(int x, int y, int n)
	{
		for (int i=0;i<9;i++)
		{
			if ((boxNumber[x][i]==n && i!=y)||(boxNumber[i][y]==n && i!=x))
				return true; 
		}
		int a=x/3;
		int b=y/3;
		a*=3;
		b*=3;
		for (int i=a;i<a+3;i++)
		{
			for (int j=b;j<b+3;j++)
			{
				//System.out.println(i+" "+j);
				if (!(i==x && j==y) && (boxNumber[i][j]==n))
					return true;
			}
		}
		return false;
	}
	
	public void processByRow()
	{
		for (int first=0;first<9;first++)
		{
			if (!isFinish(18-first))
			{
				int ct,pos;
				for (int n=1;n<=9;n++)
				{
					ct=0;
					pos=0;
					for (int second=0;second<9;second++)
					{
						if (!boxIsFixed[first][second] && !isConflict(first,second,n))
						{
						pos=second;
						ct++;
						}
					}
					if (ct==1)
					{
						fillAndFix(first,pos,n);
					}
				}
			}
		}
	}

	public void processByClm()
	{
		for (int second=0;second<9;second++)
		{
			if (!isFinish(1+second))
			{
				int ct,pos;
				for (int n=1;n<=9;n++)
				{
					ct=0;
					pos=0;
					for (int first=0;first<9;first++)
					{
						if (!boxIsFixed[first][second] && !isConflict(first,second,n))
						{
						pos=first;
						ct++;
						}
					}
					if (ct==1)
					{
						fillAndFix(pos,second,n);
					}
				}
			}
		}
	}

	public void processByBlock()
	{
		int a,b,ct,X,Y;
		for (int block=0;block<9;block++)
		{
			if (!isFinish(19+block))
			{
				for (int n=1;n<=9;n++)
				{
					ct=0;
					X=0;
					Y=0;
					a=block/3;
					b=block-3*a;
					a*=3;
					b*=3;
					for (int first=a;first<a+3;first++)
					{
						for (int second=b;second<b+3;second++)
						{
							if (!boxIsFixed[first][second] && !isConflict(first,second,n))
							{
							X=first;
							Y=second;
							ct++;
							}
						}
					}
					if (ct==1)
					{
						fillAndFix(X,Y,n);
					}
				}
			}
		}
	}

	public void print()
	{
		/*
		 ╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
		 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║ 0 │ 0 │ 0 ║
		 ╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝
		 */
		System.out.println("The "+getStr(step)+" step:");
		System.out.println("     0   1   2   3   4   5   6   7   8");
		System.out.println("   ╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗");
		System.out.println(" 0 ║ "+getString(0,0)+" │ "+getString(0,1)+" │ "+getString(0,2)+" ║ "+getString(0,3)+" │ "+getString(0,4)+" │ "+getString(0,5)+" ║ "+getString(0,6)+" │ "+getString(0,7)+" │ "+getString(0,8)+" ║");
		System.out.println("   ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
		System.out.println(" 1 ║ "+getString(1,0)+" │ "+getString(1,1)+" │ "+getString(1,2)+" ║ "+getString(1,3)+" │ "+getString(1,4)+" │ "+getString(1,5)+" ║ "+getString(1,6)+" │ "+getString(1,7)+" │ "+getString(1,8)+" ║");
		System.out.println("   ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
		System.out.println(" 2 ║ "+getString(2,0)+" │ "+getString(2,1)+" │ "+getString(2,2)+" ║ "+getString(2,3)+" │ "+getString(2,4)+" │ "+getString(2,5)+" ║ "+getString(2,6)+" │ "+getString(2,7)+" │ "+getString(2,8)+" ║");
		System.out.println("   ╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
		System.out.println(" 3 ║ "+getString(3,0)+" │ "+getString(3,1)+" │ "+getString(3,2)+" ║ "+getString(3,3)+" │ "+getString(3,4)+" │ "+getString(3,5)+" ║ "+getString(3,6)+" │ "+getString(3,7)+" │ "+getString(3,8)+" ║");
		System.out.println("   ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
		System.out.println(" 4 ║ "+getString(4,0)+" │ "+getString(4,1)+" │ "+getString(4,2)+" ║ "+getString(4,3)+" │ "+getString(4,4)+" │ "+getString(4,5)+" ║ "+getString(4,6)+" │ "+getString(4,7)+" │ "+getString(4,8)+" ║");
		System.out.println("   ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
		System.out.println(" 5 ║ "+getString(5,0)+" │ "+getString(5,1)+" │ "+getString(5,2)+" ║ "+getString(5,3)+" │ "+getString(5,4)+" │ "+getString(5,5)+" ║ "+getString(5,6)+" │ "+getString(5,7)+" │ "+getString(5,8)+" ║");
		System.out.println("   ╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
		System.out.println(" 6 ║ "+getString(6,0)+" │ "+getString(6,1)+" │ "+getString(6,2)+" ║ "+getString(6,3)+" │ "+getString(6,4)+" │ "+getString(6,5)+" ║ "+getString(6,6)+" │ "+getString(6,7)+" │ "+getString(6,8)+" ║");
		System.out.println("   ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
		System.out.println(" 7 ║ "+getString(7,0)+" │ "+getString(7,1)+" │ "+getString(7,2)+" ║ "+getString(7,3)+" │ "+getString(7,4)+" │ "+getString(7,5)+" ║ "+getString(7,6)+" │ "+getString(7,7)+" │ "+getString(7,8)+" ║");
		System.out.println("   ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
		System.out.println(" 8 ║ "+getString(8,0)+" │ "+getString(8,1)+" │ "+getString(8,2)+" ║ "+getString(8,3)+" │ "+getString(8,4)+" │ "+getString(8,5)+" ║ "+getString(8,6)+" │ "+getString(8,7)+" │ "+getString(8,8)+" ║");
		System.out.println("   ╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝");
		System.out.println("\n───────────────────────────────────────────\n");
	}
	
	public boolean ifNeedAi()
	{
		if (prevStep==step)
			return true;
		prevStep=step;
		return false;
	}
	
	public boolean isEnd()
	{
		for (int x=0;x<9;x++)
		{
			for (int y=0;y<9;y++)
			{
				if (!boxIsFixed[x][y])
					return false;
			}
		}
		return true;
	}
	
	public boolean isDeadGame()
	{
		for (int i=0;i<9;i++)
		{
			for (int j=0;j<9;j++)
			{
				if (!boxIsFixed[i][j])
				{
					int ct=0;
					for (int n=1;n<=9;n++)
					{
						
						if (!isConflict(i, j, n))
								ct++;
					}
					if (ct==0)
						return true;
				}
			}
		}
		return false;
	}
	
	public int passToAi()
	{
		sdkAi AI = new sdkAi(boxNumber,boxIsFixed,step);
		return AI.run();
	}
	public void startGame()
	{
		letUserInput();
		//long startTime=System.currentTimeMillis();  
		//run();
		//System.out.println("All Set!");
		//long endTime=System.currentTimeMillis();  
		//System.out.println("Time consumed： "+(endTime-startTime)/1000.0+"s");   
	}
	
	public int run()
	{
		while (!isEnd() && !ifNeedAi())
		{
			processByRow();
			processByClm();
			processByBlock();
		}
		//System.out.println("是否死局："+isDeadGame());
		if (isDeadGame())
		{
			System.out.println("Dead Game.");
			return 2;
		}
		int rslt=1;
		if (ifNeedAi())
		{
			//System.out.println("第 "+time+" 次AI介入。");
			rslt=passToAi();
			//System.out.println("第 "+time+" 次AI完成。");
		}
		return rslt;
	}
	
	public String getString(int x, int y)
	{
		//return boxIsFixed[x][y]?"T":"F";
		return (boxNumber[x][y]==0)?(" "):(""+boxNumber[x][y]);
	}

	
		
}

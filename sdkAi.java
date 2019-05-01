
public class sdkAi {

	public int[][] boxNumber;
	public boolean[][] boxIsFixed;
	public int step;
	public sdk help= new sdk();
	public sdkAi(int[][] boxNumber, boolean[][] boxIsFixed, int step)
	{
		this.step=step;
		this.boxNumber=deepCopy(boxNumber);
		this.boxIsFixed=deepCopy(boxIsFixed);
	}
	
	public int[] extendArray(int[] input, int n)
	{
		int[] rslt= new int[input.length+1];
		for (int i=0;i<input.length;i++)
		{
			rslt[i]=input[i];
		}
		rslt[rslt.length-1]=n;
		return rslt;
	}
	
	public int[][] deepCopy(int[][] input)
	{
		int[][] rslt=new int[input.length][input[0].length];
		for (int i=0;i<input.length;i++)
		{
			for (int j=0;j<input[0].length;j++)
			{
				rslt[i][j]=input[i][j];
			}
		}
		return rslt;
	}
	
	public boolean[][] deepCopy(boolean[][] input)
	{
		boolean[][] rslt=new boolean[input.length][input[0].length];
		for (int i=0;i<input.length;i++)
		{
			for (int j=0;j<input[0].length;j++)
			{
				rslt[i][j]=input[i][j];
			}
		}
		return rslt;
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
	
	public String getString(int x, int y)
	{
		return boxIsFixed[x][y]?"T":"F";
		//return (boxNumber[x][y]==0)?(" "):(""+boxNumber[x][y]);
	}
	
	public int run()
	{
		int x=-1,y=-1;
		boolean isFound=false;
		int a=0,b=0;
		while (!isFound&& a<9)
		{
			while (!isFound && b<9)
			{
				if (!boxIsFixed[a][b])
				{
					x=a;
					y=b;
					isFound=true;
					break;
				}
				b++;
			}
			b=0;
			a++;
		}
		int[] possibleNumberList= new int[0];
		//System.out.print("a:"+a+" b:"+b+" x,y:");
		//System.out.print(x+" "+y+":");
		for (int n=1;n<=9;n++)
		{
			if (!isConflict(x, y, n))
			{
				//System.out.print(n+" ");
				possibleNumberList=extendArray(possibleNumberList,n);
			}
		}
		System.out.println();
		
		for (int i=0;i<possibleNumberList.length;i++)
		{
			int[][] tempBoxNumber=deepCopy(boxNumber);
			boolean[][] tempBoxIsFixed=deepCopy(boxIsFixed);
			tempBoxNumber[x][y]=possibleNumberList[i];
			tempBoxIsFixed[x][y]=true;
			step++;
			sdk temp = new sdk(tempBoxNumber,tempBoxIsFixed,step);
			int dead=temp.run();
			if(dead==1)
				return 1;
		}
		return 0;
	}


}

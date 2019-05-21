import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;//importing the filereader class for reading of files.
class Fingerprint
{
	// reading the file in to an string and returning the string.
	protected String FileReading(File file)
	{
		String line = null;
		StringBuilder str = new StringBuilder();
		try{
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			while ((line = br1.readLine()) != null){
				str.append(line);
				str.append(" ");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return str.toString();
	}
	// removing the spaces in the string and returning the string without spaces. 
	String removespaces(String s1)
	{
		String s3="";
		for(int i=0;i<s1.length();i++)
		{
			if(s1.charAt(i)!=32)
			{
				s3=s3+s1.charAt(i);
			}
		}
		return s3;

	}
	//According to the ngram value breaking and storing in to an arraylist.
	ArrayList<String> ngram(String s3,int n)
	{
		ArrayList<String> al=new ArrayList<String>();
		for(int i=0;i<s3.length()-n+1;i++)
		{
			al.add(s3.substring(i,i+n));
		}
		return al;
	}
	//finding the hashvalues for the words in the arraylist according to the ngram value.
	ArrayList<Integer> hashvalues(ArrayList<String> al,int n)
	{
		ArrayList<Integer> alli=new ArrayList<Integer>();
		
		for(String i:al)
		{
			int sum=0;
			int k=0;
			int d=n;
			int t=n;
			while(k<i.length())
			{
				
				sum=(int)(sum+(i.charAt(k)*((Math.pow(t,d))-1)));
				k=k+1;
				d=d-1;
			}
			alli.add(sum);
		}
		return alli;
	}
	//compare both hashvalues in the arraylist and if same increase the count.
	int commonhashvalues(ArrayList<Integer> alli,ArrayList<Integer> alli1)
		{
			int count=0;
			for(Integer i:alli)
			{
				for(Integer j:alli1)
				{

					if(i.equals(j))
					{
						System.out.println("common"+i);
						count=count+1;
						break;
					
					}
				
				}
			}
			System.out.println(count);
			return count;

		}



	public static void main(String[] args) 
	{
		File[] fileList = new File[100];
		Scanner s=new Scanner(System.in);
		String path=args[0];
		int l=0;
		try
		{
			File Directory = new File(path);
			File[] files = Directory.listFiles();
			for (File f : files)
			{
				if (f.getName().endsWith(".txt"))
				{
					fileList[l] = f;
					l++;
				}
			}
		}
		catch (Exception e)
		 {
			e.printStackTrace();
		}
		//Scanner s=new Scanner(System.in);
		String s1;
		String s2;
		Fingerprint f1=new Fingerprint();
		Fingerprint f2=new Fingerprint();
		double[][] mat=new double[l][l];
		int n=s.nextInt();
		for (int i=0;i<l;i++) 
		{
			System.out.print(fileList[i].getName() + "\t");
		}
		System.out.println(" ");
		for(int i=0;i<l;i++)
		{
			System.out.print(fileList[i].getName()+"\t");
			System.out.println("\n");
			for (int j = i; j < l; j++)
			 {
				s1 = f1.FileReading(fileList[i]);
				s2 = f2.FileReading(fileList[j]);
		
				ArrayList<String> al=new ArrayList<String>();
				ArrayList<String> all=new ArrayList<String>();
				ArrayList<Integer> alli=new ArrayList<Integer>();
				ArrayList<Integer> alli1=new ArrayList<Integer>();
				
				String s3=f1.removespaces(s1);
				String s4=f1.removespaces(s2);
				al=f1.ngram(s3,n);
				all=f1.ngram(s4,n);
				
				System.out.println(al);
				System.out.println(all);
				alli=f1.hashvalues(al,n);
				alli1=f1.hashvalues(all,n);

				
				
				int count=f1.commonhashvalues(alli,alli1);
				System.out.println("count"+count);
				
				double percentage=(count*2.0)/(alli.size()+alli1.size())*100;

				System.out.println(percentage);
				mat[i][j]=percentage;
				mat[j][i]=percentage;
			}
		}
		System.out.print("\t");//storing the percentages  in one array printing as a array.
	    for (int i = 0; i < l; ++i)
	    {
	     System.out.printf(fileList[i].getName()+"\t");   
	    }
	    System.out.printf("\n");

	    for (int i = 0; i < l; ++i)
	    {
	        System.out.printf(fileList[i].getName()+"\t");
	        for (int j= 0; j < l; ++j)
	        {
	            System.out.printf("%.2f\t",mat[i][j]);
	        }
	        System.out.printf("\n");
	    }

	}
}

import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;//importing the filereader class for reading of files.
interface substr
{
	public String FileReading(File file);
	public String  longestlength(ArrayList<String> alli);
	public ArrayList<String> splitting(String s1);
	public ArrayList<String> commonwords(ArrayList<String> al,ArrayList<String> all);

}
class Lcs implements substr
{
	// reading the file in to an string and returning the string.
	public String FileReading(File file){
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
	//splitting of the string and storing its every substring in an arraylist.
	public ArrayList<String> splitting(String s1)
	{
		ArrayList<String> al=new ArrayList<String>();
		String s3="";
		for (int i=0;i<s1.length();i++)
		{
			s3="";
			for(int j=i;j<s1.length();j++)
			{
				s3=s3+s1.charAt(j);
				al.add(s3);

			}
		}
		return al;

	}
	//finding commonwords in both files and appending in to another arraylist. 
	public ArrayList<String> commonwords(ArrayList<String> al,ArrayList<String> all)
	{
		ArrayList<String> alli=new ArrayList<String>();

		for(String i:al)
		{
			for(String j:all)
			{
				if(i.equals(j))
				{
					alli.add(i);
				}
			}
		}
		return alli;

	}
	//finding the longest string and returning it .
	public String  longestlength(ArrayList<String> alli)
	{
		int len=0;
		String k="";
		for (String i:alli)
		{
			if(i.length()>len)
			{
				len=i.length();
				k=i;

			}

		}
		return k;
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
		Lcs l1=new Lcs();
		Lcs l2=new Lcs();
		double[][] mat=new double[l][l];
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
				s1 = l1.FileReading(fileList[i]);
				s2 = l2.FileReading(fileList[j]);

				ArrayList<String> al=new ArrayList<String>();
				ArrayList<String> all=new ArrayList<String>();
				ArrayList<String> alli=new ArrayList<String>();
		//Lcs l1=new Lcs();
				al=l1.splitting(s1);
				all=l1.splitting(s2);
					
				alli=l1.commonwords(al,all);	
					
				int len=0;
				
				String k=l1.longestlength(alli);
				System.out.println("commomstring is"+k);
				//System.out.println((k.length()-1)+k);
				//System.out.println(s1.length()+s2.length()-2);
				double percentage=((k.length()-1)*2.0)/((s1.length())+(s2.length())-2)*100;
				//System.out.println(percentage);
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
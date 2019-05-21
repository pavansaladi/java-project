import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;//importing the filereader class for reading of files.
interface bag
{
	public String FileReading(File file);
	public void frequencies(String[] s3,HashMap<String,Integer> map);
	public ArrayList<Integer> dotproduct(HashMap<String,Integer> map,HashMap<String,Integer> map1);
	public int euclidiannorm(HashMap<String,Integer> map);
	public int sumofdotproducts(ArrayList<Integer> al);
	

}
class Bagofwords implements bag
{
	// reading the file in to an string and returning the string.
	public String FileReading(File file)
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
	// for calculating the frequencies of the words in the string array.
	public void frequencies(String[] s3,HashMap<String,Integer> map)
	{
	for(String i:s3)
		{
			if(map.containsKey(i))
			{
				map.put(i,map.get(i)+1);
			}
			else
			{
				map.put(i,1);
			}
		}
		
	}
	// dot product of the two hashmap's and storing in an arraylist.
	public ArrayList<Integer> dotproduct(HashMap<String,Integer> map,HashMap<String,Integer> map1)
	{
	
	ArrayList<Integer> al=new ArrayList<Integer>();

	for(Map.Entry m:map.entrySet())
		{
			for(Map.Entry m1:map1.entrySet())
			{
				if(m.getKey().equals(m1.getKey()))
				{
					int j1=(Integer)m.getValue();
					int j2=(Integer)m1.getValue();
					al.add(j1*j2);
					
				}
			}
		}
		return al;

	}
	//summing of all dotproducts in the arraylist.
	public int sumofdotproducts(ArrayList<Integer> al)
	{
		int sum=0;
		for(int i:al)
		{
			sum=sum+i;
		}
		return sum;

	}
	// euclidian norm of the hashmap and finding the denominator.
	public int euclidiannorm(HashMap<String,Integer> map)
	{
		int j=0;
		for(Map.Entry m:map.entrySet())
		{
			j=j+(Integer)m.getValue()*(Integer)m.getValue();
		}
		return j;

	}

	public static void main(String[] args) 
	{
		File[] fileList = new File[100];
		Scanner s=new Scanner(System.in);
		String path=args[0];
		int l=0;
		System.out.println("kish");
		try
		{
			System.out.println("kios");
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
		String s1;
		String s2;
		double[][] mat=new double[l][l];
		Bagofwords bag=new Bagofwords();
		Bagofwords bag2=new Bagofwords();
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
				s1 = bag.FileReading(fileList[i]);
				s2 = bag2.FileReading(fileList[j]);

				HashMap<String,Integer> map= new HashMap<String,Integer>();
				HashMap<String,Integer> map1= new HashMap<String,Integer>();
				ArrayList<Integer> al=new ArrayList<Integer>();
						//String s1=s.nextLine();
				String s3[]=s1.split(" ");
						//String s2=s.nextLine();
				String s4[]=s2.split(" ");
						//dh1 d=new dh1();
				bag.frequencies(s3,map);
				bag.frequencies(s4,map1);
				//hasmap objects are references
						
					
				al=bag.dotproduct(map,map1);

				int sum=bag.sumofdotproducts(al);
					//System.out.println(sum);
				int d1=bag.euclidiannorm(map);
				int d2=bag.euclidiannorm(map1);
				
					
				double k=(Math.sqrt(d1))*(Math.sqrt(d2));
				double percentage=(sum/k)*100;
				System.out.println(percentage);
				mat[i][j]=percentage;
				mat[j][i]=percentage;
				map.clear();
				map1.clear();
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



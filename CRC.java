import java.util.Scanner;
import java.lang.String;
public class CRC {
	static Scanner sc=new Scanner(System.in);
	static int div1[]=new int[17];
	static int div0[]=new int[17];
	static int data[]=new int[17];
	static int rem[]=new int[17];	
	static void codeword_eval(String s1,int n)
	{
		if(data[0]==1)
		{
			for(int i=0;i<17;i++)
			{
				rem[i]=data[i]^div1[i]; 
			}
		}
		else if(data[0]==0)
		{
			for(int i=0;i<17;i++)
				rem[i]=data[i]^div0[i];
		}
		for(int i=0;i<16;i++) 
		{
			data[i]=rem[i+1];
		}
		if(n+17<s1.length()) 
			data[16]=(int)s1.charAt(n+17)-48;
	}
	public static void main(String args[])
	{
		div1[0]=div1[4]=div1[11]=div1[16]=1;
		System.out.println("Enter the dataword:");
		String d=sc.next();
		String s1=d+"0000000000000000"; 
		for(int i=0;i<17;i++)
		{
			data[i]=s1.charAt(i)-48; 
		}
		for(int i=0;i<d.length();i++)
		{
			codeword_eval(s1,i); 
		}	
		for(int i=1;i<17;i++)
			d=d+(char)(rem[i]+48); 
		System.out.println("Codeword is:"+d);
		System.out.println("Enter the Codeword to be detected for error:");
		String cw=sc.next();
		sc.close();
		for(int i=0;i<17;i++)
		{
			data[i]=(int)cw.charAt(i)-48;
		}
		for(int i=0;i<cw.length()-16;i++)
		{
			codeword_eval(cw,i);
		}	
		boolean flag=true;
		for(int i=0;i<rem.length;i++)
		{
			if(rem[i]!=0)
			{
				flag=false;
				break;
			}
		}
		if(flag==true)
			System.out.println("Codeword is error-free.Hence accepted");
		else
			System.out.println("Codeword has error.Hence rejected");
	}

}




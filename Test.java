package War;

import java.util.List;
import java.util.ArrayList;

public class Test 
{
	public static void main(String[] args)
	{
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(5);
		l.add(123);
		
		while(l.size() != 1)
		{
			l.remove(0);
			System.out.println(l);
		}
		System.out.println(l);
		
	}
	
}

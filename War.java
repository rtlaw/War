package War;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//DP
public class War
{
	Random generator = new Random();
	List<Integer> deck1 = new ArrayList<Integer>();
	List<Integer> deck2 = new ArrayList<Integer>();
	List<Integer> table = new ArrayList<Integer>();
	int rounds = 0;
	int wars = 0;
	public War()
	{
		inputDP();
		System.out.println("Starting decks:");
		System.out.println("Player1 = " + deck1 + " (" + deck1.size() + ")");
		System.out.println("Player2 = " + deck2 + " (" + deck2.size() + ")");
		play();
	}
	public static void main(String[] args)
	{
		War war = new War();	
	}
	private void inputDP()
	{
	//	int[] tab = {5, 1, 13, 10, 11, 3, 2, 10, 4, 12, 5, 11, 10, 5, 7, 6, 6, 11, 9, 6, 3, 13, 6, 1, 8, 1};
	//	int[] tab2 = {9, 12, 8, 3, 11, 10, 1, 4, 2, 4, 7, 9, 13, 8, 2, 13, 7, 4, 2, 8, 9, 12, 3, 12, 7, 5};
	//	int[] tab = {3, 11, 6, 12, 2, 13, 5, 7, 10, 3, 10, 4, 12, 11, 1, 13, 12, 2, 1, 7, 10, 6, 12, 5, 8, 1};
	//	int[] tab2 = {9, 10, 7, 9, 5, 2, 6, 1, 11, 11, 7, 9, 3, 4, 8, 3, 4, 8, 8, 4, 6, 9, 13, 2, 13, 5};
		int[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int[] tab2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		
		for(int x: tab)
		{
			deck1.add(x);
		}
		for(int x: tab2)
		{
			deck2.add(x);
		}
	}
	private void play()
	{
		while(deck1.size() != 0 && deck2.size() != 0)
		{
			singleRound();
		}
		System.out.println("Player1 = " + deck1.size());
		System.out.println(deck1);
		System.out.println("Player2 = " + deck2.size());
		System.out.println(deck2);
		if(table.size() == 52)
			System.out.println("TIE!!!");
		System.out.println("Rounds played = " + rounds);
		System.out.println("Wars fought = " + wars);
	}
	private void singleRound()
	{
		rounds++;
		int player1 = deck1.remove(0);
		int player2 = deck2.remove(0);
		table.add(player1);
		table.add(player2);
		if(player1 > player2)
		{
			playerOneWon();
		}
		else if(player1 < player2)
		{
			playerTwoWon();
		}
		else
		{
			if(table.size() != 52)
			{
				
				if(deck1.size()== 0)
					playerTwoWon();
				else if(deck2.size()== 0)
					playerOneWon();
				else
				{
					wars++;
					if(deck1.size()>= 4 && deck2.size() >= 4)
					{
					//	System.out.println("POWPlayer1 = " + deck1.size() + "Player2 = " + deck2.size());
						table.add(deck1.remove(0));
						table.add(deck2.remove(0));
						table.add(deck1.remove(0));
						table.add(deck2.remove(0));
						table.add(deck1.remove(0));
						table.add(deck2.remove(0));
					}
					else
					{
					//	System.out.println("Player1 = " + deck1.size() + "Player2 = " + deck2.size());
						while(Math.min(deck1.size(), deck2.size()) != 1)
						{
							table.add(deck1.remove(0));
							table.add(deck2.remove(0));
						}
							
					}
					singleRound();
				}
			}
		}		
	}
	private void playerOneWon()
	{
		if(table.size() == 2)
		{
			deck1.add(table.get(1));
			deck1.add(table.get(0));
			cleanTable();
		}
		else
		{
			int faceDowns = (((table.size() - 2) % 8) - 2)/2;
			if(faceDowns < 0)
				faceDowns = 3;
			
			System.out.println("Size =" + table.size() + "ile = " + faceDowns);
			for(int i = 0; i < faceDowns; i++)
			{
				deck1.add(table.get(table.size() - (3 + i + faceDowns)));
			}
			deck1.add(table.get(table.size() - 2));
			for(int i = 0; i < faceDowns; i++)
			{
				deck1.add(table.get(table.size() - (3 + i)));
			}
			deck1.add(table.get(table.size() - 1));
			
			for(int i = 0; i < 2+faceDowns*2; i++)
			{
				table.remove(table.size() - 1);
			}
			playerOneWon();
		}
		
	}
	private void playerTwoWon()
	{
		if(table.size() == 2)
		{
			deck2.add(table.get(0));
			deck2.add(table.get(1));
			cleanTable();
			
		}
		else
		{
			int faceDowns = (((table.size() - 2) % 8) - 2)/2;
			if(faceDowns < 0)
				faceDowns = 3;
			System.out.println("Size =" + table.size() + "ile = " + faceDowns);
			for(int i = 0; i < faceDowns; i++)
			{
				deck2.add(table.get(table.size() - (3 + i)));
			}
			deck2.add(table.get(table.size() - 1));
			for(int i = 0; i < faceDowns; i++)
			{
				deck2.add(table.get(table.size() - (3 + i + faceDowns)));
			}
			deck2.add(table.get(table.size() - 2));
			
			
			for(int i = 0; i < 2+faceDowns*2; i++)
			{
				table.remove(table.size() - 1);
			}
			playerTwoWon();
		}
	}
	private void cleanTable()
	{
		table.clear();
	}
}

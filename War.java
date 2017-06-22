package War;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//Random
public class War
{
	Random generator = new Random();
	List<Integer> deck1 = new ArrayList<Integer>();
	List<Integer> deck2 = new ArrayList<Integer>();
	List<Integer> table = new ArrayList<Integer>();
	public War()
	{
		inputDP();
		
	//	dealCards();
		System.out.println("Starting decks:");
		System.out.println("Player1 = " + deck1 + " (" + deck1.size() + ")");
		System.out.println("Player2 = " + deck2 + " (" + deck2.size() + ")");
		play();
	}
	public static void main(String[] args)
	{
		War war = new War();	
	}
	private void dealCards()
	{
		int[]tab = new int[13];
		int card;
		for(int i = 0; i < 52; i++)
		{
			do
			{
				card = generator.nextInt(13)+1;
			}while(tab[card-1] == 4);
			tab[card-1]++;
			
			if(i%2 == 0)
				deck1.add(card);
			else
				deck2.add(card);
		}
	}
	private void inputDP()
	{
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
	private int play()
	{
		while(deck1.size() != 0 && deck2.size() != 0)
		{
			singleRound();
		}
		System.out.println("Player1 = " + deck1.size());
		System.out.println(deck1);
		System.out.println("Player2 = " + deck2.size());
		System.out.println(deck2);
		return 0;
	}
	private void singleRound()
	{
		
		
		
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
				if(Math.max(deck1.size(), deck2.size()) != 0)
				{
					if(deck1.size()== 0)
						playerTwoWon();
					else if(deck2.size()== 0)
						playerOneWon();
					else
					{
						if(deck1.size()>= 2 && deck2.size() >= 2)
						{
							table.add(deck1.remove(0));
							table.add(deck2.remove(0));
						}
						singleRound();
					}
				}
				
				
			}
		
		
			
	}
	private void playerOneWon()
	{
		while(table.size() != 0)
			deck1.add(table.remove(generator.nextInt(table.size())));
	}
	private void playerTwoWon()
	{
		while(table.size() != 0)
			deck2.add(table.remove(generator.nextInt(table.size())));
	}
}

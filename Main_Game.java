import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
public class Main_Game {

	public static void main(String[] args) throws IOException {
		
		
		SingleLinkedList sll_player1 = new SingleLinkedList();
		SingleLinkedList sll_player2 = new SingleLinkedList();
		SingleLinkedList sll_HighScoreTable = new SingleLinkedList();
		
		int player1_score=0;
		int player2_score=0;
		
		boolean removeisdoneplayer1=false;
		boolean removeisdoneplayer2=false;
		       
		int turn=0;
		
		while(!(turn==10)) {
			turn++;
			// add random three number for player1 and player2
			add_random3number(sll_player1);
			add_random3number(sll_player2);
			
			System.out.println();
			System.out.println("Turn:"+turn);
			DisplayPlayers(sll_player1,sll_player2,player1_score,player2_score);
			
			//checking combinations for player1 and player2 
			   //first if four number is same 
			   //--------------------------
			for(int i=1;i<=6;i++) {
				
				if(sll_player1.count(i)>=4) {
					for(int j=0;j<4;j++) {						
						sll_player1.remove(i);
					}
					
				    removeisdoneplayer1=true;
				    player1_score+=10;
				}
				if(sll_player2.count(i)>=4) {
					for(int j=0;j<4;j++) {						
						sll_player2.remove(i);
					}
					
				    removeisdoneplayer2=true;
				    player2_score+=10;
				}
								
			}
			  //--------------------------
			  //second if six number is exist in list
			    
			   int countfornumbers1 =0; //if this equals six this means that list have 1,2,3,4,5,6
			   for(int i=1;i<=6;i++) {
				   if(sll_player1.search(i)) {
					   countfornumbers1++;
				   }
			   }
			   
			   if(countfornumbers1==6) {
				   for(int i=1;i<=6;i++) {
					   sll_player1.remove(i);				   
				   }
				    removeisdoneplayer1=true;
				    player1_score+=30;
				    countfornumbers1=0;
			   }
			   
			   //************************************
			   
			   int countfornumbers2 =0; //if this equals six this means that list have 1,2,3,4,5,6
			   for(int i=1;i<=6;i++) {
				   if(sll_player2.search(i)) {
					   countfornumbers2++;
				   }
			   }
			   
			   if(countfornumbers2==6) {
				   for(int i=1;i<=6;i++) {
					   sll_player2.remove(i);				   
				   }
				    removeisdoneplayer2=true;
				    player2_score+=30;
				    countfornumbers2=0;
			   }
			   
			
			  //--------------------------
			   
			
			
			 //--------------------------
			
			//if we have any remove in the list we have to print again
			if(removeisdoneplayer1&&removeisdoneplayer2) {
				System.out.println();
				System.out.println();
				DisplayPlayers(sll_player1,sll_player2,player1_score,player2_score);
				removeisdoneplayer1=false;
				removeisdoneplayer2=false;
			}
			else if(removeisdoneplayer1) {
				System.out.println();
				System.out.println();
				DisplayPlayers(sll_player1,sll_player2,player1_score,player2_score);
				removeisdoneplayer1=false;			
			}
			
			
			else if(removeisdoneplayer2) {
				System.out.println();
				System.out.println();
				DisplayPlayers(sll_player1,sll_player2,player1_score,player2_score);
				removeisdoneplayer2=false;			
			}
			
			
		}
		
		System.out.println(" ");
		System.out.println("Game is Over.");
		
		//Read HighScoreTable put in sll
	    //-------------------------------------------------------------------   
	     String fileName="HighScoreTable.txt";
	     Path HighScoreTablePath = Paths.get(fileName);											
		 Scanner HighScoreTable = new Scanner(HighScoreTablePath);	
		 int ColumnCount=2;
		 //counting the questions file lines for arrays rows.
		 int RowCount = (int)Files.lines(HighScoreTablePath).count();
		 //creating 2d array for questions.															
		 String[][] arrayHighScoreTable = new String[RowCount][ColumnCount];	
	     
		 while (HighScoreTable.hasNextLine()) {						
				for(int i=0;i<arrayHighScoreTable.length;i++) {
					String[] line = HighScoreTable.nextLine().trim().split(" ");
					for (int j=0; j<line.length; j++) {
						arrayHighScoreTable[i][j] =line[j];					               							
					}
				}
			}
		 Random rand=new Random();
				 
		 for(int i=0;i<arrayHighScoreTable.length;i++) {
			 
			 Object Person=null;
			
			 Person=new Human(arrayHighScoreTable[i][0],arrayHighScoreTable[i][1]);			 
			 sll_HighScoreTable.add(Person);
		 }
		 
		 //--------------------------------------------------------------------
		 Object playerwin=null;
	      
		 //which player win
		 System.out.println(" ");
		 if(player1_score>player2_score) {
			 playerwin=new Human("Player1",String.valueOf(player1_score));
			 System.out.println("The winner is Player1 ");
		 }
		 else if(player2_score>player1_score) {
			 
			 playerwin=new Human("Player2",String.valueOf(player2_score));
			 System.out.println("The winner is Player2 ");
		 }
		 else {
			 int number=rand.nextInt(2);
			 
			 if(number==0) {
				 playerwin=new Human("Player1",String.valueOf(player1_score));	
				 System.out.println("The winner is Player1 ");
			 }
			 else {
				 playerwin=new Human("Player2",String.valueOf(player2_score));
				 System.out.println("The winner is Player2 ");
				 
			 }
			 
		 }
		 System.out.println(" ");
		 System.out.println("High Score Table");
		 
		 
		 
		 sll_HighScoreTable.add(playerwin);
		 
		 

		 Node n = sll_player1.head;
		   
		    while(n.link != null)
		        n= n.link;
		    
		     
		 sll_HighScoreTable.insertion_Sort(sll_HighScoreTable.head);
		
		 
		
		 
		 sll_HighScoreTable.displayHighScore(playerwin);
		
	     
		


	}
	
	public static void add_random3number(SingleLinkedList sll) {
		
		Random random=new Random();
		
		for(int i=0;i<3;i++) {
			
			int number=random.nextInt(6)+1;
			sll.add(number);
						
		}
	
		
	}
     public static void DisplayPlayers(SingleLinkedList sll,SingleLinkedList sll2,int score1,int score2) {
		
    	 System.out.print("Player1: ");
    	 sll.display(score1);
    	
    	 System.out.print("Player2: ");
    	 sll2.display(score2);
    	 
		
		
	}

}


public class Human {
	private String name;
	private int score;
	private String full;
	
	
	
	
	public Human(String nn,String mm) {
		name=nn;
		full=nn+" "+mm;
	    score=Integer.parseInt(mm);  
		
		
	}



	public int getscore() {
		return score;
	}
	
	public String getfull() {
		return full;
	}




	
	
	
	
	
	

}


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SingleLinkedList {
	
	 Node head;
	
	private Node sorted;
	
	
	private static final String NEW_LINE = System.lineSeparator();
	
	public void add(Object data) {
		if(head==null) {
			Node newNode = new Node(data);
			head = newNode;
		}
		
		else
		{
			Node temp = head;
			while(temp.getLink()!=null) {
				temp = temp.getLink();
			}
			Node newNode = new Node(data);
			temp.setLink(newNode);
		}
	}
	
	
	
	
	public void display(int score) {
		if(head==null)
			System.out.println("");
		else
		{
			Node temp = head;
			while(temp!=null) {
				System.out.print(temp.getData()+" ");
				temp = temp.getLink();
			}
		}
		System.out.print("  Score: "+score);
		System.out.println();
	}
	
	public void remove(Object dataToDelete) {
		
		boolean delete=false;
		
		if (head==null)
			System.out.println("Linked list is empty");
		else {
		    
		    
			
			while(head!=null&&(Integer)head.getData()==(Integer)dataToDelete&& !delete) {
				head=head.getLink();
				delete=true;
								
			}
			
			Node temp= head;			
			Node previous = null;
  	
			while (temp!=null) {
				if (!delete && (Integer)temp.getData()==(Integer)dataToDelete ) {
					previous.setLink(temp.getLink());
					temp= previous;
					delete=true;
					
				}
				previous=temp;
				temp=temp.getLink();
			}
		}
	}
	
	
	
	
	public boolean search(Object data) {
		if(head==null) {
			System.out.println("List is empty");
			return false;
		}
		else {
			Node temp = head;
			while(temp!=null) {
				if((Integer)temp.getData()==(Integer)data)
					return true;
				temp = temp.getLink();
			}
			return false;
		}
	}
	
	public int count(Object data) {
		if (head==null)
			return 0;
		else
		{
			int count = 0;
			Node temp = head;
			while (temp!=null) {
				if((Integer)temp.getData()==(Integer)data) {
					count++;
				}
				temp = temp.getLink();
				
			}
			return count;
		}
	}
	
	public void displayHighScore(Object playerwin) throws IOException {
		if(head==null)
			System.out.println("List is empty!");
		else
		{
			boolean flag=true;
			int count=0;
			Node temp = head;
			while(temp!=null) {
				if(count==10) {
					break;
				}
				 if(temp.getData() instanceof Human) {	
					 if(flag&&(((Human) temp.getData()).getfull().equals(((Human) playerwin).getfull()))) {
						  Path path = Paths.get("HighScoreTable.txt");
						  appendToFile(path,"\r\n");
						  appendToFile(path,((Human) playerwin).getfull());
						  
						  flag=false;
						
					 }
					 System.out.println(((Human) temp.getData()).getfull());
					 
				 }
				
				temp = temp.getLink();
				count++;
			}
		}
		
	}
	
	 void insertion_Sort(Node headref)
	    {
	        // Initialize sorted linked list
	        sorted = null;
	        Node current = headref;
	       
	       
	        while (current != null)
	        {
	            // Store next for next iteration
	            Node next = current.link;
	           
	            // insert current in sorted linked list
	            sorted_Insert(current);
	           
	            // Update current
	            current = next;
	        }
	       
	        // Update head_ref to point to sorted linked list
	        head = sorted;
	    }
	 
	     
	    
	    void sorted_Insert(Node newnode)
	    {
	        // Special case for the head end
	        if (sorted == null ||((Human)sorted.data).getscore() <= ((Human)newnode.data).getscore())
	        {
	            newnode.link = sorted;
	            sorted = newnode;
	        }
	        else
	        {
	            Node current = sorted;
	           
	            // Locate the node before the point of insertion
	            while (current.link != null &&((Human) current.link.data).getscore() > ((Human)newnode.data).getscore())
	            {
	                current = current.link;
	            }
	           
	            newnode.link = current.link;
	            current.link = newnode;
	        }
	    }


	

		
	 private static void appendToFile(Path path, String content)
				throws IOException {    
     Files.write(path, content.getBytes(StandardCharsets.UTF_8),
             StandardOpenOption.CREATE,
             StandardOpenOption.APPEND);
	 }
	
	
	
	
	
	
		
} 

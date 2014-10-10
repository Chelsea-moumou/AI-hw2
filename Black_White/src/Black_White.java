import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Black_White {
	
	char[][] board = new char[8][8];
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		
	new Black_White();
	}
	
	
	Black_White()throws FileNotFoundException, IOException
	{
		int i=1;
		int searchway=0;
		BufferedReader br=null;
	
	try {
		
		br = new BufferedReader(new FileReader("./input.txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        searchway=Integer.parseInt(line);
        char currentPlayer;
        int cutoff;
        while (line != null) {
            
            line = br.readLine();
            if(i==1){
            	searchway=Integer.parseInt(line);
            	System.out.println(searchway);
            }else if(i==2){
            	currentPlayer=line.charAt(0);
            	System.out.println(currentPlayer);
            }else if(i==3){
            	cutoff=Integer.parseInt(line);
            	
            }else if(i>3&&i<12){ 
            	
            	String[] temp=line.split(" ");
            	for(int j=0;j<temp.length;j++){ 
            	
            		board[i-4][j]=temp[j].charAt(0);
            		System.out.println(board[i-4][j]);
            	}
            }
            i++;
        }
       
        }catch (IOException e) {
			e.printStackTrace();
			
        }finally{
        		br.close();
        }
	
		
	
		//search(s,t,searchway);
	
 }
	

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Black_White {
	
	char[][] board = new char[8][8];
	char[][] temp=board;
	int[][] matrix=new int[8][8];
	String[][] nameMatrix=new String[8][8];
	char currentPlayer;
    int cutoff;
	public static void main(String[] args) throws FileNotFoundException, IOException
	{	
	new Black_White();
	}
	
	void initialvaluematrix(){
		
		for(int i=0;i<8;i++){
		if(i==0|i==7){
			matrix[i][0]=99;
			matrix[i][1]=-8;
			matrix[i][2]=8;
			matrix[i][3]=6;
			matrix[i][4]=6;
			matrix[i][5]=8;
			matrix[i][6]=-8;
			matrix[i][7]=99;}
		else if(i==1|i==6){
			matrix[i][0]=-8;
			matrix[i][1]=-24;
			matrix[i][2]=-4;
			matrix[i][3]=-3;
			matrix[i][4]=-3;
			matrix[i][5]=-4;
			matrix[i][6]=-24;
			matrix[i][7]=-8;}
		else if(i==2|i==5){
			matrix[i][0]=8;
			matrix[i][1]=-4;
			matrix[i][2]=7;
			matrix[i][3]=4;
			matrix[i][4]=4;
			matrix[i][5]=7;
			matrix[i][6]=-4;
			matrix[i][7]=8;}
		else if(i==3|i==4){
			matrix[i][0]=6;
			matrix[i][1]=-3;
			matrix[i][2]=4;
			matrix[i][3]=0;
			matrix[i][4]=0;
			matrix[i][5]=4;
			matrix[i][6]=-3;
			matrix[i][7]=6;
		}
		}
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
			if(i==0){
				nameMatrix[i][j]="a"+(j+1);
				//System.out.println(nameMatrix[i][j]);
				}
			else if(i==1){
				nameMatrix[i][j]="b"+(j+1);
				}
			else if(i==2){
				nameMatrix[i][j]="c"+(j+1);
				}
			else if(i==3){
				nameMatrix[i][j]="d"+(j+1);
				}
			else if(i==4){
				nameMatrix[i][j]="e"+(j+1);
				}
			else if(i==5){
				nameMatrix[i][j]="f"+(j+1);
				}
			else if(i==6){
				nameMatrix[i][j]="g"+(j+1);
				}
			else if(i==7){
				nameMatrix[i][j]="h"+(j+1);
				}
			}
		}
		
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
        //System.out.println(searchway);
        
        while (line != null) {
            
            line = br.readLine();
            if(i==1){
            	
            	currentPlayer=line.charAt(0);
            	//System.out.println(currentPlayer);
            }else if(i==2){
            	cutoff=Integer.parseInt(line);
            	//System.out.println(cutoff);
            }else if(i>2&&i<11){ 
            	
            	for(int j=0;j<8;j++){ 
            	
            		board[i-3][j]=line.charAt(j);
            		//System.out.println(board[i-3][j]);	
            	}
            }
            i++;
        }
       
        }catch (IOException e) {
			e.printStackTrace();
			
        }finally{
        		br.close();
        }	
	initialvaluematrix();
	switch(searchway){
	case 1:
		play();
		break;
	case 2:
		playMinimax(cutoff,searchway);
		break;
	case 3:
		playAlphabeta(cutoff);
		break;
	case 4:
		break;
	
	}
	
 }
	
	void playMinimax(int cutoff,int searchway){
		List<Node> open= new ArrayList<Node>();
		List<Node> closed= new ArrayList<Node>();
		Node node=new Node(0,-1,"root",0,1,-999);
		open.add(node);
		
		int currentId = 0;
		while(!open.isEmpty())
		{
			Node currentNode=getNext(open);
			int childLength=0;
			if(currentNode.depth<cutoff+1){
			for(int i=0;i<8;i++){
				for(int j=0; j<8;j++){
					if(board[i][j]=='*'){
						if(validMove(i,j,currentPlayer)){
							childLength++;
							int tempValue=0;
							if(currentNode.depth<cutoff){
								if(currentNode.minimax==1){
									tempValue=999;
								}else{tempValue=-999;}
							}else if(currentNode.depth==cutoff){
								move(i,j,currentPlayer,searchway);
								tempValue=value(currentPlayer);}
							
							Node childNode=new Node(currentId+childLength,currentId,nameMatrix[i][j],-currentNode.minimax,currentNode.depth+1,tempValue);
						    open.add(childNode);
						}
						
					}
				}
			}
			
			}
		}
	}
	void playAlphabeta(int cutoff){
		
	}
	
	int value(char current){
		int value=0;
		char opposite;
    	
		if(current=='X'){
			opposite='O';
		}else{
			opposite='X';
		}
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				if(temp[i][j]==current){
				value=value+temp[i][j];
				}else if(temp[i][j]==opposite){
				value=value-temp[i][j];
				}			
						}
					}	
	return value;
	}
	
	public Node getNext(List<Node> open)
	{
		List<Node> sort_open=new ArrayList<Node>(open);
	
		Collections.sort(sort_open);
		Node nextNode = sort_open.get(0);
		for(int i=0;i<open.size();i++) {
			Node node = open.get(i);
			if(nextNode.id == node.id) {
				open.remove(i);
				break;
			}
		}
		
		return nextNode;
	}
	void play()
	{
		//int passFlag=-2;
		//while(passFlag<0){
		int optimalValue=-99;
		int bestX=8,bestY=8;
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				if(board[i][j]=='*'){
					if(validMove(i,j,currentPlayer)){
						if(optimalValue<matrix[i][j]){
							optimalValue=matrix[i][j];
							bestX=i;
							bestY=j;
						}
					}
					
				}
			}
		}
		if(bestX!=8&&bestY!=8){
			//String log;
			board[bestX][bestY]=currentPlayer;
			move(bestX,bestY,currentPlayer,0);
			for(int i=0;i<8;i++){
				System.out.println();
				for(int j=0; j<8;j++){
					System.out.print(board[i][j]);
					
							}
						}
			System.out.println();
		}else{
			//String log="pass";
			//System.out.println("pass");
			for(int i=0;i<8;i++){
				System.out.println();
				
				for(int j=0; j<8;j++){
					System.out.print(board[i][j]);
							}
						}
			System.out.println();
			//passFlag++;
		}
		//change currentPlayer
//		if(currentPlayer=='X'){
//			currentPlayer='O';
//		}else{
//			currentPlayer='X';
//		}
		try{
			File file = new File("./output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write(log);
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();}
		//}
	}
	
	void move(int currentX, int currentY, char current, int searchway){
	    char[][] temp1=board;
		char opposite;
	    	int x=currentX;
			int y=currentY;
			if(current=='X'){
				opposite='O';
			}else{
				opposite='X';
			}
			//right
			
			while(y<7){
				if(board[x][y+1]=='*'|board[x][y+1]==current){
					break;
				}else if(board[x][y+1]==opposite){
					y=y+1;
					if(y+1<8&&board[x][y+1]==current){
					
						while(y!=currentY){
							
							board[x][y]=current;
							y=y-1;
						}
					}
				}
				
			}
			 x=currentX;
			 y=currentY;
			//up-right
			 x=currentX;
			 y=currentY;
			while(y<7&&x>0){
				if(board[x-1][y+1]=='*'|board[x-1][y+1]==current){
					break;
				}else if(board[x-1][y+1]==opposite){
					y=y+1;
					x=x-1;
					if(x-1>-1&&y+1<8&&board[x-1][y+1]==current){
						
						while(y!=currentY){
							board[x][y]=current;
							x=x+1;
							y=y-1;
						}
					}
				}
				
			}
			//down-right
			x=currentX;
			y=currentY;
			while(y<7&&x<7){
				if(board[x+1][y+1]=='*'|board[x+1][y+1]==current){
					break;
				}else if(board[x+1][y+1]==opposite){
					y=y+1;
					x=x+1;
					if(x+1<8&&y+1<8&&board[x+1][y+1]==current){
						
						while(y!=currentY){
							board[x][y]=current;
							x=x-1;
							y=y-1;
						}
					}
				}
				
			}
			//up
			x=currentX;
			y=currentY;
			while(x>0){
				if(board[x-1][y]=='*'|board[x-1][y]==current){
					break;
				}else if(board[x-1][y]==opposite){
					
					x=x-1;
					if(x-1>-1&&board[x-1][y]==current){
						
						while(x!=currentX){
							board[x][y]=current;
							x=x+1;
							
						}
					}
				}
				
			}
			//left
			x=currentX;
			y=currentY;
			while(y>0){
				if(board[x][y-1]=='*'|board[x][y-1]==current){
					break;
				}else if(board[x][y-1]==opposite){
					y=y-1;
					if(y-1>-1&&board[x][y-1]==current){
						while(y!=currentY){
							board[x][y]=current;
							
							y=y+1;
						}
					}
				}
				
			}
			//up-left
			x=currentX;
			y=currentY;
			while(y>0&&x>0){
				if(board[x-1][y-1]=='*'|board[x-1][y-1]==current){
					break;
				}else if(board[x-1][y-1]==opposite){
					y=y-1;
					x=x-1;
					if(x-1>-1&&y-1>-1&&board[x-1][y-1]==current){
						
						while(y!=currentY){
							board[x][y]=current;
							x=x+1;
							y=y+1;
						}
					}
				}
				
			}
			//down-left
			x=currentX;
			y=currentY;
			while(y>0&&x<7){
				if(board[x+1][y-1]=='*'|board[x+1][y-1]==current){
					break;
				}else if(board[x+1][y-1]==opposite){
					y=y-1;
					x=x+1;
					if(x+1<8&&y-1>-1&&board[x+1][y-1]==current){
						
						while(y!=currentY){
							board[x][y]=current;
							x=x-1;
							y=y+1;
						}
					}
				}
				
			}
			//down
			x=currentX;
			y=currentY;
			while(x<7){
				if(board[x+1][y]=='*'|board[x+1][y]==current){
					break;
				}else if(board[x+1][y]==opposite){
					x=x+1;
					if(x+1<8&&board[x+1][y]==current){
						
						while(x!=currentX){
							board[x][y]=current;
							x=x-1;
							
						}
					}
				}
				
			}
			if(searchway==2){
				temp=board;
				board=temp1;
			}
	    }
    boolean validMove(int currentX, int currentY, char current){
			char opposite;
			int x=currentX;
			int y=currentY;
			if(current=='X'){
				opposite='O';
			}else{
				opposite='X';
			}
			
			//right
			while(y<7){
				if(board[x][y+1]=='*'|board[x][y+1]==current){
					break;
				}else if(board[x][y+1]==opposite){
					y=y+1;
					if(y+1<8&&board[x][y+1]==current){
						return true;
					}
				}
				
			}
			//up-right
			 x=currentX;
			 y=currentY;
			while(y<7&&x>0){
				if(board[x-1][y+1]=='*'|board[x-1][y+1]==current){
					break;
				}else if(board[x-1][y+1]==opposite){
					y=y+1;
					x=x-1;
					if(x-1>-1&&y+1<8&&board[x-1][y+1]==current){
						return true;
					}
				}
				
			}
			//down-right
			 x=currentX;
			 y=currentY;
			while(y<7&&x<7){
				if(board[x+1][y+1]=='*'|board[x+1][y+1]==current){
					break;
				}else if(board[x+1][y+1]==opposite){
					y=y+1;
					x=x+1;
					if(x+1<8&&y+1<8&&board[x+1][y+1]==current){
						return true;
					}
				}
				
			}
			//up
			 x=currentX;
			 y=currentY;
			while(x>0){
				if(board[x-1][y]=='*'|board[x-1][y]==current){
					break;
				}else if(board[x-1][y]==opposite){
					x=x-1;
					if(x-1>-1&&board[x-1][y]==current){
						return true;
					}
				}
				
			}
			//left
			 x=currentX;
			 y=currentY;
			while(y>0){
				if(board[x][y-1]=='*'|board[x][y-1]==current){
					break;
				}else if(board[x][y-1]==opposite){
					y=y-1;
					if(y-1>-1&&board[x][y-1]==current){
						return true;
					}
				}
				
			}
			//up-left
			 x=currentX;
			 y=currentY;
			while(y>0&&x>0){
				if(board[x-1][y-1]=='*'|board[x-1][y-1]==current){
					break;
				}else if(board[x-1][y-1]==opposite){
					y=y-1;
					x=x-1;
					if(x-1>-1&&y-1>-1&&board[x-1][y-1]==current){
						return true;
					}
				}
				
			}
			//down-left
			 x=currentX;
			 y=currentY;
			while(y>0&&x<7){
				if(board[x+1][y-1]=='*'|board[x+1][y-1]==current){
					break;
				}else if(board[x+1][y-1]==opposite){
					y=y-1;
					x=x+1;
					if(x+1<8&&y-1>-1&&board[x+1][y-1]==current){
						return true;
					}
				}
				
			}
			//down
			 x=currentX;
			 y=currentY;
			while(x<7){
				if(board[x+1][y]=='*'|board[x+1][y]==current){
					break;
				}else if(board[x+1][y]==opposite){
					x=x+1;
					if(x+1<8&&board[x+1][y]==current){
						return true;
					}
				}
				
			}
		return false;	
		}
}

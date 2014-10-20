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
			if(j==0){
				nameMatrix[i][j]="a"+(i+1);
				}
			else if(j==1){
				nameMatrix[i][j]="b"+(i+1);
				}
			else if(j==2){
				nameMatrix[i][j]="c"+(i+1);
				}
			else if(j==3){
				nameMatrix[i][j]="d"+(i+1);
				}
			else if(j==4){
				nameMatrix[i][j]="e"+(i+1);
				}
			else if(j==5){
				nameMatrix[i][j]="f"+(i+1);
				}
			else if(j==6){
				nameMatrix[i][j]="g"+(i+1);
				}
			else if(j==7){
				nameMatrix[i][j]="h"+(i+1);
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
		
		br = new BufferedReader(new FileReader("./input 3.txt"));
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
		playMinimax(true,cutoff,0,8,8,-1);
		break;
	case 3:
		playAlphabeta(true,cutoff,0,8,8,Integer.MIN_VALUE,Integer.MAX_VALUE);
		break;
	case 4:
		break;
	
	}
	
 }
	
	int playMinimax( boolean turn, int cutoff, int depth, int oi, int oj, int flag) {
		int max = Integer.MIN_VALUE;
		
		int min = Integer.MAX_VALUE;
		if(flag==0)flag=-1;
		String name = "root";
		if(oi < 8 && oj < 8)
			name = nameMatrix[oi][oj];
		else if(oi==8 && oj==8 && depth!=0)
			name = "pass";
		
		if(cutoff == 0) {
			//changePlayer();
			int val = value(currentPlayer);
			//changePlayer();
			System.out.println( name + "," + String.valueOf(depth) + "," + String.valueOf(val));
			return val;
		}

		
		System.out.println( name + "," + String.valueOf(depth) + "," + String.valueOf(turn?max:min));
		
		int resX = 8;
		int resY = 8;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(board[i][j] == '*') {
					if(validMove(i,j, currentPlayer)) {
						if(flag<0)
						flag++;
						char[][] tmpBoard = new char[8][8];
						cloneBoard(tmpBoard, board);
						move(i,j,currentPlayer, 0);
						changePlayer();
						int val = playMinimax(!turn, cutoff-1,depth+1, i, j, flag);
						changePlayer();
						if(turn) {
							if(val > max) {
								max = val;
								resX = i;
								resY = j;
							}
						} else {
							if(val < min) {
								min = val;
								resX = i;
								resY = j;
							}
						}
						board = tmpBoard;
						System.out.println(name + "," + String.valueOf(depth) + "," + String.valueOf(turn?max:min));
					}
				}
			}
		}
		if(flag==-1){
//			String name1="pass";
//			System.out.println(name1 + "," + String.valueOf(depth+1) + "," + String.valueOf(turn?max:min));
			int val = playMinimax(!turn, cutoff-1,depth+1, oi, oj,flag-1);
			if(turn) {
				if(val > max) {
					max = val;
					
				}
			} else {
				if(val < min) {
					min = val;
					
				}
			
		}
			
		}
		if(flag==-2) {
			
			int val = value(currentPlayer);
			System.out.println( name + "," + String.valueOf(depth+1) + "," + String.valueOf(val));
			return val;
		}

		
		if(turn)
			return max;
		else
			return min;
	}
	
	void changePlayer() {
		if(currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
	
		int playAlphabeta( boolean turn, int cutoff, int depth, int oi, int oj, int alpha, int beta) {
			int max = Integer.MIN_VALUE;
			
			int min = Integer.MAX_VALUE;
			
			String name = "root";
			if(oi < 8 && oj < 8)
				name = nameMatrix[oi][oj];
			
			if(cutoff == 0) {
				//changePlayer();
				int val = value(currentPlayer);
				//changePlayer();
				System.out.println( name + "," + String.valueOf(depth) + "," + String.valueOf(val)+ "," + String.valueOf(turn?alpha:val)+","+String.valueOf(turn?beta:val));
				return val;
			}
			
			
			System.out.print( name + "," + String.valueOf(depth) + "," + String.valueOf(turn?max:min) +"," + String.valueOf(alpha)+","+String.valueOf(beta)+'\n');
			
			int resX = 8;
			int resY = 8;
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if(board[i][j] == '*') {
						if(validMove(i,j, currentPlayer)) {
							char[][] tmpBoard = new char[8][8];
							cloneBoard(tmpBoard, board);
							move(i,j,currentPlayer, 0);
							changePlayer();
							int val = playAlphabeta(!turn, cutoff-1,depth+1, i, j, alpha, beta);
							changePlayer();
							if(turn) {
								if(val > max) {
									max = val;
									resX = i;
									resY = j;
									alpha=Math.max(alpha, playAlphabeta(!turn, cutoff-1,depth+1, i, j, alpha, beta));
									if(alpha>beta||alpha==beta){
										return beta;
									}
								}
							} else {
								if(val < min) {
									min = val;
									resX = i;
									resY = j;
									beta=Math.min(beta, playAlphabeta(!turn, cutoff-1,depth+1, i, j, alpha, beta));
									if(alpha>beta||alpha==beta){
										return alpha;
									}
								}
							}
							board = tmpBoard;
							System.out.println(name + "," + String.valueOf(depth) + "," + String.valueOf(turn?max:min) +"," + String.valueOf(alpha)+","+String.valueOf(beta));
						}
					}
				}
			}
			
			
			if(turn)
				return max;
			else
				return min;
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
				value=value+matrix[i][j];
				}else if(temp[i][j]==opposite){
				value=value-matrix[i][j];
				}			
						}
					}	
	return value;
	}
	
	
	void play()
	{
		String log = null;
		//int passFlag=-2;
		//while(passFlag<0){
		int optimalValue=Integer.MIN_VALUE;
		int bestX=8,bestY=8;
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				if(board[i][j]=='*'){
					if(validMove(i,j,currentPlayer)){
						move(i,j,currentPlayer,2);
						int value=value(currentPlayer);
						//System.out.println(value);
						if(optimalValue<value){
							optimalValue=value;
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
				for(int j=0; j<8;j++){
					System.out.print(board[i][j]);
					log=log+String.valueOf(board[i][j]);
							}
				log=log+'\n';
				System.out.println();
				}
			
		}else{
			//String log="pass";
			//System.out.println("pass");
			for(int i=0;i<8;i++){
				
				for(int j=0; j<8;j++){
					System.out.print(board[i][j]);
					log=log+String.valueOf(board[i][j]);
							}
				log=log+'\n';
				System.out.println();
						}
			
			//passFlag++;
		}

		try{
			File file = new File("./output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			log=log.substring(4);
			bw.write(log);
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();}
		//}
	}
	
	private void cloneBoard(char[][] aim, char[][] origin) {
		for(int i = 0; i< 8; i++)
			for(int j=0; j<8; j++)
				aim[i][j] = origin[i][j];
	}
	void move(int currentX, int currentY, char current, int searchway){
	    char[][] temp1 = new char[8][8];
	    cloneBoard(temp1, board);
	    
		char opposite;
		board[currentX][currentY]=current;
     		int x=currentX;
			int y=currentY;
			if(current=='X'){
				opposite='O';
			}else{
				opposite='X';
			}
			//right
			
			while(y<7){
				if(board[x][y+1]=='*'||board[x][y+1]==current){
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
			
			//up-right
			 x=currentX;
			 y=currentY;
			while(y<7&&x>0){
				if(board[x-1][y+1]=='*'||board[x-1][y+1]==current){
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
				if(board[x+1][y+1]=='*'||board[x+1][y+1]==current){
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
				if(board[x-1][y]=='*'||board[x-1][y]==current){
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
				if(board[x][y-1]=='*'||board[x][y-1]==current){
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
				if(board[x-1][y-1]=='*'||board[x-1][y-1]==current){
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
				if(board[x+1][y-1]=='*'||board[x+1][y-1]==current){
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
				if(board[x+1][y]=='*'||board[x+1][y]==current){
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
			
			temp = board;
			if(searchway==2){
				
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

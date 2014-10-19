import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Black_White {
	
	char[][] board = new char[8][8];
	int[][] matrix=new int[8][8];
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
	play();
 }
	
	void play()
	{
		int passFlag=-2;
		while(passFlag<0){
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
			board[bestX][bestY]=currentPlayer;
			move(bestX,bestY,currentPlayer);
			for(int i=0;i<8;i++){
				System.out.println();
				for(int j=0; j<8;j++){
					System.out.print(board[i][j]);
							}
						}
			System.out.println();
		}else{
			String log="pass";
			System.out.println("pass");
			for(int i=0;i<8;i++){
				System.out.println();
				for(int j=0; j<8;j++){
					System.out.print(board[i][j]);
							}
						}
			System.out.println();
			passFlag++;
		}
		//change currentPlayer
		if(currentPlayer=='X'){
			currentPlayer='O';
		}else{
			currentPlayer='X';
		}
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
		}
	}
	
	    void move(int currentX, int currentY, char current){
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

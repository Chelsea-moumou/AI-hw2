
public class Node implements Comparable{
	  int id;
	  int parent_id;
	  String name;
	  int depth;
	  int minimax;//1--max,-1--min
	  int value;
	  
	  public Node(int id,int parent_id, String name, int minimax,int depth,int nodeCost){
		  this.id=id;
		  this.parent_id=parent_id;
		  this.name=name;
		  this.depth=depth;
		  this.minimax=minimax;
		  this.value=value;
		  
	  }
	  @Override
		public int compareTo(Object o) {
			
				if(((Node)o).depth>this.depth)
				{
					return 1;
				}else if( ((Node)o).depth<this.depth)
				{
					return -1;
				}else {
					int d=this.name.compareTo(((Node) o).name);
					return d;
					
				}
			
		}
}

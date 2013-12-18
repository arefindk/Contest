import java.util.Scanner;


public class Elevator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int cs, last, next, cost, dif;
		while(s.hasNextInt()){
			cs = s.nextInt();
			
			if(cs == 0){
				break;
			}
			
			else{
				cost=0;
				last=0;
				for(int i = 0; i<cs ; i++){
					next = s.nextInt();
					dif=next-last;
					if(dif<0){
						cost +=( dif * -1 * 4);
					}					
					else{
						cost +=( dif * 6);
					}
					last=next;
				}
				cost += (cs)*5; 
				
				System.out.println(cost);
			}
		}
		
		s.close();
	}

}

package fourQueen;


public class fourQueen {

	public static void main(String[] args) {

		GP gp = new GP();

		gp.main();
	}

}

//クイーンの位置とfitnessの値を持つクラス
class queen{
	int[] position = new int[4];
	int fitness = 0;

	queen(int[] rand){
		for(int i = 0; i<position.length;i++){
			position[i] = rand[i];
		}
	}
}

//遺伝的プログラミングを行うコントローラー
class GP{

	queen[] fourqueens = new queen[4];
	int[] winner = new int[2];

	void main(){
		init();


		evaluation();

		str();
		int count = 0;

		System.out.println("--------START--------");

		while(!(termination())){
			select();
			crossover();
			mutation();
			evaluation();
			str();
			System.out.println("--------"+ count+"--------");
			count++;
		}

		System.out.println(count);
		answer();
	}

	void init(){
		int[] rand = new int[4];

		for(int i = 0; i<fourqueens.length;i++){
			for(int j = 0;j<rand.length;j++){
				rand[j] = (int)(Math.random()*4) + 1;
			}
			fourqueens[i] = new queen(rand);
		}
	}

	void evaluation(){
		for(int i = 0;i<fourqueens.length;i++){
			fourqueens[i].fitness = 0;
		}

		for(int i = 0;i<fourqueens.length;i++){
			for(int j = 0;j < 4;j++){
				for(int k = j + 1;k < 4; k++){
					if(j == 3){
						break;
					}
					if(fourqueens[i].position[j] == fourqueens[i].position[k]){
						fourqueens[i].fitness++;
					}
						else if(fourqueens[i].position[j] + (k - j) == fourqueens[i].position[k]){
						fourqueens[i].fitness++;
					}else if(fourqueens[i].position[j] - (k - j) == fourqueens[i].position[k]){
						fourqueens[i].fitness++;
					}
				}
			}
		}
	}

	//バイナリートーナメント
	void select(){

		if(fourqueens[0].fitness > fourqueens[1].fitness){
			winner[0] = 1;
		}else{
			winner[0] = 0;
		}

		if(fourqueens[2].fitness > fourqueens[3].fitness){
			winner[1] = 3;
		}else{
			winner[1] = 2;
		}

	}

	//交配
	void crossover(){
		int[] position1 = new int[4];
		int[] position2 = new int[4];

		for(int i = 0;i<position1.length;i++){
			position1[i] = fourqueens[winner[0]].position[i];
			position2[i] = fourqueens[winner[1]].position[i];
		}

		for(int i = 0;i<position1.length;i++){
			fourqueens[0].position[i] = position1[i];
			fourqueens[1].position[i] = position2[i];
		}


		int rand = (int)(Math.random()*4);

		for(int i = 0;i<position1.length;i++){
			if(rand == i){
				fourqueens[2].position[i] = fourqueens[1].position[i];
				fourqueens[3].position[i] = fourqueens[0].position[i];
			}else{
				fourqueens[2].position[i] = fourqueens[0].position[i];
				fourqueens[3].position[i] = fourqueens[1].position[i];
			}
		}
 	}

	//突然変異
	void mutation(){
		//突然変異する組、ポジション、値を乱数で生成
		int queens_rand = (int)(Math.random()*4);
		int queens_posi_rand = (int)(Math.random()*4);
		int values_rand = (int)(Math.random()*4) + 1;

		fourqueens[queens_rand].position[queens_posi_rand] = values_rand;
	}

	//終了条件を判定
	boolean termination(){

		boolean flag = false;

		for(int i = 0; i<fourqueens.length;i++){
			if(fourqueens[i].fitness == 0){
				flag = true;
				break;
			}else{
				flag = false;
			}
		}
		return flag;

	}

	void str(){
		for(int i = 0; i<fourqueens.length;i++){
			System.out.print("[");
			for(int j = 0; j < 4;j++){
				System.out.print(fourqueens[i].position[j] );
				if(j != 3){
					System.out.print(",");
				}
			}
			System.out.print("]");
			System.out.println(fourqueens[i].fitness);
		}
	}

	//答え表示
	void answer(){

		System.out.print("Answer:[");
		for(int i = 0; i<fourqueens.length;i++){
			if(fourqueens[i].fitness == 0){
				for(int j = 0; j < 4;j++){
					System.out.print(fourqueens[i].position[j]);

					if(j != 3){
						System.out.print(",");
					}
				}
			}
		}
		System.out.println("]");
	}

}

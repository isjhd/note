
public class BaHuangHou {
	

	public static void main(String[] args) {

		int a [] = new int[8];
		int i = 7;
		T t = new T();
		t.huanghou(a, i);

		for (int j = 0; j < a.length; j++) {
			System.out.print(a[j] + " ");
		}

	}
}

class T {


	public boolean [] huanghou(int[] map, int i){

		
		if ( i == 0 ) {
			map[i] = 0;
			return true;
		}else{

			return huanghou(map, i -1);

			if ( huanghou(map[i],i) == huanghou(map[i-1],i) || huanghou(map[i],i) == huanghou(map[i-1]+1,i) ) {
				map[i] += 1;
				return true;
			}else{
				return true;
			}

		}
	}
}
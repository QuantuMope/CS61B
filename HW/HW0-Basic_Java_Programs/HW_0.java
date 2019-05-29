public class HW_0{

  /* Finds the max int value of a list. */
  public static int max(int[] m){
    int max = 0;
    for (int i = 0; i < m.length; i++){
      if (m[i] > max){
        max = m[i];
      }
    }
    return max;
  }

  public static void windowPosSum(int[] a, int n){
    int sum = 0;
    for (int i = 0; i < a.length; i++){
      for (int c = 0; c <= n; c++){

        if (a[i] < 0){
          sum = a[i];
          continue;
        }
        else if ((i+c) > a.length-1){
          break;
        }
        else {
          sum += a[c+i];
        }
      }
      a[i] = sum;
      sum = 0;
    }
  }

  public static void main(String[] args){
    int[] numbers = {1, -1, -1, 10, 5, -1};
    windowPosSum(numbers, 2);
    System.out.println(java.util.Arrays.toString(numbers));

  }
}

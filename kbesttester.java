 public class kbesttester{
      
 public static void main(String[] args) {
                KBestCounter<Integer> counter = new KBestCounter<>(3);

                int[] data = {4, 2, 9, 1, 7, 1, 5, 3, 10};
                for (int value : data) {
                    counter.count(value);
                }

               System.out.println(counter.kbest());
            }
 }

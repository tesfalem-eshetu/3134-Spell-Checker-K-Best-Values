import java.util.*;

public class KBestCounter<T extends Comparable<? super T>> implements KBest<T>{
    //instantiating private instance variavles 
        private int k;
        private PriorityQueue<T> priorityQueue;
// the constructor takes the k value and intializes the 
//the priority queue
        public KBestCounter(int k) {
            this.k = k;
            this.priorityQueue = new PriorityQueue<>(k);
        }

//this method proccess each individual inputs 
//time complexity is    O(log k)
        public void count(T x) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(x);

            } else if (priorityQueue.peek().compareTo(x) < 0){

                priorityQueue.poll();
                priorityQueue.add(x);
            }
        }
//this method returns list of sorted arraylist 
//time complexity is O(k log k)
     public List<T> kbest() {
            List<T> result = new ArrayList<>(priorityQueue);
            Collections.sort(result);
            return result;
        }
    }


/*
put(k, v) lägger till nyckelvärdeparet <k, v>. Om nyckel k redan är associerad med ett värde, säg vg, så ska k hädanefter istället vara associerad med v. I detta fall får man tänka på att om v < vg eller v > vg så kan utbytet av värde innebära att kö-placeringen ändras.
get(k) returnerar det värde som är associerat med nyckeln k, eller null och k inte är associerad med ett värde.
peek() returnerar det (eller ett av de) nyckel-värdepar som har högst prioritet (minst värde). Paret tas inte bort.
poll() returnerar och tar bort det nyckel-värdepar som har högst prioritet.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K,V> { //K = Key, V = Value

    private HashMap<K,V> map;
    private ArrayList<Pair<K, V>> set;

    public APrioMap(){
        map = new HashMap<>();
        set = new ArrayList<>();
    }

    @Override
    public void put(K k, V v) {

    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public Pair<K, V> poll() {
        if(set.size() > 0) {
            Pair high = peek();
            remove(high);
            return high;
        }
        return null;
    }

    @Override
    public Pair<K, V> peek() {
        if(set.size() == 0)
            return null;

        return set.get(0);
    }

    public void remove(Pair e) {
        int j = set.indexOf(e);
        if(j >= 0 && set.size() > 0) {
            swap(j, set.size() - 1);
            set.remove(set.size() - 1);
            if (set.size() != j) {
                bubbleDown(j);
                bubbleUp(j);
            }
        }
    }

    private void bubbleUp(int index){
        //int index = set.size()-1;
        int parentIndex = (index-1)/2;
        while (hasParent(index) && comp.compare(set.get(index), set.get(parentIndex)) < 0){
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex = (index-1)/2;
        }
    }
    private void bubbleDown(int index){
        //int index = 0;
        Pair element = set.get(index);
        int leftChild = index*2 +1;
        int rightChild = index*2 +2;

        if(hasChildL(index) && hasChildR(index)) { //finns 2 barn
            Pair lChild = set.get(leftChild);
            Pair rChild = set.get(rightChild);
            if (comp.compare(lChild, rChild ) < 0) { //minsta barnet till vänster
                if (comp.compare(element, lChild) > 0) { //noden är större än barnet till vänster
                    swap(index, leftChild);
                    bubbleDown(leftChild);
                }
            }else{
                if(comp.compare(element, rChild) > 0) { //noden är större än barnet till höger
                    swap(index, rightChild);
                    bubbleDown(rightChild);
                }
            }
        }else if(hasChildL(index)){ //finns ett barn och det är på vänster
            Pair lChild = set.get(leftChild);
            if(comp.compare(element, lChild) > 0 ) {
                swap(index, leftChild);
                bubbleDown(leftChild);
            }
        }
    }

    private boolean hasParent(int i){
        return i >= 1;
    }
    private boolean hasChildL(int i){
        i = i*2+1;
        return i < set.size();
    }

    private boolean hasChildR(int i){
        i = i*2+2;
        return i < set.size();
    }

    private void swap(int i1, int i2){
        Pair temp = set.get(i2);
        set.set(i2, set.get(i1));
        set.set(i1, temp);
    }

    @Override
    public String toString() {
        return "BinHeap{" +
                "set=" + set +
                '}';
    }

}

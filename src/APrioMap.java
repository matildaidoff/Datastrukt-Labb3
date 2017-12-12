/*
put(k, v) lägger till nyckelvärdeparet <k, v>. Om nyckel k redan är associerad med ett värde, säg vg, så ska k hädanefter istället vara associerad med v. I detta fall får man tänka på att om v < vg eller v > vg så kan utbytet av värde innebära att kö-placeringen ändras.
get(k) returnerar det värde som är associerat med nyckeln k, eller null och k inte är associerad med ett värde.
peek() returnerar det (eller ett av de) nyckel-värdepar som har högst prioritet (minst värde). Paret tas inte bort.
poll() returnerar och tar bort det nyckel-värdepar som har högst prioritet.
 */


import java.util.ArrayList;
import java.util.HashMap;

public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K,V> { //K = Key, V = Value

    private HashMap<K,Integer> map;
    private ArrayList<Pair<K, V>> set;

    public APrioMap(){
        map = new HashMap<>();
        set = new ArrayList<>();
    }

    @Override
    public void put(K k, V v) {
        if (!map.containsKey(k)){
            set.add(new Pair<K,V>(k,v));
            map.put(k,set.size()-1);
            bubbleUp(set.size()-1);

        } else {
            set.get(map.get(k)).b = v;
            bubbleDown(map.get(k));
            bubbleUp(map.get(k));
        }

    }

    @Override
    public V get(K k) {
        if (!map.containsKey(k)){
            return null;
        }
        return set.get(map.get(k)).b;
    }

    @Override
    public Pair<K,V> poll() {
        if(set.size() > 0) {
            Pair<K,V> high = peek();
            remove(high);
            return high;
        }
        return null;
    }

    @Override
    public Pair<K,V> peek() {
        if(set.size() == 0)
            return null;

        return set.get(0);
    }

    private void remove(Pair<K,V> e) {
        map.remove(e.a);
        int j = map.get(e.a);
        if(j >= 0 && set.size() > 0) {
            swap(j, set.size() - 1);
            set.remove(set.size() - 1);
            if (!set.isEmpty()) {
                map.replace(set.get(0).a, 0);
                bubbleDown(j);
            }
        }
    }

    private void bubbleUp(int index){
        //int index = set.size()-1;
        int parentIndex = (index-1)/2;
        while (hasParent(index) && set.get(index).b.compareTo(set.get(parentIndex).b ) < 0){
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex = (index-1)/2;
        }
    }
    private void bubbleDown(int index){
        //int index = 0;
        V element = set.get(index).b;
        int leftChild = index*2 +1;
        int rightChild = index*2 +2;

        if(hasChildL(index) && hasChildR(index)) { //finns 2 barn
            V lChild = set.get(leftChild).b;
            V rChild = set.get(rightChild).b;
            if (lChild.compareTo(rChild) < 0) { //minsta barnet till vänster
                if (element.compareTo(lChild) > 0) { //noden är större än barnet till vänster
                    swap(index, leftChild);
                    bubbleDown(leftChild);
                }
            }else{
                if(element.compareTo(rChild) > 0) { //noden är större än barnet till höger
                    swap(index, rightChild);
                    bubbleDown(rightChild);
                }
            }
        }else if(hasChildL(index)){ //finns ett barn och det är på vänster
            V lChild = set.get(leftChild).b;
            if(element.compareTo(lChild) > 0 ) {
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
        Pair<K,V> temp = set.get(i2);
        set.set(i2, set.get(i1));
        set.set(i1, temp);

        map.replace(set.get(i1).a, i1);
        map.replace(set.get(i2).a, i2);
    }

    @Override
    public String toString() {
        return "BinHeap{" +
                "set=" + set +
                '}';
    }

}

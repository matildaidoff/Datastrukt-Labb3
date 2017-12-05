/*
put(k, v) lägger till nyckelvärdeparet <k, v>. Om nyckel k redan är associerad med ett värde, säg vg, så ska k hädanefter istället vara associerad med v. I detta fall får man tänka på att om v < vg eller v > vg så kan utbytet av värde innebära att kö-placeringen ändras.
get(k) returnerar det värde som är associerat med nyckeln k, eller null och k inte är associerad med ett värde.
peek() returnerar det (eller ett av de) nyckel-värdepar som har högst prioritet (minst värde). Paret tas inte bort.
poll() returnerar och tar bort det nyckel-värdepar som har högst prioritet.
 */


import java.util.HashMap;

public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K,V> {

    private HashMap<K,Integer> map;

    public APrioMap(){
        map = new HashMap<>();
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
        return null;
    }

    @Override
    public Pair<K, V> peek() {
        return null;
    }
}

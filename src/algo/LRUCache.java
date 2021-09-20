package algo;

import java.util.LinkedHashMap;

public class LRUCache {

    LinkedHashMap<Integer, Integer> map;
    int cap;
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int value = map.get(key);
        map.remove(key);
        map.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
            return;
        }
        if (map.size() == cap) {
            int oldest = map.keySet().iterator().next();
            map.remove(oldest);
        }
        map.put(key, value);
    }
}

package algo;

import java.util.*;

public class LFUCache {

    Map<Integer, Integer> map;
    Map<Integer, Integer> numToFreq;
    Map<Integer, LinkedHashSet<Integer>> freqMap;
    int cap;
    int min;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.min = 0;
        map = new HashMap<>();
        numToFreq = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int value = map.get(key);
        markFreq(key);
        return value;
    }

    public void put(int key, int value) {
        if (this.cap <= 0) {
            return;
        }
        if (map.containsKey(key)) {
            map.put(key, value);
            markFreq(key);
            return;
        }
        if (map.size() == cap) {
            Set<Integer> smallSet = freqMap.get(this.min);
            int oldest = smallSet.iterator().next();
            smallSet.remove(oldest);
            if (smallSet.isEmpty()) {
                freqMap.remove(this.min);
            }
            numToFreq.remove(oldest);
            map.remove(oldest);
        }
        map.put(key, value);
        numToFreq.put(key, 1);
        LinkedHashSet<Integer> freqSet = freqMap.getOrDefault(1, new LinkedHashSet<>());
        freqSet.add(key);
        freqMap.put(1, freqSet);
        this.min = 1;
    }

    private void markFreq(int key) {
        int freq = numToFreq.get(key);
        Set<Integer> set = freqMap.get(freq);
        set.remove(key);
        if (set.isEmpty()) {
            freqMap.remove(freq);
            if (this.min == freq) {
                this.min++;
            }
        }
        freqMap.putIfAbsent(freq+1, new LinkedHashSet<>());
        freqMap.get(freq+1).add(key);
        numToFreq.put(key, freq+1);
    }
}

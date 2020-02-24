package ab2.impl.MaroltLiebhartKrainer;

import ab2.AuDHashSet;



public class HashSetImpl implements AuDHashSet {
    private int capacity;
    private int prime;
    private long[] set;
    private int size;


    public HashSetImpl(int capacity) {
        capacity *= 2.5;
        this.capacity = capacity;
        set = new long[capacity];
        for (int i = capacity; i > 0; i--) { // changed the capacity *1,5
            if(HashSetImpl.isPrime(i)) {
                prime = i;
                i = -1;
            }
        }
        if (prime < 1) {
            prime = 1;
        }
    }
    public static boolean isPrime(final long value) {
        if (value <= 16) {
            return (value == 2 || value == 3 || value == 5 || value == 7 || value == 11 || value == 13);
        }
        if (value % 2 == 0 || value % 3 == 0 || value % 5 == 0 || value % 7 == 0) {
            return false;
        }
        for (long i = 10; i * i <= value; i += 10) {
            if (value % (i+1) == 0) {
                return false;
            }
            if (value % (i+3) == 0) {
                return false;
            }
            if (value % (i+7) == 0) {
                return false;
            }
            if (value % (i+9) == 0) {
                return false;
            }
        }
        return true;
    }



    @Override
    public void add(long value) {
        if (size>=capacity) {
            System.out.println("Array is full");
            return;
        }
        int firstHash = firstHash(value);
        int secondHash = secondHash(value);
        while(set[firstHash] != 0 && set[firstHash] != value) {
            firstHash += secondHash;
            firstHash = firstHash % capacity;
        }
        set[firstHash] = value;
        size++;
    }


    @Override
    public boolean contains(long value) {
        int firstHash = firstHash(value);
        int secondHash = secondHash(value);
        int i = 0;
        while(set[firstHash] != 0 && i < capacity) {
            if (set[firstHash] == value) {
                return true;
            }
            firstHash += secondHash;
            firstHash = firstHash % capacity;
            i++;
        }
        return false;
    }

    private int firstHash(long value) {
        int firstHash = (int)(value%capacity);
        while (firstHash < 0) {
            firstHash *= -1;
        }
        return firstHash;
    }

    private int secondHash(long value) {
        int secondHash = (int) (value % (prime));
        if (secondHash < 0) {
            secondHash *= -1;
        }
        return prime - (secondHash%prime);
    }

    public long[] getSet() {
        return set;
    }
}

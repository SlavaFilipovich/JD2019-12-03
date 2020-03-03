package by.it.cherkas.jd02_03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Backet {
    ConcurrentHashMap<Good, Integer> backets=new ConcurrentHashMap<>();
    public Backet(){

    }
    public Backet(ConcurrentHashMap<Good, Integer> backets){
        this.backets = backets;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Backet backet = (Backet) o;
        return backets != null ? backets.equals(backet.backets) : backet.backets == null;
    }

    @Override
    public int hashCode() {
        return backets != null ? backets.hashCode() : 0;
    }

    @Override
    public String toString(){
        return "Backet{" + "backets=" + backets + "}";
    }
}



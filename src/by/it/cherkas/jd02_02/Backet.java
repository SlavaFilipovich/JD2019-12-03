package by.it.cherkas.jd02_02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Backet {
    Map<Good, Integer> backets=new HashMap<>();
    public Backet(){

    }
    public Backet(Map<Good, Integer> backets){
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



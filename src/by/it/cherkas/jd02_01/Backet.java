package by.it.cherkas.jd02_01;

import java.util.HashSet;
import java.util.Set;

public class Backet {
    Set<Good> backets=new HashSet<>();
    public Backet(){

    }
    public Backet(Set<Good> backets){
        this.backets = backets;
    }

    public Set<Good> getBackets(){
        return backets;
    }

    public void setBackets(Set<Good> backets){
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



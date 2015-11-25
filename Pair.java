/**
 * Created by pranavan on 26/06/15.
 */
public class Pair implements Comparable<Pair>{
    private double longti;
    private double lati;

    public Pair(double longti, long lati){
        this.longti = longti;
        this.lati = lati;
    }

    public double getLati() {
        return lati;
    }

    public double getLongti(){
        return longti;
    }
    @Override
    public int compareTo(Pair pair) {
        if(lati > pair.getLati()){
            return 1;
        } else if(lati == pair.getLati()){
            if(longti > pair.getLongti())
                return 1;
            else if(longti == pair.getLongti())
                return 0;
            else
                return -1;
        } else{
            return -1;
        }
    }
}

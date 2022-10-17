package sorting;

import java.sql.Array;
import java.util.Arrays;

/**
 * Author Pierre Schaus
 *
 * Assume the following 5x5 matrix that represent a grid surface:
 * int [][] tab = new int[][] {{1,3,3,1,3},
 *                             {4,2,2,4,5},
 *                             {4,4,1,4,2},
 *                             {1,4,2,3,6},
 *                             {1,1,1,6,3}};
 *
 * Each entry in the matrix represents an altitude point at the corresponding grid coordinate.
 * The goal is to implement a GlobalWarmingImpl class that extends the GlobalWarming abstract class described below.
 *
 * Given a global water level, all positions in the matrix with a value <= the water level are flooded and therefore unsafe.
 * So, assuming the water level is 3, all safe points are highlighted between parenthesis:
 *
 *   1 , 3 , 3 , 1 , 3
 *  (4), 2 , 2 ,(4),(5)
 *  (4),(4), 1 ,(4), 2
 *   1 ,(4), 2 , 3 ,(6)
 *   1 , 1 , 1 ,(6), 3}
 *
 * The method you need to implement is nbSafePoints
 * - calculating the number of safe points for a given water level
 *
 * Complete the code below. Pay attention to the expected time complexity of each method.
 * To meet this time complexity, you need to do some pre-computation in the constructor.
 * Feel free to create internal classes in GlobalWarmingImpl to make your implementation easier.
 * Feel free to use any method or data structure available in the Java language and API.
 */

abstract class GlobalWarming {


    protected final int[][] altitude;

    /**
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     */
    public GlobalWarming(int[][] altitude) {
        this.altitude = altitude;
    }

    /**
     *
     * @param waterLevel
     * @return the number of entries in altitude matrix that would be above
     *         the specified waterLevel.
     *         Warning: this is not the waterLevel given in the constructor/
     */
    public abstract int nbSafePoints(int waterLevel);

}


public class GlobalWarmingImpl extends GlobalWarming {

    //IMPORTANT : Quand on veut process une matrice, on essaye de la rendre
    // dans un tableau, en stockant la matrice ligne collée par ligne.

    int[] flatMatrix = new int[this.altitude.length*this.altitude.length];
    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        // TODO
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        int srcIndex = 0;

        for (int i = 0; i < this.altitude.length; i++) {

            System.arraycopy(altitude[i],0,flatMatrix,srcIndex,altitude.length);
            srcIndex+=altitude.length;
        }
        Arrays.sort(flatMatrix);
        //ça nous permet de trier les éléments et d'avoir un tableau avec les altitudes pré-triées.
        //On ne perd pas d'information car on garde la matrice dans super(altitude)
    }

    /**
     * Returns the number of safe points given a water level
     *
     * @param waterLevel the level of water
     */
    public int nbSafePoints(int waterLevel) {



        int counter = 0;
        //Si on fait une boucle, on a une complexité de O(n^2). Sauf que le
        //tableau est trié donc y'a moyen de faire mieux

        counter = search(flatMatrix,0, flatMatrix.length-1, waterLevel);
        if (counter > -1){
            return flatMatrix.length-counter;
        }

        return 0;

    }

    /**
     * On cherche le premier élément incorrect et puis on prend tout ce qu'il reste
     * au-dessus avec la taille du tableau
     */
    public int search(int[] a,int lo, int hi, int level){

        int mid = lo + (hi-lo)/2;

        if (lo <= hi){

            if(a[mid] == level && (mid+1) < a.length && a[mid+1] > a[mid]){
                return mid+1;
            }
            if(a[mid] > level){
                return search(a,lo,mid-1,level);
            }else {
                return search(a,mid+1,hi,level);
            }

        }

        if (mid < flatMatrix.length && a[mid] > level){
            return mid;
        }
        return -1;



    }

}

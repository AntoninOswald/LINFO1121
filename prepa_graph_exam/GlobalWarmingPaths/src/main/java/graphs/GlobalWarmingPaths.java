package graphs;



import java.util.*;

/**
 * Author Pierre Schaus
 *
 * Assume the following 5x5 matrix that represent a grid surface:
 * int [][] tab = new int[][] {{1,3,3,1,3},
 *                             {4,2,2,4,5},
 *                             {4,4,5,4,2},
 *                             {1,4,2,3,6},
 *                             {1,1,1,6,3}};
 *
 * Given a global water level, all positions in the matrix
 * with a value <= the water level are flooded and therefore unsafe.
 * So, assuming the water level is 3,
 * all safe points are highlighted between parenthesis:
 *
 *   1 , 3 , 3 , 1 , 3
 *  (4), 2 , 2 ,(4),(5)
 *  (4),(4),(5),(4), 2
 *   1 ,(4), 2 , 3 ,(6)
 *   1 , 1 , 1 ,(6), 3}
 *
 * The method you need to implement is
 * a method that find a safe-path between
 * two positions (row,column) on the matrix.
 * The path assume you only make horizontal or vertical moves
 * but not diagonal moves.
 *
 * For a water level of 4, the shortest path
 * between (1,0) and (1,3) is
 * (1,0) -> (2,0) -> (2,1) -> (2,2) -> (2,3) -> (1,3)
 *
 *
 * Complete the code below so that the {@code  shortestPath} method
 * works as expected
 */
public class GlobalWarmingPaths {

    int waterLevel;
    int [][] altitude;

    boolean[][] marked;

    Point[][] prev;

    int rows,cols;

    public GlobalWarmingPaths(int[][] altitude, int waterLevel) {
        this.waterLevel = waterLevel;
        this.altitude = altitude;
        rows = altitude.length;
        cols = altitude[0].length;


    }


    /**
     * Computes the shortest path between point p1 and p2
     * @param p1 the starting point
     * @param p2 the ending point
     * @return the list of the points starting
     *         from p1 and ending in p2 that corresponds
     *         the shortest path.
     *         If no such path, an empty list.
     */
    public List<Point> shortestPath(Point p1, Point p2) {


        if (p1.equals(p2)) {
            LinkedList<Point> path = new LinkedList<>();
            if (altitude[p1.getX()][p1.getY()] > waterLevel) {
                path.add(p1);
                return path;
            }
            return null;
        }

        marked = new boolean[rows][cols];
        prev = new Point[rows][cols];

        final int[][] pos = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        LinkedList<Point> queue = new LinkedList<>();
        queue.add(p1);



        while (!queue.isEmpty()) {

            Point current = queue.remove();
            if (current.getX() == p2.getX() && current.getY() == p2.getY()){
                break;
            }

            for (int i = 0; i < 4; i++) {
                int dx = pos[i][0];
                int dy = pos[i][1];

                int newX = current.getX() + dx;
                int newY = current.getY() + dy;


                if ((newX >= 0 && newX < marked.length) &&
                        (newY >= 0 && newY < marked[0].length)
                        && altitude[newX][newY] > waterLevel) {

                    if (!marked[newX][newY]) {

                        marked[newX][newY] = true;
                        prev[newX][newY] = current;

                        queue.add(new Point(newX, newY));

                    }
                }
            }
        }

        if (!marked[p2.getX()][p2.getY()]){
            return new LinkedList<Point>();
        }


        LinkedList<Point> list = new LinkedList<>();

        for (Point i = p2; !i.equals(p1); i = prev[i.getX()][i.getY()]){
            list.add(i);
        }
        list.add(p1);
        Collections.reverse(list);
        return list;

    }

    /**
     * This class represent a point in a 2-dimension discrete plane.
     * This is used to identify the cells of a grid
     * with X = row, Y = column
     */
    static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return p.x == this.x && p.y == this.y;
            }
            return false;
        }
    }
}

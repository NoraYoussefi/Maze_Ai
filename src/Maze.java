
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import static java.lang.System.in;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import labr_code.interface_principale;

/**
 * Class Maze - private class for representing search space as a two-dimensional
 * maze
 */
public class Maze {

    public static short OBSTICLE = -1;
    public static short START_LOC_VALUE = -2;
    public static short GOAL_LOC_VALUE = -3;
    public static short Bomb = -4;
    int width = 0;
    int height = 0;
    public Dimension startLoc = new Dimension();
    public Dimension goalLoc = new Dimension();
    /**
     * The maze (or search space) data is stored as a short integer rather than
     * as a boolean so that bread-first style searches can use the array to
     * store search depth. A value of -1 indicates a barrier in the maze.
     */
    private final short[][] maze;

    public Maze(int width, int height) {
        System.out.println("New maze of size " + width + " by " + height);
        this.width = width;
        this.height = height;
        maze = new short[width + 2][height + 2];

        // ---------------
        short[][] matrice = new short[21][21];

        int sizeW = 0;
        int j = 0;
        int i = 0;
        int k = 0;
        int l = 0;

        for (i = 0; i < width + 2; i++) {
            for (j = 0; j < height + 2; j++) {
                maze[i][j] = 0;
            }
        }
        for (i = 0; i < height + 2; i++) {
            maze[0][i] = maze[width + 1][i] = OBSTICLE;
        }
        for (i = 0; i < width + 2; i++) {
            maze[i][0] = maze[i][height + 1] = OBSTICLE;
        }
        /**
         * Randomize the maze by putting up arbitray obsticals
         */
//        java.util.Random rnd = new java.util.Random();
//        rnd.setSeed((long)28);
//        int max_obsticles = (width * height)*30/100;
//        for (i=0; i<max_obsticles; i++) {
//            int x = rnd.nextInt(width-1)+1;
//            int y = rnd.nextInt(height-1)+1;
//            maze[x+1][y+1] = OBSTICLE;
//        }

        //---------- ce qu'on a ajouter ----------
        File fileDirs = new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\AI_project\\src\\mazeFile\\LABY_21x21.txt");

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileDirs), "utf-8"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        }

        String str;

        try {
            i = 1;
            while ((str = in.readLine()) != null) {

                for (j = 1; j < str.length(); j++) {
                    if (str.charAt(j) == ' ') {
                        maze[j][i] = 0;
                    } else {
                        maze[j][i] = -1;
                    }

                }
                i++;
            }

            // ---------- lecture de maze
            for (i = 0; i < maze.length; i++) {

                for (j = 0; j < maze[i].length; j++) {
                    System.out.print(maze[i][j]);
                }
                System.out.println();
            }

        } catch (IOException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        }
        addObsticles();

        /**
         * Specify the starting location
         */
        startLoc.width = 0;
        startLoc.height = 0;
        maze[1][1] = START_LOC_VALUE;
        /**
         * Specify the goal location
         */
        goalLoc.width = width - 1;
        goalLoc.height = height - 1;
        maze[width][height] = GOAL_LOC_VALUE;

    }

    synchronized public short getValue(int x, int y) {
        return maze[x + 1][y + 1];
    }

    synchronized public void setValue(int x, int y, short value) {
        maze[x + 1][y + 1] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //--------------ajouter des obstacles------------------
    public void addObsticles() {
        File fileDirs = new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\AI_project\\src\\mazeFile\\LABY_21x21.txt");

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileDirs), "utf-8"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        }

        String str;

        try {
            int i = 1;
            while ((str = in.readLine()) != null) {
                int j;

                for (j = 1; j < str.length(); j++) {
                    if (str.charAt(j) != ' ') {
                        /**
                         * Randomize the maze by putting up arbitray obsticals
                         */
                        java.util.Random rnd = new java.util.Random();
                        rnd.setSeed((long) 28);
                        int max_obsticles = (width * height) * 5 / 100;
                        for (i = 0; i < max_obsticles; i++) {
                            int x = rnd.nextInt(width - 1) + 1;
                            int y = rnd.nextInt(height - 1) + 1;
                            maze[x + 1][y + 1] = Bomb;
//                             maze[x + 1][y + 1] = START_LOC_VALUE;
                        }

//                        maze[j][i] = -1;
                    }
                }
                i++;
            }

        } catch (IOException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

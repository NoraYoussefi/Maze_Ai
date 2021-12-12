
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import labr_code.interface_principale;
import javax.imageio.ImageIO;
/**
 * Title:        MazeDepthFirstSearch<p>
 * Description:  Demo program for Java AI Programming<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

public class MazeDepthFirstSearch extends javax.swing.JFrame implements  KeyListener{
    JPanel jPanel1 = new JPanel();
    DepthFirstSearchEngine currentSearchEngine = null;
    Maze maze=null;
    Graphics g=null;
    BufferedImage image=null;
    Graphics g2=null;
    
    boolean GameWon=false;
    int AgentvsCount=0;
    int Score=50;
    int refreshCount=0;

    public MazeDepthFirstSearch(int height, int width) {
        try {
         jbInit();
        } catch (Exception e) {
          System.out.println("GUI initilization error: " + e);
        }
       System.out.println("Constructor called: ");

        currentSearchEngine = new DepthFirstSearchEngine(height, width);
        repaint();
    }

    
    
    // Fonction prise de MazeBreadthFirstSearch
    public void paint(Graphics g_unused) {
        
        if (currentSearchEngine == null) return;
        
        maze = currentSearchEngine.getMaze();
        
        //---------------------------------------------
        if(refreshCount==0)maze.setValue(0, 0, Maze.START_LOC_VALUE); System.out.println("repaint called");
        refreshCount++;
        //---------------------------------------------
        
        int width = maze.getWidth();
        int height = maze.getHeight();
        System.out.println("Size of current maze: " + width + " by " + height);
        
        g = jPanel1.getGraphics();
        image = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
        
        g2 = image.getGraphics();
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0, 700, 700);
        g2.setColor(Color.black);
        
//        maze.setValue(0, 0, Maze.START_LOC_VALUE);  //set the agent's starting point
        
//        maze.getAgentLocation();
        
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                
                short val = maze.getValue(x,y);
                
                
                
                
                if ( val == Maze.OBSTICLE) {
                    g2.setColor(Color.BLACK);
                    //colorier carre
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29); //x,y,width,height
                    
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29); //creer carre
                } 
                else if (val == Maze.START_LOC_VALUE) {
                    //if game is won
                    if(x==width-1 && y==height-1){
                        GameWon=true;
                        System.out.println("_______________You Reached The Goal - GAME WON_______________");
                        break;
                    }
                    
                    
                    g2.setColor(Color.blue);
                    
                    //--------------------------------------
                    System.out.println("Repaint : set to ("+x+","+y+")");

                    g2.drawString("S", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } 
                else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    g2.drawString("G", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                 
                } 
                else if (val == Maze.Bomb) {
//                    g2.setColor(Color.MAGENTA)

//-----------------Draw Bomb image ra bdita kmel 3liha nta a omar wla ahmad------------------------------

//final BufferedImage image1;
//                    try {
//                        image1 = ImageIO.read(new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\AI_project\\src\\mazeFile\\bomb.png"));
//                    g2.drawImage(image1, 30, 30, null);
//                    } catch (IOException ex) {
//                        Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
//                    }

//----------------------------------------
//        JPanel pane;
//                    pane = new JPanel() {
//                        @Override
//                        protected void paintComponent(Graphics g) {
//                            super.paintComponent(g);
//                            g.drawImage(image, 0, 0, null);
//                        }
//                    };
//        g2.add(pane);
//----------------------------------------------------------
//g2.drawImage(image1, 0, 0, null);
                      //g2.drawImage(bomb.png, x, y, rootPane);
                     // g2.setIconImage(new ImageIcon("bomb.png").getImage());
                    g2.drawString(String.valueOf(Character.toChars(001)), 16 + x * 29, 19 + y * 29); //001 est le code ascii d'un visage 
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } 
                else {
                	g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        // redraw the path in black:
        g2.setColor(Color.black);
        Dimension [] path = currentSearchEngine.getPath();
        for (int i=1; i< (path.length-1); i++) {
          int x = path[i].width;
          int y = path[i].height;
          short val = maze.getValue(x,y);
          g2.drawString("" + (path.length - i), 16 + x * 29, 19 + y * 29);
        }
        g.drawImage(image, 30, 40, 700, 700, null);

    }

    public static void main(String[] args) {
        int size = LireFichierText();
        MazeDepthFirstSearch mazeSearch1 = new MazeDepthFirstSearch(size-1,size);
        
    }

    private void jbInit() throws Exception {

        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("MazeDepthFirstSearch");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        this.setSize(1000,700);
        this.setVisible(true);
        this.addKeyListener(this);
    }
    
    
    
    // ------------- Lire fichier 
    
    
    public static int LireFichierText(){
         
        int sizeW=0;
        File fileDirs = new File("C:\\Users\\lenovo\\Desktop\\AI_project\\AI_project\\src\\mazeFile\\LABY_21x21.txt");

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
            while ((str = in.readLine()) != null) {
//          System.out.println("Read Line is : "+str);
            sizeW=str.length()+1;
            }
        } catch (IOException ex) {
            Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("maze width: "+sizeW);
         
        return sizeW;
     }

    
    
    
    
    public boolean checkWin(int col,int line){
        if(maze.getValue(col,line)==Maze.GOAL_LOC_VALUE) GameWon=true;
        return GameWon;
    }
    
    public int countAgentVsBomb(int col,int line){
        if(maze.getValue(col,line)==Maze.Bomb)++AgentvsCount; Score -=2;
        return Score;
    }
    
    
    
    
    //------------------KET LISTENER METHODS (agent mouvement)---------------------------//   
    @Override
    public void keyReleased(KeyEvent e) {
        
//        if(GameWon){  //si il a rencontre une bomb >=4 fois
                            //suprimmer ce if, pour bien tester
              
        switch(e.getKeyCode()){   
            
                case Maze.RIGHT:  
                    if(maze.getValue(maze.col+1,maze.line)!=Maze.OBSTICLE){  //check if next move is an obstacle
                                            
                        //decrementer score si recontre d'agent et bomb 
                        if(maze.getValue(maze.col+1,maze.line)==Maze.Bomb  && Score!=0) Score-=2;
                      
                        //deplacer le 'S' (agent)
                        maze.setValue(++maze.col, maze.line, Maze.START_LOC_VALUE);
                        
                        //suprimmer l'ancien 'S' dans la matrice
                        maze.setValue(maze.col-1, maze.line, (short)0);
                    }
                    else{
                        System.out.println("___OBSTACLE HERE"+maze.getValue(maze.col, maze.line));
                    }
                    break;
                     
                    
                    
                case Maze.LEFT:
                   if(maze.getValue(maze.col-1,maze.line )!=Maze.OBSTICLE){  //check if next move is an obstacle
                        
                        if(maze.getValue(maze.col-1,maze.line)==Maze.Bomb && Score!=0) Score-=2;

                        maze.setValue(--maze.col, maze.line, Maze.START_LOC_VALUE);
                        maze.setValue(maze.col+1, maze.line, (short)0);
                    }
                   else{
                        System.out.println("___OBSTACLE HERE"+maze.getValue(maze.col, maze.line));
                    }
                    break;

                    
                    
                case Maze.UP: 
                    if(maze.getValue(maze.col, maze.line-1)!=Maze.OBSTICLE){  //check if next move is an obstacle
                        
                        if(maze.getValue(maze.col,maze.line-1)==Maze.Bomb && Score!=0) Score-=2;
                        
                        maze.setValue(maze.col, --maze.line, Maze.START_LOC_VALUE);
                        maze.setValue(maze.col, maze.line+1, (short)0);
                    }
                      else{
                        System.out.println("___OBSTACLE HERE"+maze.getValue(maze.col, maze.line));
                    }
                    break;

                    
                    
                case Maze.DOWN:
                    if(maze.getValue(maze.col, maze.line+1)!=Maze.OBSTICLE){  //check if next move is an obstacle
                        
                       if(maze.getValue(maze.col,maze.line+1)==Maze.Bomb && Score!=0) Score-=2;

                        maze.setValue(maze.col, ++maze.line, Maze.START_LOC_VALUE);
                        maze.setValue(maze.col, maze.line-1, (short)0);
                    }
                    else{
                        System.out.println("___OBSTACLE HERE "+maze.getValue(maze.col, maze.line));
                    }
                    break;       
            } 
        
        //_____UPDATE LE FRAME_____//
        repaint();
        }
     
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
    }
    
    
    
    
 
    
    
    

    
    
   
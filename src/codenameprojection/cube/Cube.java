/*
 * The MIT License
 *
 * Copyright 2019 Elias Eskelinen.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package codenameprojection.cube;

import JFUtils.Range;
import JFUtils.point.Point2D;
import JFUtils.point.Point3D;
import codenameprojection.drawables.vertexGroup;
import codenameprojection.driver;
import codenameprojection.models.Model;
import codenameprojection.modelParser;
import codenameprojection.models.ModelFrame;
import java.awt.FlowLayout;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 *
 * @author Jonnelafin
 */
public class Cube {
    public static void main(String[] args) {
        new Cube();
    }

    codenameprojection.driver Driver;
    JFrame frame;
    private Instant beginTime;
    private Duration deltaTime;
    public Cube() {
        Driver = new driver();
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run(); //To change body of generated methods, choose Tools | Templates.
                Driver.run();
                //Driver = new driver(null);
            }
            
        };
        modelParser.filename = "assets/models/cube/cube";
        modelParser.size = 100;
        Driver.startWithNoModel = false;
        
        LinkedList<Model> points = constructCloud(); // //new LinkedList<>();
        LinkedList<Integer> handles = new LinkedList<>();
        for(Model m : points){
            Integer handle = m.hashCode();
            Driver.models.put(handle, m);
            handles.add(handle);
        }
        LinkedList<Model> grid = constructGrid();
        LinkedList<Integer> gridHandles = new LinkedList<>();
        for(Model m : grid){
            Integer handle = m.hashCode();
            Driver.models.put(handle, m);
            gridHandles.add(handle);
        }
        
        t.start();
        while(!Driver.running){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cube.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int cubeHandle = 2;
        
        
        Driver.s.r.debug = false;
        
        Driver.an_pause = false;
        //Driver.zero();
        
        Driver.s.r.usePixelRendering = false;
        Driver.s.r.drawFaces = true;
        Driver.s.r.drawPoints = true;
        Driver.s.r.drawLines = true;
        Driver.s.r.shading = false;
        Driver.rotation_mode = true;
        System.out.println("Init complete!");
        
        frame = new JFrame("\"PB3D\" Fly simulator :)");
        
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        JSlider step = new JSlider(0, 100, 0);
        
        frame.add(step);
        //frame.setVisible(true);
        
        float p = -1;
        
        int a = 2;
        Random r = new Random();
        float x = 0;
        float y = 0;
        int l_f = 0;
        Driver.inp.verbodose = true;
        Integer[] defaultKeys = new Integer[]{
            //74, 
            //76, 
            //73, 
            //75,
            //87,
            //a
            //65,
            //83,
            //d
            //68,
            81, //q
            69  //e
        };
        for(int i : new Range(1000)){
            Driver.ingoredInputs.add(i);
        }
        for(int i : defaultKeys){
            Driver.ingoredInputs.removeAll(Arrays.asList(defaultKeys));
        }
        
        
        Driver.shadingMultiplier = 0.5F;
        LinkedList<Integer> boltHandles = new LinkedList<>();
        float speed = 0.4F;
//        Driver.screenPosition_org = new Point3D(0, 0, 0);
//        Point3D last_sp = Driver.screenPosition_org.clone();
        float side = 1.3F;
        float up = -2F;
        float front = -8;
        float front2 = 0;
        float shipRotY = 0;
        float shipRotZ = 0;
        
        
        
        float thrust = 0F;
        float v = 0F;
        Point3D vel = new Point3D(0, 0, 0);
        //float shipRotX = 0;
        
        boolean pause = false;
        while (true) {
            beginTime = Instant.now();
            
            Model cubeM = (Model) Driver.models.values().toArray()[1];
            cubeM.hideLines = true;
            cubeM.hidePoints = true;
            
            try {
                Driver.angleYM = Driver.angleYM + Driver.inp.cX * (deltaTime.getNano() * .0000000003);
                //System.out.println(Driver.angleYM);
                Driver.inp.cX = (int) (Driver.inp.cX * 0.5);    //0.0002
                Driver.angleXM = Driver.angleXM + Driver.inp.cY * (deltaTime.getNano() * .0000000003);
                Driver.inp.cY = (int) (Driver.inp.cY * 0.5);
            } catch (Exception e) {
            }
            //System.out.println(Driver.inp.mouseX());
            LinkedList<Integer> torem = new LinkedList<>();
            if (!pause) {
                
            }
            //space
            if(Driver.inp.keys[32]){
                
            }
            //w
            if(Driver.inp.keys[87]){
            //    cubeM.setX(cubeM.getX() + 100);
            cubeM.rotation_X++;
                //System.out.println("W!");
            }
            if(Driver.inp.keys[83]){
            }
            //a
            if(Driver.inp.keys[65]){
                
            }
            //d
            if(Driver.inp.keys[68]){
            }
            //q
            if(Driver.inp.keys[81]){
            }
            //e
            if(Driver.inp.keys[69]){
            }
            //b
            if(Driver.inp.keys[66]){
                Driver.s.r.debug = !Driver.s.r.debug;
                Driver.inp.keys[66] = false;
            }
            //p
            if(Driver.inp.keys[80]){
                pause = !pause;
                Driver.inp.keys[80] = false;
            }
            //o
            if(Driver.inp.keys[79]){
                //Driver.s.r.
            }
//            last_sp = Driver.screenPosition_org.clone();
            //Sleep
            try {
                Thread.sleep((long) 0.00001);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cube.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            deltaTime = Duration.between(beginTime, Instant.now());
//            System.out.println("Fly.java excecution time: " + deltaTime.getNano());
        }
    }
    
    int size = 1400*10;
    
    int rx = 5;
    int ry = 5;
    int rz = 5;
    LinkedList<Model> constructCloud(){
        Random rnd = new Random();
        LinkedList<Model> out = new LinkedList<Model>();
        for(int x : new Range(rx)){
            for (int y : new Range(ry)) {
                for (int z : new Range(rz)){
                    LinkedList<ModelFrame> frames = new LinkedList<>();
                    LinkedList<Point3D> points = new LinkedList<>();
                    LinkedList<Integer[]> lines = new LinkedList<>();
                    LinkedList<Point3D[]> faces = new LinkedList<>();
                    LinkedList<vertexGroup> color = new LinkedList<>();
                    
                    int rndX = rnd.nextInt(size*2) - size;
                    int rndY = rnd.nextInt(size*2) - size;
                    int rndZ = rnd.nextInt(size*2) - size;
                    
                    boolean hasConnections = rnd.nextBoolean();
                    if(hasConnections){
                        hasConnections = rnd.nextBoolean();
                    }
                    if(hasConnections){
                        hasConnections = rnd.nextBoolean();
                    }
                    if(hasConnections){
                        hasConnections = rnd.nextBoolean();
                    }
                    if(hasConnections){
                        hasConnections = rnd.nextBoolean();
                    }
                    if(hasConnections){
                        hasConnections = rnd.nextBoolean();
                    }
                    
                    
                    points.add(new Point3D(rndX, rndY, rndZ));
                    
                    if(hasConnections && out.size() > 2){
                        //lines.add(new Integer[]{points.getFirst().identifier, out.get(rnd.nextInt(out.size()-1)).getFrame(0).points.getFirst().identifier});
                    }
                    
                    
                    
                    frames.add(new ModelFrame(points , lines, faces, color));
                    Model m = new Model(frames, true);
                    m.hidePoints = false;
                    out.add(m);
                }
            }
        }
        
        return out;
    }
    
    int rx2 = 3;
    int ry2 = rx2;
    int rz2 = rx2;
    LinkedList<Model> constructGrid(){
        int ind = 0;
        Random rnd = new Random();
        LinkedList<Model> out = new LinkedList<>();
        for(int z : new Range(rz2)){
            for (int x : new Range(rx2)) {
                for (int y: new Range(ry2)){
                    LinkedList<ModelFrame> frames = new LinkedList<>();
                    LinkedList<Point3D> points = new LinkedList<>();
                    LinkedList<Integer[]> lines = new LinkedList<>();
                    LinkedList<Point3D[]> faces = new LinkedList<>();
                    LinkedList<vertexGroup> color = new LinkedList<>();

                    double rndX = (x +0.5 - (rx2 / 2) )*2;
                    double rndY = (y +.5- (ry2 / 2) )*2;
                    double rndZ = (z +.5- (rz2 / 2) )*2;



                    points.add(new Point3D(-rndX, -rndZ, -rndY));


                    if(out.size() > 2){
                        try {
                            Point3D last = out.get(ind - 1).getFrame(0).points.getFirst();
                            if (y !=0) {
                                lines.add(new Integer[]{points.getFirst().identifier, last.identifier});
                            }
                        } catch (Exception e) {
                        }
                            
                            
                        try {
                            Point3D pair = out.get(ind - ry2).getFrame(0).points.getFirst();
                            if (x != 0) {
                                lines.add(new Integer[]{points.getFirst().identifier, pair.identifier});
                            }
                        } catch (Exception e) {
                        }
                        
                        try {
                            Point3D pair = out.get(ind - (rx2 * ry2)*rz2).getFrame(0).points.getFirst();
                            if (false) {
                                lines.add(new Integer[]{points.getFirst().identifier, pair.identifier});
                            }
                        } catch (Exception e) {
                            System.out.println(ind - (rx2 * ry2)*rz2);
                        }
                            //lines.add(new Integer[]{points.getFirst().identifier, out.get(ind - ry2).getFrame(0).points.getFirst().identifier});
                            //lines.add(new Integer[]{points.getFirst().identifier, out.get(ind - rz2).getFrame(0).points.getFirst().identifier});
                            //faces.add(new Point2D[]{

                            //});
                    }
                    //if(y == ry2){
                    //    lines.add(new Integer[]{points.getFirst().identifier, out.get(ind - ry2).getFrame(0).points.getFirst().identifier});
                    //}


                    frames.add(new ModelFrame(points , lines, faces, color));
                    Model m = new Model(frames, true);
                    m.hidePoints = false;
                    m.hideLines = false;
                    out.add(m);
                    ind = ind + 1;
                }
            }
        }
        
        return out;
    }
    
}

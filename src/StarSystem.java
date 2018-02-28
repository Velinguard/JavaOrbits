import CelestialBodies.Objects.*;
import CelestialBodies.Orbit;
import CelestialBodies.Planet;
import CelestialBodies.PlanetObj;
import CelestialBodies.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class StarSystem extends JPanel {
    private static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    private static int SCALER = 1;
    private static double milliSecondTimer;
    private int count;
    private ArrayList<Integer> keysDown;
    private ArrayList<Paintable> objects;
    private ArrayList<Orbit> orbits;
    private static boolean running;

    public StarSystem(){
        //Init
        keysDown = new ArrayList<>();
        objects = new ArrayList<>();
        orbits = new ArrayList<>();

        //Planet
        objects.add(new PlanetObj(100, 100, 20, BigDecimal.ZERO));
        //Sun
        objects.add(new PlanetObj(100, 150, 40, BigDecimal.ZERO));
        objects.add(new PlanetObj(100, 50, 10, BigDecimal.ZERO));
        orbits.add(new Orbit(BigDecimal.valueOf(50), BigDecimal.valueOf(100)
                , (Planet) objects.get(0), (Star) objects.get(1), BigDecimal.ZERO));
        orbits.add(new Orbit(BigDecimal.valueOf(100), BigDecimal.valueOf(150),
                (Planet) objects.get(2),(Star) objects.get(1), BigDecimal.valueOf(Math.PI) ));

        /*
        orbit.planet.incrementAngle(BigDecimal.valueOf(Math.toRadians(30)));
        orbit.updatePlanetPosition();
        */
        for (int i = 0; i < 40; i++){
            orbits.get(0).planet.incrementAngle(BigDecimal.valueOf(Math.PI / 16));
            orbits.get(0).updatePlanetPosition();
            objects.add(((PlanetObj) orbits.get(0).planet).newBall());
        }
        running = true;

        //Other
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
    }
    public static void main(String[] args) throws InterruptedException{
        JFrame frame = new JFrame("Star System");
        StarSystem app = new StarSystem();
        frame.setSize((WIDTH * SCALER),(HEIGHT * SCALER));
        frame.add(app);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.requestFocus();
        long lastLoopTime = System.nanoTime();
        int fps = 0, lastFpsTime = 0, lastMilliSecondTimer = 0, count = 1;
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        //Game Loop
        while(true){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            lastFpsTime += updateLength;
            lastMilliSecondTimer += updateLength;
            fps++;
            if (lastFpsTime > 100000000 / 6 * count){
                if (running){
                    milliSecondTimer += (0.1 / 6);
                    app.gravity();
                }
                count++;
            }
            if (lastFpsTime >= 1000000000){
                System.out.println("(FPS: "+fps+")");
                //milliSecondTimer += 1;
                lastFpsTime = 0;
                fps = 0;
                count = 1;
            }
            app.repaint();

            Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
        }
    }

    public void gravity(){
        orbits.stream().forEach(t -> {
            t.planet.incrementAngle(BigDecimal.valueOf(Math.PI / 64));
            t.updatePlanetPosition();
        });
    }

    //Window Painter
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.setFocusable(true);
        this.requestFocusInWindow();

        objects.stream().forEach(t -> t.paint(g2d));
    }
    //Listens for button presses
    public class MyKeyListener implements KeyListener{

        public void action(){
            if (keysDown.contains(KeyEvent.VK_SPACE)){
                running = !running;
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!keysDown.contains(e.getKeyCode())){
                keysDown.add(e.getKeyCode());
            }
            action();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keysDown.remove(new Integer(e.getKeyCode()));
        }
    }
}

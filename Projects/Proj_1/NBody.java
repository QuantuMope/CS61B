import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NBody{

    public static final int planetAmount = 5;

    public static double readRadius(String fileName){
        In in = new In(fileName);
        int list_length = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        // Skips over first two entries.
        int list_length = in.readInt();
        double radius = in.readDouble();

        Body planets[] = new Body[planetAmount];
        for (int i = 0; i < planetAmount; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Body planet = new Body(xxPos, yyPos, xxVel,
                                    yyVel, mass, img);
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args){
        // Collect necessary inputs.
        double T = 157788000.0;    //Double.parseDouble(args[0]);
        double dt = 25000.0;         //Double.parseDouble(args[1]);
        String fileName = "data/planets.txt";   //args[2];
        In in = new In(fileName);

        // Draw the background.
        double radius = readRadius(fileName);
        StdDraw.setScale(-radius, radius);

        String imageToDraw = "images/starfield.jpg";

        StdDraw.picture(0, 0, imageToDraw);

        // Drawing the planets.
        StdDraw.enableDoubleBuffering();
        Body[] planets = readBodies(fileName);

        for (Body planet : planets){
            planet.draw();
            StdDraw.show();
        }
        StdDraw.pause(2000);

        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        double sumForceX = 0;
        double sumForceY = 0;
        int index = 0;

        for (double time = 0; time < T; time += dt){
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for (Body planet : planets){
                for (int i = 0; i < 5; i++){
                    if (i == index){
                        continue;
                    }
                    sumForceX += planet.calcForceExertedByX(planets[i]);
                    sumForceY += planet.calcForceExertedByY(planets[i]);
                    System.out.println(planet.calcForceExertedByX(planets[i]));
                
                }
                xForces[index] = sumForceX;
                yForces[index] = sumForceY;

                sumForceX = 0;
                sumForceY = 0;
                index++;
                if (index == planetAmount - 1){
                    index = 0;
                }

            }
            index = 0;
            for (Body planet : planets){
                planet.update(dt, xForces[index], yForces[index]);
                planet.draw();
                index++;
                if (index == planetAmount - 1){
                    index = 0;
                }

            }
            StdDraw.show();
            StdDraw.pause(10);

        }

    }
}

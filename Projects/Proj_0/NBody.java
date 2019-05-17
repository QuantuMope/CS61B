/**
 * NBody class simulates planetary simulation for different planets.
 *
 * @author Andrew Choi
 * @Date 05/10/19
 */
public class NBody{

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

        Body[] planets = new Body[5];
        for (int i = 0; i < planets.length; i++){
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

        // Prep for background.
        double radius = readRadius(fileName);
        StdDraw.setScale(-radius, radius);
        String imageToDraw = "images/starfield.jpg";

        // Initialize necessary arrays.
        StdDraw.enableDoubleBuffering();
        Body[] planets = readBodies(fileName);
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        // Simulate through animation.
        for (double time = 0; time < T; time += dt){
            // Clear image.
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);

            for (int p = 0; p < planets.length; p++) {
                xForces[p] = planets[p].calcNetForceExertedByX(planets);
                yForces[p] = planets[p].calcNetForceExertedByY(planets);
            }

            for (int p = 0; p < planets.length; p++){
                planets[p].update(dt, xForces[p], yForces[p]);
                planets[p].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }

        // Print final values.
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int p = 0; p < planets.length; p++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[p].xxPos, planets[p].yyPos, planets[p].xxVel,
                  planets[p].yyVel, planets[p].mass, planets[p].imgFileName);
        }
    }
}

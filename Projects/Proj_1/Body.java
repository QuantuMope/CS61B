import java.lang.*;

public class Body{

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img){

                    this.xxPos = xP;
                    this.yyPos = yP;
                    this.xxVel = xV;
                    this.yyVel = yV;
                    this.mass = m;
                    this.imgFileName = img;
                }

    public Body(Body b){

        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;

    }

    public double calcDistance(Body comp){
        double dx;
        double dy;
        double distance;
        dx = comp.xxPos - this.xxPos;
        dy = comp.yyPos - this.yyPos;
        distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return distance;
    }

    public double calcForceExertedBy(Body comp){
        double distance;
        double force;
        distance = calcDistance(comp);
        force = (G * this.mass * comp.mass) / Math.pow(distance, 2);
        return force;
    }

    public double calcForceExertedByX(Body comp){
        double dx;
        double distance;
        double force;
        double forceX;
        dx = comp.xxPos - this.xxPos;
        distance = calcDistance(comp);
        force = calcForceExertedBy(comp);
        forceX = (force * dx) / distance;
        return forceX;
    }

    public double calcForceExertedByY(Body comp){
        double dy;
        double distance;
        double force;
        double forceY;
        dy = comp.yyPos - this.yyPos;
        distance = calcDistance(comp);
        force = calcForceExertedBy(comp);
        forceY = (force * dy) / distance;
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] planets){
        double sumForceX = 0;
        for (Body planet : planets){
            if (this.equals(planet)){
                continue;
            }
            sumForceX += calcForceExertedByX(planet);
        }
        return sumForceX;
    }

    public double calcNetForceExertedByY(Body[] planets){
        double sumForceY = 0;
        for (Body planet : planets){
            if (this.equals(planet)){
                continue;
            }
            sumForceY += calcForceExertedByY(planet);
        }
        return sumForceY;
    }


    public void update(double seconds, double forceX, double forceY){
        double acc, accX, accY;
        accX = forceX / this.mass;
        accY = forceY / this.mass;
        acc = Math.sqrt(Math.pow(accX, 2) + Math.pow(accY, 2));

        this.xxVel = this.xxVel + seconds * accX;
        this.yyVel = this.yyVel + seconds * accY;

        this.xxPos = this.xxPos + seconds * this.xxVel;
        this.yyPos = this.yyPos + seconds * this.yyVel;

    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}

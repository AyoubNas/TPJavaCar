import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;

/**
 * Write a description of class Cars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car2
{

    String proprietaire = "aucun";
    ColorImage carImage = new ColorImage("car-racing1-pink.png");
    ColorImage jerrican = new ColorImage("fuel.png");
    //carImage.scale = 0.5;
    double consoEssence = 10.0;
    double essenceDansReservoir = 0.1;
    int x = 200;
    int y = 200;
    Canvas canvas = new Canvas(); // declare une zone pour dessiner des objets
    
    public Car2 (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Car2 () {}
    
    public Car2(String nomDuproprietaire) 
    {
        proprietaire = nomDuproprietaire;
        carImage = new ColorImage();
    }
    
    public Car2 (String nomDuproprietaire, double newconsoEssence) 
    {
        proprietaire = nomDuproprietaire;
        carImage = new ColorImage( );
        consoEssence = newconsoEssence;
    }

    public void moveForward(double dist)
    { 
        if (essenceDansReservoir > 0)
        {
        // Obtain the current rotation in degrees
        int rotationInDegrees  = carImage.getRotation();
        
        // Tache 1 - Convertissez la rotation courante de degres en radians
        // Initialisez la variable "rotationInRadians" avec une valeur correcte.
        // ----------------------------------------------------
        // Ecrivez votre code ici :
        
        
        double rotationInRadians=rotationInDegrees*2*Math.PI/360;
       
        // Tache 2 - Calculez la distance a parcourir en x et en y (en fonction de l'angle de rotation)
        // Initialisez les variables distX et distY avec une valeur correcte.
        // Voir enonce en pdf pour plus de details (cos, sin du module java Math)
        // ----------------------------------------------------
        // Ecrivez votre code ici :
                     
        double distX=dist*Math.cos(rotationInRadians);
        double distY=dist*Math.sin(rotationInRadians);
        
        // ----------------------------------------------------
        
        
        // Move the car in both x and y directions with the correct distances
        // Notice that setX() and setY() take int as argument
        carImage.setX(carImage.getX() + (int)distX);
        carImage.setY(carImage.getY() + (int)distY);
        
        // Update the amount of gas in tank assumming that each unit of dist is 10m
        double distKm = dist / 100.0;
        double essenceUtilisee = distKm / 100.0 * consoEssence;
        essenceDansReservoir = essenceDansReservoir - essenceUtilisee;
        IO.outputln("Amount of gas used: " + essenceUtilisee + ", gas remained: " + essenceDansReservoir);
    } else
    {
        IO.outputln("Plus de d'essence");
        this.canvas.add(jerrican,this.x+20,this.y+20); // ajoute l'image de la voiture aux coordonnees indiquees
    }
    }
    
    public void tournerEnRond(int rayon)
    {
        double dist = 2*Math.PI*rayon;
        System.out.println(dist);
        double distParcouru = 0;
        double d = dist / 360;
        while (distParcouru < dist)
        {
            this.makeTurn(1);
            this.moveForward(d);
            distParcouru+=d;
            try{
            Thread.sleep(1);
            }catch (InterruptedException e) {}
        }
    }
    
    public void makeTurn(int angleAdditionnel) 
    { 
        // Change l'orientation de la voiture depuis l'orientation actuelle
        carImage.setRotation(carImage.getRotation() + angleAdditionnel);
    }
    
    // addGas ajoute un volume d'essence indiqué au reservoir
     public void addGas(double essenceSupplementaire) {
         essenceDansReservoir = essenceDansReservoir + essenceSupplementaire;
         if (essenceDansReservoir > 0)
         {
             canvas.remove(jerrican);
         }
    }
    
    public void Car2Demo()
    {
        
        canvas.add(carImage,this.x,this.y); // ajoute l'image de la voiture aux coordonnees indiquees
    }

}

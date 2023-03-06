import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static void staticParser(List<Particle> particles, int N, int L, double r, String staticFile) throws Exception {
        File file = new File(staticFile);
        Scanner sc = new Scanner(file);

        N = sc.nextInt();
        L = sc.nextInt();

        while (sc.hasNextLine()){
            Particle particle = new Particle();
            particle.setRadius(sc.nextFloat());
            //Skip properties
            sc.next();
            particles.add(particle);
        }

        r = particles.get(0).getRadius();
        
        sc.close();
    }

    public static void dynamicParser(List<Particle> particles, String dynamicFile) throws FileNotFoundException {
        File file = new File(dynamicFile);
        Scanner sc = new Scanner(file);

        //Skip time
        sc.nextLine();
        int i = 0;
        while (sc.hasNextLine()){
            particles.get(i).setX(sc.nextFloat());
            particles.get(i).setY(sc.nextFloat());
            i++;
        }
    }
}

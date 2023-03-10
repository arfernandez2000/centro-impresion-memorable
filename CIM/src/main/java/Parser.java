import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    /**
     * Lee del archivo de valores estáticos para poder guardad las partículas y algunas propiedades
     * @param particles Lista de partículas vacía
     * @param staticFile Path al archivo de valores estáticos
     * @param properties Objeto de propiedades del sistema
     * @throws Exception Por errores de lectura y apertura de archivos
     */
    public static void staticParser(List<Particle> particles, String staticFile, Properties properties) throws Exception {
        File file = new File(staticFile);
        Scanner sc = new Scanner(file);

        properties.setN(sc.nextInt());
        properties.setL(sc.nextInt());

        while (sc.hasNextLine()){
            Particle particle = new Particle();
            particle.setRadius(sc.nextFloat());
            //Skip properties
            sc.next();
            particles.add(particle);
        }

        properties.setR(particles.get(0).getRadius());

        sc.close();

    }

    /**
     * Lee del archivo de valores dinámicos para poder guardad las posiciones de las partículas
     * @param particles Lista de partículas
     * @param dynamicFile Path al archivo de valores estáticos
     * @throws FileNotFoundException Por errores de lectura y apertura de archivos
     *
     */
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

        sc.close();
    }
}

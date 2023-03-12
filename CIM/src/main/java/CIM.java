import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CIM {

    private static Map<Integer, List<Particle>> cells;
    private static Properties properties;

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try {
            ConsoleParser.parseArguments(args, properties);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Particle> particles = new ArrayList<>();
        Parser.staticParser(particles,ConsoleParser.staticFile, properties);
        Parser.dynamicParser(particles, ConsoleParser.dynamicFile);

        if (properties.getR_c() == null)
            properties.setR_c(15F);

        if(properties.isTest()){
            bestMForDensity(properties, particles);
            return;
        }

        if (properties.getM() != null){
            if(properties.getL()/(float) properties.getM() <= (properties.getR_c() + 2* properties.getR())) {
                System.out.println("M no cumple la condición de desigualdad");
                return;
            }
        } else
            properties.setM(CIM.calculateM(properties.getL(), properties.getR_c(), properties.getR()));

        long start = System.currentTimeMillis();

        if (!properties.isBrute()) {
            cellIndexMethod(properties, particles);
        } else {
            bruteForceMethod(properties, particles);
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("Algoritmo: " + (properties.isBrute() ? "Brute Force" : "Cell Index Method"));
        System.out.println("Periodic: " + properties.isPeriodic());
        System.out.println("M: " + properties.getM());
        System.out.println("r_c: " + properties.getR_c());
        System.out.println("Tiempo de ejecución: ");
        System.out.println(time + " ms");

        try {
            FileWriter myWriter;
            if(properties.isBrute())
                myWriter = new FileWriter("src/main/resources/output_brute.txt");
            else
                myWriter = new FileWriter("src/main/resources/output.txt");
            for (Particle p: particles) {
                myWriter.write(p.getId() + "\t" + p.printNeighbour() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al abrir el output.txt.");
            e.printStackTrace();
        }


    }

    private static void bestMForDensity(Properties prop, List<Particle> particles) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/test/best_m_for_density_times.txt");
            myWriter.write("N: " + prop.getN() + "\n");
            if(prop.isBrute()) {
                long start = System.currentTimeMillis();
                bruteForceMethod(prop, particles);
                long time = System.currentTimeMillis() - start;
                myWriter.write("Brute Force " + "\n" + "Tiempo: " + time + " ms" + "\n");
            }
            else {
                prop.setM(calculateM(prop.getL(), prop.getR_c(), prop.getR()));
                for (int i = 0; i < 13; i++) {
                    if(i != 0)
                        prop.setM(prop.getM() - 1);
                    long start = System.currentTimeMillis();
                    cellIndexMethod(prop, particles);
                    long time = System.currentTimeMillis() - start;
                    myWriter.write("M: " + prop.getM() + " Tiempo: " + time + " ms" + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al abrir el output.txt.");
            e.printStackTrace();
        }

    }

    private static void bruteForceMethod(Properties prop, List<Particle> particles) {
        for (Particle p1 : particles) {
            for (Particle p2 : particles) {
                if (p1.equals(p2)) continue;
                float dist_x = Math.abs(p1.getX() - p2.getX());
                float dist_y = Math.abs(p1.getY() - p2.getY());
                float delta_x = dist_x;
                float delta_y = dist_y;
                if (prop.isPeriodic()) {
                    delta_x = Math.min(prop.getL() - dist_x, dist_x);
                    delta_y = Math.min(prop.getL() - dist_y, dist_y);
                }
                double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
                if (distance - 2 * p1.getRadius() < prop.getR_c()) {
                    p1.addNeighbour(p2);
                }
            }
        }
    }

    private static void cellIndexMethod(Properties prop, List<Particle> particles){
        cells = new HashMap<>();
        properties = prop;
        for (int i = 0; i < properties.getM() * properties.getM() ; i++){
            cells.put(i, new ArrayList<>());
        }

        for (Particle p : particles){
            double cellLength = properties.getL()/ (double) properties.getM();

            int x = (int) Math.floor(p.getX() / cellLength);
            int y = (int) Math.floor(p.getY() / cellLength);

            int cellIndex = (y * properties.getM() + x);

            List <Particle> particleList = cells.get(cellIndex);
            p.setxCell(x);
            p.setyCell(y);
            particleList.add(p);
        }

        for (List<Particle> cellParticles : cells.values()){
            for (Particle p : cellParticles){

                int cellX = p.getxCell();
                int cellY = p.getyCell();

                findNeighbour(p, cellX, cellY, 0);
                findNeighbour(p, cellX, cellY + 1, 1);
                findNeighbour(p, cellX + 1, cellY + 1, 2);
                findNeighbour(p, cellX + 1, cellY, 3);
                findNeighbour(p, cellX + 1, cellY - 1, 4);
            }
        }

    }

    private static void findNeighbour(Particle particle, int cellX, int cellY, int space) {
        if (!properties.isPeriodic()) {
            if (cellX >= properties.getM() || cellY >= properties.getM() || cellY < 0) {
                return;
            }
        } else {
            cellY = (cellY + properties.getM()) % properties.getM();
            cellX = (cellX + properties.getM()) % properties.getM();
        }
        int neigCellIndex = (cellY * properties.getM() + cellX);

        List<Particle> particles = cells.get(neigCellIndex);


        for (Particle neigParticle : particles){
            //Chequear que no sean la misma
            if (!neigParticle.equals(particle)){
                float dist_x = Math.abs(particle.getX() - neigParticle.getX());
                float dist_y = Math.abs(particle.getY() - neigParticle.getY());
                float delta_x = dist_x;
                float delta_y = dist_y;

                if (properties.isPeriodic()) {
                    delta_x = Math.min(properties.getL() - dist_x, dist_x);
                    delta_y = Math.min(properties.getL() - dist_y, dist_y);
                }

                double distance = Math.sqrt(Math.pow(delta_x, 2) + Math.pow(delta_y, 2))
                        - 2 * particle.getRadius();

                if (distance < properties.getR_c()){
                    particle.addNeighbour(neigParticle);
                    neigParticle.addNeighbour(particle);
                }
            }
        }
    }

    // L/M > r_c + 2r
    // M < L / (r_c + 2r)
    private static int calculateM(int L, float r_c, float r) {
        float M = L / (r_c + 2*r);

        return (int) Math.floor(M);
    }



}

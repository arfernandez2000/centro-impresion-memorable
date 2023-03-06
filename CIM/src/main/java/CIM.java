import java.util.ArrayList;
import java.util.List;

public class CIM {

    public static void main(String[] args) throws Exception {
        List<Particle> particles = new ArrayList<>();
        int N = 0;
        int L = 0;
        float r = 0;
        Parser.staticParser(particles, N, L, r, args[0]);
        Parser.dynamicParser(particles, args[1]);

        int M = CIM.calculateM(L, 1, r);

        for (Particle particle: particles) {
            System.out.println(particle);
        }

        System.out.println(M);


    }


    // L/M > r_c + 2r
    // M < L / (r_c + 2r)
    private static int calculateM(int L, float r_c, float r) {
        float M = L / (r_c + 2*r);
        return (int) Math.floor(M);
    }

}

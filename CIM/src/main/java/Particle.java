import java.util.*;

public class Particle {

    private int id;

    private static int s_id = 0;

    private float x;
    private float y;
    private float radius;

    //Con el método cim logro no tener repetidos,
    // excepto que dos partículas estén en la misma cell
    private Set<Particle> neighbours = new HashSet<>();

    private int xCell;
    private int yCell;

    public Particle() {
        id = s_id++;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Set<Particle> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Particle> neighbours) {
        this.neighbours = neighbours;
    }

    public int getxCell() {
        return xCell;
    }

    public void setxCell(int xCell) {
        this.xCell = xCell;
    }

    public int getyCell() {
        return yCell;
    }

    public void setyCell(int yCell) {
        this.yCell = yCell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        if (Float.compare(particle.x, x) != 0) return false;
        if (Float.compare(particle.y, y) != 0) return false;
        return Float.compare(particle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != 0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != 0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (radius != 0.0f ? Float.floatToIntBits(radius) : 0);
        return result;
    }

    public void addNeighbour(Particle particle) {
        this.neighbours.add(particle);
    }

    public String printNeighbour() {
        String neighboursList = this.neighbours.toString();
        return neighboursList.substring(1, neighboursList.length() - 1);
    }
}

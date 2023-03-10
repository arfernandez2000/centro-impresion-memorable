import java.util.ArrayList;
import java.util.List;

public class Particle {

    private float x;
    private float y;
    private float radius;
    private List<Particle> neighbours = new ArrayList<>();

    private int xCell;
    private int yCell;

    public Particle() { }

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

    public List<Particle> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Particle> neighbours) {
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

    @Override
    public String toString() {
        return "Particle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", neighboursCount=" + neighbours.size() +
                ", xCell=" + xCell +
                ", yCell=" + yCell +
                '}';
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
}

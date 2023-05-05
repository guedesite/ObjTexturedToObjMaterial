package fr.guedesite.objmtl;

import java.util.Objects;

public class Vertex3d {
    private double x;
    private double y;
    private int z;

    public Vertex3d(double x, double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void set(double x, double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Vertex3d v) {
        double dx = v.getX() - this.x;
        double dy = v.getY() - this.y;
        int dz = v.getZ() - this.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public void translate(double dx, double dy, int dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex3d)) return false;
        Vertex3d vertex3d = (Vertex3d) o;
        return Double.compare(vertex3d.x, x) == 0 &&
                Double.compare(vertex3d.y, y) == 0 &&
                Integer.compare(vertex3d.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
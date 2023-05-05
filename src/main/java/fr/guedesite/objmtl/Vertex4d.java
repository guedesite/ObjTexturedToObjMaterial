package fr.guedesite.objmtl;

public class Vertex4d {
    private double x;
    private double y;
    private double z;
    private int w;

    public Vertex4d(double x, double y, double z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void set(double x, double y, double z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public double distance(Vertex4d v) {
        double dx = v.getX() - this.x;
        double dy = v.getY() - this.y;
        double dz = v.getZ() - this.z;
        int dw = v.getW() - this.w;
        return Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    public void translate(double dx, double dy, double dz, int dw) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
        this.w += dw;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vertex4d)) {
            return false;
        }
        Vertex4d v = (Vertex4d) o;
        return Double.compare(x, v.getX()) == 0 && Double.compare(y, v.getY()) == 0 && Double.compare(z, v.getZ()) == 0 && Integer.compare(w, v.getW()) == 0;
    }
}
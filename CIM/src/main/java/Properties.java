public class Properties {
    private int N;
    private int L;
    private Integer M;
    private float r;
    private Float r_c;
    private boolean brute;
    private boolean periodic;

    public Properties() {
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public Integer getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public Float getR_c() {
        return r_c;
    }

    public void setR_c(float r_c) {
        this.r_c = r_c;
    }

    public boolean isBrute() {
        return brute;
    }

    public void setBrute(boolean brute) {
        this.brute = brute;
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }
}

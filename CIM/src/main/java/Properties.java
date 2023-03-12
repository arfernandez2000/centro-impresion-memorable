public class Properties {
    private int N;
    private int L;
    private Integer M;
    private float r;
    private Float r_c;
    private boolean brute;
    private boolean periodic;

    private boolean test;

    public Properties() { }

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


    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public Float getR_c() {
        return r_c;
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

    public void setM(Integer m) {
        M = m;
    }

    public void setR_c(Float r_c) {
        this.r_c = r_c;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
}

package com.stepic.extends_ex;


public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ComplexNumber)) {
            return false;
        }
        if(Double.compare(this.re, ((ComplexNumber) o).re) != 0){
            return false;
        }
        if(Double.compare(this.im, ((ComplexNumber) o).im) != 0){
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = 17;
        long longBits = Double.doubleToLongBits(this.im);
        result = 37 * result + (int)(longBits - (longBits >>> 32));
        longBits = Double.doubleToLongBits(this.re);
        result = 37 * result + (int)(longBits - (longBits >>> 32));
        return result;
    }


}

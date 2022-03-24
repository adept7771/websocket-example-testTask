
package dataModels;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @Expose
    private List<List<String>> a;
    @Expose
    private List<List<String>> b;
    @Expose
    private String e;
    @Expose
    private String E;
    @Expose
    private String s;
    @SerializedName("U")
    private Long U;
    @SerializedName("u")
    private Long u;

    public List<List<String>> getA() {
        return a;
    }

    public void setA(List<List<String>> a) {
        this.a = a;
    }

    public List<List<String>> getB() {
        return b;
    }

    public void setB(List<List<String>> b) {
        this.b = b;
    }

    public String gete() {
        return e;
    }

    public void sete(String e) {
        this.e = e;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        this.E = e;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Long getU() {
        return U;
    }

    public void setU(Long u) {
        this.U = u;
    }

    public Long getu() {
        return u;
    }

    public void setu(Long u) {
        this.u = u;
    }

}

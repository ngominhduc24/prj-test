package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GiayKhaiSinh {

    private int id;
    private String SoGiayKhaiSinh;
    private String HoVaTenCha;
    private String HoVaTenMe;
    private String HoVaTenCon;
    private String GioiTinhCon;
    private Date NgaySinhCon;
    private String DanTocCon;
    private String QuocTichCon;

    public GiayKhaiSinh() {
    }

    public GiayKhaiSinh(int id, String SoGiayKhaiSinh, String HoVaTenCha, String HoVaTenMe, String HoVaTenCon, String GioiTinhCon, String NgaySinhCon, String DanTocCon, String QuocTichCon) {
        this.id = id;
        this.SoGiayKhaiSinh = SoGiayKhaiSinh;
        this.HoVaTenCha = HoVaTenCha;
        this.HoVaTenMe = HoVaTenMe;
        this.HoVaTenCon = HoVaTenCon;
        this.GioiTinhCon = GioiTinhCon;
//        this.NgaySinhCon = NgaySinhCon;
        this.DanTocCon = DanTocCon;
        this.QuocTichCon = QuocTichCon;
    }

    // Các phương thức getter và setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoGiayKhaiSinh() {
        return SoGiayKhaiSinh;
    }

    public void setSoGiayKhaiSinh(String SoGiayKhaiSinh) {
        this.SoGiayKhaiSinh = SoGiayKhaiSinh;
    }

    public String getHoVaTenCha() {
        return HoVaTenCha;
    }

    public void setHoVaTenCha(String HoVaTenCha) {
        this.HoVaTenCha = HoVaTenCha;
    }

    public String getHoVaTenMe() {
        return HoVaTenMe;
    }

    public void setHoVaTenMe(String HoVaTenMe) {
        this.HoVaTenMe = HoVaTenMe;
    }

    public String getHoVaTenCon() {
        return HoVaTenCon;
    }

    public void setHoVaTenCon(String HoVaTenCon) {
        this.HoVaTenCon = HoVaTenCon;
    }

    public String getGioiTinhCon() {
        return GioiTinhCon;
    }

    public void setGioiTinhCon(String GioiTinhCon) {
        this.GioiTinhCon = GioiTinhCon;
    }

    public String getNgaySinhCon() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(NgaySinhCon);
    }

    public void setNgaySinhCon(String NgaySinhCon) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(NgaySinhCon);
            this.NgaySinhCon = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
        }
    }

    public String getDanTocCon() {
        return DanTocCon;
    }

    public void setDanTocCon(String DanTocCon) {
        this.DanTocCon = DanTocCon;
    }

    public String getQuocTichCon() {
        return QuocTichCon;
    }

    public void setQuocTichCon(String QuocTichCon) {
        this.QuocTichCon = QuocTichCon;
    }

    @Override
    public String toString() {
        return "GiayKhaiSinh{"
                + "id=" + id
                + ", SoGiayKhaiSinh='" + SoGiayKhaiSinh + '\''
                + ", HoVaTenCha='" + HoVaTenCha + '\''
                + ", HoVaTenMe='" + HoVaTenMe + '\''
                + ", HoVaTenCon='" + HoVaTenCon + '\''
                + ", GioiTinhCon='" + GioiTinhCon + '\''
                + ", NgaySinhCon='" + NgaySinhCon + '\''
                + ", DanTocCon='" + DanTocCon + '\''
                + ", QuocTichCon='" + QuocTichCon + '\''
                + '}';
    }
}

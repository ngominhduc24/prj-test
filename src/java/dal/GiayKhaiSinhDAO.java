package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GiayKhaiSinh;

public class GiayKhaiSinhDAO {

    public List<GiayKhaiSinh> getAllGiayKhaiSinh() {
        String sql = "SELECT * FROM GiayKhaiSinh";
        List<GiayKhaiSinh> listGiayKhaiSinh = new ArrayList<>();
        try {
            PreparedStatement ps = DbContext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiayKhaiSinh giayKhaiSinh = new GiayKhaiSinh();
                giayKhaiSinh.setId(rs.getInt("id"));
                giayKhaiSinh.setSoGiayKhaiSinh(rs.getString("SoGiayKhaiSinh"));
                giayKhaiSinh.setHoVaTenCha(rs.getString("HoVaTenCha"));
                giayKhaiSinh.setHoVaTenMe(rs.getString("HoVaTenMe"));
                giayKhaiSinh.setHoVaTenCon(rs.getString("HoVaTenCon"));
                giayKhaiSinh.setGioiTinhCon(rs.getString("GioiTinhCon"));
                giayKhaiSinh.setNgaySinhCon(rs.getString("NgaySinhCon"));
                giayKhaiSinh.setDanTocCon(rs.getString("DanTocCon"));
                giayKhaiSinh.setQuocTichCon(rs.getString("QuocTichCon"));
                listGiayKhaiSinh.add(giayKhaiSinh);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listGiayKhaiSinh;
    }

    public GiayKhaiSinh createGiayKhaiSinh(GiayKhaiSinh giayKhaiSinh) {
        String sql = "INSERT INTO GiayKhaiSinh(SoGiayKhaiSinh, HoVaTenCha, HoVaTenMe, HoVaTenCon, GioiTinhCon, NgaySinhCon, DanTocCon, QuocTichCon) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DbContext.getConnection().prepareStatement(sql);
            ps.setString(1, giayKhaiSinh.getSoGiayKhaiSinh());
            ps.setString(2, giayKhaiSinh.getHoVaTenCha());
            ps.setString(3, giayKhaiSinh.getHoVaTenMe());
            ps.setString(4, giayKhaiSinh.getHoVaTenCon());
            ps.setString(5, giayKhaiSinh.getGioiTinhCon());
            ps.setString(6, giayKhaiSinh.getNgaySinhCon());
            ps.setString(7, giayKhaiSinh.getDanTocCon());
            ps.setString(8, giayKhaiSinh.getQuocTichCon());
            ps.executeUpdate();
            return giayKhaiSinh;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public GiayKhaiSinh getGiayKhaiSinhById(int id) {
    String sql = "SELECT * FROM GiayKhaiSinh WHERE id = ?";
    try {
        PreparedStatement ps = DbContext.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            GiayKhaiSinh giayKhaiSinh = new GiayKhaiSinh();
            giayKhaiSinh.setId(rs.getInt("id"));
            giayKhaiSinh.setSoGiayKhaiSinh(rs.getString("SoGiayKhaiSinh"));
            giayKhaiSinh.setHoVaTenCha(rs.getString("HoVaTenCha"));
            giayKhaiSinh.setHoVaTenMe(rs.getString("HoVaTenMe"));
            giayKhaiSinh.setHoVaTenCon(rs.getString("HoVaTenCon"));
            giayKhaiSinh.setGioiTinhCon(rs.getString("GioiTinhCon"));
            giayKhaiSinh.setNgaySinhCon(rs.getString("NgaySinhCon"));
            giayKhaiSinh.setDanTocCon(rs.getString("DanTocCon"));
            giayKhaiSinh.setQuocTichCon(rs.getString("QuocTichCon"));
            return giayKhaiSinh;
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return null; // Trả về null nếu không tìm thấy giấy khai sinh với ID tương ứng
}
public boolean updateGiayKhaiSinh(GiayKhaiSinh giayKhaiSinh) {
    String sql = "UPDATE GiayKhaiSinh SET SoGiayKhaiSinh = ?, HoVaTenCha = ?, HoVaTenMe = ?, HoVaTenCon = ?, GioiTinhCon = ?,  DanTocCon = ?, QuocTichCon = ? WHERE id = ?";
    try {
        PreparedStatement ps = DbContext.getConnection().prepareStatement(sql);
        ps.setString(1, giayKhaiSinh.getSoGiayKhaiSinh());
        ps.setString(2, giayKhaiSinh.getHoVaTenCha());
        ps.setString(3, giayKhaiSinh.getHoVaTenMe());
        ps.setString(4, giayKhaiSinh.getHoVaTenCon());
        ps.setString(5, giayKhaiSinh.getGioiTinhCon());
        ps.setString(6, giayKhaiSinh.getDanTocCon());
        ps.setString(7, giayKhaiSinh.getQuocTichCon());
        ps.setInt(8, giayKhaiSinh.getId());
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return false;
}
    public boolean deleteGiayKhaiSinhByID(int id) {
        String sql = "DELETE FROM GiayKhaiSinh WHERE id = ?";
        try {
            PreparedStatement ps = DbContext.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
}

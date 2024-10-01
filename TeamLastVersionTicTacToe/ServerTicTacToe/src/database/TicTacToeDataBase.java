package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver; 
/**
 *
 * @author Mai Ibrahem
 */
public class TicTacToeDataBase {
    private static TicTacToeDataBase instanceData; // Singleton instance
    private Connection con;               // Database connection
    private ResultSet rs;                 // Result set for queries
    private PreparedStatement pst;        // Prepared statement for queries
    
    public TicTacToeDataBase() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/TicTacToeDataBase", "root", "root");
    }

    
    public synchronized void SignUp(String email, String username, String password) throws SQLException {
        
        pst = con.prepareStatement("insert into player(email, username, password) values(?, ?, ?)");
        pst.setString(1, email);
        pst.setString(2, username);
        pst.setString(3, password);
        pst.executeUpdate();
        
    }
    public synchronized boolean isCredentialsValid(String email, String password) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM player WHERE email = ? AND password = ?")) {
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }
    }

    public synchronized boolean isEmailExists(String email) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM player WHERE email = ?")) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }
    }

    public synchronized void updateIsActive(String email, boolean isActive) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("UPDATE player SET isactive = ? WHERE email = ?")) {
            pst.setBoolean(1, isActive);
            pst.setString(2, email);
            pst.executeUpdate();
        }
    }
    
    public synchronized String checkRegister(String email, String username) {
        ResultSet checkRs;
        PreparedStatement pstCheck;

        try {
            pstCheck = con.prepareStatement("select * from player where username = ? and email = ?");
            pstCheck.setString(1, username);
            pstCheck.setString(2, email);
            checkRs = pstCheck.executeQuery();
            if (checkRs.next()) {
                return "already signed-up";
            }
        } catch (SQLException ex) {
            System.out.println("here");
            Logger.getLogger(TicTacToeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Registered Successfully";
    }
    
    public synchronized String checkSignIn(String email, String password) {
        ResultSet checkRs;
        PreparedStatement pstCheck;

        if (!checkIsActive(email)) {
            try {
                pstCheck = con.prepareStatement("select * from player where email = ?");
                pstCheck.setString(1, email);
                checkRs = pstCheck.executeQuery();
                if (checkRs.next()) {
                    if (password.equals(checkRs.getString(4))) {
                        return "Logged in successfully";
                    }
                    return "Password is incorrect";
                }
                return "Email is incorrect";
            } catch (SQLException ex) {
                Logger.getLogger(TicTacToeDataBase.class.getName()).log(Level.SEVERE, null, ex);
                return "Connection issue, please try again later";
            }
        } else {
            System.out.println("This Email already sign-in " + checkIsActive(email));
            return "This Email is already sign-in";
        }
    }
    
    public Boolean checkIsActive(String email) {
        ResultSet checkRs;
        PreparedStatement pstCheck;

        try {
            pstCheck = con.prepareStatement("select isactive from player where email = ?");
            pstCheck.setString(1, email);
            checkRs = pstCheck.executeQuery();
            checkRs.next();
            return checkRs.getBoolean("isactive");
        } catch (SQLException ex) {
            System.out.println("Invalid Email address");
        }
        return false;
    }
    public static synchronized TicTacToeDataBase getInstance() throws SQLException {
        if (instanceData == null) {
            instanceData = new TicTacToeDataBase();
        }
        return instanceData;
    }
    
    
    public synchronized List<String> getActivePlayers() throws SQLException {
    List<String> activePlayers = new ArrayList<>();
    try (PreparedStatement pst = con.prepareStatement("SELECT username FROM player WHERE isactive = true")) {
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            activePlayers.add(rs.getString("username"));
        }
    }
    return activePlayers;
}
    
    public String getUsernameByEmail(String email) throws SQLException {
    String query = "SELECT username FROM player WHERE email = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("username");
        } else {
            return null;
        }
    }
}

    public int getScoreByEmail(String email) throws SQLException {
    String query = "SELECT score FROM player WHERE email = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("score");
        } else {
            return 0;
        }
    }
}
    
     public synchronized void updateScore(String email, int score) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("UPDATE player SET score = ? WHERE email = ?")) {
           // pst.setBoolean(1, isActive);
           pst.setInt(1, score);
            pst.setString(2, email);
            pst.executeUpdate();
        }
    }
     
     
     
     
     public int getActivePlayerCount() {
        int activeCount = 0;
        try {
            pst = con.prepareStatement("select count(*) as count from player where isactive = true");
            rs = pst.executeQuery();
            if (rs.next()) {
                activeCount = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicTacToeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activeCount;
    }
     
      public int getInactivePlayerCount() {
        int inactiveCount = 0;
        try {
            pst = con.prepareStatement("select count(*) as count from player where isactive = false");
            rs = pst.executeQuery();
            if (rs.next()) {
                inactiveCount = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicTacToeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inactiveCount;
    }
      
      

}

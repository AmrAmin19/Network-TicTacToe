/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author amram
 */
public class SharedData {
    protected String PlayerName1;
    protected String PlayerName2;
    

    public void setPlayerName1(String PlayerName1) {
        this.PlayerName1 = PlayerName1;
    }

    public void setPlayerName2(String PlayerName2) {
        this.PlayerName2 = PlayerName2;
    }

    public String getPlayerName1() {
        return PlayerName1;
    }

    public String getPlayerName2() {
        return PlayerName2;
    }

//    public SharedData(String PlayerName1, String PlayerName2) {
//        this.PlayerName1 = PlayerName1;
//        this.PlayerName2 = PlayerName2;
//    }
    
}

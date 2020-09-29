public class Player {
    
    int troops;
    
    Player() {
        troops = 0;
    }
    
    Player(int tr) {
        troops = tr;
    }
    
    public void setTroops(int tr) {
        troops = tr;
    }
    
    public int getTroops() {
        return troops;
    }
}

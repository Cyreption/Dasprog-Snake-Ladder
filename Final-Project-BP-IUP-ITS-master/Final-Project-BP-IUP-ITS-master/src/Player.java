public class Player {
    private final int number;
    private final String colour;
    private String name = "Player ";
    private int currentTile;
    private boolean isStunned = false;

    public Player(int number, int currentTile, String colour) {
        this.number = number;
        this.name += number;
        this.currentTile = currentTile;
        this.colour = colour;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public int getCurrentCase() {
        return this.currentTile;
    }

    public void setCurrentCase(int n) {
        this.currentTile = n;
    }

    public String getColour() {
        return this.colour;
    }

    public boolean isStunned() {
        return this.isStunned;
    }

    public void setStunned(boolean stunned) {
        this.isStunned = stunned;
    }
}

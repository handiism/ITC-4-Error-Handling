package id.handiism.genshin.vision;

public class Hydro {
    private final String name;
    private int healthPoint;
    private final int attack;
    private final int elementalMastery;

    public Hydro(String name, int healthPoint, int attack, int elementalMastery) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.elementalMastery = elementalMastery;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public int getElementalMastery() {
        return this.elementalMastery;
    }

    public String getName() {
        return name;
    }

    public void physicalAttack(Anemo anemo) {
        anemo.setHealthPoint(anemo.getHealthPoint() - this.attack);
    }

    public void physicalAttack(Hydro hydro) {
        hydro.setHealthPoint(hydro.getHealthPoint() - this.attack);
    }

    public void physicalAttack(Pyro pyro) {
        pyro.setHealthPoint(pyro.getHealthPoint() - this.attack);
    }

    public void elementalAttack(Anemo anemo) {
        if (this.elementalMastery > anemo.getElementalMastery()) {
            anemo.setHealthPoint(anemo.getHealthPoint() - this.elementalMastery);
        }

    }

    public void elementalAttack(Pyro pyro) {
        if (this.elementalMastery > pyro.getElementalMastery()) {
            pyro.setHealthPoint(pyro.getHealthPoint() - 2 * this.elementalMastery);
        }

    }

    public boolean isAlive() {
        return this.healthPoint > 0;
    }
}

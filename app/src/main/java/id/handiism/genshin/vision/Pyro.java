package id.handiism.genshin.vision;

public class Pyro {
    private final String name;
    private int healthPoint;
    private final int attack;
    private final int elementalMastery;

    public Pyro(String name, int healthPoint, int attack, int elementalMastery) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.elementalMastery = elementalMastery;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getElementalMastery() {
        return elementalMastery;
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


    public void elementalAttack(Hydro hydro) {
        if (this.elementalMastery > hydro.getElementalMastery()) {
            hydro.setHealthPoint((int) (hydro.getHealthPoint() - 1.5 * this.elementalMastery));
        }
    }

    public void elementalAttack(Anemo anemo) {
        if (this.elementalMastery > anemo.getElementalMastery()) {
            anemo.setHealthPoint(anemo.getHealthPoint() - this.elementalMastery);
        }
    }

    public boolean isAlive() {
        return this.healthPoint > 0;
    }
}

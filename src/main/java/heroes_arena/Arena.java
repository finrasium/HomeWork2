package heroes_arena;

public class Arena {
    private final Hero fighter1;
    private final Hero fighter2;
    private int roundCounter;

    public Arena(Hero fighter1,Hero fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.roundCounter = 0;
    }
    public void fighterStatus(Hero fighter){
        System.out.print(fighter.getName() + " (HP: " + fighter.getHealth() + "/" + fighter.getMaxHealth());
        if (fighter instanceof Mage){
            Mage mage = (Mage) fighter;
            System.out.print(", Mana: " + mage.getMana() + "/" + mage.getMaxMana());
        } else{
            Warrior warrior = (Warrior) fighter;
            System.out.print(", Armor: " + warrior.getArmor());
        }
        System.out.println(")");
    }
    public void startTournament() {
        while (fighter1.isAlive() && fighter2.isAlive()) {
            roundCounter += 1;
            System.out.println("раунд номер " + roundCounter);
            Hero attacker;
            Hero defender;
            if (Math.random() < 0.5) {
                attacker = fighter1;
                defender = fighter2;
            } else {
                attacker = fighter2;
                defender = fighter1;
            }
            ActionType attAction = attacker.makeTurn(defender);
            System.out.println("Персонаж " + attacker.getName() + " совершил действие: " + attAction.getDescription());
            if (!defender.isAlive()) {
                System.out.println(defender.getName() + " ВСЁ!");
                break;
            }
            ActionType defAction = attacker.makeTurn(attacker);
            System.out.println("Персонаж " + defender.getName() + " совершил действие: " + defAction.getDescription());
            if (!attacker.isAlive()) {
                System.out.println(attacker.getName() + " ВСЁ!");
                break;
            }
            fighterStatus(fighter1);
            fighterStatus(fighter2);
        }
        if (fighter1.isAlive()) {
            System.out.println("ПОБЕДИТЕЛЬ: " + fighter1.getName());
        } else if (fighter2.isAlive()) {
            System.out.println("ПОБЕДИТЕЛЬ: " + fighter2.getName());
        } else {
            System.out.println("хз возможно ли, они они себя убили");
        }
    }
}

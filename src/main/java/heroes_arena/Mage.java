package heroes_arena;

public class Mage extends Hero implements Castable,Restable {
    private final int maxMana;
    private int mana;

    public Mage(String name, int maxHealth, int baseAttack, int maxMana){
        super(name,maxHealth,baseAttack);
        if (maxMana < MIN_STAT_VALUE) {
            throw new IllegalArgumentException();
        }
        this.maxMana = maxMana;
        this.mana = maxMana;
    }
    @Override
    public void attack(Hero target) {
        if (this.mana > 10) {
            target.takeDamage(getBaseAttack() * 2);
            this.mana -= 10;
            System.out.println(getName() + " делает магический выстрел в " + target.getName() + " на " + getBaseAttack() * 2 + " урона. Осталось маны: " + this.mana + ")");
            if (this.mana < 0) {
                this.mana = 0;
            }
        } else {
            target.takeDamage(getBaseAttack() / 2);
            this.mana += 5;
            if (this.mana >= maxMana) {
                this.mana = maxMana;
            }
            System.out.println(getName() + " слабо бьет посохом " + target.getName() + " на " + getBaseAttack() / 2 + " урона и восстанавливает 5 маны.");

        }
    }
    @Override
    public ActionType makeTurn(Hero target){
        if (canCast()) {
            castSpecialSkill(target);
            return ActionType.SPECIAL_SKILL;
        }
        if (needsRest()) {
            rest();
            return ActionType.REST;
        }
        attack(target);
        return ActionType.BASE_ATTACK;
    }
    @Override
    public boolean canCast() {
        return this.mana >= 25;
    }
    @Override
    public void castSpecialSkill(Hero target){
        if (canCast()){
            target.takeDamage(getBaseAttack()*3);
            this.mana -= 25;
        }else{
            System.out.println("не получилось запульнуть огненную глыбу лмао");
        }
    }
    @Override
    public boolean needsRest(){
        return (this.mana == 0 || (getHealth() < (getMaxHealth() * 0.3)));
    }
    @Override
    public void rest(){
        this.mana = maxMana;
        heal(getBaseAttack());
    }
    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }
    }


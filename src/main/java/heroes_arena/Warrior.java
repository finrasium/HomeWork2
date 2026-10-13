package heroes_arena;

public class Warrior extends Hero implements Castable,Restable{
    private final int armor;
    public Warrior(String name, int maxHealth, int baseAttack, int armor){
        super(name,maxHealth,baseAttack);
        if (armor < MIN_STAT_VALUE){
            throw new IllegalArgumentException("недействительное значение бропи");
        }
        this.armor = armor;
    }
    @Override
    public void takeDamage(int damage){
        int finalDamage = damage - armor;
        if (finalDamage <1){
            finalDamage = 1;
        }
        super.takeDamage(finalDamage);
    }
    @Override
    public void attack(Hero target){
        target.takeDamage(getBaseAttack());
        System.out.println(getName() + " нанес " + target.getName() + " " + getBaseAttack() + " урона физ. атакой");
    }
    public ActionType makeTurn(Hero target){
        if (canCast()){
            castSpecialSkill(target);
            return ActionType.SPECIAL_SKILL;
        }
        if (needsRest()){
            rest();
            return ActionType.REST;
        }
        attack(target);
        return (ActionType.BASE_ATTACK);
    }
    @Override
    public boolean canCast(){
        return(getHealth()*2<getMaxHealth());
    }
    @Override
    public void castSpecialSkill(Hero target){
        if (canCast()){
            int specialDamage = getBaseAttack()+(armor*2);
            target.takeDamage(specialDamage);
        }else{
            System.out.println("Воин попытался ударить щитом, но потерял равновесие!");
        }

    }
    @Override
    public boolean needsRest(){
        return(getHealth() <(getMaxHealth() * 0.15));
    }
    @Override
    public void rest(){
        heal(armor*2);
    }
    public int getArmor(){
        return armor;
    }
}

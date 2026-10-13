package heroes_arena;

public abstract class Hero {
    public static final int MIN_STAT_VALUE = 1;

    private final String name;
    private final int maxHealth;
    private final int BaseAttack;
    private int health;

    public Hero(String name,int maxHealth,int BaseAttack){
        if (maxHealth < MIN_STAT_VALUE || BaseAttack < MIN_STAT_VALUE || name == null || name.isBlank()){
            throw new IllegalArgumentException("Недействительные значения входных данных");
        }
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.BaseAttack = BaseAttack;
    }
    public String getName(){
        return name;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getBaseAttack(){
        return BaseAttack;
    }
    public int getHealth(){
        return health;
    }

    public abstract void attack(Hero target);
    public abstract ActionType makeTurn(Hero target);

    public void takeDamage(int damage){
        if (damage < 0){
            damage = 0;
        }
        this.health -=damage;
        if (this.health < 0) {
            this.health = 0;
        }
        if (this.health == 0) {
            System.out.println(this.name + " пал в бою!");
        }
    }
    public void heal(int amount){
        if (amount < 0){
            amount = 0;
        }
        this.health+=amount;
        if (this.health > maxHealth){
            this.health = maxHealth;
        }
    }
    public boolean isAlive(){
        return(this.health >0);
    }
}

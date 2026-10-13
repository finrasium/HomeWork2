package heroes_arena;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начинаем игру. Вводи персов");
        Hero fighter1 = characterCreator(scanner);
        Hero fighter2 = characterCreator(scanner);
        Arena arena = new Arena(fighter1,fighter2);
        arena.startTournament();
    }

    private static Hero characterCreator(Scanner scanner) {
        System.out.print("Выберите класс (1 - Воин, 2 - Маг): ");
        int characterType = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите имя персонажа: ");
        String name = scanner.nextLine();
        System.out.print("Введите максимальное здоровье: ");
        int maxHealth = scanner.nextInt();
        System.out.print("Введите базовую атаку: ");
        int baseAttack = scanner.nextInt();

        if (characterType == 1){
            System.out.print("Введите показатель брони: ");
            int armor = scanner.nextInt();
            return new Warrior(name,maxHealth,baseAttack,armor);
        }else{
            System.out.print("Введите максимальный запас маны: ");
            int maxMana = scanner.nextInt();
            return new Mage(name, maxHealth, baseAttack, maxMana);
        }
    }
}

package atm_HW;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BigDecimal primary_balance = new BigDecimal("10000.00");
        Account primary_account = new Account(12345, 999, primary_balance, BankType.AUM);

        Scanner scanner = new Scanner(System.in);

        System.out.println("WELCOME");

        System.out.println("Введите номер карты");
        int input_card_number = scanner.nextInt();
        System.out.println("Введите пинкод");
        int input_pin_code = scanner.nextInt();

        if (input_card_number != primary_account.card_number || input_pin_code != primary_account.pin_code) {
            System.out.println("Вводные данные не совпадают с эталоном");
            return;
        }

        System.out.println("Успешный вход в эталонный аккаунт");
        CashMachine atm = new CashMachine();

        System.out.println("Внесите деньги на счет"); //защита от дурака есть внутри CashMachine
        BigDecimal added_amount = scanner.nextBigDecimal();
        primary_account.balance = atm.transfer_money(primary_account.getBalance(), added_amount);
        System.out.println("Тест 1. Новый баланс: "+ primary_account.getBalance());

        System.out.println("Сколько снять со счета?");
        BigDecimal desired_amount = scanner.nextBigDecimal();
        primary_account.balance = atm.withdrawal(primary_account.getBalance(),desired_amount,primary_account.bankType);
        System.out.println("Тест 2. Новый баланс: "+ primary_account.getBalance());
    }
}
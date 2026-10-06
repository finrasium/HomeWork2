package atm_HW;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    public int card_number;
    public int pin_code;
    public BigDecimal balance;
    public BankType bankType;

    public Account(int card_number, int pin_code, BigDecimal balance, BankType bankType){
        if (card_number >= 10000 && card_number <= 99999){
            this.card_number=card_number;
        }else{
            System.out.println("Invalid card number (must be 5 digits long");
            this.card_number = 0;
        }

        if (pin_code >= 100 && pin_code <= 999){
            this.pin_code = pin_code;
        } else{
            System.out.println("Invalid pin code (must be 3 digits long");
            this.pin_code = 0;
        }

        if (balance == null){
            System.out.println("Could not get current balance");
            this.balance = BigDecimal.ZERO;

        }else{
            this.balance = balance.setScale(2,RoundingMode.HALF_UP);
            }

        if (bankType == null){
            this.bankType = BankType.NEO;
        } else{
            this.bankType = bankType;
        }
    }
    public int getCard_number(){
        return this.card_number;
    }
    public int getPin_code(){
        return this.pin_code;
    }
    public BigDecimal getBalance(){
        return this.balance;
    }
    public BankType getBankType() {
        return bankType;
    }

    @Override
    public String toString() {
        String text_to_return = bankType.getName_in_russian() +
                "Карта: " + card_number + ", Баланс: " + balance + " руб.";
        return text_to_return;
    }
}

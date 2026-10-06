package atm_HW;

import java.math.BigDecimal;
import java.math.RoundingMode;
public class CashMachine implements WithdrawalOperations,DepositOperations {

    @Override
    public BigDecimal transfer_money(BigDecimal balance,BigDecimal added_amount){
        if (added_amount == null || added_amount.compareTo(BigDecimal.ZERO) <=0){
            return balance;
        }
        BigDecimal new_balance = balance.add(added_amount);
        return new_balance.setScale(2,RoundingMode.HALF_UP);
    }
    @Override
    public BigDecimal withdrawal(BigDecimal balance, BigDecimal desired_amount, BankType bankType){
        BigDecimal with_commission = desired_amount.add(applyCommission(desired_amount,bankType));
        if (balance.compareTo(with_commission) < 0){
            System.out.println("Недостаточно средств");
            return balance;
        }
        return balance.subtract(with_commission).setScale(2,RoundingMode.HALF_UP);
    }
}

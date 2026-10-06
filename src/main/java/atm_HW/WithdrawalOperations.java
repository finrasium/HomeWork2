package atm_HW;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface WithdrawalOperations {
    BigDecimal withdrawal(BigDecimal balance,
                          BigDecimal desired_amount,
                          BankType bankType);
    default BigDecimal applyCommission (BigDecimal desired_amount,BankType bankType){
        if (desired_amount == null || bankType == null){
            return BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
        }
        BigDecimal interest = bankType.getInterest();
        BigDecimal with_comission = desired_amount.multiply(interest);
        return with_comission.setScale(2,RoundingMode.HALF_UP);
    }

}

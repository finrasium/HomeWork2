package atm_HW;
import java.math.BigDecimal;

public interface DepositOperations {
    BigDecimal transfer_money(BigDecimal balance,BigDecimal added_amount);
}

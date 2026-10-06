package atm_HW;
import java.math.BigDecimal;

public enum BankType {
    NEO("НеоКредит Банк", new BigDecimal("0.01")),
    AUM("Арум Финтех", new BigDecimal("0.02")),
    VTA("Вектор Альянс Банк", new BigDecimal("0.00"));

    public String name_in_russian;
    public BigDecimal interest;

    BankType(String name_in_russian,BigDecimal interest){
        this.name_in_russian = name_in_russian;
        this.interest = interest;
    }
    public String getName_in_russian(){
        return name_in_russian;
    }
    public BigDecimal getInterest(){
        return interest;
    }
}

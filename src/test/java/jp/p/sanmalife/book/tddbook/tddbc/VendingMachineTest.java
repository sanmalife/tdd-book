package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendingMachineTest {

    @Test
    public void 初期状態の時_投入金額の総計は0円() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals(0, vendingMachine.total);
    }

}

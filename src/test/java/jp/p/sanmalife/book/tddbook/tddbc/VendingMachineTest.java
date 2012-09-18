package jp.p.sanmalife.book.tddbook.tddbc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendingMachineTest {

    @Test
    public void 初期状態の時_投入金額の総計は0円() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals(0, vendingMachine.total);
    }

    @Test
    public void お金を1回投入すると投入金額の総計が増加する() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insert(10);
        assertEquals(10, vendingMachine.total);
    }

    @Test
    public void お金を2回投入すると投入金額の総計は2回の投入金額の合計になる() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        assertEquals(60, vendingMachine.total);
    }
}

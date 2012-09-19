package jp.p.sanmalife.book.tddbook.tddbc;

/**
 * ジュースクラス
 * 
 * @author kozai.takeshi
 * 
 */
public class Juice {

    /**
     * ジュースの名前
     */
    private String name;

    /**
     * ジュースの値段
     */
    private int price;

    /**
     * コンストラクタ
     * 
     * @param name
     *            ジュースの名前
     * @param price
     *            ジュースの値段
     */
    public Juice(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Juice)) {
            return false;
        }
        Juice juice = (Juice) obj;
        return name.equals(juice.name) && price == juice.price;
    }

}

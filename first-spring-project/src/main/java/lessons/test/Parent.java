package lessons.test;

/**
 * Created by SBT-Klyshov-DA on 13.06.2018.
 */
class Children extends Parent {
    public int j;
    private int i = 10;
    private String s = "str";
    @Override
    public void common() {
        System.out.println(s);
    }
    public static void main(String[] args) {
        Children children = new Children();
        System.out.println(children.j);
    }
}

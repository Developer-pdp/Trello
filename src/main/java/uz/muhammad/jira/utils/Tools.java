package uz.muhammad.jira.utils;

/**
 * @author Team <Developers>
 * @project Trello
 * @since 17/06/22   18:28   (Friday)
 */
public class Tools {

    public static void printMiddle(String text, int space, char with){
        int mid = (space-text.length())/2;
        for (int i = 0; i < mid; i++) {
            System.out.print(with);
        }
        Writer.print(text);
        for (int i = 0; i < mid; i++) {
            System.out.print(with);
        }
    }

    public static void printMiddle(String text, int space, char with, String color){
        int mid = (space-text.length())/2;
        for (int i = 0; i < mid; i++) {
            System.out.print(with);
        }
        Writer.print(text,color);
        for (int i = 0; i < mid; i++) {
            System.out.print(with);
        }
    }

}

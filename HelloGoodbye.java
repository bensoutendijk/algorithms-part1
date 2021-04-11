/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {


    public static void main(String[] args) {
        String firstname, lastname;

        firstname = args[0];
        lastname = args[1];

        System.out.println(String.format("Hello %s and %s.", firstname, lastname));
        System.out.println(String.format("Goodbye %s and %s.", lastname, firstname));
    }
}

package be.intecbrussel;

import java.util.LinkedHashSet;

public class App
{
    public static void main( String[] args )
    {

        // CREATING 3 USERS AND ADDING THEM TO A SET

        final var user1 = new User();
        user1.setUserId(1);
        user1.setEmail("john@doe.com");
        user1.setPassword("password");

        final var user2 = new User();
        user2.setUserId(2);
        user2.setEmail("mary@woe.com");
        user2.setPassword("password");

        final var user3 = new User();
        user3.setUserId(3);
        user3.setEmail("nikola@tesla.com");
        user3.setPassword("password");

        final var userList = new LinkedHashSet<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        // CREATING 3 MESSAGES AND ADDING THEM TO A SET

        final var message1 = new Message();
        message1.setMessageId(1);
        message1.setFromUserId(1);
        message1.setToUserId(2);
        message1.setMessageContent("Hello Mary!");

        final var message2 = new Message();
        message2.setMessageId(2);
        message2.setFromUserId(2);
        message2.setToUserId(1);
        message2.setMessageContent("Hello John!");

        final var message3 = new Message();
        message3.setMessageId(3);
        message3.setFromUserId(3);
        message3.setToUserId(1);
        message3.setMessageContent("Hello Nikola!");

        final var messageList = new LinkedHashSet<>();
        messageList.add(message1);
        messageList.add(message2);
        messageList.add(message3);

        // CREATING 3 IMAGES AND ADDING THEM TO A SET

        final var image1 = new Image();
        image1.setImageId(1);
        image1.setImageUrl("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        image1.setImageType("png");

        final var image2 = new Image();
        image2.setImageId(2);
        image2.setImageUrl("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");

        final var image3 = new Image();
        image3.setImageId(3);
        image3.setImageUrl("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");

        final var imageList = new LinkedHashSet<>();
        imageList.add(image1);
        imageList.add(image2);
        imageList.add(image3);


        // PRINTING ALL USERS

        System.out.println("\nPrinting all users:");
        for (var user : userList) {
            System.out.println(user);
        }

        // PRINTING ALL MESSAGES

        System.out.println("\nPrinting all messages:");
        for (var message : messageList) {
            System.out.println(message);
        }

        // PRINTING ALL IMAGES

        System.out.println("\nPrinting all images:");
        for (var image : imageList) {
            System.out.println(image);
        }

    }
}

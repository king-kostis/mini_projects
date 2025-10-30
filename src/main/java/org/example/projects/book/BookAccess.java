package org.example.projects.book;

public class BookAccess{
    public static void main(String[] args){
        Book book1 = new Book();
        book1.setTitle("Atomic Habits");
        book1.setAuthor("James Clear");
        book1.setPrice(30.00f);

        Book book2 = new Book();
        book2.setTitle("Sapiens");
        book2.setAuthor("Yuval Noah Harari");
        book2.setPrice(25.00f);

        System.out.println("The first book object is");
        System.out.println(book1);
        System.out.println("\nThe second book object is");
        System.out.println(book2);

        comparePrices(book1, book2);
    }

    public static void comparePrices(Book book1, Book book2){
        float book1Price = book1.getPrice();
        float book2Price = book2.getPrice();
        String priceComp;

        if(book1Price > book2Price){
            priceComp = "The price of " + book1Price + " is more than " + book2Price;
        } else if(book1Price == book2Price){
            priceComp = "The price of " + book1Price + " is as much as " + book2Price;
        } else {
            priceComp = "The price of " + book2Price + " is more than " + book1Price;
        }

        System.out.println(priceComp);
    }
}










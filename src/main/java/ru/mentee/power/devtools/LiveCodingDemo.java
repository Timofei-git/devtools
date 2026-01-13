package ru.mentee.power.devtools;

import java.util.Scanner;

public class LiveCodingDemo {
  public static void fizzBuzz(int n) {
    for (int i = 0; i < n; i++) {
      if (i % 5 == 0 && i % 3 == 0) {
        System.out.println(i + " = FizzBuzz");
      } else if (i % 3 == 0) {
        System.out.println(i + " = Fizz");
      } else if (i % 5 == 0) {
        System.out.println(i + " = Buzz");
      } else {
        System.out.println(i + " = is not Fizz or Buzz");
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the number: ");
    int num = scanner.nextInt();
    fizzBuzz(num);
  }
}

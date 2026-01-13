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

  public static String sumEven(int[] numbers) {
    int sum = 0;
    String result;
    if (numbers.length == 0 || numbers == null) {
      return "Enter at least 3 elements";
    } else {
      for (int i = 0; i < numbers.length; i++) {
        if (numbers[i] % 2 == 0) {
          sum += numbers[i];
        }
      }
      result = String.valueOf(sum);
    }
    return result;
  }

  public static String findMax(int[] numbers) {
    int max = numbers[0];
    if (numbers.length == 0 || numbers == null) {
      return "Enter at least 3 elements";
    } else {
      for (int i = 0; i < numbers.length; i++) {
        if (numbers[i] > max) {
          max = numbers[i];
        }
      }
    }
    return String.valueOf(max);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the number: ");
    int num = scanner.nextInt();
    fizzBuzz(num);
    int[] arr = new int[num];
    for (int i = 0; i < num; i++) {
      System.out.println("Enter the " + i + " number");
      arr[i] = scanner.nextInt();
    }
    String sum = sumEven(arr);
    System.out.println(sum);
    String max = sumEven(arr);
    System.out.println(max);
  }
}

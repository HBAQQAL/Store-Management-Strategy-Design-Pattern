import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

interface PaymentStrategy {
  void pay(float amount);
}

class CardStrategy implements PaymentStrategy {
  @Override
  public void pay(float amount) {
    System.out.println("paying by smart card");
  }
}

class PaypalStrategy implements PaymentStrategy {
  @Override
  public void pay(float amount) {
    System.out.println("paying by Paypal");
  }
}

class PaymentManager {
  private PaymentStrategy paymentStrategy;

  public void SetPaymentStrategy(PaymentStrategy strategy) {
    this.paymentStrategy = strategy;
  }

  public void pay(float amount) {
    System.out.println("paying ......");
    this.paymentStrategy.pay(amount);
  }
}

class Store {

  public static void main(String[] args)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException, ClassNotFoundException {
    HashMap<Integer, String> paymentOptions = new HashMap<>() {
      {
        put(1, "CardStrategy");
        put(2, "PaypalStrategy");
      }
    };
    Scanner scanner = new Scanner(System.in);
    int choice;
    PaymentManager paymentManager = new PaymentManager();
    System.out.println("you need to pay 4000$  select you payment method");
    choice = scanner.nextInt();
    PaymentStrategy strategy = (PaymentStrategy) Class.forName(paymentOptions.get(choice)).getDeclaredConstructor()
        .newInstance();
    paymentManager.SetPaymentStrategy(strategy);
    paymentManager.pay(3290023);
    scanner.close();

  }
}

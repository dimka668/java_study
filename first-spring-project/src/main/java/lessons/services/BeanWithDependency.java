package lessons.services;

public class BeanWithDependency {
    public BeanWithDependency() {
    }

    public BeanWithDependency(GreetingService greetingService) { greetingService.sayGreeting(); }

    public String printText() { return "Some text";}
}

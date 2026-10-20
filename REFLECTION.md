## Reflection 



## 1. Meaningful Names

While coding, I struggled to find a good name for the variable `selectedServiceNumber`. I initially named it `choicer`, but that name doesn't really tell anyone what the variable represents. I later renamed it to `selectedServiceNumber` because it stores the number of the service selected by the customer. The name might still be a little confusing if you haven't used the interface, where services are selected by entering a number, but it was the best name I could come up with. Some variables in my menu class still have generic names such as `choice`, which could potentially be improved. However, I'm happy with method names like `getAvailableSlots()` and `getBookedSlots()`, since they clearly describe what the methods do (intention revealing names)

## 2. Functions

While developing my application, I tried to split the code into smaller methods instead of having everything in one large method. For example, I created the `selectService()` method because I needed the same functionality in multiple places, such as when viewing available times and booking an appointment. Doing this helped me avoid repeating code and made other methods easier to read. However, I still have some large methods, especially those containing `switch` statements with multiple `case` branches and a lot of `System.out.println()` calls. These methods could probably be improved by separating the menu output from the logic that handles user input. This would also follow the Clean Code principle that a function should do one thing.


## 3. Comments

I haven't really used many comments in my application because I first tried to make the code understandable through descriptive method and variable names instead. But then i decied to add comments with the help of co-pilot. In Clean Code Robert explains that comments shouldn't be used to explain code that could be made clearer by improving the code itself. I agree to some extent but as a beginner, comments can make it much easier for me to understand code even if the name of the method is good. I've always been bad at commenting my code because when I'm in my "flow", I just want to get my code written as quickly as possible. However, I think I could improve by adding comments when the reasoning behind a piece of code isn't obvious.

## 4. File-Length

In Clean Code Martin talks about file length and explains that smaller files are generally easier to understand and maintain. I totally agree, because sometimes I get lost in my own files when they become too long. In the book and in one of the lectures, around 200 lines is suggested as a good target for file length, but my `SalonMenu.java` file has around 400 lines of code. I think that's a bit too much, even though Martin mentions 500 lines as an approximate upper limit rather than a strict rule. Looking back, I could probably have split the customer and administrator menus into separate classes to reduce the file length. In the future, I'm going to try to keep my files closer to 200 lines when it makes sense. But at the same time i dont wanna split the file into smaller pieces just to make it smaller if it makes the code more complicated and harder to understand.

## 5. Objects and Data Structures

In Clean code Martin talks about Data Abstraction and how objects should hide their internal data instead of letting other classes access everything directly. I think i have done this pretty well in some parts of my booking module for example my `Slot` class has a `book()` method that handles the booking instead of letting other classes change it directly. This is an example of encapsulation because the `Slot` class handles its own booking logic. But in my `SalonService` class I use `slot.getBooking() == null` to check if a slot is available which means that the class needs to know how the booking is stored. Looking back I think it would have been better to create an `isAvailable()` method in the `Slot` class instead. That way I would be using Data Abstraction better because other classes wouldn't need to know that `null` means the slot is available.

## 6. Error Handling

In clean code martin talks about using exceptions instead of codes when something goes wrong in the code. I have used IllegalArgumentException in my SalonService class when someone tries to acess a schedule that doesnt exist for example. But in my SalonMenu i used try/catch to show an error message instead of letting the application crash. In one of the lectreus we also talked about creating custom exception classes using inhertiance. I didnt do that in my application i think custom excpetions could have been useful if I wanted to handle diffrent errors in diffrent ways. But for my application i think the built in expections are good enough since most of my errors are handled by showing the message to the user.

## 7. Boundaries

In clean code martin talks about boundaries and how we should be careful when using third party code. In my app im using another students SVG module to create booking stats. Instead of using the BartChart class everywhere in my application i created a seperate class called BookingStatisticsExporter that handles the SVG module. I think this was a pretty good decision because if something changes in the SVG module i would just have to change the code in that class. In the book Martin talks about learning tests which could help us understand how the third party module works. I didnt write any tests for the module but looking back at it now it would have been useful for sure.

## 8. Unit Tests

In clean code martin talks about the F.I.R.S.T principles and how unit tests should be Fast independant, repeatable, self validating and timely. I have written JUnit tests for my booking module, for example testing my Slot class to make sure bookings work and that you cant book the same slot twice. I think my tests follow the independent principle pretty well because each test creates its own object and doesnt depend on other tests running first. To be honest i didnt have the F.I.R.S.T principle in mind when i wrote the tests, i was just trying to make sure my methods worked correctly. In the future i am going to try to write my tests earlier instead of doing it after all my code is done.

## 9. Classes

In clean code martin talks about the single responsibility principle (SRP) and how a class should only have one reason to change. I think i have followed this pretty well in some parts of my application. For example i seperated my SalonService class from Salonmenu so the booking logic isnt mixed with the menu code. But my SalonMenu class is still pretty big and handles both the customer and admin meny. Looking back i think i could have split it into 2 diffrent classes like customerMenu and AdminMenu to make the responsibilites moer clear and the code easier to read and mantain. Martin also talks about cohesion and how the methods in a class should belong together, to be honest i think seperating the menus could improve the cohesion.

## 10. Systems
In clean code Martin talks about seperating construction from use and how we should try to keep the code that creates objects seperate from the code that uses them. I think i have done this prett ywell in my App class where i create my SalonService and SalonMenu objects before starting the menu. I also pass my Salon Service into SalonMenu through its constructor(Dependency Injection). Again, i didnt think about this when i wrote my code, i mostly wanted to avoid having everything inside my main() method. However i still create some objects directly inside my menu code like BookingStatisticsExporter so its not completely seperated. I think this is something i could improve ifm y application becomes bigger and more complicated.

## 11. Changes to my module
I added an isAvailable() method to my Slot class so other classes can check if a slot is available without needing to know that null means the slot is empty. I also kept the old getBooking() method to maintain compatibility.

When I added the isAvailable() method, I also added two new JUnit tests to check that a slot is available when created and unavailable after booking it.

## 12. Using Another Students Module

It was really fun to use someone else's module. It felt more "real", and I can imagine that's what it's gonna be like when I work as a developer in the future. I always thought that using someone else's code would be really difficult, but the person who made the module did a really good job, and the README helped me out a lot. It was actually easier than I expected to understand how to use the module and integrate it into my own application.

## 13. AI USE

Ive used Chatgpt and Copilot while working on this project. i used Copilot to help me write comments in my code. I also used AI to fix my english and improve the structure of my README file to make it look better and easier to understand. In some of my reflections i used Chagpt to correct my English and grammar and also to get some ideas on what i could write about based on my own code. I also used ChatGPT to help me write repetitive code. Ive used Chatgpt to get ideas on how to write better error handling and to understand how exceptions work. AI also helped me set up some configuration files like settings.gradle and build.gradle. Chatgpt also helped me structure and the test_Report file.
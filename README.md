# VirtualPet

## Overview

This program utilizes the Model-View-Controller design to run a Tamagotchi-style virtual pet game that a user can interact with. The program also implements the Strategy pattern to dynamically change the virtual pet's behaviour over time by changing the virtual pet's life stage, which changes how the levels of its needs increase upon user interaction and decrease over time. The user must interact with the virtual pet to maintain its health and happiness, leading to its growth. 

## List of Features

Pet Creation 
- Customize the virtual pet by choosing its name.

- Interaction
Engage with the virtual pet through four programmed actions: feeding, playing, cleaning, and putting it to sleep.

- Health and Mood Maintenance
Maintain the virtual pet's health and happiness by fulfilling its needs through interacting with it.
Failure to interact with the pet results in mood changes and deteriorating health, ultimately leading in death if neglected. 

- Pet Growth
Witness different life stages, from child, adult, to senior.

## How to Run

From a JAR file: 
- Download VirtualPet.jar 
- Ensure you are in the correct directory and run in terminal:
```bash
java -jar VirtualPet.jar
```

## How to Use the Program

This program runs a virtual pet. Upon startup, the user is prompted to enter a name for their pet, which must be a valid String (contains characters, must not be only whitespace). 

The user can then use the interaction buttons to interact with the pet using four actions: feed, play, clean, and put to sleep. Interacting with the pet increases its needs, keeping it healthy and happy. 

The pet's needs will decrease over time so the user must continue to interact with their pet to maintain its health and happiness. 

If a user successfully keeps their pet healthy and happy, then after some time, the pet will grow up into a new life stage. There are three life stages: Child, Adult, and Senior. 

If a user neglects their pet, the pet becomes sick and will eventually die if any of its needs hit zero. 

## Design Changes

From version 1: 
- Specific intervals for each pet life stage was removed and replaced with one interval for all life stages. This was done to simplify the code and handle timed events better, which included playing interaction gifs and increasing need levels, which were happening at the same time as decreases. 
- Increase and decrease rates were adjusted for smoother gameplay and ensuring the user can be successful in maintaining their virtual pet. 
- Changed conditions to grow up from health being 100 to health state being healthy to ensure when playing, the pet can grow. With the time delays in interactions, it was difficult to reach 100 health.
- Added getAge() to VirtualPet interface / changed getAge() from private to public in VirtualPetImpl class to allow for user to see the pet's age in the graphical user interface, allowing for better tracking of when life stages should change.
- Implementation of a Controller and View were added utilizing Java Swing. 

## Assumptions

JDK version 11 or greater required. 

Dependencies: 
- Java Swing: This program relies on the Java Swing library for building the graphical user interface, including the use of JFrame.
- Java Standard Library
    - Timer: Utilized for scheduling events.
    - TimerTask: Utilized for defining tasks to be scheduled by Timer.
    - HashMap: Utilized for efficient data storage and retrieval.
    - ArrayList: Utilized for dynamic array-based storage.
    - Collections: Utilized for its utility methods related to collections, such as addAll(). 

## Limitations

Usage of Java Swing resulted in choppy animations and images may flicker from time to time. Additionally, the appearance of graphical user interface varies among different operating systems, but the overall functionality of the program is unaffected. 

## Citations

1. Oracle. (2014). HashMap (Java Platform SE 8). Retrieved from https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
2. Oracle. (n.d.). How to Use BorderLayout. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
3. Oracle. (n.d.). How to Use Buttons, Check Boxes, and Radio Buttons. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
4. Oracle. (n.d.). How to Make Dialogs. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
5. Oracle. (n.d.). How to Use GridLayout. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
6. Oracle. (n.d.). How to Use Labels. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/components/label.html\
7. Oracle. (n.d.). How to Use Progress Bars. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/components/progress.html
8. Oracle. (2014). Timer (Java Platform SE 8). Retrieved from https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html

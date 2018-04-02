# e-commerce-scs
Experimental implementation of a service based e-commerce implementation. Sub-Domains can be implemented using a small monolith approach, a micro-service based approach or something in between.

# Basic architecture
Services are implemented either by using a simple crud approach. This basically means that there are some kind of endpoints, mainly rest or web mvc, that directly do some work. The more domain logic focused approach is a Domain Driven Design (DDD) based implementation. In this case business logic is strictly separated from other things like api endpoints, logging or transaction handling.

# Integration

## Messaging
For basic communication kafka between services is used instead of calling api endpoints of each other to reduce coupling between services. The main goal is that each service is as independent as possible. If one service is down all other services should still be able to fulfill their use-cases.

To be able to visualize how integration behaves at runtime a graph based visualization will be created that show how eventual consistency behaves and the system evolves. Again, this will be a service and maybe some existing tooling can be leveraged like neo4j.

# Cross-cutting requirements

Creating multiple application that behaves like one single application is challenging. Therefore there need to be some guidelines how all applications handle certain requirements.

## Navigation
To have one navigation the navigation is included using edge site includes, I-Frames or some frontend based implementation. The definition can be either created centrally and could be fetched at build time or an endpoint can be called at runtime. The interesting part is that not every application needs a navigation like the checkout. This comes natural by requirements to reduce the distraction to allow a clear and simple checkout process.

## Footer and other static elements
Footer and similar static parts of the page need to be defined in one place. Either an I-Frame based approach is used or for example the footer link configuration need to be stored somewhere. This place needs to be accessible from all applications. Footer elements probably change a few times. Therefore some build time process could fetch them and could be used to create the area. In general the target is to have as less as possible runtime dependencies to other services.

## Localization
There will be a central localization definition location that is accessible at build-time. How services implement localization is not predefined.

## Basket
Some of the applications needs some basket handling. For example, there will be context pages and product pages. Both applications should be able to show the current state of the basket. Probably a frontend based implementation is a good way because it allows caching pages nicely. The basket page itself will be part of the product catalog application. Products can be very special. Also the basket page can be special to represent the product properties clearly.
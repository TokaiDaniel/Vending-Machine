# Vending Machine

## Story

We want to create a vending machine API for a manufacturing company. The software handles the transactions for the Vending machine.
The machine is capable of collecting money, selling products, refunding, keeps track of the inventory, gives a consumption report and allows reset operation for the machine supplier.

## Tasks

1. The vending machine accepts coins of 1, 5, 10 and 25 cents.
2. Allow user to select products Coke (25), Pepsi (35), Soda (45)
3. Allow users to take a refund by canceling the request.
4. Return the selected product and remaining change if there is any.
5. Allow reset operation for vending machine supplier.
6. Keep track of the current inventory.
7. Report on consumption by product.


## General requirements

1. Create model classes containing all business logic for the above APIs.
2. Create a test suite that operates the model classes with test data to cover cases of the specification (ensure sufficient test coverage).
3. Use Clean Code principles for production and test code.
4. Use Java 8 streams where possible
5. The code should be easy to read, understand and ready for modification by other developers if and when business logic changes.
6. Use the TDD principle.
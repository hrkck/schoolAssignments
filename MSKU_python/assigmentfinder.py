def find_my_assignment():
    name = input("Enter Your Sirname: \n")
    number = input("Enter your school number (a nine(9) number): \n")

    # Checking if the number is entered correct:
    while len(number) != 9:
        number = input(
            "Students of MSKU cannot have a school number like this. Please enter a nine(9) digit number:\n")
        x = 1
        while x == 1:
            try:
                int(number)
            except ValueError:
                number = input(
                    "This is not a number. Please enter a nine(9) digit number:\n")
            else:
                x = 2

    # Manipulating data to decide the corresponding assignment:
    test1 = True if (len(name) % 2 == 0) else False
    test2 = True if (int(number) % 2 == 0) else False

    if test1 and test2:
        print("\nYou're Responsible with 1b and 2b")
    if test1 and not test2:
        print("\nYou're Responsible with 1b and 2a")
    if not test1 and test2:
        print("\nYou're Responsible with 1a and 2b")
    if not test1 and not test2:
        print("\nYou're Responsible with 1a and 2a")


find_my_assignment()

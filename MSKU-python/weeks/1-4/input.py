
name = input("What is your name: ")
print("Hello " + name)

response = input("What is your radius? ")
r = float(response)
area = 3.14159 * r**2
# print("The area is " + area)     # error since area is float
print("The area is " + str(area))
print("The area is ", area)
print("r=", r)
print("r=" + str(r))




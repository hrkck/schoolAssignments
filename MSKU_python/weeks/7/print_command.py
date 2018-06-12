
num1 = int(input("Number 1: "))
num2 = int(input("Number 2: "))

# print("Sum of .. and .. is ... Product of .. and .. is ..")
print("Sum of " + str(num1) + " and " + str(num2) + " is " +
      str(num1+num2) + ". Product of " + str(num1) + " and " +
      str(num2) + " is " + str(num1*num2))
      
# python2

print("Sum of %d and %d is %d. Division of %d and %d is %d" 
       % (num1,num2,num1+num2,num1,num2,num1 / num2))  
       
print("Sum of %d and %d is %d. Division of %d and %d is %f" 
       % (num1,num2,num1+num2,num1,num2,num1 / num2))           
       
print("Sum of %d and %d is %d. Division of %d and %d is %.2f" 
       % (num1,num2,num1+num2,num1,num2,num1 / num2))  
       
# python
print("PYTHON 3 OUTPUT")
print("Sum of {0} and {1} is {2}. Division of {3} and {4} is {5}"
         .format(num1,num2,num1+num2,num1,num2,num1 / num2) ) 
         
print("Sum of {0} and {1} is {2}. Division of {0} and {1} is {3:.4}"
         .format(num1,num2,num1+num2,num1 / num2) )         
                          
       
       

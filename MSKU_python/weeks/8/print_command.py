
nums = [7,3,5,9,1,12]

for num in nums:
   print("Current number is {0}".format(num))
                          
for num in nums:
   print(num, end=", ") 
   
print("\n")

a = 5
b = 7
c = a * b
print(a,b,c,b-a)         
print(a,b,c,b-a,sep=", ") 
print(a,b,c,b-a,", ") 

for num in [1,5,9,256,257,10000, 13, 25]:
   print(str(num) +"'s square is " + str(num ** 0.5)) 

print("Using format")
for num in [1,5,9,256,257,10000, 13, 25]:
   print("{0}'s square is {1}".format(num,num ** 0.5))        
   
print("Using format with additional criteria")
for num in [1,5,9,256,257,10000, 13, 25]:
   print("{0:5d}'s square is {1:.2f}".format(num,num ** 0.5))           
   
   
   
   
   
   
   
   
   
   
   
   
   

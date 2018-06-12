
print("A")
print("B")
i = 14
while i<=10:
   # print("C")
   # print("D")
   if i % 2 == 0:
     print(i,"is even")   
   else: 
     print(i,"is odd")            
   i = i + 1
print("E")
print("F")   
k = 10   
print("Testing for loop")
print("A")   
for k in range(7):
  print("k =", k)
  # print("Hello")
  if k % 2 == 0:
      for j in range(5):
        print("    j =", j)
    # print("World")
print("Z")  
print("Two values for range")
for k in range(125,131):
  print("k =", k)
print("Three values for range")  
for k in range(80,98,3):
  print("k =", k)
print("Counting backwards")   
for k in range(98,80,-1):
  print("k =", k)
  
print("For loop is actually used for iterating over values")

names = ["ali", "kaya", "izzet", "oya", "can"]

for n in names:
   print("Hello",n)     
   
for num in [34,12,45,21,13]:
   print("num =",num)   
   
for i in [1,2,3,4,5,6,7,8,9,10]:
   print("i =",i)  
   
nums = [5,10,50,15]

total = 0
for n in nums:
  total = total + n
print("total = ", total)
print("average = ", total / len(nums))  

nums = [20,34,12,45,21,12,67, 67, 67,13,75,21,34,89,12,67,78]
m = 0
# nums = [-4,-7,-9,-1]
# m = nums[0]
for num in nums:
  print("Iteration begins", "m =", m, "num =", num)
  if (num > m):
    m = num
  print("Iteration ends  ", "m =", m, "num =", num)
print("Maximum of numbers", nums, "is", m)    
      




















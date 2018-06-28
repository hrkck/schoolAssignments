
print("Starting loop")
for num in range(5,15):
   print("Iteration started...") 
   if num % 2 == 0:
     print(num, "is even")
   print("Iteration completed...") 
print("Loop finished")     

for num in range(5,15):
   print("C1", end="->") 
   if num % 2 == 0:
     print("C2", end="->")
   else:
     print("C3", end="->")
   print("C4")    

print("Save (S)")
print("Load (L)")
print("Quit (Q)")
response = input("Enter your choice: ")
if response == "s" or response == "S":
   print("Saving your game...")
else: 
   if response == "l" or response == "L":
      print("Loading your game...")
   else:
      if response == "q" or response == "Q":
         print("Bye bye...")
      else:
         print("Unknown choice")   
            
print("Save (S)")
print("Load (L)")
print("Quit (Q)")
response = input("Enter your choice: ")
if response == "s" or response == "S":
   print("Saving your game...")
elif response == "l" or response == "L":
   print("Loading your game...")
elif response == "q" or response == "Q":
   print("Bye bye...")
else:
   print("Unknown choice")   
   
a = 6
b = 10

if a>b:
   if a % 2 == 0:
     print("A1")
   elif b < 30:
     print("A2")
   else:  
     print("A3")
elif a+b < 20:
   if b % 2 == 0:
     print("A4")        
   else:
     print("A5")        
else:
  print("A6")     
     
   
   
   
   
   

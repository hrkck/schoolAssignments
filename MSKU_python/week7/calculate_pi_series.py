
total = 0
term = 1
i = 1
while term > 0.0000001:
  if i%2 == 1:    
     total = total + term
  else:   
     total = total - term
  i = i + 1
  term = 1 / (2*i - 1)
  if i%10000 == 0:
      print(" PI = ", total * 4)
  
print("I used " + str(i) + " iterations (run of loop)")  
  

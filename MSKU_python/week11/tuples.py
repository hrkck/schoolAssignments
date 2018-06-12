
x = (2,3,8)
y = ("ali", 5, 2.34, ["505-1234567", "252-1234567"])

print(y[0])
(name, grade, gpa)  = y
print(gpa)
#  y[0] = "veli"    # error

people = [ ("Ali", 25), ("Veli", 28), ("Oya", 23) ]

for person in people:
  print("{0}'s age is {1}".format(person[0], person[1]))
  
for (name, age) in people:  
  print("{0}'s age is {1}".format(name, age))
  
words = "Hello all. It is a nice day".split()
for word in words:
  print(word.upper())  
  
for i in range(len(words)):
  if i%2 == 1: print(i, words[i].upper())  
  
for (i,word) in enumerate(words):
  if i%2 == 1: print(i, word.upper())
  
  
  
  
  


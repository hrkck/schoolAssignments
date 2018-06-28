
# count the letters in a string and print the results

# sentence = "today i'm feeling lucky."
sentence = "fenerbahce galatasaray besiktas"


a_count = 0
b_count = 0
c_count = 0
# ...
z_count = 0

for letter in sentence:
  if (letter == "a"): a_count += 1
  elif (letter == "b"): b_count += 1
  elif (letter == "c"): c_count += 1
  # ...
  elif (letter == "z"): z_count += 1
  
letter_counts = [0,0,0,0, 0] # 27 zeros  
for letter in sentence:
  if (letter == "a"): letter_counts[0] += 1
  elif (letter == "b"): letter_counts[1] += 1
  elif (letter == "c"): letter_counts[2] += 1
  # ...
  elif (letter == "z"): letter_counts[27] += 1

"""  
letters_found = []
letters_count = []

for letter in sentence:
  if letter in letters_found:
     i = letters_found.index(letter)
     letters_count[i] += 1
  else:
     letters_found.append(letter)
     letters_count.append(1)
  print(letters_found)
  print(letters_count)
  print("----------------------------------")   
"""
          
counter = {}
for letter in sentence:
#  if counter.get(letter) == None:
  if not letter in counter: 
     counter[letter] = 1
  else:
     counter[letter] += 1   # counter[letter] = count[letter] + 1     
  # print(counter)
  # print("----------------------------------")  

counter = {}
for letter in sentence:
  counter[letter] = counter.get(letter,0) + 1

  
for (letter,cnt)  in counter.items():   
   print("{} found {} times.".format(letter,cnt))


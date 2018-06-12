
# https://docs.python.org/3/library/stdtypes.html?highlight=list#str

paragraph = """Please contact help@youtube.com for your questions. 
This is an invalid email: pembeci@google.
This is an invalid email: pembeci@.google
This is an invalid email: pembeci@google...com.tr
This is an invalid email: izzet.pembeci@google
This is a valid email: pembeci@google.com.tr
This is a valid email: izzet.pembeci@google.com.tr
This is a valid email: pembeci1902@google.com.tr
This is a valid email: pemb*eci1902@google.com.tr


"""

words = paragraph.split()
for word in words:
#   if "@" in word and "." in word:
#     print("Email found:", word)
    if "@" in word:
      l = word.split("@")
      username = l[0]
      domain = l[1]
      # if "." in domain:
      # if "." in domain.strip("."):
      domain_words = domain.split(".")
      if "" not in domain_words and len(domain_words) > 1:
         valid_username = True
         for char in username:
            if not(char.isdigit() or char.isalpha() or char in "._-"):
               valid_username = False
               break
         if valid_username:      
             print("Email found:", word)
             
username1 = "Çağlar"
username2 = "Caglar"
print(len(username1))
print(username1.lower())
print(username2.lower())
print(username1.upper())
print(username2.upper())

username3 = "Çağlar Akıncıoğlu"
print(username3.replace("ı", "i"))
print(username3)
username3 = username3.replace("ı", "i")
print(username3)
         
      
      
      
      
      
      
      
      

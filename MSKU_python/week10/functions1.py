def make_turkish(a_string):
   invalid = ["ğ", "ü", "ç", "ı", "ö", "ş"]
   valid = ["g", "u", "c", "i", "o", "s"]
   for i in range(len(invalid)):
      a_string = a_string.replace(invalid[i], valid[i])
   return a_string
    
print(make_turkish("ulaş"))
corrected = make_turkish("ali ulaş özgür kişisel")
print(make_turkish())
print(corrected)

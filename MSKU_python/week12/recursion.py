import time

def f1():
  print("I am printed from f()")
  time.sleep(0.1)
  f1()
  
# f1()  

def fact(n):
  if n < 0:
     print("fact defined for non-negative integers")
  elif n==0:
     return 1
  else:
     result = n * fact(n-1)   
     return result

print(fact(54))  

def listsum(numList):
   if len(numList) == 1:
        return numList[0]
   else:
        first = numList[0]
        rest = numList[1:]
        result = first + listsum(rest)
        return result 

print(listsum([7,3,2,9]))

def r(s):
  if s == "":   # base case
    return ""
  else:
    return r(s[1:]) + s[0]   

print(r("cimbom"))

def fl(a_list, n):
   if a_list == []:  # base case
        return []
   else:             # recursive part
        first = a_list[0]
        rest = a_list[1:]
        if (n == first):
           return fl(rest, n)
        else:   
           return [first] + fl(rest, n)

print(fl([2,4,2,6,12,2,5,7,2,9,2,2,6,7,8], 2))

def toStr(n,base):
   convertString = "0123456789ABCDEF"
   if n < base:
      return convertString[n]
   else:
      return toStr(n//base,base) + convertString[n%base]

print(toStr(1453,16))






  



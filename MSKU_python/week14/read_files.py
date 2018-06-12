
def method1():
    fileref = open("data.txt", "r")

    for line in fileref:
        line = line.strip()
        # print(line)
        values = line.split()
        print('QB ', values[0], values[1], 'had a rating of ', values[10] )

    fileref.readlines()
    fileref.close()

def method2():
    fileref = open("data.txt", "r")

    lines = fileref.readlines()
    for line in lines:
        line = line.strip()
        # print(line)
        values = line.split()
        print('QB ', values[0], values[1], 'had a rating of ', values[10] )

    fileref.close()

def method3():
    fileref = open("data.txt", "r")

    line = fileref.readline()
    while (line != ""):
        line = line.strip()
        # print(line)
        values = line.split()
        print('QB ', values[0], values[1], 'had a rating of ', values[10] )
        line = fileref.readline()

    fileref.close()

print("In read_files")
method3()
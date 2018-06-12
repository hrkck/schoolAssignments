
def method1():
    fileref = open("data2.txt", "r")

    for (i,line) in enumerate(fileref):
        if (i<=2) : continue
        line = line.strip()
        # print(line)
        values = line.split()
        print('QB ', values[0], values[1], 'had a rating of ', values[10] )

    fileref.readlines()
    fileref.close()

def method2():
    fileref = open("data2.txt", "r")

    lines = fileref.readlines()
    for line in lines[3:]:
        line = line.strip()
        # print(line)
        values = line.split()
        print('QB ', values[0], values[1], 'had a rating of ', values[10] )

    fileref.close()

def method3():
    fileref = open("data2.txt", "r")
    outfile = open("qbnames.txt", "w")
    # to append to the end of the file (keeping older lines) use "a" as mode
    # outfile = open("qbnames.txt", "a")
    # skip first three lines
    for i in range(3): fileref.readline()

    line = fileref.readline()
    while (line != ""):
        line = line.strip()
        # print(line)
        values = line.split()
        print('QB ', values[0], values[1], 'had a rating of ', values[10] )
        line = fileref.readline()
        dataline = values[1] + ';    ' + values[0]
        outfile.write(dataline + "\n")

    fileref.close()

method3()

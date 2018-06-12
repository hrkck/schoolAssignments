# This program reverses any given sentence.

sentence = input("Write a Sentence to reverse:",)
sentence_as_list = sentence.split()
print(sentence_as_list)
#
reversed_sentence_as_list = []


def reverser():

    reversed_sentence_as_list.append(sentence_as_list[-1])
    del sentence_as_list[-1]
    if len(sentence_as_list) == 0:
        return
    else:
        reverser()

reverser()

end = " ".join(reversed_sentence_as_list)
print(end)

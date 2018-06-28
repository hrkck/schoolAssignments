# Here go all the memory I/O functions (so also savers) and their helpers

import datetime
import os

import nibabel as nib
import numpy as np
from functools import partial


def load_images(start, stop, test, compression):
    """
    Load and return images from storage with a given compression.
    :param start: number of first image to be loaded
    :param stop: number of first image that will NOT be loaded
    :param test: If true, load test data; if false load train data
    :param compression: Compression ratio for faster loads
    :return: Images as np.array
    """
    images = []
    which = "test" if test else "train"
    for i in range(start, stop):
        # "set_which/which_i+1.nii" -> this should be fine.
        rel_path = "../data/set_" + which + "/" + which + "_" + str((i + 1)) + ".nii"
        image = nib.load(get_path(rel_path))  # load the input
        image_data = image.get_data()  # extract the relevant input from it.
        image_data = compress(image_data, compression)
        images.append(image_data)  # add image to its array.
        print "Data loaded:", i + 1 - start, "/", (stop - start)
    return np.array(images)


def load_all_images(test, compression):
    """
    Load and return ALL images from storage with a given compression.
    :param test: Load 'Train' or 'Test' data
    :param compression: Compression ratio for faster loads
    :return: Images as np.array
    """
    start = 0
    if test:
        stop = 138
    else:
        stop = 278
    return load_images(start, stop, test, compression)


def compress(image, compression):
    """
    Compress and return given image by selecting pixels with regular intervals of width 'compression'.
    :param image: 4D image
    :param compression: compression ratio
    :return: image
    """
    new_image = np.empty(
        shape=(image.shape[0] / compression, image.shape[1] / compression, image.shape[2] / compression, 1))
    for x in range(0, len(new_image)):
        for y in range(0, len(new_image[0])):
            for z in range(0, len(new_image[0][0])):
                new_image[x][y][z][0] = image[x * compression][y * compression][z * compression][0]
    return new_image


def load_feature(f):
    """
    Load data from given 'feature_function' from memory.
    :param feature_function: the function to loaded
    :return: features corresponding to the given 'feature_function'
    """
    rel_path = "output/saved_features/" + (
        (f.func.__name__ + "_" + "_".join(
            [(str(key) + "_" + (f.keywords[key].__name__ if callable(f.keywords[key]) else str(f.keywords[key]))) for
             key in f.keywords.keys()])) if
        type(f) is partial else f.__name__) + ".txt"
    features = np.loadtxt(get_path(rel_path))
    if len(features.shape) == 1:
        features = np.array([[feature] for feature in features])  # packing of features if necessary
    return features


def save_feature(f, data):
    """
    Save given 'data' for given 'feature_function' in memory.
    :param feature_function: the function to be saved
    :param data: feature value corresponding to the given 'feature_function'
    """
    print "check"
    rel_path = "output/saved_features/" + (
        (f.func.__name__ + "_" + "_".join(
            [(str(key) + "_" + (f.keywords[key].__name__ if callable(f.keywords[key]) else str(f.keywords[key]))) for
             key in f.keywords.keys()])) if
        type(f) is partial else f.__name__) + ".txt"
    np.savetxt(get_path(rel_path), data)


def load_ages():
    """
    Load train ages from memory.
    :return: ages
    """
    return np.genfromtxt(get_path("../data/targets.csv"), delimiter=',')


def save_ages(ages, model_name, features_names):
    """
    Save test ages that were predicted using the specified model and features.
    :param ages: array of ages
    :param model_name: name (string) of model
    :param features_names: names of features concatenated as one string
    """
    target = open(get_path("../final_sub.csv"),"w")

    content = 'ID,Prediction\n'
    for i in range(len(ages)):
        content += str(i + 1) + "," + str(ages[i]) + "\n"

    target.write(content)
    target.close()


def save_results(model_name, features_names, error):
    """
    Save experiments results in one txt document that can be used as reference.
    :param model_name: name (string) of model
    :param features_names: names of features concatenated as one string
    :param error: obtained error in the experiment
    """
    if exist("output/experiments_results.csv"):
        content = ""
    else:
        content = "Model name,Features names,Error,Date Time\n"
    target = open(get_path("output/experiments_results.csv"), "a")
    content += (
        model_name + "," + features_names + "," + str(error) + "," + str(datetime.datetime.now()) + "\n")
    target.write(content)
    target.close()


def get_path(rel_path):
    """
    Get absolute path of the relative path (relative with respect to the root of the project folder)
    :param rel_path: relative path
    :return: absolute path
    """
    norm_path = os.path.normpath("../" + rel_path)
    return os.path.abspath(os.path.join(os.path.dirname(__file__), norm_path))


def exist(rel_path):
    """
    Check if file with relative path exists.(relative with respect to the root of the project folder)
    :param rel_path: relative path
    :return: true if it exists, false if it doesn't
    """
    try:
        target = open(get_path(rel_path))
        target.close()
        return True
    except IOError:
        return False


def exists_feature(f):
    """
    Check if give feature exists in memory (i.e. is already calculated before and stored in memory)
    :param feature_function: function of the feature to check
    :return: true if it exists, false if it doesn't
    """
    rel_path = "output/saved_features/" + \
               ((f.func.__name__ + "_" + "_".join(
                   [(str(key) + "_" + (f.keywords[key].__name__ if callable(f.keywords[key]) else str(f.keywords[key])))
                    for key in f.keywords.keys()])) if
                type(f) is partial else f.__name__) + ".txt"
    return exist(rel_path)

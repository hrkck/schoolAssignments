# Required Machine Learning tools to make experiments among different features and models
# Execute this script in 'wrapper.py' to have your results saved in 'output/experiments_results.txt'
# For final submissions, use script 'core.py'

from features import *
from loaders import load_ages, save_results, get_path
from functools import partial

import itertools
import os
import math

from sklearn import linear_model
from sklearn.metrics import log_loss
from sklearn.model_selection import KFold


def execute(images):
    """
    Execute the test with given images.
    :param images: array of 4D images for feature extraction
    """
    # greedy(linear_model.LogisticRegression(penalty='l1'),
    #        [partial(sobel, feature_function=border_smooth_sample), partial(sobel, feature_function=fourier),
    #         partial(sobel, feature_function=PCA_transform), partial(sobel, feature_function=grey_white_ratio),
    #         partial(sobel, feature_function=variance_feature), partial(sobel, feature_function=mean_feature),
    #         partial(sobel, feature_function=polyfit), partial(sobel, feature_function=feature_on_cerebrum),
    #         border_smooth_sample, feature_on_cerebrum, grey_white_ratio, fourier, PCA_transform, polyfit, smooth_sample,
    #         mean_feature, variance_feature, median_feature, feature_on_cuboid], images)

    # simple(linear_model.LogisticRegression(penalty='l1'), [partial(sobel, feature_function=border_smooth_sample)],
    #        images)
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'),
                    #  sobel, images,
                    #  [partial(PCA_transform, number_of_components=2), partial(PCA_transform, number_of_components=3),
                    #   partial(PCA_transform, number_of_components=4), partial(PCA_transform, number_of_components=5),
                    #   partial(PCA_transform, number_of_components=6), partial(PCA_transform, number_of_components=7)])
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'),
                    #  feature_subimages, images, [sobel], range(2, 10, 2), range(2, 10, 2), range(2, 10, 2))

    # optimize_feature(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #                  sobel, images,
    #                  [PCA_transform, polyfit, grey_white_ratio, fourier, mean_feature, variance_feature, smooth_sample])
    # simple(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #        [sobel], images)
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #                  fourier, images, range(1,7), [center])
    # simple(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #        [feature_on_cuboid], images)
    # simple(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #        [feature_border_images], images)

    # combinations(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #              [fourier, PCA_transform, polyfit], images)
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'), linear_model.LogisticRegression.predict_proba,
    #                  feature_border_images, images, range(3, 10, 2),
    #                  range(0, 16, 4), range(1, 16, 4),
    #                  [fourier])
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'), grey_white_ratio, images, range(100, 2000, 100))
    # simple(linear_model.LogisticRegression(penalty='l1', class_weight={1: 1, 0: 1}),
    #       linear_model.LogisticRegression.predict_proba, [fourier], images)
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'),
    #                  linear_model.LogisticRegression.predict_proba,
    #                  fourier, images, range(1, 7, 1))
    # optimize_feature(linear_model.LogisticRegression(penalty='l1'), polyfit, images, range(1, 30, 1))
    # simple(linear_model.LogisticRegression(penalty='l1'), [feature_border_images], images)
    # simple(linear_model.LogisticRegression(penalty='l1'), [PCA_transform, polyfit], images)
    # combinations(linear_model.LogisticRegression(penalty='l1'),
    #              [polyfit, border_smooth_sample, smooth_sample, spread, grey_white_ratio, mean_feature,
    #               variance_feature, local_maxima_3, local_maxima_without_smoothing, PCA_transform],
    #              images)


def simple(model_object, features_functions, images):
    """
    Do simple test with only one model and one list of features.
    :param model_object: Estimator model object
    :param features_functions: list of features to use
    :param images: array of 4D images for feature extraction
    """
    model_name = type(model_object).__name__
    features_names = "_".join([((f.func.__name__ + "_" + "_".join(
        [(str(key) + "_" + (f.keywords[key].__name__ if callable(f.keywords[key]) else str(f.keywords[key]))) for key in
         f.keywords.keys()])) if type(f) is partial else f.__name__)
                               for f in features_functions])
    print "Model:", model_name
    print "Features:", features_names

    # extract features
    features = compute_features(features_functions, images, speedup=True)

    # preprocess features
    # features = preprocessing_variance_threshold(features)

    ages = load_ages()

    # get cv error
    err = cross_validation(model_object, features, ages)

    print "Error:", err
    save_results(model_name, features_names, err)
    print "=" * 16 + "END" + "=" * 16
    return err


def combinations(model_object, features_functions, images):
    """
    Do multiple simple tests with combinations of the given 'features_functions'.
    :param model_object:
    :param features_functions: lists of features to use
    :param images: 4D images for feature extraction
    """
    perms = [perm for l in range(1, len(features_functions) + 1) for perm in
             itertools.combinations(features_functions, l)]
    for features_functions in perms:
        simple(model_object, features_functions, images)


def multiple(model_objects, list_of_features_functions, images, combination=False):
    """
    Do multiple simple tests with multiple models.
    :param model_objects: Estimator model
    :param list_of_features_functions: (n) number of statements of feature functions in a 'n x 1' dim matrix
    :param images: 4D images for feature extraction
    :param combination: if true, this method also tries combinations of the lists of features that are listed
    """
    for model in model_objects:
        for features_functions in list_of_features_functions:
            if combination:
                combinations(model, features_functions, images)
            else:
                simple(model, features_functions, images)


def optimize_feature(model_object, feature_function, images, *list_of_args):
    """
    Find the best arguments (the arguments that minimize the cv-error) the for the given 'feature_function'.
    :param model_object: Estimator model
    :param prediction_method: the method to use for predicting, the standard 'predict' only return 0 or 1
    :param feature_function: the feature to optimize
    :param images: images to use
    :param list_of_args: variable number of arguments for the feature_function
    """
    ages = load_ages()
    best_args = []
    best_err = 10
    if len(list_of_args) == 1:
        tries = len(list_of_args[0])
    else:
        tries = reduce(lambda x, y: x * y, map(lambda x: len(x), list_of_args))
    for counter, args in enumerate(itertools.product(*list_of_args)):
        features = feature_function(images, *args)
        err = cross_validation(model_object, features, ages)
        if err < best_err:
            best_err = err
            best_args = args
        print "Arguments:", args, "Err:", err
        print "Done:", counter + 1, "/", tries
    print "BEST", "Arguments:", best_args, "Err", best_err


def greedy(model_object, list_of_features_functions, images):
    """
    Find the best combination of features for the given model.
    :param model_object: Estimator model
    :param list_of_features_functions: the possible features
    :param images: images to use
    """
    current_error = 10  # maximal value of the log loss function
    improvement = 1
    possible_features = list_of_features_functions
    best_features = list()
    while improvement > 0:
        min_error = 10
        current_best_feature = None
        for feature_function in possible_features:
            test_features = best_features + [feature_function]
            error = simple(model_object, test_features, images)
            if error < min_error:
                min_error = error
                current_best_feature = feature_function
        improvement = current_error - min_error
        if improvement > 0:
            best_features.append(current_best_feature)
            current_error = min_error
            possible_features.remove(current_best_feature)
    print "the best feature combination: ", best_features


def cross_validation(model_object, features, ages):
    """
    Retrieve the cross-validation error for a given test.
    :param model_object: Estimator model
    :param prediction_method: the method to use for predicting, the standard 'predict' only return 0 or 1
    :param features: the features to use
    :param ages: the correct labels
    :return:
    """
    k = min(int(math.sqrt(len(ages))), 10)  # engineers solution
    kf = KFold(n_splits=k)
    err = 0
    for train_index, test_index in kf.split(features):
        model_object.fit(features[train_index], ages[train_index])
        prediction = model_object.predict_proba(features[test_index])[:, 1]
        err += log_loss(ages[test_index], prediction)
    return err / k

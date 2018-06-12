# FINAL pipeline of Machine Learning process.
# Execute the 'execute()' function in 'wrapper.py' to have a '.csv' file for final submission.
# For experiments, use script 'experiments.py'

from sklearn import linear_model

from features import *
from loaders import load_ages, save_ages


def execute(images):
    """
    Execute the Machine Learning Pipeline on a given images and save the results in a '.csv' file.
    :param images: array of images for feature extraction
    """

    # extract features
    features_functions = [fourier]
    features = compute_features(features_functions, images)

    # prepare data for fit
    train_features = features[:278]
    test_features = features[278:]
    train_ages = load_ages()

    # fit data
    model = linear_model.LogisticRegression(penalty='l1')
    model.fit(train_features, train_ages)

    # predict
    test_ages = model.predict_proba(test_features)[:,1]

    # output
    model_name = type(model).__name__
    features_names = " ".join([f.__name__ for f in features_functions])
    save_ages(test_ages, model_name, features_names)

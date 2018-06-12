from textwrap import wrap
import numpy as np
import os
from matplotlib.colors import ListedColormap
import matplotlib.pyplot as plt


from sklearn.linear_model import LogisticRegression
from sklearn.neural_network import MLPClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RBF
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier
from sklearn.naive_bayes import GaussianNB



def test_classifiers(classifiers, features, images, clfs):
    """
    Test a classifier against different features or a feature against different classifiers and plot the log loss errors.
    :param classifiers: One or more classifiers in an array
    :param feature: One or more features in an array
    :param images: NIFTI images
    :param clfs: bool, True if testing different classifiers, False if different features
    return: Saves a bar chart graphics in /images
    """
    if clfs:
        # Generate names of classifiers for plotting. Then wrap them to fit
        names = tuple(type(clf).__name__ for clf in classifiers)
        names = [ '-\n'.join(wrap(name, 7)) for name in names ]

        # machine learning process
        errors = []
        for clf in classifiers:
            error = simple(clf, features, images)
            errors.append(error)

        # Plot
        fgr, ax = plt.subplots()
        ax.set_ylabel('LogLoss Errors', fontsize=20)
        ax.set_xlabel('Classifiers', fontsize=20)
        ax.set_title('Error rates of Classifiers with feature '+features[0].__name__.upper())

        x = np.arange(len(errors)) # x locations of the classifiers
        ax.bar(x + 0.8, errors, align="center", tick_label=names)

        for i, error in enumerate(errors):
            ax.text(i + .5, error + .1, str(round(error, 5)), color='blue', fontweight='bold')

        plt.tight_layout()
        fgr.set_size_inches(11,7)
        plt.axis((0,len(classifiers)+1, 0, 5))
        # Save the figure to file
        for i in range(20):
            PATH = get_path("images/classifiers_test_" + features[0].__name__ + str(i+1) + ".png")
            if not os.path.exists(PATH):
                fgr.savefig(PATH)
                break
            else:
                continue
        plt.show()

    if not clfs:
        # Generate names of features for plotting. Then wrap them to fit
        names = tuple(feature.__name__ for feature in features)
        names = [ '-\n'.join(wrap(name, 7)) for name in names ]

        # machine learning process
        errors = []
        clf = classifiers[0] # Since only one classifier is passed
        for feature in features: # Try every feature once alone
            error = simple(clf, [feature], images)
            errors.append(error)

        # Plot
        fgr, ax = plt.subplots()
        ax.set_ylabel('LogLoss Errors', fontsize=20)
        ax.set_xlabel('Features', fontsize=20)
        ax.set_title('Error rates of different features with '+type(clf).__name__.upper())

        x = np.arange(len(errors)) # x locations of the classifiers
        ax.bar(x + 0.8, errors, align="center", tick_label=names)

        for i, error in enumerate(errors):
            ax.text(i + .5, error + .05, str(round(error, 5)), color='blue', fontweight='bold')

        plt.tight_layout()
        plt.axis((0,len(features)+1, 0, 5))
        fgr.set_size_inches(11,7)
        # Save the figure to file
        for i in range(20):
            PATH = get_path("images/classifiers_test_" + type(clf).__name__ + str(i+1) + ".png")
            if not os.path.exists(PATH):
                fgr.savefig(PATH)
                break
            else:
                continue
        plt.show()
